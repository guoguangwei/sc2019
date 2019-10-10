package com.xzsd.pc.tDriverInfo.dao;

import com.xzsd.pc.tDriverInfo.entity.Driver;
import com.xzsd.pc.tDriverInfo.entity.User;
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
     */
    void addDriver(Driver driver);


}
