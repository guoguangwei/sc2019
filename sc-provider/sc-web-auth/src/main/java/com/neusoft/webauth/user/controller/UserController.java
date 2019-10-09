package com.neusoft.webauth.user.controller;

import com.neusoft.core.exception.ScServerException;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.webauth.role.service.RoleService;
import com.neusoft.webauth.user.entity.UserInfo;
import com.neusoft.webauth.user.entity.UserSettingDTO;
import com.neusoft.webauth.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description 用户管理
 * @Author zhu.qf@neusoft.com
 * @Date 2018/11/28
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    /**
     * 部门：南京软件研发中心
     * 功能：新增用户
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/26 10:58
     */
//    @SystemLog1(operation = "新增用户信息测试操作。。。。。")
    @PostMapping("saveUser")
    public AppResponse saveUser(@Valid UserInfo userInfo) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            userInfo.setCreateBy(userId);
            AppResponse appResponse = userService.saveUser(userInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("用户注册失败", e);
            throw new ScServerException("用户新增失败，请重试");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：获取用户列表
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/29
     */
//    @SystemLog1(operation = "获取用户列表。。。。。")
    @RequestMapping(value = "listUsers")
    public AppResponse listUsers(UserInfo userInfo) {
        try {
            return userService.listUsers(userInfo);
        } catch (Exception e) {
            logger.error("用户获取异常", e);
            throw new ScServerException("查询错误，请重试");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：删除用户信息
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/30
     */
    @PostMapping("deleteUser")
    public AppResponse deleteUser(UserSettingDTO userSettingDTO) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            userSettingDTO.setLastModifiedBy(userId);
            return userService.deleteUser(userSettingDTO);
        } catch (Exception e) {
            logger.error("用户删除错误", e);
            throw new ScServerException("用户删除错误");
        }
    }
    /**
     * 部门：南京软件研发中心
     * 功能：修改密码
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/30
     */
    @PostMapping("updatePwd")
    public AppResponse updatePwd(UserInfo userInfo) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            userInfo.setLastModifiedBy(userId);
            return userService.updatePwd(userInfo);
        } catch (Exception e) {
            logger.error("修改异常", e);
            throw new ScServerException("修改密码失败，请重试");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：修改用户 TODO
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/30
     */
    @PostMapping("updateUser")
    public AppResponse updateUser(@Valid UserInfo userInfo) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            userInfo.setCreateBy(userId);
            userInfo.setLastModifiedBy(userId);
            return userService.updateUser(userInfo);
        } catch (Exception e) {
            logger.error("修改用户信息错误", e);
            throw new ScServerException("系统错误，请重试");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：查询所有的角色，及用户已授权的角色
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/13 15:10
     */
    @PostMapping("listRolesAndChecked")
    public AppResponse listRolesAndChecked(@RequestParam("userCode") String userCode) {
        try {
            Map<String, Object> map = userService.listRolesAndChecked(userCode);

            return AppResponse.success("", map);
        }catch (Exception e){
            logger.error("用户角色查询", e);
            throw new ScServerException("用户角色查询失败，请重试");
        }
    }
    /**
     * 部门：南京软件研发中心
     * 功能：用户指派角色
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/13 15:10
     */
    @PostMapping("saveUserRole")
    public AppResponse saveUserRole(UserSettingDTO userSettingDTO) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            userSettingDTO.setCreateBy(userId);
            userSettingDTO.setLastModifiedBy(userId);
            return userService.saveUserRole(userSettingDTO);
        }catch (Exception e){
            logger.error("用户角色查询", e);
            throw new ScServerException("用户角色查询失败，请重试");
        }
    }


    /**
     * 根据用户代码获取用户信息
     *
     * @param userCode 用户代码
     * @return 用户信息
     */
    @RequestMapping(value = "getUserByUserCode")
    public AppResponse getUserByUserCode(@NotNull(message = "用户代码不能为空") String userCode) {

        UserInfo userInfo = null;
        try {
            userInfo = userService.getUserById(userCode);
        } catch (Exception e) {
            logger.error("用户查询错误", e);
            throw new ScServerException("查询错误，请重试");
        }
        if (userInfo == null) {
            return AppResponse.notFound("无查询结果");
        }

        return AppResponse.success("查询成功", userInfo);
    }

    /**
     * 部门：南京软件研发中心
     * 功能：获取用户岗位信息
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/12/3
     */
    @RequestMapping(value = "listPostsByUser")
    public AppResponse listPostsByUser(UserSettingDTO userSettingDTO) {
        try {
            return userService.listPostsByUser(userSettingDTO);
        } catch (Exception e) {
            logger.error("岗位查询错误", e);
            throw new ScServerException("查询错误，请重试");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：用户管理-设置岗位，保存
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/12/3
     */
    @PostMapping("saveUserPosts")
    public AppResponse saveUserPosts(UserSettingDTO userSettingDTO){
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            userSettingDTO.setLastModifiedBy(userId);
            userSettingDTO.setCreateBy(userId);
            return userService.saveUserPosts(userSettingDTO);
        }catch (Exception e){
            logger.error("修改用户岗位", e);
            throw new ScServerException("修改用户岗位错误");
        }
    }

    /**
     * 获取当前用户角色
     * @param
     * @return
     */
    @PostMapping("showUserRole")
    public AppResponse showUserRole(HttpServletRequest request){
        try {
            String USER_CODE = request.getParameter("USER_CODE");
            Map<String, Object> resultMap = userService.showUserRole(USER_CODE);
            return  AppResponse.success("查询成功", resultMap);
        } catch (Exception e) {
            logger.error("获取当前用户角色", e);
            throw new ScServerException("获取当前用户角色错误");
        }
    }

    /**
     * 设置默认角色
     * @param request
     * @return
     */
    @PostMapping("setDefaultRole")
    public AppResponse setDefaultRole(HttpServletRequest request){
        try {
            String USER_CODE = request.getParameter("USER_CODE");//用户代码
            String ROLE_CODE = request.getParameter("ROLE_CODE");//角色代码
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            roleService.setDefaultRole(USER_CODE,ROLE_CODE,userId);
        }catch (Exception e){
            logger.error("设置默认角色", e);
            throw new ScServerException("设置默认角色错误");
        }
        return AppResponse.success("设置默认角色成功");
    }


    /**
     * 部门：南京软件研发中心
     * 功能：用户管理-设置部门，保存
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/12/3
     */
    @PostMapping("saveUserDepts")
    public AppResponse saveUserDepts(UserSettingDTO userSettingDTO){
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            userSettingDTO.setCreateBy(userId);
            userSettingDTO.setLastModifiedBy(userId);
            return userService.saveUserDepts(userSettingDTO);
        }catch (Exception e){
            logger.error("修改用户部门", e);
            throw new ScServerException("修改用户部门错误");
        }
    }
}
