package com.neusoft.webauth.post.service;

import com.neusoft.core.page.PageUtils;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.neusoft.util.UUIDUtils;
import com.neusoft.webauth.post.dao.PostDao;
import com.neusoft.webauth.post.entity.PostInfo;
import com.neusoft.webauth.post.entity.PostsSetDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService {

    @Resource
    private PostDao postDao;

    /**
     * 部门：南京软件研发中心
     * 功能：查询岗位信息
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/27
     */
    public AppResponse listPostsByPage(PostInfo postInfo){
        List<PostInfo> postInfoList = postDao.listPostsByPage(postInfo);

//        if (CollectionUtils.isEmpty(postInfoList)) {
//            return AppResponse.notFound("未查询到结果");
//        }
        return AppResponse.success("查询成功", PageUtils.getPageInfo(postInfoList));
    }

    /**
     * 获取所有岗位信息
     * @return
     */
    public List<PostInfo> getAllPosts(){
        return postDao.getAllPosts();
    }

    /**
     * 部门：南京软件研发中心
     * 功能：新建岗位
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/27
     */
    public AppResponse insertPost(PostInfo postinfo){
        AppResponse appResponse = AppResponse.success();
        // 校验岗位名称是否存在
        int countPostName = postDao.countPostName(postinfo);
        if(0 != countPostName) {
            return AppResponse.bizError("岗位名称已存在，请重新输入！");
        }
        // 岗位代码
        postinfo.setPostCode(StringUtil.getCommonCode(2));
        postinfo.setId(UUIDUtils.getUUID());
        // 新增岗位
        int count = postDao.insertPost(postinfo);
        if(0 == count) {
            appResponse = AppResponse.bizError("新增失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 部门：南京软件研发中心
     * 功能：修改岗位信息
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/27
     */
    public AppResponse updatePostInfo(PostInfo postinfo){
        AppResponse appResponse = AppResponse.success();
        // 校验岗位名称是否存在
        int countPostName = postDao.countPostName(postinfo);
        if(0 != countPostName) {
            return AppResponse.bizError("岗位名称已存在，请重新输入！");
        }
        int count = postDao.updatePostInfo(postinfo);
        if(0 == count) {
            appResponse = AppResponse.bizError("系统数据有变化，请刷新列表！");
        }
        return appResponse;
    }

    /**
     * 部门：南京软件研发中心
     * 功能：删除岗位
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/28
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deletePostInfo(PostsSetDTO postsSetDTO) {
        AppResponse appResponse = AppResponse.success();
        // 删除人员岗位关联表
        postDao.deleteUsersPostByPost(postsSetDTO);
        //删除岗位信息
        int count = postDao.deletePostInfo(postsSetDTO);
        if(0 == count) {
            return AppResponse.bizError("删除失败，请刷新重试！");
        }
        return appResponse;
    }

    /**
     * 获取部门表中所有的部门信息
     * @return
     */
    public List<PostInfo> getDeptsList(){
        return postDao.getDeptsList();
    }
}
