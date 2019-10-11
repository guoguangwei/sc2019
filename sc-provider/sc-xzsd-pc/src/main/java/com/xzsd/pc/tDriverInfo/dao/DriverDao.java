package com.xzsd.pc.tDriverInfo.dao;

import com.xzsd.pc.tDriverInfo.entity.Driver;
import com.xzsd.pc.tDriverInfo.entity.User;
import com.xzsd.pc.tDriverInfo.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface DriverDao {

    /**
     * @Author: guo
     * @deprecated: 查询司机信息
     * @Data: 2019/10/10
     */
    List findAllDriver(Map<String, Object> retMap);

    /**
     * @Author: guo
     * @deprecated: 查询司机是否已存在
     * @Data: 2019/10/10
     */
    int haveAccount(User user);

    /**
     * @Author: guo
     * @deprecated: 查询同一城市所拥有的司机数量
     * @Data: 2019/10/10
     */
    int driverNumber(Driver driver);

    /**
     * @Author: guo
     * @deprecated: 添加司机信息
     * @Data: 2019/10/10
     * @return
     */
    int addDriver(Driver driver);


    /**
     * @Author: guo
     * @deprecated: 添加司机账户
     * @Data: 2019/10/11
     * @return: int
     */
    int addDriverAccount(Driver driver);

    /**
     * @Author: guo
     * @deprecated: 查询账户角色是否为默认角色
     * @Data: 2019/10/11
     * @return: int
     */
    int userRole(UserRole userRole);

    /**
     * @Author: guo
     * @deprecated: 添加用户角色
     * @Data: 2019/10/11
     * @return: int
     */
    int addUserRole(UserRole userRole);
}
