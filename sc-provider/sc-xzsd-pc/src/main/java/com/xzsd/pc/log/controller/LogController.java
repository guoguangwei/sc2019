package com.xzsd.pc.log.controller;


import com.neusoft.core.exception.ScServerException;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.log.entity.LogDTO;
import com.xzsd.pc.log.entity.LogVO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xzsd.pc.log.service.LogService;

import io.swagger.annotations.Api;

/**
 *
 * @Description:  日志信息接口层
 * @Author:       ZWL
 * @CreateDate:   2019年5月19日
 * @Version:      V1.0
 *
 */
@Api(description = "日志信息",value="日志信息" )
@RestController
@RequestMapping("/log")
public class LogController {

	private static final  Logger logger = LoggerFactory.getLogger(LogController.class);

	@Autowired
	public LogService logService;

	/**
	 * 部门：青岛软件研发中心
	 * 功能：日志列表
	 * 描述：略
	 * 作成者：zwl
	 * 作成时间：2019/5/14
	 */
	@ApiOperation("日志列表")
	@PostMapping("/listLog")
	public AppResponse listOrder(LogVO logVO) {
		try {
			//将VO赋值给DTO
			LogDTO logDTO = new LogDTO();
			BeanUtils.copyProperties(logVO,logDTO);
			return logService.listLog(logDTO);
		} catch (Exception e) {
			logger.error("日志信息获取异常", e);
			throw new ScServerException("日志信息获取失败，请重试");
		}
	}


}