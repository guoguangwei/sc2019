package com.xzsd.pc.storesInfo.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.storesInfo.entity.*;

import java.util.Map;

public interface StoresService {

    /**
     * @author: guo
     * @deprecated: 查询门店信息
     * @Date: 2019/10/12
     */
    AppResponse findAllStoreInfo(Map<String, Object> retMap);

    /**
     * @author: guo
     * @deprecated: 添加门店信息
     * @Date: 2019/10/12
     */
    AppResponse additionStore(Store store, User user, UserRole userRole);

    /**
     * @author: guo
     * @deprecated: 回显province信息
     * @Date: 2019/10/12
     */
    Map<String, Object> findProvince(Province province);

    /**
     * @author: guo
     * @deprecated: 回显county信息
     * @Date: 2019/10/12
     */
    Map<String, Object> findCounty(County county);

    AppResponse findProvinceCounty(Province province, County county);
}
