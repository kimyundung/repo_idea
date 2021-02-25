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

    // 根据角色id查询关联资源分类
    public List<ResourceCategory> findResourceCategoryByRoleId(Integer roleId);
    // 根据获取到了资源分类和角色id, 查询资源
    public List<Resource> findResourceByRoleIdAndResourceCategoryId(Integer roleId, Integer categoryId);
    // 根据角色id删除角色与资源中间表
    public void deleteRoleResourceRelationByRoleId(Integer roleId);
    // 根据角色id与资源id创建角色与资源关系
    public void RoleResourceRelation(Role_resource_relation role_resource_relation);
}
