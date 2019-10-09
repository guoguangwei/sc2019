package com.xzsd.pc.log.entity;

public class ActionDto {
    private String id;
    private String userNo;
    private String ip;
    private String funParam;
    private String funDesc;
    private String funName;
    private Integer opType;
    private String detail;
    private String createBy;
    private String status;
    private String role;
    private String orderNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFunParam() {
        return funParam;
    }

    public void setFunParam(String funParam) {
        this.funParam = funParam;
    }

    public String getFunDesc() {
        return funDesc;
    }

    public void setFunDesc(String funDesc) {
        this.funDesc = funDesc;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
