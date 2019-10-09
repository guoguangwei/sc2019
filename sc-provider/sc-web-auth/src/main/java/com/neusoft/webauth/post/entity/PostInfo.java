package com.neusoft.webauth.post.entity;

import java.util.Date;

public class PostInfo {
    /**
     * 岗位代码
     */
    private String postCode;
    /**
     * 岗位名称
     */
    private String postName;
    /**
     * 岗位名称简（页面默认=名称，但可以自己录）
     */
    private String postNameJ;
    /**
     * 部门代码
     */
    private String deptCode;
    /**
     * 作废标记（1作废，0未作废）
     */
    private int isDeleted;
    /**
     * iD
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
     * 部门名称
     */
    private String deptName;
    /**
     * 查询部门表的Code
     */
    private String oldDeptCode;
    /**
     * 版本号
     */
    private String version;

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostNameJ() {
        return postNameJ;
    }

    public void setPostNameJ(String postNameJ) {
        this.postNameJ = postNameJ;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOldDeptCode() {
        return oldDeptCode;
    }

    public void setOldDeptCode(String oldDeptCode) {
        this.oldDeptCode = oldDeptCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
