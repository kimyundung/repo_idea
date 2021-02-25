package com.lagou.dao;


import com.lagou.domain.*;

import java.util.List;

public interface RoleMapper {
    // 查询所有角色+条件
    public List<Role> findAllRole(Role role);
    // 添加角色
    public void saveRole(Role role);
    // 更新角色
    public void updateRole(Role role);
    // 根据角色ID查询关联菜单
    public List<Integer> findMenuByRoleId(int roleId);
    // 根据roleId清空中间表的关联关系
    public void deleteRoleContextMenu(Integer rid);
    // 根据角色分配菜单信息
    public void RoleContextMenu(Role_menu_relation role_menu_relation);
    // 删除角色
    public void deleteRole(Integer id);
}
