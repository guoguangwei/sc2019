package com.xzsd.pc.storesInfo.entity;

import java.util.Date;

public class County {

    //县/区代码
    private String countyCode;
    //县/区名称
    private String countyName;
    //省代码
    private String provinceCode;
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
    //邮编
    private String postcode;
    //校边店起始编码
    private String storeNoFrom;

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
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

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStoreNoFrom() {
        return storeNoFrom;
    }

    public void setStoreNoFrom(String storeNoFrom) {
        this.storeNoFrom = storeNoFrom;
    }
}
