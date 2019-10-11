package com.xzsd.pc.tDriverInfo.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.tDriverInfo.entity.Driver;
import com.xzsd.pc.tDriverInfo.entity.User;
import com.xzsd.pc.tDriverInfo.entity.UserRole;

import java.util.Map;

public interface DriverService {

    /**
     * @Author: guo
     * @deprecated: 查询司机信息
     * @Data: 2019/10/10
     */
    AppResponse findAllDriver(Map<String, Object> retMap);

    /**
     * @Author: guo
     * @deprecated: 添加司机信息
     * @Data: 2019/10/10
     */
    AppResponse additionDriver(Driver driver, User user, UserRole userRole);
}
