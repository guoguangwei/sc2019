package com.xzsd.pc.dictionary.controller;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import com.xzsd.pc.dictionary.entity.DictionaryDTO;
import com.xzsd.pc.dictionary.entity.DictionaryVO;
import com.xzsd.pc.dictionary.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.core.exception.ScServerException;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 字典相关controller
 */
@Api("字典管理")
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryController.class);
    @Resource
    private DictionaryService dictionaryService;
    /**
     * @Dept：南京软件研发中心
     * @Description：字典列表查询
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    @ApiOperation("字典列表")
    @PostMapping("listDictionary")
    public AppResponse listDictionary (DictionaryVO dictionaryVO){
        try {
            DictionaryDTO dictionaryDTO = new DictionaryDTO();
            BeanUtils.copyProperties(dictionaryVO,dictionaryDTO);
            return dictionaryService.listDictionary(dictionaryDTO);
        }catch (Exception e) {
            logger.error("获取异常", e);
            throw new ScServerException("查询错误，请重试");
        }
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：父级代码
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    @ApiOperation("父级代码")
    @PostMapping("listDictionaryFather")
    public AppResponse listDictionaryFather (DictionaryVO dictionaryVO){
        try {
            DictionaryDTO dictionaryDTO = new DictionaryDTO();
            BeanUtils.copyProperties(dictionaryVO,dictionaryDTO);
            return dictionaryService.listDictionaryFather(dictionaryDTO);
        }catch (Exception e) {
            logger.error("获取异常", e);
            throw new ScServerException("查询错误，请重试");
        }
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：新增字典
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    @ApiOperation("新增字典")
    @PostMapping("saveDictionary")
    public AppResponse saveDictionary (DictionaryVO dictionaryVO){
        try {
            String userId = SecurityUtils.getCurrentUserId();
            DictionaryDTO dictionaryDTO = new DictionaryDTO();
            BeanUtils.copyProperties(dictionaryVO,dictionaryDTO);
            dictionaryDTO.setCreateBy(userId);
            dictionaryDTO.setLastModifiedBy(userId);
            return dictionaryService.saveDictionary(dictionaryDTO);
        }catch (Exception e) {
            logger.error("获取异常", e);
            throw new ScServerException("新增错误，请重试");
        }
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：修改字典
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    @ApiOperation("修改字典")
    @PostMapping("updateDictionary")
    public AppResponse updateDictionary (DictionaryVO dictionaryVO){
        try {
            String userId = SecurityUtils.getCurrentUserId();
            DictionaryDTO dictionaryDTO = new DictionaryDTO();
            BeanUtils.copyProperties(dictionaryVO,dictionaryDTO);
            dictionaryDTO.setLastModifiedBy(userId);
            return dictionaryService.updateDictionary(dictionaryDTO);
        }catch (Exception e) {
            logger.error("修改字典异常", e);
            throw new ScServerException("修改失败，请重试");
        }
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：获取修改信息详情
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    @ApiOperation("获取字典详情")
    @RequestMapping(value = "getDictionary")
    public AppResponse getDictionary(@NotNull(message = "代码不能为空") String id) {
        DictionaryVO dictionaryVO = null;
        try {
            dictionaryVO = dictionaryService.getDictionary(id);
        } catch (Exception e) {
            logger.error("用户查询错误", e);
            throw new ScServerException("查询错误，请重试");
        }
        return AppResponse.success("查询成功", dictionaryVO);
    }
}
