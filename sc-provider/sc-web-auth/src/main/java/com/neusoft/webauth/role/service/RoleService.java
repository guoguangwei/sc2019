package com.neusoft.webauth.role.service;

import com.neusoft.core.page.PageUtils;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.neusoft.util.UUIDUtils;
import com.neusoft.webauth.menu.dao.MenuBtnDao;
import com.neusoft.webauth.menu.dao.MenuDao;
import com.neusoft.webauth.menu.entity.Menu;
import com.neusoft.webauth.menu.entity.MenuBtn;
import com.neusoft.webauth.role.dao.RoleDao;
import com.neusoft.webauth.role.entity.RoleInfo;
import com.neusoft.webauth.role.entity.RoleMenu;
import com.neusoft.webauth.role.entity.RoleUserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RoleService
 * @Description 角色管理
 * @Author zhu.qf@neusoft.com
 * @Date 2018/11/28
 */
@Service
public class RoleService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private MenuDao menuDao;

    @Resource
    private MenuBtnDao menuBtnDao;

    /**
     * 部门：南京软件研发中心
     * 功能：新增角色
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/29
     */
    public AppResponse saveRole(RoleInfo roleinfo) {
        AppResponse appResponse = AppResponse.success("新增成功！");
        roleinfo.setId(UUIDUtils.getUUID());
        roleinfo.setRoleCode(StringUtil.getCommonCode(2));
        int count = roleDao.saveRole(roleinfo);
        if(0 == count) {
            appResponse = AppResponse.bizError("新增失败！");
        }
        return appResponse;
    }

    /**
     * 部门：南京软件研发中心
     * 功能：删除角色
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/9/3
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteRole(String roles, String userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleCodes",roles.split(","));
        map.put("updatedBy",userId);

        // 删除用户角色
        roleDao.deleteRolesUser(map);

        // 删除角色菜单
        roleDao.deleteRoleMenuBy(map);

        // 删除角色菜单按钮
        MenuBtn menuBtn = new MenuBtn();
        menuBtn.setRoleCodes(roles.split(","));
        menuBtnDao.deleteRoleMenuBtnBy(menuBtn);

        return roleDao.deleteRole(map);
    }

    /**
     * 部门：南京软件研发中心
     * 功能：修改角色
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/29
     */
    public AppResponse updateRole(RoleInfo roleinfo) {
        AppResponse appResponse = AppResponse.success("修改成功！");
        // 修改角色
        int count = roleDao.updateRole(roleinfo);
        if(0 == count) {
            appResponse = AppResponse.bizError("修改失败！");
        }
        return appResponse;
    }

    /**
     * 部门：南京软件研发中心
     * 功能：分页查询角色
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/29
     */
    public AppResponse listRoles(RoleInfo roleInfo) {
        List<RoleInfo> roleList = roleDao.listRolesByPage(roleInfo);
//        if (CollectionUtils.isEmpty(roleList)) {
//            return AppResponse.notFound("未查询到结果");
//        }
        return AppResponse.success("查询成功", PageUtils.getPageInfo(roleList));
    }

    /**
     * 获取选中用户的角色信息
     * @param userCode
     * @return
     */
    public List<RoleInfo> getUserRole(String userCode) {
        return roleDao.listUserRoles(userCode);
    }

    /**
     * 设置默认角色
     * @param userCode
     * @param roleCode
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int setDefaultRole(String userCode, String roleCode, String userId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("USER_CODE", userCode);
        params.put("ROLE_CODE", roleCode);
        params.put("UPDATED_BY", userId);
        // 清除原有的默认角色
        roleDao.clearUserDefaultRole(params);
        // 设置新的默认角色
        return  roleDao.setDefaultRole(params);
    }

    /**
     * 部门：南京软件研发中心
     * 功能：角色管理-用户授权，获取当前页已授权用户角色
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/12/4
     */
    public AppResponse listRoleUsersCurrentPage(RoleUserDTO roleUserDTO) {
        return AppResponse.success("查询成功", roleDao.listRoleUsers(roleUserDTO));
    }

    /**
     * 部门：南京软件研发中心
     * 功能：角色管理-用户授权
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/12/4
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveRoleUsers(RoleUserDTO roleUserDTO) {
        // 删除当前页的用户角色
        roleDao.deleteRoleUsers(roleUserDTO);
        if(roleUserDTO.getUserCodesChecked().size() > 0){
            // 新增用户角色
            int count = roleDao.insertRoleUsers(roleUserDTO);
            if(0 == count) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 回滚
                return AppResponse.bizError("用户授权失败，请重试！");
            }
        }
        return AppResponse.success();
    }

    /**
     * 根据角色代码获取菜单
     * @param role_code
     * @return
     */
    public List<Menu> getRoleMenuBy(String role_code) {
        return menuDao.listRoleMenusBy(role_code);
    }

    /**
     * 部门：南京软件研发中心
     * 功能：角色管理-菜单授权，获取已授权的角色按钮
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/12/3
     */
    public Map<String,Object> listRoleMenuBtns(MenuBtn menuBtn) {

        List<MenuBtn> btnListChecked= menuBtnDao.listRoleMenuBtns(menuBtn);
        Map<String,Object> mapList=new HashMap<String,Object>();
        mapList.put("checked",btnListChecked);
        return mapList;
    }


    /**
     * 部门：南京软件研发中心
     * 功能：角色管理-角色菜单授权
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/8/16
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveRoleMenuBtn(RoleMenu roleMenu) {
        // 删除角色菜单
        roleDao.deleteRoleMenu(roleMenu.getRoleCode());
        // 删除菜单按钮
        Map<String, Object> btn = new HashMap<String, Object>();
        btn.put("roleCode", roleMenu.getRoleCode());
        menuBtnDao.deleteRoleMenuBtns(btn);
        //判断页面是否选择了菜单
        if(roleMenu.getMenuCodes().size() > 0){
            // 新增角色菜单
            if(!StringUtils.isEmpty(roleMenu.getMenuCodes())) {
                int count = roleDao.insertRoleMenu(roleMenu);
                if (0 == count) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 回滚
                    return AppResponse.bizError("菜单授权失败，请重试！");
                }
            }
        }
        // 新增角色菜单按钮
        if(null != roleMenu.getMenuBtn() && 0 != roleMenu.getMenuBtn().size()) {
            List<MenuBtn> list = new ArrayList<MenuBtn>();
            for (int i = 0; i < roleMenu.getMenuBtn().size(); i++) {
                MenuBtn obj = new MenuBtn();
                String[] menu_btn = roleMenu.getMenuBtn().get(i).split(":");
                obj.setRoleCode(roleMenu.getRoleCode());
                obj.setMenuCode(menu_btn[0]);
                obj.setButtonCode(menu_btn[1]);
                obj.setCreateBy(roleMenu.getCreateBy());
                list.add(obj);
            }
            // 新增角色菜单按钮
            int count = menuBtnDao.insertRoleMenuBtn(list);
            if (0 == count) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 回滚
                return AppResponse.bizError("菜单授权失败，请重试！");
            }
        }
        return AppResponse.success();
    }

    public List<MenuBtn> getMenuRoleBtn(MenuBtn btn) {
        return roleDao.getMenuRoleBtn(btn);
    }
}
