package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;

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
}
