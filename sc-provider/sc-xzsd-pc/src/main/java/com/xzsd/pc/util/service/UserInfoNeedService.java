package com.xzsd.pc.util.service;

import com.xzsd.pc.util.dao.UserInfoNeedDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wwb
 * @Date: 2019/5/16 20:45
 * @Description: 获取必需的用户信息，如管理员标志
 */
@Service
public class UserInfoNeedService {

    @Autowired
    private UserInfoNeedDao userInfoNeedDao;

    /**
     * @Author: wwb
     * @Description: 通过统计当前用户管理员角色的数目非零判定用户为管理员角色
     * @param userCode
     * @return: java.lang.String
     * @Date: 2019/5/17 21:08
     */
    public String getUserRole(String userCode){
        Map<String, Object> map = new HashMap(1);
        map.put("user_code", userCode);
        int adminNum = userInfoNeedDao.countAdminRole(map);
        if(adminNum > 0){
            return "1";
        }
        return "0";
    }

    /**
     * @Author: zwl
     * @Description: 通过统计当前用户管理员角色的数目非零判定用户为管理员角色
     * @param userCode
     * @return: java.lang.String
     * @Date: 2019/6/13 21:08
     */
    public String getUserType(String userCode){
       return userInfoNeedDao.getUserType(userCode);
    }
}
