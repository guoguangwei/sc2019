package com.xzsd.pc.log.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.log.entity.LogDTO;

/**
 *  
 * @Description:  日志信息——SERVICE
 * @Author:       ZWL   
 * @CreateDate:   2019年5月19日
 * @Version:      V1.0
 *    
 */
public interface LogService {


	/**
	 * 部门：青岛软件研发中心
	 * 功能：获取日志信息列表
	 * 描述：略
	 * @author：zwl
	 * @date ：2019/5/14
	 * @param logDTO
	 * @return com.neusoft.core.restful.AppResponse
	 */
	AppResponse listLog(LogDTO logDTO);
}