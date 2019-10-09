package com.xzsd.pc.log.dao;

import java.util.List;

import com.xzsd.pc.log.entity.ActionDto;
import com.xzsd.pc.log.entity.LogDTO;
import com.xzsd.pc.log.entity.LogVO;
import org.apache.ibatis.annotations.Mapper;

/**   
 *  
 * @Description:  日志信息——DAO
 * @Author:       ZWL   
 * @CreateDate:   2019年5月19日
 * @Version:      V1.0
 *    
 */
@Mapper
public interface LogDao {

    /**
     * 部门：青岛软件研发中心
     * 功能：获取日志信息列表
     * 描述：略
     * @author：zwl
     * @date ：2019/5/14
     * @param logDTO
     * @return com.xzsd.pc.log.entity.LogVO
     */
    List<LogVO> listLogByPage(LogDTO logDTO);

    /**
     * @Dept：青岛软件研发中心
     * @Description：操作日志添加
     * @Author：DXL
     * @Date: 2019年5月30日
     * @Param：orderMasterDto
     * @Return：
     **/
    void insertLog(ActionDto actionDto);
}
