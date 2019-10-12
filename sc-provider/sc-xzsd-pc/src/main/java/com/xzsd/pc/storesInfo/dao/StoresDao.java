package com.xzsd.pc.storesInfo.dao;

import com.xzsd.pc.storesInfo.entity.*;
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

    /**
     * @author: guo
     * @deprecated: 查询门店账号是否存在
     * @Date: 2019/10/12
     */
    int haveAccount(User user);

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
     * ===========================================
     * @author: guo
     * @deprecated: 回显province
     * @Date: 2019/10/12
     */
    List<Province> findProvinces(Province province);

    /**
     * @author: guo
     * @deprecated: 回显county
     * @Date: 2019/10/12
     */
    List<Province> findCountys(County county);
}
