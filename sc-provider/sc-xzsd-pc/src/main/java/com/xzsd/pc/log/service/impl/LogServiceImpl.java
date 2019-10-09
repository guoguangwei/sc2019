package com.xzsd.pc.log.service.impl;


import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.log.dao.LogDao;
import com.xzsd.pc.log.entity.LogDTO;
import com.xzsd.pc.log.entity.LogVO;
import org.springframework.stereotype.Service;

import com.xzsd.pc.log.service.LogService;

import javax.annotation.Resource;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**   
 *  
 * @Description:  日志信息——SERVICEIMPL
 * @Author:       ZWL   
 * @CreateDate:   2019年5月19日
 * @Version:      V1.0
 *    
 */
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogDao logDao;


    /**
     * 部门：青岛软件研发中心
     * 功能：获取日志信息列表
     * 描述：略
     * @author：zwl
     * @date ：2019/5/14
     * @param logDTO
     * @return com.neusoft.core.restful.AppResponse
     */
    @Override
    public AppResponse listLog(LogDTO logDTO) {
        List<LogVO> bannerList = logDao.listLogByPage(logDTO);
        return AppResponse.success("查询成功！", getPageInfo(bannerList));
    }
}