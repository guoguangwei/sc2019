package com.neusoft.webauth.post.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class PostParams {
    /**
     * 岗位代码
     */
    @NotNull(message = "岗位代码不能为空")
    private String postCode;
    /**
     * 岗位名称
     */
    @NotNull(message = "岗位名称不能为空")
    private String postName;
    /**
     * 岗位名称简（页面默认=名称，但可以自己录）
     */
    private String postNameJ;
    /**
     * 部门代码
     */
    @NotNull(message = "部门代码不能为空")
    private String deptCode;
    /**
     * 作废标记（1作废，0未作废）
     */
    @NotNull(message = "作废标记不能为空")
    private int isDeleted;
    /**
     * UUID
     */
    @NotNull(message = "UUID不能为空")
    private String id;
    /**
     * 序号（从0开始）
     */
    @NotNull(message = "序号不能为空")
    private int sortNo;
    /**
     * 创建时间
     */
    @NotNull(message = "创建时间不能为空")
    private Date gmtCreate;
    /**
     * 创建者(用户代码)
     */
    @NotNull(message = "创建者不能为空")
    private String createBy;
    /**
     * 更新时间
     */
    @NotNull(message = "更新时间不能为空")
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
}
