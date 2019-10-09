package com.xzsd.pc.dictionary.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.dictionary.entity.DictionaryDTO;
import com.xzsd.pc.dictionary.entity.DictionaryVO;

/**
 * @部门：南京软件研发中心
 * @功能： 字典相关接口
 * @Author：yan-jd
 * @Date 2019/2/18
 **/
public interface DictionaryService {
    /**
     * @Dept：南京软件研发中心
     * @Description：字典列表查询
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    AppResponse listDictionary(DictionaryDTO companyDTO);

    /**
     * @Dept：南京软件研发中心
     * @Description：父级代码
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    AppResponse listDictionaryFather(DictionaryDTO companyDTO);
    /**
     * @Dept：南京软件研发中心
     * @Description：新增字典
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    AppResponse saveDictionary(DictionaryDTO companyDTO);

    /**
     * @Dept：南京软件研发中心
     * @Description：修改字典
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    AppResponse updateDictionary(DictionaryDTO companyDTO);

    /**
     * @Dept：南京软件研发中心
     * @Description：获取修改信息详情
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    DictionaryVO getDictionary(String id);
}
