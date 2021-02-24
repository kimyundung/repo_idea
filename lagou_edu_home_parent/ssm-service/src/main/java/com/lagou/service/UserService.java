package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.*;

import java.util.List;

public interface UserService {
    // 分页查询用户
    public PageInfo<User> findAllUserByPage(UserVO userVO);
    // 修改用户状态
    public void updateUserStatus(User user);
    // 用户登陆
    public User login(User user) throws Exception;
    // 根据用户id查询关联角色信息
    public List<Role> findUserRelationRoleById(Integer id);
    // 用户关联角色
    public void UserContextRole(UserVO userVO);
    // 获取用户权限进行菜单动态展示
    public ResponseResult getUserPermissions(Integer userId);
}
