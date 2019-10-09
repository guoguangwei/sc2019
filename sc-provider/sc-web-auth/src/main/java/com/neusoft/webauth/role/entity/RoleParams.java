package com.neusoft.webauth.role.entity;


import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * <p>角色查询数据模型</p>
 * <p>创建日期：2018-03-05</p>
 *
 * @author 印政权  yin,zhq@neusoft.com
 */
public class RoleParams {

    /**
     * 角色代码
     */
    @NotNull(message = "角色代码不能为空")
    private String roleCode;

    /**
     *角色名称
     */
    @NotNull(message = "角色名称不能为空")
    private String roleName;

    /**
     * 备注
     */
    @NotNull(message = "备注不能为空")
    private String remark;

    /**
     * 作废标记 作废标记 0为存在，1为作废
     */
    private int isDeleted;

    /**
     * UUID
     */
    private String id;

    /**
     * 序号
     */
    private int sortNo;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 创建者
     */
    @NotNull(message = "创建人不能为空")
    private String createBy;

    /**
     * 更新时间
     */
    private Date gmtModified;

    /**

     * 更新者
     */
    private String lastModifiedBy;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
}
