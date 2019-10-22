package com.xzsd.pc.hotGoods.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.hotGoods.entity.Goods;
import com.xzsd.pc.hotGoods.entity.HomeHotGoods;
import com.xzsd.pc.hotGoods.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/goodsInfo")
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    /**
     * @author: guo
     * @deprecated: 查询热门商品
     * @data: 2019/10/15
     * @return
     */
    @RequestMapping(value = "/findGoodsInfo")
    public AppResponse findAllGoods(@RequestParam Map<String, Object> retMap) {

        System.out.println(SecurityUtils.getCurrentUserUsername());
        return goodsService.findGoodsInfo(retMap);
    }

    /**
     * @author: guo
     * @deprecated: 查询热门商品,二级查询
     * @data: 2019/10/15
     * @return
     */
    @RequestMapping(value = "/getPageTGoodsSku")
    public AppResponse getPageTGoodsSku(@RequestParam Map<String, Object> resMap) {

        return goodsService.getPageTGoodsSku(resMap);
    }

    /**
     * @author: guo
     * @deprecated: 添加热门商品
     * @data: 2019/10/15
     * @return
     */
    @RequestMapping(value = "/addGoodsInfo", method = RequestMethod.POST)
    public AppResponse additionGoodsInfo(HomeHotGoods homeHotGoods) {

        return goodsService.additionGoodsInfo(homeHotGoods);
    }

    /**
     * @author: guo
     * @deprecated: 回显修改热门商品信息
     * @data: 2019/10/15
     * @return
     */
    @RequestMapping(value = "/showUpdateInfo")
    public AppResponse showUpdateInfo(HomeHotGoods homeHotGoods, Goods goods) {
        return goodsService.showUpdateInfo(homeHotGoods, goods);
    }


    /**
     * @author: guo
     * @deprecated: 修改热门商品信息
     * @data: 2019/10/15
     * @return
     */
    @RequestMapping(value = "/updateGoodsInfo", method = RequestMethod.POST)
    public AppResponse updateHotGoodsInfo(HomeHotGoods homeHotGoods) {

        return goodsService.updateHotGoodsInfo(homeHotGoods);
    }


    /**
     * @author: guo
     * @deprecated: 删除热门商品信息
     * @data: 2019/10/16
     * @return
     */
    @RequestMapping(value = "/delHotGoodsInfo", method = RequestMethod.POST)
    public AppResponse deleteHotGoodsInfo(@RequestBody ArrayList<String> id_list) {

        String skuNo = SecurityUtils.getCurrentUserId();
        Map delMap = new HashMap(2);

        delMap.put("id_list", id_list);
        delMap.put("last_modified_by", skuNo == null ? "null" : skuNo);
        AppResponse appResponse = goodsService.deleteHotGoodsInfo(delMap);

        return appResponse;
    }



    //================================PageInfo分页查询


    @RequestMapping("/userList")
    public Map<String, Object> getAllUser(Integer pageNum,Integer pageSize,Goods goods) {

        return goodsService.Submenu(goods, pageNum, pageSize);
    }

}
