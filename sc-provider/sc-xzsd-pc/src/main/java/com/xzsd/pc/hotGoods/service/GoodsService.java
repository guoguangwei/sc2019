package com.xzsd.pc.hotGoods.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.hotGoods.entity.Goods;
import com.xzsd.pc.hotGoods.entity.HomeHotGoods;

import java.util.Map;

public interface GoodsService {

    /**
     * @author: guo
     * @param retMap
     * @data: 2019/10/15
     * @return
     */
    AppResponse findGoodsInfo(Map<String, Object> retMap);

    /**
     * @author: guo
     * @deprecated: 查询热门商品,二级查询
     * @data: 2019/10/15
     * @return
     */
    AppResponse getPageTGoodsSku(Map<String, Object> resMap);

    /**
     * @author: guo
     * @deprecated: 添加热门商品
     * @data: 2019/10/15
     * @return
     */
    AppResponse additionGoodsInfo(HomeHotGoods homeHotGoods);

    /**
     * @author: guo
     * @deprecated: 回显修改热门商品信息
     * @data: 2019/10/15
     * @return
     */
    AppResponse showUpdateInfo(HomeHotGoods homeHotGoods, Goods goods);

    /**
     * @author: guo
     * @deprecated: 修改热门商品信息
     * @data: 2019/10/15
     * @return
     */
    AppResponse updateHotGoodsInfo(HomeHotGoods homeHotGoods);

    /**
     * @author: guo
     * @deprecated: 删除热门商品信息
     * @data: 2019/10/16
     * @return
     */
    AppResponse deleteHotGoodsInfo(Map delMap);

    //================================PageInfo分页查询

    Map<String, Object> Submenu(Goods goods, Integer pageNum, Integer pageSize);



}
