package com.neusoft.webauth.department.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.neusoft.util.UUIDUtils;
import com.neusoft.webauth.base.constant.GlobalConstant;
import com.neusoft.webauth.base.entity.Tree;
import com.neusoft.webauth.department.dao.DeptDao;
import com.neusoft.webauth.department.entity.DepartmentInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Service
public class DeptService {

    @Resource
    private DeptDao deptDao;

    /**
     * 部门：南京软件研发中心
     * 功能：获取部门树
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/8/3
     */
    public Tree listDepts(String rootid) {
        List<DepartmentInfo> allDepts = deptDao.listDepts();
        Tree rootTree = new Tree();
        initTree(rootTree,allDepts,rootid);
        return rootTree;
    }

    /**
     * 部门：南京软件研发中心
     * 功能：格式化树结构
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/8/3
     */
    private void initTree(Tree rootTree, List<DepartmentInfo> allDepts, String selfCode){
        Iterator<DepartmentInfo> departIterator = allDepts.iterator();
        while(departIterator.hasNext()){
            DepartmentInfo temp = departIterator.next();
            //初始化自身节点
            if(temp.getDeptCode().equals(selfCode)){
                deptToTree(temp,rootTree);
            }else if(temp.getParentDeptCode().equals(selfCode)){
                //初始化子节点
                Tree children = new Tree();
                deptToTree(temp,children);
                rootTree.getChildren().add(children);
                //递归
                initTree(children,allDepts,temp.getDeptCode());
            }
        }
    }

    private void deptToTree(DepartmentInfo dept, Tree tree){
        tree.setId(dept.getDeptCode());
        tree.setPid(dept.getParentDeptCode());
        tree.setLabel(dept.getDeptName());
        tree.setAttributes(dept);
    }

    /**
     * 部门：南京软件研发中心
     * 功能：新增部门
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/8/3
     */
    public AppResponse insertDeptInfo(DepartmentInfo departmentInfo){
        AppResponse appResponse = AppResponse.success("新建成功！");

        // 校验部门名称是否存在
        int countName = deptDao.countDeptName(departmentInfo);
        if(0 != countName) {
            return AppResponse.bizError("部门名称已存在，请重新输入！");
        }

        departmentInfo.setDeptCode(StringUtil.getCommonCode(2));
        departmentInfo.setId(UUIDUtils.getUUID());
        //如果是根节点，部门职能代码=部门代码，否则部门职能范围代码=上级部门职能范围代码+3位
        if (null == departmentInfo.getParentDeptCode() || "".equals(departmentInfo.getParentDeptCode())) {
            departmentInfo.setParentDeptCode(GlobalConstant.DEPARTMENT_ROOT);
            departmentInfo.setAuthCode(departmentInfo.getDeptCode());
        } else {
            departmentInfo.setAuthCode(deptDao.getDeptAuthCode(departmentInfo));
        }
        // 新增部门
        int count = deptDao.insertDeptInfo(departmentInfo);
        // 新增失败
        if (0 == count) {
            appResponse = AppResponse.bizError("新增部门失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 部门：南京软件研发中心
     * 功能：修改部门信息
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/8/3
     */
    public AppResponse updateDeptInfo(DepartmentInfo departmentInfo){
        AppResponse appResponse = AppResponse.success("修改成功");
        // 校验部门名称是否存在
        int countName = deptDao.countDeptName(departmentInfo);
        if(0 != countName) {
            return AppResponse.bizError("部门名称已存在，请重新输入！");
        }
        // 修改部门信息
        int count = deptDao.updateDeptInfo(departmentInfo);
        if (0 == count) {
            appResponse = AppResponse.versionError("数据有变化，请刷新！");
        }
        return appResponse;
    }

    /**
     * 部门：南京软件研发中心
     * 功能：删除部门
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/8/3
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteDeptInfo(DepartmentInfo departmentInfo){
        AppResponse appResponse = AppResponse.success("删除成功");
        // 校验部门及子部门下是否存在用户，存在则不能删除
        int countUser = deptDao.countUserByAuthCode(departmentInfo);
        if(0 != countUser) {
            return AppResponse.bizError("部门或者子部门下存在用户，不能删除！");
        }
        // 校验部门下是否存在岗位，存在则不能删除
        int countPost = deptDao.countDeptPosts(departmentInfo);
        if(0 != countPost) {
            return AppResponse.bizError("部门或者子部门下存在岗位，不能删除！");
        }
        // 删除部门信息
        deptDao.deleteDeptInfoByAuthcode(departmentInfo);
        // 删除用户部门
        deptDao.deleteUserDeptByAuthCode(departmentInfo);
        return appResponse;
    }

    /**
     * 部门：南京软件研发中心
     * 功能：用户管理-设置部门，获取用户已授权部门
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/30
     */
    public List<String> listUserDepartment(String userCode) {
        return deptDao.listUserDepartment(userCode);
    }
}
