package com.xzsd.pc.util.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @Author: wwb
 * @Date: 2019/5/16 20:53
 * @Description:
 */
@Mapper
public interface UserInfoNeedDao {

   /**
    * 统计当前用户的管理员角色数目
    * @Author: wwb
    * @param map
    * @return: int
    * @Date: 2019/5/17 21:09
    */
   int countAdminRole(Map<String, Object> map);

   /**
    * 部门：青岛软件研发中心
    * 功能：获取用户身份
    * 描述：略
    * @author ：zwl
    * 作成时间：2019/5/14
    * @param userCode
    * @return com.xzsd.pc.order.entity.OrderVO
    */
   String getUserType(String userCode);
}
