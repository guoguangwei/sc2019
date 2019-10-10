package com.xzsd.pc.patriarch.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: wwb
 * @Date: 2019/5/14 13:32
 * @Description:
 */
@Mapper
@Component
public interface PatriarchDao {

    /**
     * 查询家长信息
     * @Author: wwb
     * @param map
     * @return: java.util.List<java.util.Map < java.lang.String , java.lang.Object>>
     * @Date: 2019/5/17
     */
    List<Map<String, Object>> listPatriarchInfo(Map<String, Object> map);

    /**
     * 删除家长信息
     * @Author: wwb
     * @param map
     * @return: int
     * @Date: 2019/5/17
     */
    int removePatriarchInfo(Map<String, Object> map);

    /**
     * 删除家长用户对应的角色
     * @Author: wwb
     * @param map
     * @return: int
     * @Date: 2019/5/17
     */
    int removePatriarchRole(Map<String, Object> map);

    /**
     * 查询是否有家长存在未完成的订单,查询出结果则证明有
     * @Author: wwb
     * @Description: 查询是否有家长存在未完成的订单
     * @param userCodeList
     * @return: List<String>
     * @Date: 2019/5/17 18:26
     */
    List<String> listUnfinishedOrderUser(ArrayList userCodeList);

    /**
     * 删除角色信息后，查询出还有角色信息的用户列表
     * @Author: wwb
     * @Description: 删除角色信息后，查询出还有角色信息的用户列表
     * @param map
     * @return: List<String>
     * @Date: 2019/5/17 18:26
     */
    List<String> listHaveRoleUser(Map<String, Object> map);

    /**
     * 删除用户信息
     * @Author: wwb
     * @Description: 删除用户信息
     * @param map
     * @return: int
     * @Date: 2019/5/17 18:26
     */
    int removeUserInfo(Map<String, Object> map);


}
