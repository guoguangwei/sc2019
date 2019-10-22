package com.xzsd.pc.hotGoods.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.page.Page;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.UUIDUtils;
import com.xzsd.pc.hotGoods.dao.GoodsDao;
import com.xzsd.pc.hotGoods.entity.Goods;
import com.xzsd.pc.hotGoods.entity.HomeHotGoods;
import com.xzsd.pc.hotGoods.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.neusoft.core.page.PageUtils.getPageInfo;

@Service
public class GoodsServiceImpl implements GoodsService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsDao goodsDao;

    /**
     * @return
     * @author: guo
     * @deprecated: 查询热门商品
     * @data: 2019/10/15
     */
    @Override
    public AppResponse findGoodsInfo(Map<String, Object> retMap) {

        try {
            int currentPage = retMap.get("pageNum") == null ? 1 : Integer.parseInt(retMap.get("pageNum").toString());
            int limit = retMap.get("pageSize") == null ? 10 : Integer.parseInt(retMap.get("pageSize").toString());
            PageHelper.startPage(currentPage, limit);
            List<Goods> godsInfoList;
            Page page = new Page();

            try {
                godsInfoList = goodsDao.findGoodsInfo(retMap);
            } catch (Exception e) {
                logger.error(e.getMessage());
                return AppResponse.serverError("查询热门信息失败，请联系系统管理员");
            }

            if (CollectionUtils.isEmpty(godsInfoList)) {
                return AppResponse.notFound("未查询到结果");
            }

            page.setTotalRecord((int) new PageInfo<>(godsInfoList).getTotal());
            page.setRecords(godsInfoList);
            page.setCurrentPage(currentPage);
            page.setPageSize(limit);
            System.out.println(page);
            return AppResponse.success("查询成功", page);


        } catch (Exception e) {
            logger.info(e.getMessage());
            return AppResponse.serverError("查询司机信息失败，请联系系统管理员");
        }
    }

    /**
     * @return
     * @author: guo
     * @deprecated: 查询热门商品, 二级查询
     * @data: 2019/10/15
     */
    @Override
    public AppResponse getPageTGoodsSku(Map<String, Object> resMap) {

        try {
            int currentPage = resMap.get("pageNum") == null ? 1 : Integer.parseInt(resMap.get("pageNum").toString());
            int limit = resMap.get("pageSize") == null ? 10 : Integer.parseInt(resMap.get("pageSize").toString());
            PageHelper.startPage(currentPage, limit);
            List<Goods> PageTGoodsList;
            Page page = new Page();

            try {
                PageTGoodsList = goodsDao.getPageTGoodsSku(resMap);
            } catch (Exception e) {
                logger.error(e.getMessage());
                return AppResponse.serverError("二级查询热门信息失败，请联系系统管理员");
            }

            if (CollectionUtils.isEmpty(PageTGoodsList)) {
                return AppResponse.serverError("二级查询热门信息失败，请联系系统管理员");
            }

            page.setTotalRecord((int) new PageInfo<>(PageTGoodsList).getTotal());
            page.setRecords(PageTGoodsList);
            page.setCurrentPage(currentPage);
            page.setPageSize(limit);
            return AppResponse.success("查询成功", page);

        } catch (Exception e) {
            logger.info(e.getMessage());
            return AppResponse.serverError("二级查询热门信息失败，请联系系统管理员");
        }
    }

    /**
     * @return
     * @author: guo
     * @deprecated: 添加热门商品
     * @data: 2019/10/15
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AppResponse additionGoodsInfo(HomeHotGoods homeHotGoods) {

        try {
            //查询该热门商品是否已存在
            if (goodsDao.havedGoods(homeHotGoods) > 0) {
                return AppResponse.bizError("该商品已存在，无法新增");
            }

            //查看排序号是否存在
            if (goodsDao.havedOrderID(homeHotGoods) > 0) {
                int count = goodsDao.hotGoodsCount(homeHotGoods);
                homeHotGoods.setSortNo(count + 1);
            }
            homeHotGoods.setId(UUIDUtils.getUUID());
            int addGoods = goodsDao.additionGoods(homeHotGoods);
            if (addGoods > 0) {
                return AppResponse.success("热门信息添加成功");
            } else {
                return AppResponse.bizError("热门信息添加失败");
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return AppResponse.bizError("添加门店失败，请联系管理员！");
        }

    }

    /**
     * @return
     * @author: guo
     * @deprecated: 回显修改热门商品信息
     * @data: 2019/10/15
     */
    @Override
    public AppResponse showUpdateInfo(HomeHotGoods homeHotGoods, Goods goods) {

        List<HomeHotGoods> hotList = null;
        try {
            hotList = goodsDao.showUpdateInfo(homeHotGoods);
        } catch (Exception e) {

        }
        return AppResponse.success("回显热门商品数据成功", getPageInfo(hotList));
    }

    /**
     * @return
     * @author: guo
     * @deprecated: 修改热门商品信息
     * @data: 2019/10/15
     */
    @Override
    public AppResponse updateHotGoodsInfo(HomeHotGoods homeHotGoods) {

        try {
            //查询该热门商品是否已存在
            if (goodsDao.havedGoods(homeHotGoods) > 0) {
                return AppResponse.bizError("该商品已存在，无法新增");
            }

            //查看排序号是否存在
            if (goodsDao.havedOrderID(homeHotGoods) > 0) {
                int count = goodsDao.hotGoodsCount(homeHotGoods);
                homeHotGoods.setSortNo(count + 1);
            }

            int addGoods = goodsDao.updateHotGoodsInfo(homeHotGoods);
            if (addGoods > 0) {
                return AppResponse.success("热门信息添加成功");
            } else {
                return AppResponse.bizError("热门信息添加失败");
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            return AppResponse.bizError("添加门店失败，请联系管理员！");
        }
    }


    /**
     * @param idList
     * @return
     * @author: guo
     * @deprecated: 删除热门商品信息
     * @data: 2019/10/16
     */
    @Override
    public AppResponse deleteHotGoodsInfo(ArrayList<String> idList) {

        try {
            int count = 0;
            count = goodsDao.deleteHotGoodsInfo(idList);

            if (count > 0) {
                return AppResponse.success("成功删除" + count + "条热门信息");
            } else {
                return AppResponse.success("删除热门信息失败");
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return AppResponse.bizError("删除商品失败");
        }
    }


//================================PageInfo分页查询


    @Override
    public Map<String, Object> Submenu(Goods goods, Integer pageNum, Integer pageSize) {

        Map<String, Object> retMap = new HashMap<String, Object>();
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> list = goodsDao.Submenu(goods);
        PageInfo<Goods> page = new PageInfo<Goods>(list);
        long count = page.getTotal();

        retMap.put("msg", "数据获取成功");
        retMap.put("code", 0);
        retMap.put("data", list);
        retMap.put("count", count);

        return retMap;
    }
}
