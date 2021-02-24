package com.lagou.service;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuService {
    // 查询全部的父子菜单信息
    public List<Menu> findSubMenuList();
    // 查询所有菜单
    public List<Menu> findAllMenu();
    // 根据id查询菜单
    public Menu findMenuById(Integer id);
    // 添加菜单
    public void saveMenu(Menu menu);
    // 更新菜单
    public void updateMenu(Menu menu);
}
