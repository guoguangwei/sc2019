package com.xzsd.pc.patriarch.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.log.annotation.OperationControllerLog;
import com.xzsd.pc.patriarch.service.PatriarchService;
import com.xzsd.pc.util.service.UserInfoNeedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wwb
 * @Date: 2019/5/14 16:40
 * @Description:后台家长端信息管理
 */
@Api("后台家长端信息管理")
@RestController
@Validated
@RequestMapping("patriarch")
public class PatriarchController {
    @Autowired
    private PatriarchService patriarchService;

    @Autowired
    private UserInfoNeedService userInfoNeedService;

    /**
     * @Author: wwb
     * @Description: 查询家长信息
     * @param map
     * @return: com.neusoft.core.restful.AppResponse
     * @Date: 2019/5/17 17:16
     */
    @ApiOperation(value="查询家长信息",notes = "查询的条件")
    //@ApiOperation接口文档注解,它不是spring自带的注解是swagger里的
    //@ApiOperation(value = “接口说明”, httpMethod = “接口请求方式”, response = “接口返回参数类型”, notes = “接口发布说明”)
    @RequestMapping(value="/listPatriarchInfo", method= RequestMethod.POST)
    public AppResponse listPatriarchInfo(@RequestParam Map<String, Object> map) {
        String userCode = SecurityUtils.getCurrentUserId();
        String isAdmin = userInfoNeedService.getUserType(userCode);
        map.put("is_admin", isAdmin);
        map.put("user_code", userCode);
        return patriarchService.listPatriarchInfo(map);
    }

    /**
     * @Author: wwb
     * @Description: 删除家长信息
     * @param list
     * @return: com.neusoft.core.restful.AppResponse
     * @Date: 2019/5/17 17:16
     */
    @ApiOperation(value="删除家长信息",notes = "删除的条件")
    @RequestMapping(value="/removePatriarchInfo", method= RequestMethod.POST)
    @OperationControllerLog(description = "删除家长信息")
    public AppResponse removePatriarchInfo(@RequestParam(value = "user_no_list") @ApiParam(value="要删除的用户编号列表", required = true) ArrayList list){
        String userCode = SecurityUtils.getCurrentUserId();
        Map map = new HashMap(2);
        map.put("user_no_list", list);
        map.put("user_code", userCode==null ? "null" : userCode);
        return patriarchService.removePatriarchInfo(map);
    }

}
