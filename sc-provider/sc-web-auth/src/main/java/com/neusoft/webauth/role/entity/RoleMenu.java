package com.neusoft.webauth.role.entity;

import com.neusoft.webauth.menu.entity.MenuBtn;

import java.util.List;

/**
 * @ClassName RoleMenu
 * @Description 角色管理
 * @Author zhu.qf@neusoft.com
 * @Date 2018/11/28
 */
public class RoleMenu {
    /**
     * 角色code
     */
    private String roleCode;
    /**
     * 勾选的菜单id集合
     */
    private List<String> menuCodes;
    /**
     * 选的按钮集合
     */
    private List<String> menuBtn;

    private String createBy;
    private String lastModifiedBy;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public List<String> getMenuCodes() {
        return menuCodes;
    }

    public void setMenuCodes(List<String> menuCodes) {
        this.menuCodes = menuCodes;
    }

    public List<String> getMenuBtn() {
        return menuBtn;
    }

    public void setMenuBtn(List<String> menuBtn) {
        this.menuBtn = menuBtn;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
