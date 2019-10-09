package com.neusoft.webauth.post.dao;

import com.neusoft.webauth.post.entity.PostInfo;
import com.neusoft.webauth.post.entity.PostsSetDTO;
import com.neusoft.webauth.user.entity.UserSettingDTO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName PostDao
 * @Description 岗位管理管理
 * @Author zhu.qf@neusoft.com
 * @Date 2018/11/28
 */
public interface PostDao {
    /**
     * 岗位列表
     * @param postInfo 岗位查询条件
     * @return
     */
    List<PostInfo> listPostsByPage(PostInfo postInfo);

    List<PostInfo> getAllPosts();

    /**
     * 新增岗位
     * @param postInfo 岗位信息
     * @return 新增条数
     */
    int insertPost(PostInfo postInfo);

    /**
     * 修改岗位
     * @param postInfo 岗位信息
     * @return 修改条数
     */
    int updatePostInfo(PostInfo postInfo);

    /**
     * 删除岗位信息
     * @param postsSetDTO 岗位信息
     * @return 删除条数
     */
    int deletePostInfo(PostsSetDTO postsSetDTO);

    /**
     * 删除用户岗位关联关系
     * @param postsSetDTO 岗位信息
     * @return 删除条数
     */
    int deleteUsersPostByPost(PostsSetDTO postsSetDTO);

    List<PostInfo> getDeptsList();   //查询部门表中所有的部门信息

    List<PostInfo> getBmStations(String s);

    String[] getUserGws(String user_code);

    /**
     * 用户管理-删除-删除用岗位
     * @param userSettingDTO
     * @return
     */
    int deleteUsersPost(UserSettingDTO userSettingDTO);

    /**
     * 校验岗位名称是否存在
     * @param postInfo 岗位信息
     * @return 条数
     */
    int countPostName(PostInfo postInfo);

    /**
     * 用户管理-设置部门，删除未选中部门的用户岗位关联关系
     * @param userSettingDTO
     * @return
     */
    int deleteUserPostUncheked(UserSettingDTO userSettingDTO);

    /**
     * 用户管理-设置岗位，查询用户授权部门下的全部岗位
     * @param userSettingDTO
     * @return
     */
    List<PostInfo> listPostByUserDeptByPage(UserSettingDTO userSettingDTO);

    /**
     * 用户管理-设置岗位，查询用户已授权的岗位
     * @param map 当前岗位信息
     * @return
     */
    List<String> listUserPosts(Map<String, Object> map);

    /**
     * 用户管理-设置岗位，保存，删除当前页的用户岗位信息
     * @param userSettingDTO 用户岗位信息
     * @return
     */
    int deleteCurPageUserPosts(UserSettingDTO userSettingDTO);

    /**
     * 用户管理-设置岗位，保存，新增用户岗位
     * @param userSettingDTO 用户岗位信息
     * @return
     */
    int insertUserPosts(UserSettingDTO userSettingDTO);
}
