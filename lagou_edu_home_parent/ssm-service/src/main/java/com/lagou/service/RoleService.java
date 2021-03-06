package com.lagou.service;

import com.lagou.domain.*;

import java.util.List;

public interface RoleService {
    // 查询所有角色+条件
    public List<Role> findAllRole(Role role);
    // 添加角色
    public void saveRole(Role role);
    // 更新角色
    public void updateRole(Role role);
    // 根据角色ID查询关联菜单
    public List<Integer> findMenuByRoleId(int roleId);
    // 根据角色分配菜单信息
    public void RoleContextMenu(RoleMenuVO roleMenuVO);
    // 删除角色
    public void deleteRole(Integer id);

    // 根据角色id获取关联资源信息
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId);
    // 为角色分配菜单
    public void RoleResourceRelation(RoleResourceVo roleResourceVo);
}
