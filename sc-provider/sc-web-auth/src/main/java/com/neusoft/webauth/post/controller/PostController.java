package com.neusoft.webauth.post.controller;

import com.neusoft.core.exception.ScServerException;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.webauth.post.entity.PostInfo;
import com.neusoft.webauth.post.entity.PostsSetDTO;
import com.neusoft.webauth.post.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
@Validated
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Resource
    private PostService postService;

    /**
     * 部门：南京软件研发中心
     * 功能：查询岗位列表
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/27
     */
    @PostMapping("listPosts")
    public AppResponse listPostsByPage(PostInfo postInfo) {
        try {
           return postService.listPostsByPage(postInfo);
        } catch (Exception e) {
            logger.error("获取异常", e);
            throw new ScServerException("查询错误，请重试");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：新增岗位
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/27
     */
    @PostMapping("insertPost")
    public AppResponse insertPost(@Valid PostInfo postinfo){
        try {
            String userId = SecurityUtils.getCurrentUserId();
            postinfo.setCreateBy(userId);
            return postService.insertPost(postinfo);
        } catch (Exception e) {
            logger.error("岗位新增异常", e);
            throw new ScServerException("新增失败，请重试");
        }
    }
    /**
     * 部门：南京软件研发中心
     * 功能：修改岗位信息
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/28
     */
    @PostMapping("updatePost")
    public AppResponse updatePost(@Valid PostInfo postinfo){
        try {
            String userId = SecurityUtils.getCurrentUserId();
            postinfo.setLastModifiedBy(userId);
            return postService.updatePostInfo(postinfo);
        } catch (Exception e) {
            logger.error("岗位修改异常", e);
            throw new ScServerException("修改失败，请重试");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：删除岗位信息
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/28
     */
    @PostMapping("deletePost")
    public AppResponse deletePost(PostsSetDTO postsSetDTO) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            postsSetDTO.setLastModifiedBy(userId);
            return postService.deletePostInfo(postsSetDTO);
        } catch (Exception e) {
            logger.error("岗位删除错误", e);
            throw new ScServerException("岗位删除错误");
        }
    }

    /**
     * 修改岗位信息
     */
    @PostMapping("getdeptslist")
    public AppResponse getDeptsList(){
        try {
            List<PostInfo> deptsInfoList = null;
            deptsInfoList = postService.getDeptsList();
            return AppResponse.success("查询成功", deptsInfoList);
        } catch (Exception e) {
            logger.error("查询异常", e);
            throw new ScServerException("查询失败，请重试");
        }
    }
}
