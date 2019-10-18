package com.xzsd.pc.storesInfo.dao;

import com.xzsd.pc.storesInfo.entity.County;
import com.xzsd.pc.storesInfo.entity.Province;
import com.xzsd.pc.storesInfo.entity.Store;
import com.xzsd.pc.storesInfo.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface StoresDao {

    /**
     * @author: guo
     * @deprecated: 查询门店信息
     * @Date: 2019/10/12
     */
    List findAllStoreInfo(Map<String, Object> retMap);

    /*=================================添加门店信息部分===================================*/
    /**
     * @author: guo
     * @deprecated: 查询门店账号是否存在
     * @Date: 2019/10/12
     */
    int haveAccount(Store store);

    /**
     * @author: guo
     * @deprecated: 查询门店账号是否存在
     * @Date: 2019/10/12
     */
    int storeNumber(Store store);

    /**
     * @author: guo
     * @deprecated: 添加门店信息
     * @Date: 2019/10/12
     */
    int addStore(Store store);

    /**
     * @author: guo
     * @deprecated: 新增门店用户
     * @Date: 2019/10/12
     */
    int addStoreAccount(Store store);

    /**
     * @author: guo
     * @deprecated: 查询账户角色是否已有默认角色
     * @Date: 2019/10/12
     */
    int userRole(UserRole userRole);

    /**
     * @author: guo
     * @deprecated: 新增门店角色
     * @Date: 2019/10/12
     */
    int addUserRole(UserRole userRole);

    /**
     * @author: guo
     * @deprecated: 回显province
     * @Date: 2019/10/12
     */
    List<Province> findProvince(Province province);

    /**
     * @author: guo
     * @deprecated: 回显county
     * @Date: 2019/10/12
     */
    List<County> findCounty(County county);

    /**
     * ============
     * @author: guo
     * @deprecated: 回显province,省份城市在同一个方法中查询
     * @Date: 2019/10/12
     */
    List<Province> findProvinces(Province province);

    /**
     * @author: guo
     * @deprecated: 回显county,省份城市在同一个方法中查询
     * @Date: 2019/10/12
     */
    List<Province> findCountys(County county);

    /*=================================编辑门店信息部分===================================*/
    /**
     * @author: guo
     * @deprecated: 获取修改门店信息
     * @Date: 2019/10/13
     */
    Store findStoreById(String id);

    /**
     * @author: guo
     * @deprecated: 修改门店信息
     * @Date: 2019/10/13
     */
    int updateStoreInfo(Store store);

    /**
     * @author: guo
     * @deprecated: 修改账号信息
     * @Date: 2019/10/13
     */
    int updateStoreAccount(Store store);

    /*=================================删除门店信息部分===================================*/

    /**
     * @author: guo
     * @deprecated: 批量删除门店信息
     * @Date: 2019/10/13
     */
    int deleteStoreInfo(Map<String, Object> map);

    /**
     * @author: guo
     * @deprecated: 批量删除用户信息
     * @Date: 2019/10/13
     */
    int deleteUserInfo(Map<String, Object> map);

    /**
     * @author: guo
     * @deprecated: 批量删除用户信息
     * @Date: 2019/10/13
     */
    int deleteRoleUserInfo(Map<String, Object> map);

    /**
     * @author: guo
     * @deprecated: 查询是否还有角色
     * @Date: 2019/10/13
     */
    List<String> listHaveRoleUser(Map<String, Object> map);
}
