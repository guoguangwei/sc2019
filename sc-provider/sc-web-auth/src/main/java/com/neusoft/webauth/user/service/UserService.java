package com.neusoft.webauth.user.service;

import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
//import com.neusoft.security.client.aspectlog.config.SystemLog;
import com.neusoft.util.StringUtil;
import com.neusoft.util.UUIDUtils;
import com.neusoft.webauth.department.dao.DeptDao;
import com.neusoft.webauth.department.entity.DepartmentUserDTO;
import com.neusoft.webauth.post.dao.PostDao;
import com.neusoft.webauth.post.entity.PostInfo;
import com.neusoft.webauth.role.dao.RoleDao;
import com.neusoft.webauth.role.entity.RoleInfo;
import com.neusoft.webauth.user.dao.UserDao;
import com.neusoft.webauth.user.entity.UserInfo;
import com.neusoft.webauth.user.entity.UserSettingDTO;
import com.neusoft.webauth.utils.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**
 * @ClassName UserService
 * @Description 用户管理
 * @Author zhu.qf@neusoft.com
 * @Date 2018/11/28
 */
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private DeptDao deptDao;

    @Resource
    private PostDao postDao;

    /**
     * 部门：南京软件研发中心
     * 功能：新增用户
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/8/3
     */

    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveUser(UserInfo userInfo) {

        // 校验账号是否存在
        int countUserAcct = userDao.countUserAcct(userInfo);
        if(0 != countUserAcct) {
            return AppResponse.bizError("用户账号已存在，请重新输入！");
        }
        // 密码加密 默认为123456
        String pwd = PasswordUtils.generatePassword("123456");
        userInfo.setUserCode(StringUtil.getCommonCode(2));
        userInfo.setUserPwd(pwd);
        userInfo.setIsDeleted(0);
        userInfo.setId(UUIDUtils.getUUID());
        // 新增用户
        int count = userDao.saveUser(userInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        // 新增用户部门
        //this.insertUserDept(userInfo);
        return AppResponse.success("新增成功！");
    }

    /**
     * 部门：南京软件研发中心
     * 功能：新增用户部门
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/8/3
     */
    private void insertUserDept(UserInfo userInfo) {
        DepartmentUserDTO departmentUserDTO = new DepartmentUserDTO();
        departmentUserDTO.setCreateBy(userInfo.getCreateBy());
        departmentUserDTO.setDeptCode(userInfo.getDeptCode());
        departmentUserDTO.setUserCode(userInfo.getUserCode());
        departmentUserDTO.setId(UUIDUtils.getUUID());
        deptDao.insertUserDept(departmentUserDTO);
    }


    /**
     * 通过用户代码查找用户
     *
     * @param userCode 用户代码
     * @return 用户信息
     */
    public UserInfo getUserById(String userCode) {
        return userDao.getUserById(userCode);
    }


    /**
     * 部门：南京软件研发中心
     * 功能：获取用户列表
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/29
     */
    //@SystemLog(operation = "获取用户列表。。。。。")
    public AppResponse listUsers(UserInfo userInfo) {
        List<UserInfo> userInfoList = userDao.listUsersByPage(userInfo);

        return AppResponse.success("查询成功！", getPageInfo(userInfoList));
    }

    /**
     * 部门：南京软件研发中心
     * 功能：修改用户信息
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/8/3
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateUser(UserInfo userInfo) {
        AppResponse appResponse = AppResponse.success("修改成功");
        // 校验账号是否存在
        int countUserAcct = userDao.countUserAcct(userInfo);
        if(0 != countUserAcct) {
            return AppResponse.bizError("用户账号已存在，请重新输入！");
        }
        // 修改用户信息
        int count = userDao.updateUser(userInfo);
        if (0 == count) {
            appResponse = AppResponse.versionError("数据有变化，请刷新！");
            return appResponse;
        }
        // 新增用部门
        return appResponse;
    }

    /**
     * 部门：南京软件研发中心
     * 功能：删除用户信息
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/30
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteUser(UserSettingDTO userSettingDTO) {
        AppResponse appResponse = AppResponse.success("删除成功！");
        // 删除用户岗位
        //postDao.deleteUsersPost(userSettingDTO);
        // 删除用户部门
        //deptDao.deleteUsersDept(userSettingDTO);
        // 删除用户角色
        roleDao.deleteUsersRole(userSettingDTO);
        // 删除用户
        int count = userDao.deleteUser(userSettingDTO);
        if(0 == count) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 回滚
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 部门：南京软件研发中心
     * 功能：修改密码
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/30
     */
    public AppResponse updatePwd(UserInfo userInfo) {
        AppResponse appResponse = AppResponse.success("修改密码成功！");
        // 需要校验原密码是否正确
        if(null != userInfo.getUserPwd() && !"".equals(userInfo.getUserPwd())) {
            String oldPwd = PasswordUtils.generatePassword(userInfo.getUserPwd());
            // 获取用户信息
            UserInfo userDetail = userDao.getUserById(userInfo.getUserCode());

            if(null == userDetail) {
                return AppResponse.bizError("用户不存在或已被删除！");
            } else {
                if(!oldPwd.equals(userDetail.getUserPwd())) {
                    return AppResponse.bizError("原密码不匹配，请重新输入！");
                }
            }
        }
        // 修改密码
        userInfo.setNewPwd(PasswordUtils.generatePassword(userInfo.getNewPwd()));
        int count = userDao.updateUserPwd(userInfo);
        if(0 == count) {
            appResponse = AppResponse.bizError("修改密码失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 展示用户角色信息
     * @param userCode 用户代码
     * @return
     */
    public Map<String, Object> showUserRole(String userCode) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //获取所有角色信息
        resultMap.put("listAllRoles",roleDao.listRolesByPage(null));
        //获取当前用户角色信息
        resultMap.put("userOwnRoles",roleDao.listUserRoles(userCode));
        return resultMap;
    }

    /**
     * 部门：南京软件研发中心
     * 功能：用户管理-设置岗位，获取岗位信息
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/12/3
     */
    public AppResponse listPostsByUser(UserSettingDTO userSettingDTO) {
        Map<String, Object> map = new HashMap<>();

        // 查询用户授权部门下的全部岗位
        List<PostInfo> postInfoList = postDao.listPostByUserDeptByPage(userSettingDTO);
        PageInfo pageInfo = (PageInfo) getPageInfo(postInfoList);
        map.put("data", pageInfo);

        if (0 != pageInfo.getList().size()) {
            // 查询用户已授权的岗位
            Map<String, Object> params = new HashMap<>();
            params.put("postList", pageInfo.getList());
            params.put("userCode", userSettingDTO.getUserCode());
            map.put("checked", postDao.listUserPosts(params));
        } else {
            map.put("checked", null);
        }

        return AppResponse.success("查询成功！", map);
    }

    /**
     * 部门：南京软件研发中心
     * 功能：用户管理-设置岗位，保存
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/12/3
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveUserPosts(UserSettingDTO userSettingDTO) {
        // 删除当前页设置过的岗位信息
        postDao.deleteCurPageUserPosts(userSettingDTO);
        //判断页面是否勾选岗位
        if(userSettingDTO.getPostList().size() > 0){
            // 新增用户岗位
            int count = postDao.insertUserPosts(userSettingDTO);
            if(0 == count) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 回滚
                return AppResponse.bizError("设置岗位失败，请重试！");
            }
        }
        return AppResponse.success("设置岗位成功！");
    }

    /**
     * 部门：南京软件研发中心
     * 功能：用户管理-设置部门，保存
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/12/3
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveUserDepts(UserSettingDTO userSettingDTO) {

        // 删除用户部门
        deptDao.deleteUsersDept(userSettingDTO);
        // 删除删除未选中部门的用户岗位关联关系
        postDao.deleteUserPostUncheked(userSettingDTO);
        // 新增用户部门
        int count = deptDao.insertUserDepts(userSettingDTO);
        if(0 == count) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 回滚
            return AppResponse.bizError("设置部门失败，请重试！");
        }
        return AppResponse.success("设置部门成功！");
    }

    public List<UserInfo> getPostByUser(String userCode) {
        return userDao.getPostByUser(userCode);
    }


    /**
     * 部门：南京软件研发中心
     * 功能：查询所有的角色，及用户已授权的角色
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/13 14:26
     */
    public Map<String, Object> listRolesAndChecked(String userCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 查询所有的角色
        List<RoleInfo> roleInfoList = roleDao.listRolesByPage(null);
        // 查询用户已授权的角色
        List<RoleInfo> checkedRoleList = roleDao.listUserRoles(userCode);

        map.put("all", roleInfoList);
        map.put("checked", checkedRoleList);
        return  map;
    }

    /**
     * 部门：南京软件研发中心
     * 功能：用户指派角色
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/13 16:18
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveUserRole(UserSettingDTO userSettingDTO) {
        // 删除用户角色
        roleDao.deleteUsersRole(userSettingDTO);
        // 角色授权
        int count = roleDao.insertUserRole(userSettingDTO);
        if(0 == count) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 回滚
            return AppResponse.bizError("指派角色失败，请重试！");
        }
        return AppResponse.success("指派角色成功！");
    }

}
