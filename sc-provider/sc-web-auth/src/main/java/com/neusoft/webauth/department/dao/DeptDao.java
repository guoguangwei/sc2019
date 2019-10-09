package com.neusoft.webauth.department.dao;

import com.neusoft.webauth.department.entity.DepartmentInfo;
import com.neusoft.webauth.department.entity.DepartmentUserDTO;
import com.neusoft.webauth.user.entity.UserSettingDTO;

import java.util.List;
/**
 * @ClassName DeptDao
 * @Description 部门管理
 * @Author zhu.qf@neusoft.com
 * @Date 2018/11/28
 */
public interface DeptDao {

    /**
     * 查询所有部门
     * @return 部门集合
     */
    List<DepartmentInfo> listDepts();

    /**
     * 新增部门
     * @param departmentInfo 部门信息
     * @return
     */
    int insertDeptInfo(DepartmentInfo departmentInfo);

    /**
     * 更新部门信息
     * @param departmentInfo 部门信息
     * @return 更新条数
     */
    int updateDeptInfo(DepartmentInfo departmentInfo);

    /**
     * 删除部门
     * @param departmentInfo 部门范围代码
     * @return 删除条数
     */
    int deleteDeptInfoByAuthcode(DepartmentInfo departmentInfo);

    /**
     * 用户管理-设置部门，获取用户已授权部门
     * @param userCode 用户代码
     * @return
     */
    List<String> listUserDepartment(String userCode);

    /**
     * 用户管理-删除-删除选中用户的部门信息
     * @param userSettingDTO 选中的用户
     * @return
     */
    int deleteUsersDept(UserSettingDTO userSettingDTO);

    /**
     * 用户管理-设置部门，保存
     * @param userSettingDTO
     * @return
     */
    int insertUserDepts(UserSettingDTO userSettingDTO);

    /**
     * 新增用户部门
     * @param departmentUserDTO 用户部门信息
     * @return
     */
    int insertUserDept(DepartmentUserDTO departmentUserDTO);

    /**
     * 获取部门范围代码
     * @param departmentInfo 部门信息
     * @return
     */
    String getDeptAuthCode(DepartmentInfo departmentInfo);

    /**
     * 查询部门及子部门下的人员数量
     * @param departmentInfo 选中的部门范围代码
     * @return 数量
     */
    int countUserByAuthCode(DepartmentInfo departmentInfo);

    /**
     * 删除用户部门
     * @param departmentInfo 选中的部门范围代码
     * @return 删除条数
     */
    int deleteUserDeptByAuthCode(DepartmentInfo departmentInfo);

    /**
     * 校验部门名称是否存在
     * @param departmentInfo 岗位信息
     * @return 条数
     */
    int countDeptName(DepartmentInfo departmentInfo);

    /**
     * 查询部门及子部门下的岗位数量
     * @param departmentInfo 选中的部门范围代码
     * @return 数量
     */
    int countDeptPosts(DepartmentInfo departmentInfo);
}
