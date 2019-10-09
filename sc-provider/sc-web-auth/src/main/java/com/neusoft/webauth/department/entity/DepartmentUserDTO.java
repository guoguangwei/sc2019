package com.neusoft.webauth.department.entity;

import java.util.List;

/**
 * @ClassName DepartmentUserDTO
 * @Description 用户部门信息
 * @Author zhu.qf@neusoft.com
 * @Date 2018/11/29
 */
public class DepartmentUserDTO {
    private String userCode;
    private String deptCode;
    private List<String> deptList;
    private String createBy;
    private String id;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public List<String> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<String> deptList) {
        this.deptList = deptList;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
