package com.neusoft.webauth.role.dao;


import com.neusoft.webauth.menu.entity.MenuBtn;
import com.neusoft.webauth.role.entity.RoleInfo;
import com.neusoft.webauth.role.entity.RoleMenu;
import com.neusoft.webauth.role.entity.RoleUserDTO;
import com.neusoft.webauth.user.entity.UserSettingDTO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RoleDao
 * @Description 角色管理
 * @Author zhu.qf@neusoft.com
 * @Date 2018/11/28
 */
public interface RoleDao {
    /**
     * 分页查询角色
     * @param roleInfo 角色信息
     * @return
     */
    List<RoleInfo> listRolesByPage(RoleInfo roleInfo);

    /**
     * 新增角色
     * @param roleInfo 角色信息
     * @return
     */
    int saveRole(RoleInfo roleInfo);

    /**
     * 修改角色
     * @param roleInfo 角色信息
     * @return
     */
    int updateRole(RoleInfo roleInfo);

    /**
     * 删除角色
     * @param map 角色信息
     * @return
     */
    int deleteRole(Map<String, Object> map);

    /**
     * 用户管理-角色授权，查询用户已授权角色信息
     * @param userCode 用户代码
     * @return
     */
    List<RoleInfo> listUserRoles(String userCode);

    /**
     * 用户管理-删除,删除选中用户关联的角色信息/用户管理-角色授权，删除用户角色关联关系
     * @param userSettingDTO
     * @return
     */
    int deleteUsersRole(UserSettingDTO userSettingDTO);

    /**
     * 用户管理-角色授权，保存
     * @param userSettingDTO 授权信息
     * @return
     */
    int insertUserRole(UserSettingDTO userSettingDTO);

    void clearUserDefaultRole(Map<String, String> params);

    int setDefaultRole(Map<String, String> params);

    /**
     * 角色管理-用户授权，获取当前页已授权用户角色
     * @param roleUserDTO 用户角色信息
     * @return
     */
    List<String> listRoleUsers(RoleUserDTO roleUserDTO);

    /**
     * 角色管理-用户授权，删除当前页绑定的用户角色关系
     * @param roleUserDTO 用户角色信息
     * @return
     */
    int deleteRoleUsers(RoleUserDTO roleUserDTO);

    /**
     * 角色管理-用户授权，新增角色用户关系
     * @param roleUserDTO 用户角色信息
     * @return
     */
    int insertRoleUsers(RoleUserDTO roleUserDTO);

    /**
     * 角色管理-删除角色关联的用户信息
     * @param params 选中的角色信息
     * @return
     */
    int deleteRolesUser(Map<String, Object> params);

    void deleteRoleMenu(String role_code);

    /**
     * 角色管理-菜单授权，新增角色菜单
     * @param roleMenu 角色菜单信息
     * @return
     */
    int insertRoleMenu(RoleMenu roleMenu);

    List<MenuBtn> getMenuRoleBtn(MenuBtn btn);

    int deleteRoleMenuBy(Map<String, Object> params);
}
