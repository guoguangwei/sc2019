package com.neusoft.webauth.role.controller;

import com.neusoft.core.exception.ScServerException;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.webauth.menu.entity.Menu;
import com.neusoft.webauth.menu.entity.MenuBtn;
import com.neusoft.webauth.role.entity.RoleInfo;
import com.neusoft.webauth.role.entity.RoleMenu;
import com.neusoft.webauth.role.entity.RoleUserDTO;
import com.neusoft.webauth.role.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
/**
 * @ClassName RoleController
 * @Description 角色管理
 * @Author zhu.qf@neusoft.com
 * @Date 2018/11/28
 */
@RestController
@RequestMapping("/role")
@Validated
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Resource
    private RoleService roleService;

    /**
     * 部门：南京软件研发中心
     * 功能：新增角色
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/29
     */
    @PostMapping("saveRole")
    public AppResponse saveRole(@Valid RoleInfo roleinfo){
        try {
            String userId = SecurityUtils.getCurrentUserId();
            roleinfo.setCreateBy(userId);
            return roleService.saveRole(roleinfo);
        } catch (Exception e) {
            logger.error("角色新增异常", e);
            throw new ScServerException("新增失败，请重试！");
        }
    }

    /**
     * 删除角色
     * 部门：软件开发事业部
     * 描述：略
     * 作成者：印政权
     * 作成时间：2018-03-07
     */
    @PostMapping("deleteRole")
    public AppResponse deleteRole(HttpServletRequest request){
        try {
            String roles = request.getParameter("roles");
            String userId = SecurityUtils.getCurrentUserId();
            roleService.deleteRole(roles,userId);
            return AppResponse.success("角色删除成功");
        } catch (Exception e) {
            logger.error("角色删除错误", e);
            throw new ScServerException("角色删除错误");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：修改角色
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/29
     */
    @PostMapping("updateRole")
    public AppResponse updateRole(@Valid RoleInfo roleinfo){
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            roleinfo.setLastModifiedBy(userId);
            return roleService.updateRole(roleinfo);
        } catch (Exception e) {
            logger.error("角色修改异常", e);
            throw new ScServerException("修改失败，请重试！");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：分页查询角色
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/29
     */
    @PostMapping("listRoles")
    public AppResponse listRoles(RoleInfo roleInfo) {
        try {
            return roleService.listRoles(roleInfo);
        } catch (Exception e) {
            logger.error("获取异常", e);
            throw new ScServerException("查询错误，请重试");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：角色管理-用户授权，获取当前页已授权用户角色
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/12/4
     */
    @PostMapping("listRoleUsersCurrentPage")
    public AppResponse listRoleUsersCurrentPage(RoleUserDTO roleUserDTO){
        try {
            return roleService.listRoleUsersCurrentPage(roleUserDTO);
        } catch (Exception e) {
            logger.error("获取异常", e);
            throw new ScServerException("查询错误，请重试");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：角色管理-用户授权
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/12/4
     */
    @PostMapping("saveRoleUsers")
    public AppResponse saveRoleUsers(RoleUserDTO roleUserDTO){
        try {
            String userId = SecurityUtils.getCurrentUserId();
            roleUserDTO.setCreateBy(userId);
            roleUserDTO.setLastModifiedBy(userId);
            return roleService.saveRoleUsers(roleUserDTO);
        } catch (Exception e) {
            logger.error("获取异常", e);
            throw new ScServerException("用户授权失败，请重试！");
        }
    }

    /**
     * 当前角色分配的菜单
     * @param request
     * @return
     */
    @PostMapping("getRoleAssginMenu")
    public AppResponse getRoleAssginMenu(HttpServletRequest request){
        try {
            // 角色编号
            String roleCode = request.getParameter("ROLE_CODE");
            List<Menu> menu = roleService.getRoleMenuBy(roleCode);
            return AppResponse.success("查询成功", menu);
        } catch (Exception e) {
            logger.error("获取异常", e);
            throw new ScServerException("查询错误，请重试");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：角色管理-菜单授权，获取已授权的角色按钮
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/12/3
     */
    @PostMapping("listRoleMenuBtns")
    public AppResponse listRoleMenuBtns(MenuBtn menuBtn){
        try {
            Map<String,Object> map = roleService.listRoleMenuBtns(menuBtn);
            return AppResponse.success("查询成功", map);
        } catch (Exception e) {
            logger.error("获取异常", e);
            throw new ScServerException("查询错误，请重试");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：角色管理-菜单授权
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/12/4
     */
    @PostMapping("saveRoleMenuBtn")
    public AppResponse saveRoleMenuBtn(RoleMenu roleMenu){
        try {
            String userId = SecurityUtils.getCurrentUserId();
            roleMenu.setCreateBy(userId);
            roleMenu.setLastModifiedBy(userId);
            return roleService.saveRoleMenuBtn(roleMenu);

        } catch (Exception e) {
            logger.error("获取异常", e);
            return AppResponse.notFound("设置失败");
        }
    }

    /**
     * 根据角色代码 获取菜单按钮
     * @param request
     * @return
     */
    @PostMapping("getRoleMenuButton")
    public AppResponse getRoleMenuButton(HttpServletRequest request){
        try {
            // 当前用户账号
            String userAcct = request.getParameter("USER_NAME");
            // 菜单代码
            String menuCode = request.getParameter("MENU_CODE");
            // 按钮代码
            String buttonCode = request.getParameter("BUTTON_CODE");
            MenuBtn btn = new MenuBtn();
            btn.setMenuCode(menuCode);
            btn.setButtonCode(buttonCode);
            btn.setUserAcct(userAcct);
            return AppResponse.success("查询成功", roleService.getMenuRoleBtn(btn));
        }catch (Exception e){
            logger.error("获取菜单按钮失败", e);
            throw new ScServerException("获取菜单按钮失败，请重试");
        }
    }
}
