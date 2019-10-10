package com.xzsd.pc.tDriverInfo.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.tDriverInfo.entity.Driver;
import com.xzsd.pc.tDriverInfo.entity.User;
import com.xzsd.pc.tDriverInfo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: guo
 * @Description: 后台司机信息管理
 * @Date： 2019/10/09
 */

@Validated
@RestController
@RequestMapping("driverInfo")
public class DriverController {

    @Autowired
    private DriverService driverService;

    /**
     * @Author: guo
     * @deprecated: 查询司机信息
     * @Data: 2019/10/10
     */
    @RequestMapping(value = "findAllDriver", method = RequestMethod.POST)
    public AppResponse findAllDriver(@RequestParam Map<String, Object> retMap) {

        return driverService.findAllDriver(retMap);
    }

    /**
     * @Author: guo
     * @deprecated: 添加司机信息
     * @data: 2019/10/10
     */
    @RequestMapping(value = "addDriver", method = RequestMethod.POST)
    public AppResponse additionDriver(@RequestParam Map<String, Object> addMap, Driver driver, User user) {

        return driverService.additionDriver(addMap, driver, user);
    }
}
