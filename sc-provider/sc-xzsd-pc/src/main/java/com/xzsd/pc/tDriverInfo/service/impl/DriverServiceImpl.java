package com.xzsd.pc.tDriverInfo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.page.Page;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.neusoft.util.UUIDUtils;
import com.neusoft.webauth.utils.PasswordUtils;
import com.xzsd.pc.tDriverInfo.dao.DriverDao;
import com.xzsd.pc.tDriverInfo.entity.Driver;
import com.xzsd.pc.tDriverInfo.entity.User;
import com.xzsd.pc.tDriverInfo.entity.UserRole;
import com.xzsd.pc.tDriverInfo.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class DriverServiceImpl implements DriverService {

    private static final Logger logger = LoggerFactory.getLogger(DriverServiceImpl.class);

    @Autowired
    private DriverDao driverDao;

    /**
     * @Author: guo
     * @deprecated: 查询司机信息
     * @Data: 2019/10/10
     */
    //手动开启事务回滚
    @Transactional(rollbackFor = Exception.class)
    public AppResponse findAllDriver(Map<String, Object> retMap) {

        try {
            //获取当前页为第几页（即pageNum的值），如果为null，则默认为第一页，否则当前获取的值为当前页
            int currentPage = retMap.get("pageNum") == null ? 1 : Integer.parseInt(retMap.get("pageNum").toString());
            //获取一次页面展示多少条数据（即pageSize的值），如果为null，则默认为10条，否则当前获取的值为页面总条数
            int limit = retMap.get("pageSize") == null ? 10 : Integer.parseInt(retMap.get("pageSize").toString());
            //调用PageHelper类中的startPage方法
            PageHelper.startPage(currentPage, limit);
            List infoList = null;
            Page page = new Page();

            try {
                infoList = driverDao.findAllDriver(retMap);
            } catch (NumberFormatException e) {
                logger.error(e.getMessage());
                return AppResponse.serverError("查询司机信息失败，请联系系统管理员");
            }

            //判断infoList是否为空
            if (CollectionUtils.isEmpty(infoList)) {
                return AppResponse.notFound("未查询到司机信息的结果");
            }

            page.setTotalRecord((int) new PageInfo<>(infoList).getTotal());
            page.setRecords(infoList);
            return AppResponse.success("查询司机信息成功", page);

        }catch (Exception e) {
            logger.info(e.getMessage());
            //手动开启事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return AppResponse.serverError("查询司机信息失败，请联系系统管理员");
        }
    }

    /**
     * @Author: guo
     * @deprecated: 添加司机信息
     * @Data: 2019/10/10
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse additionDriver(Driver driver, User user, UserRole userRole) {

        try {

            //验证账号是否已存在，
            int account = driverDao.haveAccount(user);
            if ( 0 != account ) {
                return AppResponse.bizError("该账号名已存在，请重新输入！");
            }
            //手动设置id的值，随机生成
            driver.setId(UUIDUtils.getUUID());
            //手动设置user_code的值，调用StringUtil类中的 getCommonCode（）方法
            driver.setUserCode(StringUtil.getCommonCode(2));
            //查询同一城市的司机总数
            int amount = driverDao.driverNumber(driver);
            //手动设置driver_no的值
            driver.setDriverNo("D" + driver.getProvinceNo()
                    + driver.getCountyNo() + (amount + 1));
            //添加司机信息
            int addDriverNumber = driverDao.addDriver(driver);
            System.out.println(addDriverNumber);

            //新增司机用户
            //随机生成id
            user.setId(UUIDUtils.getUUID());
            //将密码进行加密
            String pwd = PasswordUtils.generatePassword(driver.getUserPwd());
            System.out.println(pwd);
            driver.setUserPwd(pwd);
            int addDriverAcc = driverDao.addDriverAccount(driver);
            System.out.println(addDriverAcc);

            //新增司机角色
            userRole.setId(UUIDUtils.getUUID());
            userRole.setUserCode(driver.getUserCode());
            //查询账户角色是否已有默认角色
            if (driverDao.userRole(userRole) > 0) {
                userRole.setIsDefault(0);
            }else {
                userRole.setIsDefault(1);
            }

            int addUserRo = driverDao.addUserRole(userRole);
            System.out.println(addUserRo);

            if(addDriverNumber > 0 && addDriverAcc > 0 && addUserRo > 0) {
                return AppResponse.success("添加司机信息成功！");
            }else {
                return AppResponse.serverError("添加失败司机信息，请联系管理员！");
            }

        }catch (Exception e) {
            logger.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return AppResponse.bizError("添加司机失败！");
        }
    }
}
