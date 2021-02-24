package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    // 查询所有角色+条件
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        return new ResponseResult(true,200,"成功获取角色信息",allRole);
    }

    // 添加角色
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){
        if(role.getId()==null){
            roleService.saveRole(role);
            return new ResponseResult(true,200,"添加角色成功",null);
        } else {
            roleService.updateRole(role);
            return new ResponseResult(true,200,"更新角色成功",null);
        }
    }

    // 查询全部的父子菜单信息
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> subMenuList = menuService.findSubMenuList();
        Map<String,Object> map = new HashMap<>();
        map.put("parentMenuList",subMenuList);
        return new ResponseResult(true,200,"查询所有父子菜单成功",map);
    }

    // 根据角色ID查询关联菜单
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);
        return new ResponseResult(true,200,"响应成功",menuByRoleId);
    }

    // 根据角色分配菜单信息
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVO roleMenuVO){
        roleService.RoleContextMenu(roleMenuVO);
        return new ResponseResult(true,200,"成功根据角色分配菜单信息",null);
    }

    // 删除角色
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);
        return new ResponseResult(true,200,"删除角色成功",null);
    }
}
