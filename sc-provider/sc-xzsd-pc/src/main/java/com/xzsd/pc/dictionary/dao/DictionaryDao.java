package com.xzsd.pc.dictionary.dao;


import com.xzsd.pc.dictionary.entity.DictionaryDO;
import com.xzsd.pc.dictionary.entity.DictionaryVO;

import java.util.List;


public interface DictionaryDao {
    /**
     * @Dept：南京软件研发中心
     * @Description：字典列表查询
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    List<DictionaryVO> listDictionaryByPage(DictionaryDO dictionaryDO);
    /**
     * @Dept：南京软件研发中心
     * @Description：父级代码
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    List<DictionaryVO> listDictionaryFatherByPage(DictionaryDO dictionaryDO);
    /**
     * @Dept：南京软件研发中心
     * @Description：新增字典
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    void saveDictionary(DictionaryDO dictionaryDO);

    /**
     * @部门：南京软件研发中心
     * @功能：验证字典名称是否存在
     * @Author：yan-jd
     * @Date 2019/3/5
     * @param dictionaryDO
     * @return
     */
    int countDictionaryName(DictionaryDO dictionaryDO);

    /**
     * @Dept：南京软件研发中心
     * @Description：修改字典
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    int updateDictionary(DictionaryDO dictionaryDO);



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
