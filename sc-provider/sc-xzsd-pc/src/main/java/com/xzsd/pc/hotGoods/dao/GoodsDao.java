package com.xzsd.pc.hotGoods.dao;

import com.xzsd.pc.hotGoods.entity.Goods;
import com.xzsd.pc.hotGoods.entity.HomeHotGoods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface GoodsDao {

    /**
     * @author: guo
     * @param retMap
     * @data: 2019/10/15
     * @return
     */
    List<Goods> findGoodsInfo(Map<String, Object> retMap);

    /**
     * @author: guo
     * @deprecated: 查询热门商品,二级查询
     * @data: 2019/10/15
     * @return
     */
    List<Goods> getPageTGoodsSku(Map<String, Object> resMap);

    /**
     * @author: guo
     * @deprecated: 查询该热门商品是否已存在
     * @data: 2019/10/15
     * @return
     */
    int havedGoods(HomeHotGoods homeHotGoods);

    /**
     * @author: guo
     * @deprecated: 查看排序号是否存在
     * @data: 2019/10/15
     * @return
     */
    int havedOrderID(HomeHotGoods homeHotGoods);

    /**
     * @author: guo
     * @deprecated: 查询该热门商品总数
     * @data: 2019/10/15
     * @return
     */
    int hotGoodsCount(HomeHotGoods homeHotGoods);

    /**
     * @author: guo
     * @deprecated: 添加热门商品
     * @data: 2019/10/15
     * @return
     */
    int additionGoods(HomeHotGoods homeHotGoods);

    /**
     * @author: guo
     * @deprecated: 回显修改热门商品信息
     * @data: 2019/10/15
     * @return
     */
    List<HomeHotGoods> showUpdateInfo(HomeHotGoods homeHotGoods);

    /**
     * @author: guo
     * @deprecated: 修改热门商品信息
     * @data: 2019/10/15
     * @return
     */
    int updateHotGoodsInfo(HomeHotGoods homeHotGoods);

    /**
     * @author: guo
     * @deprecated: 删除热门商品信息
     * @data: 2019/10/16
     * @return
     */
    int deleteHotGoodsInfo(String id);


    //================================PageInfo分页查询

    List<Goods> Submenu(Goods goods);


}
