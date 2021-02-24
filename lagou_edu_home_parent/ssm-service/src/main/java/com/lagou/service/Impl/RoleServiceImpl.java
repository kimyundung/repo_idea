package com.lagou.service.Impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    // 查询所有角色+条件
    @Override
    public List<Role> findAllRole(Role role) {
        return roleMapper.findAllRole(role);
    }

    // 添加角色
    @Override
    public void saveRole(Role role) {
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("kim");
        role.setUpdatedBy("kim");
        roleMapper.saveRole(role);
    }

    // 更新角色
    @Override
    public void updateRole(Role role) {
        role.setUpdatedBy("kim1");
        role.setUpdatedTime(new Date());
        roleMapper.updateRole(role);
    }

    // 根据角色ID查询关联菜单
    @Override
    public List<Integer> findMenuByRoleId(int roleId) {
        return roleMapper.findMenuByRoleId(roleId);
    }

    // 根据角色分配菜单信息
    @Override
    public void RoleContextMenu(RoleMenuVO roleMenuVO) {
        // 1. 清空角色关联菜单数据
        // 2. 为角色分配菜单
        roleMapper.deleteRoleContextMenu(roleMenuVO.getRoleId());
        for(Integer mid: roleMenuVO.getMenuIdList()){
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setRoleId(roleMenuVO.getRoleId());
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setCreatedTime(new Date());
            role_menu_relation.setUpdatedTime(new Date());
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedBy("system");
            roleMapper.RoleContextMenu(role_menu_relation);
        }
    }

    // 删除角色
    @Override
    public void deleteRole(Integer id) {
        // 1. 清空中间表关联关系
        // 2. 删除角色
        roleMapper.deleteRoleContextMenu(id);
        roleMapper.deleteRole(id);
    }
}
