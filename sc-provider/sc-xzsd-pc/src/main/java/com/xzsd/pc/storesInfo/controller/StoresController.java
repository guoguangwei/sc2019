package com.xzsd.pc.storesInfo.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.storesInfo.entity.County;
import com.xzsd.pc.storesInfo.entity.Province;
import com.xzsd.pc.storesInfo.entity.Store;
import com.xzsd.pc.storesInfo.entity.UserRole;
import com.xzsd.pc.storesInfo.service.StoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: guo
 * @deprecated: 门店信息管理
 * @Date: 2019/10/12
 */

@RestController
@RequestMapping("/storesInfo")
public class StoresController {

    @Autowired
    private StoresService storesService;

    /**
     * @author: guo
     * @deprecated: 查询门店信息
     * @Date: 2019/10/12
     */
    @RequestMapping(value = "/findAllStoresInfo", method = RequestMethod.POST)
    public AppResponse findAllStoreInfo(@RequestParam Map<String, Object> retMap) {

        return storesService.findAllStoreInfo(retMap);
    }

    /**
     * @author: guo
     * @deprecated: 回显省份和城市的列表信息
     * @Date: 2019/10/12
     */
    @RequestMapping(value = "/provinceCounty", method = RequestMethod.POST)
    public AppResponse findProvinceCounty(Province province, County county) {

        return storesService.findProvinceCounty(province, county);
    }

    /**
     * @author: guo
     * @deprecated: 回显province信息
     * @Date: 2019/10/12
     */
    @RequestMapping(value = "/findProvince", method = RequestMethod.POST)
    public AppResponse findProvince(Province province) {

        return storesService.findProvince(province);
    }

    /**
     * @author: guo
     * @deprecated: 回显province信息
     * @Date: 2019/10/12
     */
    @RequestMapping(value = "/findCounty", method = RequestMethod.POST)
    public AppResponse findCounty(County county) {

        return storesService.findCounty(county);
    }

    /**
     * @author: guo
     * @deprecated: 添加门店信息
     * @Date: 2019/10/12
     */
    @RequestMapping(value = "/addStoreInfo", method = RequestMethod.POST)
    public AppResponse additionStore(Store store, UserRole userRole) {

        return storesService.additionStore(store, userRole);
    }

    /**
     * @author: guo
     * @deprecated: 获取修改门店信息
     * @Date: 2019/10/13
     */
    @RequestMapping(value = "/findStoreById")
    public AppResponse findStoreById(@RequestParam(value = "id") String id) {

        return storesService.findStoreById(id);
    }

    /**
     * @author: guo
     * @deprecated: 修改门店信息
     * @Date: 2019/10/13
     */
    @RequestMapping(value = "/updateStore", method = RequestMethod.POST)
    public AppResponse updateStore(Store store) {

        return storesService.updateStore(store);
    }

    /**
     * @author: guo
     * @deprecated: 批量删除门店信息
     * @Date: 2019/10/13
     */
    @RequestMapping(value = "/delStores", method = RequestMethod.POST)
    public AppResponse deleteStores(@RequestParam(value = "user_code_list")ArrayList list) {

        String userCode = SecurityUtils.getCurrentUserId();
        Map map = new HashMap(2);

        map.put("user_code_list", list);
        map.put("last_modified_by", userCode == null ? "null" : userCode);
        AppResponse appResponse = storesService.deleteStoreInfo(map);
        return appResponse;
    }


}
