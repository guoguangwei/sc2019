package com.neusoft.webauth.role.entity;

import java.util.List;

/**
 * @ClassName RoleUserDTO
 * @Description 角色管理-用户授权
 * @Author zhu.qf@neusoft.com
 * @Date 2018/12/4
 */
public class RoleUserDTO {
    /**
     * 角色代码
     */
    private String roleCode;
    /**
     * 勾选的用户代码
     */
    private List<String> userCodesChecked;
    /**
     * 当前页的用户代码
     */
    private List<String> userCodesPage;

    private String createBy;
    private String lastModifiedBy;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public List<String> getUserCodesChecked() {
        return userCodesChecked;
    }

    public void setUserCodesChecked(List<String> userCodesChecked) {
        this.userCodesChecked = userCodesChecked;
    }

    public List<String> getUserCodesPage() {
        return userCodesPage;
    }

    public void setUserCodesPage(List<String> userCodesPage) {
        this.userCodesPage = userCodesPage;
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