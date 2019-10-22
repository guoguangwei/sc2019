package com.xzsd.pc.storesInfo.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.page.Page;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.neusoft.util.UUIDUtils;
import com.neusoft.webauth.utils.PasswordUtils;
import com.xzsd.pc.storesInfo.dao.StoresDao;
import com.xzsd.pc.storesInfo.entity.County;
import com.xzsd.pc.storesInfo.entity.Province;
import com.xzsd.pc.storesInfo.entity.Store;
import com.xzsd.pc.storesInfo.entity.UserRole;
import com.xzsd.pc.storesInfo.service.StoresService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.neusoft.core.page.PageUtils.getPageInfo;

@Service
public class StoresServiceImpl implements StoresService {

    private static final Logger logger = LoggerFactory.getLogger(StoresServiceImpl.class);
//    private final Logger logger = LoggerFactory.getLogger(this.getClass);

    @Autowired
    private StoresDao storesDao;

    /**
     * @author: guo
     * @deprecated: 查询门店信息
     * @Date: 2019/10/12
     */
    @Override
    public AppResponse findAllStoreInfo(Map<String, Object> retMap) {

        try {
            //获取当前页为第几页（即pageNum的值），如果为null，则默认为第一页，否则当前获取的值为当前页
            int currentPage = retMap.get("pageNum") == null ? 1 : Integer.parseInt(retMap.get("pageNum").toString());
            //获取一次页面展示多少条数据（即pageSize的值），如果为null，则默认为10条，否则当前获取的值为页面总条数
            int limit = retMap.get("pageSize") == null ? 10 : Integer.parseInt(retMap.get("pageSize").toString());
            //调用PageHelper类中的startPage方法
            PageHelper.startPage(currentPage, limit);
            List storesInfoList;
            Page page = new Page();

            try {
                storesInfoList = storesDao.findAllStoreInfo(retMap);
            } catch (NumberFormatException e) {
                logger.error(e.getMessage());
                return AppResponse.serverError("查询门店信息失败，请联系系统管理员");
            }

            //判断infoList是否为空
            if (CollectionUtils.isEmpty(storesInfoList)) {
                return AppResponse.notFound("未查询到门店信息的结果");
            }

            page.setTotalRecord((int) new PageInfo<>(storesInfoList).getTotal());
            page.setRecords(storesInfoList);
            page.setCurrentPage(currentPage);
            page.setPageSize(limit);

            return AppResponse.success("查询门店信息成功", page);

        } catch (Exception e) {
            logger.info(e.getMessage());
            return AppResponse.serverError("查询门店信息失败，请联系系统管理员");
        }
    }

    /**
     * @author: guo
     * @deprecated: 回显province信息
     * @Date: 2019/10/12
     */
    @Override
    public AppResponse findProvince(Province province) {

        List<Province> proList;
        try {
            proList = storesDao.findProvince(province);
            System.out.println(proList);

        } catch (Exception e) {
            logger.info(e.getMessage());
            return AppResponse.success("省份信息获取失败!");
        }

        return AppResponse.success("省份信息获取成功!", getPageInfo(proList));
    }

    /**
     * @author: guo
     * @deprecated: 回显county信息
     * @Date: 2019/10/12
     */
    @Override
    public AppResponse findCounty(County county) {

        List<County> couList;
        try {
            couList = storesDao.findCounty(county);
            System.out.println(couList);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return AppResponse.success("城市、区信息获取失败!");
        }

        return AppResponse.success("城市、区信息获取成功!", getPageInfo(couList));
    }

    /**
     * @author: guo
     * @deprecated: 回显省份和城市的列表信息
     * @Date: 2019/10/12
     */
    @Override
    public AppResponse findProvinceCounty(Province province, County county) {

        Map<String, Object> revMap = new HashMap<>();
        List<Province> pList;
        List<Province> cList;

        try {
            pList = storesDao.findProvinces(province);
            System.out.println(pList);
            cList = storesDao.findCountys(county);
            System.out.println(cList);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return AppResponse.serverError("获取省份和城市失败！");
        }
        revMap.put("proList", pList);
        revMap.put("couList", cList);
        return AppResponse.success("获取省份和城市成功！", getPageInfo(revMap));
    }

    /**
     * @author: guo
     * @deprecated: 获取修改门店信息
     * @Date: 2019/10/13
     */
    @Override
    public AppResponse findStoreById(String id) {

        Store store;
        try {
            store = storesDao.findStoreById(id);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return AppResponse.serverError("获取修改门店信息失败！");
        }
        return AppResponse.success("获取修改门店信息成功！", getPageInfo(store));
    }

