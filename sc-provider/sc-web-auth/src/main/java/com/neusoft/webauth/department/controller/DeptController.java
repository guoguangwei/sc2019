package com.neusoft.webauth.department.controller;

import com.neusoft.core.exception.ScServerException;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.webauth.base.constant.GlobalConstant;
import com.neusoft.webauth.base.entity.Tree;
import com.neusoft.webauth.department.entity.DepartmentInfo;
import com.neusoft.webauth.department.service.DeptService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api("部门管理")
@RestController
@RequestMapping("/dept")
@Validated
public class DeptController {

    private static final Logger logger = LoggerFactory.getLogger(DeptController.class);

    @Resource
    private DeptService deptService;

    /**
     * 部门：南京软件研发中心
     * 功能：查询部门树
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/27
     */
    @ApiOperation("部门树")
    @PostMapping("listDepts")
    public AppResponse listDepts() {
        Tree treeMenus = null;
        try {
            treeMenus = deptService.listDepts(GlobalConstant.DEPARTMENT_ROOT);
        } catch (Exception e) {
            logger.error("获取异常", e);
            throw new ScServerException("查询错误，请重试");
        }

        return AppResponse.success("查询成功", treeMenus.getChildren());
    }

    /**
     * 部门：南京软件研发中心
     * 功能：新建部门
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/8/2
     */
    @ApiOperation("新建部门")
    @PostMapping("insertDept")
    public AppResponse insertDept(@Valid DepartmentInfo departmentinfo) {
        try {
            String userId = SecurityUtils.getCurrentUserId();
            departmentinfo.setCreateBy(userId);
             return deptService.insertDeptInfo(departmentinfo);
        } catch (Exception e) {
            logger.error("部门新增异常", e);
            throw new ScServerException("新增失败，请重试");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：修改部门
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/8/3
     */
    @PostMapping("updateDept")
    public AppResponse updateDept(@Valid DepartmentInfo departmentinfo) {
        String userId = SecurityUtils.getCurrentUserId();
        departmentinfo.setLastModifiedBy(userId);
        try {
            return deptService.updateDeptInfo(departmentinfo);
        } catch (Exception e) {
            logger.error("部门修改异常", e);
            throw new ScServerException("修改失败，请重试");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：删除部门
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/8/3
     */
    @PostMapping("deleteDept")
    public AppResponse deleteDept(@Valid DepartmentInfo departmentinfo) {
        try {
            String userId = SecurityUtils.getCurrentUserId();
            departmentinfo.setLastModifiedBy(userId);

            return deptService.deleteDeptInfo(departmentinfo);
        } catch (Exception e) {
            logger.error("部门删除异常", e);
            throw new ScServerException("删除失败，请重试");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：用户管理-设置部门，获取用户已授权部门
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/30
     */
    @PostMapping("listUserDepts")
    public AppResponse listUserDepts(@RequestParam(name = "userCode") String userCode) {
        try {
            return AppResponse.success("查询成功", deptService.listUserDepartment(userCode));
        } catch (Exception e) {
            logger.error("获取部门异常", e);
            throw new ScServerException("获取部门失败，请重试");
        }
    }
}
