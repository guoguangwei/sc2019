package com.xzsd.pc.log.entity;

import java.util.Date;


/**   
 *  
 * @Description:  日志信息
 * @Author:       ZWL   
 * @CreateDate:   2019年5月19日
 * @Version:      V1.0
 *    
 */
public class LogDO {
	private String id;
	private String userNo;
	private String ip;
	private String funParam;
	private String funDesc;
	private String funName;
	private Integer opType;
	private String detail;
	private Integer isDeleted;
	private Integer sortNo;
	private Date gmtCreate;
	private String createBy;
	private Date gmtModified;
	private String lastModifiedBy;
	private Integer version;
	private String status;
	private String orderNo;
	private String role;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

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

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "LogDO{" +
				"id='" + id + '\'' +
				", userNo='" + userNo + '\'' +
				", ip='" + ip + '\'' +
				", funParam='" + funParam + '\'' +
				", funDesc='" + funDesc + '\'' +
				", funName='" + funName + '\'' +
				", opType=" + opType +
				", detail='" + detail + '\'' +
				", isDeleted=" + isDeleted +
				", sortNo=" + sortNo +
				", gmtCreate=" + gmtCreate +
				", createBy='" + createBy + '\'' +
				", gmtModified=" + gmtModified +
				", lastModifiedBy='" + lastModifiedBy + '\'' +
				", version=" + version +
				'}';
	}
}
