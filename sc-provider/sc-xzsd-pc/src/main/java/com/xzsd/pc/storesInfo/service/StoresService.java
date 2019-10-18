package com.xzsd.pc.storesInfo.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.storesInfo.entity.County;
import com.xzsd.pc.storesInfo.entity.Province;
import com.xzsd.pc.storesInfo.entity.Store;
import com.xzsd.pc.storesInfo.entity.UserRole;

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
    AppResponse additionStore(Store store, UserRole userRole);

    /**
     * @author: guo
     * @deprecated: 回显province信息
     * @Date: 2019/10/12
     */
    AppResponse findProvince(Province province);

    /**
     * @author: guo
     * @deprecated: 回显county信息
     * @Date: 2019/10/12
     */
    AppResponse findCounty(County county);

    /**
     * @author: guo
     * @deprecated: 回显省份和城市的列表信息
     * @Date: 2019/10/12
     */
    AppResponse findProvinceCounty(Province province, County county);

    /**
     * @author: guo
     * @deprecated: 修改门店信息
     * @Date: 2019/10/13
     */
    AppResponse updateStore(Store store);

    /**
     * @author: guo
     * @deprecated: 获取修改门店信息
     * @Date: 2019/10/13
     */
    AppResponse findStoreById(String id);

    /**
     * @author: guo
     * @deprecated: 批量删除门店信息
     * @Date: 2019/10/14
     * @return
     */
    AppResponse deleteStoreInfo(Map<String, Object> map);
}
