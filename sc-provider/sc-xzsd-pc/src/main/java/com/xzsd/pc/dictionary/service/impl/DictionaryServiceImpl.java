package com.xzsd.pc.dictionary.service.impl;

import static com.neusoft.core.page.PageUtils.getPageInfo;

import java.util.List;

import javax.annotation.Resource;

import com.xzsd.pc.dictionary.dao.DictionaryDao;
import com.xzsd.pc.dictionary.entity.DictionaryDO;
import com.xzsd.pc.dictionary.entity.DictionaryDTO;
import com.xzsd.pc.dictionary.entity.DictionaryVO;
import com.xzsd.pc.dictionary.service.DictionaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.neusoft.util.UUIDUtils;

@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Resource
    private DictionaryDao dictionaryDao;

    /**
     * @Dept：南京软件研发中心
     * @Description：字典列表查询
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    @Override
    public AppResponse listDictionary(DictionaryDTO dictionaryDTO){
        DictionaryDO dictionaryDO = new DictionaryDO();
        //将DTO赋值给PO
        BeanUtils.copyProperties(dictionaryDTO,dictionaryDO);
        List<DictionaryVO> dictionaryDTOList = dictionaryDao.listDictionaryByPage(dictionaryDO);
        return AppResponse.success("查询成功", getPageInfo(dictionaryDTOList));
    }
    /**
     * @Dept：南京软件研发中心
     * @Description：父级代码
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    @Override
    public AppResponse listDictionaryFather(DictionaryDTO dictionaryDTO){
        DictionaryDO dictionaryDO = new DictionaryDO();
        //将DTO赋值给PO
        BeanUtils.copyProperties(dictionaryDTO,dictionaryDO);
        List<DictionaryVO> dictionaryDTOList = dictionaryDao.listDictionaryFatherByPage(dictionaryDO);
        return AppResponse.success("查询成功", getPageInfo(dictionaryDTOList));
    }
    /**
     * @Dept：南京软件研发中心
     * @Description：新增字典
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    @Override
    public AppResponse saveDictionary(DictionaryDTO dictionaryDTO) {
        DictionaryDO dictionaryDO = new DictionaryDO();
        //将DTO赋值给PO
        BeanUtils.copyProperties(dictionaryDTO,dictionaryDO);
        dictionaryDO.setDictValue(StringUtil.getCommonCode(2));
        //验证字典名称是否存在
        int countName = dictionaryDao.countDictionaryName(dictionaryDO);
        if(0 != countName) {
            return AppResponse.bizError("字典名称已存在，请重新输入！");
        }
        dictionaryDO.setId(UUIDUtils.getUUID());
        dictionaryDao.saveDictionary(dictionaryDO);
        return AppResponse.success("新增成功！");
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：修改字典
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    @Override
    public AppResponse updateDictionary(DictionaryDTO dictionaryDTO) {
        AppResponse appResponse = AppResponse.success("修改成功");
        DictionaryDO dictionaryDO = new DictionaryDO();
        //将DTO赋值给PO
        BeanUtils.copyProperties(dictionaryDTO,dictionaryDO);
        //验证字典名称是否存在
        int countName = dictionaryDao.countDictionaryName(dictionaryDO);
        if(0 != countName) {
            return AppResponse.bizError("字典名称已存在，请重新输入！");
        }
        // 修改字典信息
        dictionaryDao.updateDictionary(dictionaryDO);
        return appResponse;
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：获取修改信息详情
     * @Author：yan-jd
     * @Date: 2019/2/18
     * @Param：dictionaryDTO
     * @Return：com.neusoft.core.restful.AppResponse
     **/
    @Override
    public DictionaryVO getDictionary(String id) {
        DictionaryVO getDictionary= dictionaryDao.getDictionary(id);
        return getDictionary;
    }

}
