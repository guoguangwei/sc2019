package com.xzsd.pc.patriarch.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.page.Page;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.patriarch.dao.PatriarchDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: wwb
 * @Date: 2019/5/14 13:32
 * @Description: 后台家长信息管理
 */
@Service
public class PatriarchService {
    private static final Logger logger = LoggerFactory.getLogger(PatriarchService.class);
    @Autowired
    private PatriarchDao patriarchDao;

    /**
     * @Author: wwb
     * @Description: 查询家长信息
     * @param map
     * @return: com.neusoft.core.restful.AppResponse
     * @Date: 2019/5/17 21:03
     */
    public AppResponse listPatriarchInfo(@RequestParam Map<String,Object> map) {
        try {
            int currentPage = map.get("pageNum") == null ? 1 : Integer.parseInt(map.get("pageNum").toString());
            int limit = map.get("pageSize") == null ? 10 : Integer.parseInt(map.get("pageSize").toString());
            PageHelper.startPage(currentPage, limit);
            List infoList = null;
            Page page = new Page();
            try {
                infoList = patriarchDao.listPatriarchInfo(map);
            } catch (NumberFormatException e) {
                logger.error(e.getMessage());
                return AppResponse.serverError("查询家长信息失败，请联系系统管理员");
            }
            if (CollectionUtils.isEmpty(infoList)) {
                return AppResponse.notFound("未查询到家长信息的结果");
            }
            page.setTotalRecord((int) new PageInfo<>(infoList).getTotal());
            page.setRecords(infoList);
            page.setCurrentPage(currentPage);
            page.setPageSize(limit);

            return AppResponse.success("查询家长信息成功", page);
        } catch (Exception e) {
            logger.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return AppResponse.serverError("查询家长信息失败，请联系系统管理员");
        }
    }

    /**
     * @Author: wwb
     * @Description: 删除家长信息
     * @param map
     * @return: com.neusoft.core.restful.AppResponse
     * @Date: 2019/5/17 21:03
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse removePatriarchInfo(@RequestBody Map<String,Object> map) {
        try {
            ArrayList userCoodeList = (ArrayList) map.get("user_no_list");
            //禁止删除用户列表
            List<String> prohibitList = patriarchDao.listUnfinishedOrderUser(userCoodeList);
            if(prohibitList.size() == userCoodeList.size()){
                return AppResponse.bizError("所选用户都有未完成订单，无法删除！");
            }
            if(prohibitList.size()== 0 || prohibitList == null){
                int delPat = patriarchDao.removePatriarchInfo(map);
                int delRole = patriarchDao.removePatriarchRole(map);
                List<String> haveRoleUser = patriarchDao.listHaveRoleUser(map);
                if(haveRoleUser.size() != userCoodeList.size()){
                    //得到没有角色的用户列表
                    userCoodeList.removeAll(haveRoleUser);
                    map.put("user_no_list", userCoodeList);
                    //删除没角色的用户信息
                    patriarchDao.removeUserInfo(map);
                }
                if(delPat >0 && delRole>0){
                    return AppResponse.success("删除家长信息成功");
                }else{
                    return AppResponse.serverError("删除家长信息失败，请联系系统管理员");
                }
            }
            userCoodeList.removeAll(prohibitList);
            map.put("user_no_list", userCoodeList);
            int delPat = patriarchDao.removePatriarchInfo(map);
            int delRole = patriarchDao.removePatriarchRole(map);
            List<String> haveRoleUser = patriarchDao.listHaveRoleUser(map);
            if(haveRoleUser.size() != userCoodeList.size()){
                //得到没有角色的用户列表
                userCoodeList.removeAll(haveRoleUser);
                map.put("user_no_list", userCoodeList);
                //删除没角色的用户信息
                patriarchDao.removeUserInfo(map);
            }
            if(delPat >0 && delRole>0){
                return AppResponse.success("成功删除家长信息" + userCoodeList.size() + "条，"+ prohibitList.size() + "条因存在未完成订单无法删除");
            }else{
                return AppResponse.serverError("删除家长信息失败了，请联系系统管理员");
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return AppResponse.serverError("删除家长信息失败，请联系系统管理员");
        }
    }
}
