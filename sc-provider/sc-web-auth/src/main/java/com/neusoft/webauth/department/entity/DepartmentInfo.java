package com.neusoft.webauth.department.entity;

import java.util.Date;

/**
 * @ClassName DepartmentInfo
 * @Description 部门管理 - 部门实体类
 * @Author zhu.qf@neusoft.com
 * @Date 2018/11/28
 */
public class DepartmentInfo {
    /**
     * 部门代码
     */
    private String deptCode;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 部门名称简（页面默认=名称，但可以自己录）
     */
    private String deptNameJ;
    /**
     * 上级部门代码
     */
    private String parentDeptCode;
    /**
     * 部门范围代码
     */
    private String authCode;
    /**
     * 备注
     */
    private String remark;
    /**
     * 作废标记（1作废，0未作废）
     */
    private int isDeleted;
    /**
     * UUID
     */
    private String id;
    /**
     * 序号（从0开始）
     */
    private int sortNo;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 创建者(用户代码)
     */
    private String createBy;
    /**
     * 更新时间
     */
    private Date gmtModified;
    /**
     * 更新者(用户代码)
     */
    private String lastModifiedBy;

    /**
     * 上级部门职能范围代码
     */
    private String parentAuthCode;

    /**
     * 版本号
     */
    private int version;

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptNameJ() {
        return deptNameJ;
    }

    public void setDeptNameJ(String deptNameJ) {
        this.deptNameJ = deptNameJ;
    }

    public String getParentDeptCode() {
        return parentDeptCode;
    }

    public void setParentDeptCode(String parentDeptCode) {
        this.parentDeptCode = parentDeptCode;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getParentAuthCode() {
        return parentAuthCode;
    }

    public void setParentAuthCode(String parentAuthCode) {
        this.parentAuthCode = parentAuthCode;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
