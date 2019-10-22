package com.xzsd.pc.hotGoods.entity;

import java.util.Date;

public class CateCode {

    //分类代码
    private String cateCode;
    //分类名称
    private String cateName;
    //上级分类代码
    private String cateCodeParent;
    // 层级（第一层1，后续2,3等等）
    private int level;
    //备注
    private String remark;
    //父类标记1是0否（即：该类目是否还有子分类）
    private int isParent;
    //作废标记（1作废，0未作废）
    private int isDeleted;
    //UUID
    private String id;
    //序号（从0开始）
    private int sortNo;
    //创建时间
    private Date gmtCreate;
    //创建者(登录账号)
    private String createBy;
    //更新时间
    private Date gmtModified;
    //更新者(登录账号)
    private String lastModifiedBy;
    //版本号
    private int version;

    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateCodeParent() {
        return cateCodeParent;
    }

    public void setCateCodeParent(String cateCodeParent) {
        this.cateCodeParent = cateCodeParent;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getIsParent() {
        return isParent;
    }

    public void setIsParent(int isParent) {
        this.isParent = isParent;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
