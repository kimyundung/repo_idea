package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {
    // 分页查询用户
    public List<User> findAllUserByPage(UserVO userVO);
    // 修改用户状态
    public void updateUserStatus(User user);
    // 用户登陆 (根据用户名查询用户)
    public User login(User user);
    // 根据用户id清空中间表
    public void deleteUserContextRole(Integer userId);
    // 分配角色
    public void UserContextRole(User_Role_relation user_role_relation);
    // 1.根据用户id查询关联角色信息
    public List<Role> findUserRelationRoleById(Integer id);
    // 2.根据角色id查询角色拥有的顶级菜单 (-1)
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);
    // 3.根据pid查询子菜单信息
    public List<Menu> findSubMenuByPid(Integer pid);
    // 4.获取用户拥有的资源权限信息
    public List<Resource> findResourceByRoleId(List<Integer> ids);
    public List<Resource> findResourceByRoleId2(List<Integer> ids);
    
    public void test11();
    public void test21();
    public void test31();
    public void test41();
    public void test5();
}
