package com.xzsd.pc.storesInfo.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.storesInfo.entity.*;
import com.xzsd.pc.storesInfo.service.StoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: guo
 * @deprecated: 门店信息管理
 * @Date: 2019/10/12
 */

@RestController
@RequestMapping("storesInfo")
public class StoresController {

    @Autowired
    private StoresService storesService;

    /**
     * @author: guo
     * @deprecated: 查询门店信息
     * @Date: 2019/10/12
     */
    @RequestMapping(value = "findAllStoresInfo", method = RequestMethod.POST)
    public AppResponse findAllStoreInfo(@RequestParam Map<String, Object> retMap) {

        return storesService.findAllStoreInfo(retMap);
    }

    /**
     * @author: guo
     * @deprecated: 回显省份和城市的列表信息
     * @Date: 2019/10/12
     */
    @RequestMapping(value = "provinceCounty", method = RequestMethod.POST)
    public AppResponse findProvinceCounty(Province province, County county) {

        return storesService.findProvinceCounty(province, county);
    }

    /**
     * @author: guo
     * @deprecated: 回显province信息
     * @Date: 2019/10/12
     */
    @RequestMapping(value = "findProvince", method = RequestMethod.POST)
    public Map<String, Object> findProvince(Province province) {

        return storesService.findProvince(province);
    }

    /**
     * @author: guo
     * @deprecated: 回显province信息
     * @Date: 2019/10/12
     */
    @RequestMapping(value = "findCounty", method = RequestMethod.POST)
    public Map<String, Object> findCounty(County county) {

        return storesService.findCounty(county);
    }

    /**
     * @author: guo
     * @deprecated: 添加门店信息
     * @Date: 2019/10/12
     */
    @RequestMapping(value = "addStoreInfo", method = RequestMethod.POST)
    public AppResponse additionStore(Store store, User user, UserRole userRole) {

        return storesService.additionStore(store, user, userRole);
    }
}
