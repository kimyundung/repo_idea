package com.lagou.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    // 分页查询用户
    @Override
    public PageInfo<User> findAllUserByPage(UserVO userVO) {
        PageHelper.startPage(userVO.getCurrentPage(),userVO.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVO);
        PageInfo<User> userPageInfo = new PageInfo<>(allUserByPage);
        return userPageInfo;
    }

    // 修改用户状态
    @Override
    public void updateUserStatus(User user) {
        user.setUpdate_time(new Date());
        userMapper.updateUserStatus(user);
    }

    // 用户登陆
    @Override
    public User login(User user) throws Exception {
        User user1 = userMapper.login(user);
        if(user1!=null && Md5.verify(user.getPassword(),"lagou",user1.getPassword())){
            return user1;
        }
        return null;
    }

    // 根据用户id查询关联角色信息
    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> roleList = userMapper.findUserRelationRoleById(id);
        return roleList;
    }

    // 用户关联角色
    @Override
    public void UserContextRole(UserVO userVO) {
        // 1. 根据用户id清空中间表
        // 2. 重新建立关联关系
        userMapper.deleteUserContextRole(userVO.getUserId());
        for(Integer rId:userVO.getRoleIdList()){
            // 封装数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVO.getUserId());
            user_role_relation.setRoleId(rId);
            Date date = new Date();
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");
            userMapper.UserContextRole(user_role_relation);
        }
    }

    // 获取用户权限信息
    @Override
    public ResponseResult getUserPermissions(Integer userId) {
        // 1.获取当前用户拥有的角色 + 获取角色id保存到list集合中
        // 2.根据角色id查询父菜单 + 父菜单关联子菜单
        // 3.获取思源信息
        // 4.封装数据并返回

        List<Role> roleList = userMapper.findUserRelationRoleById(userId);
        List<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }

        List<Menu> menuList = new ArrayList<>();
        List<Resource> resourceList = new ArrayList<>();
        if(roleIds.size()>0){
            menuList = userMapper.findParentMenuByRoleId(roleIds);
            for (Menu menu : menuList) {
                List<Menu> subMenuList = userMapper.findSubMenuByPid(menu.getParentId());
                menu.setSubMenuList(subMenuList);
            }
            resourceList = userMapper.findResourceByRoleId(roleIds);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("menuList",menuList);
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"获取用户权限信息成功",map);
    }
}