    /**
     * @author: guo
     * @deprecated: 修改门店信息
     * @Date: 2019/10/13
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateStore(Store store) {

        try {
            //验证账号是否已存在，
            int account = storesDao.haveAccount(store);
            if (0 != account) {
                return AppResponse.bizError("该账号名或电话号码已存在，请重新输入！");
            }

            //更新门店信息
            int updateStore = storesDao.updateStoreInfo(store);
            System.out.println(updateStore);

            //更新账号信息
            //将密码进行加密
            String pwd = PasswordUtils.generatePassword(store.getUserPwd());
            System.out.println(pwd);
            store.setUserPwd(pwd);
            int updateStoreAcc = storesDao.updateStoreAccount(store);
            System.out.println(updateStoreAcc);

            //判断两个添加方法是否全部成功执行
            if (updateStore > 0 && updateStoreAcc > 0) {
                return AppResponse.success("更新门店信息成功！");
            } else {
                return AppResponse.serverError("更新门店信息失败，请联系管理员！");
            }

        } catch (Exception e) {
            logger.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return AppResponse.serverError("更新门店信息失败！");
        }
//        return AppResponse.success("更新门店信息成功！");
    }

    /**
     * @author: guo
     * @deprecated: 添加门店信息
     * @Date: 2019/10/12
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AppResponse additionStore(Store store, UserRole userRole) {

        try {
            //验证账号是否已存在，
            int account = storesDao.haveAccount(store);
            if (0 != account) {
                return AppResponse.bizError("该账号名或电话号码已存在，请重新输入！");
            }
            //手动设置id的值，随机生成
            store.setId(UUIDUtils.getUUID());
            //手动设置user_code的值，调用StringUtil类中的 getCommonCode（）方法
            store.setUserCode(StringUtil.getCommonCode(2));
            //查询同一城市的商店现有数量
            int amount = storesDao.storeNumber(store);
            //手动设置driver_no的值
            store.setStoreNo("S" + store.getProvinceNo()
                    + store.getCountyNo() + (amount + 1));
            //添加门店信息
            int addStoreNumber = storesDao.addStore(store);
            System.out.println(addStoreNumber);

            //新增门店用户
            //随机生成id
            store.setId(UUIDUtils.getUUID());
            //将密码进行加密
            String pwd = PasswordUtils.generatePassword(store.getUserPwd());
            System.out.println(pwd);
            store.setUserPwd(pwd);

            int addStoreAcc = storesDao.addStoreAccount(store);
            System.out.println(addStoreAcc);

            //新增门店角色
            userRole.setId(UUIDUtils.getUUID());
            userRole.setUserCode(store.getUserCode());
            //查询账户角色是否已有默认角色
            if (storesDao.userRole(userRole) > 0) {
                userRole.setIsDefault(0);
            } else {
                userRole.setIsDefault(1);
            }

            int addUserRo = storesDao.addUserRole(userRole);
            System.out.println(addUserRo);

            //判断三个添加方法是否全部成功执行
            if (addStoreNumber > 0 && addStoreAcc > 0 && addUserRo > 0) {
                return AppResponse.success("添加门店信息成功！");
            } else {
                return AppResponse.serverError("添加门店信息失败，请联系管理员！");
            }

        } catch (Exception e) {
            logger.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return AppResponse.bizError("添加门店失败，请联系管理员！");
        }
    }

    /**
     * @author: guo
     * @deprecated: 批量删除门店信息
     * @Date: 2019/10/13
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteStoreInfo(Map<String, Object> map) {

        try {
            ArrayList userCodeList = (ArrayList) map.get("user_code_list");
            int delStores = storesDao.deleteStoreInfo(map);
            int delUsers = storesDao.deleteUserInfo(map);

            List<String> haveRoleUser = storesDao.listHaveRoleUser(map);
            if(haveRoleUser.size() != userCodeList.size()){
                //得到没有角色的用户列表
                userCodeList.removeAll(haveRoleUser);
                map.put("user_code_list", userCodeList);
                //删除没角色的用户信息
                storesDao.deleteRoleUserInfo(map);
            }

            if (delStores > 0 && delUsers > 0) {
                return AppResponse.success("删除司机及司机角色信息成功");
            } else {
                return AppResponse.serverError("删除司机信息失败，请联系系统管理员");
            }

        }catch (Exception e) {
            logger.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return AppResponse.serverError("删除门店信息失败，请联系系统管理员");
        }
    }
}
