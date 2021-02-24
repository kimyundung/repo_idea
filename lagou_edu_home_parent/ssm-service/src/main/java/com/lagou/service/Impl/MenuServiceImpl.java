package com.lagou.service.Impl;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    // 查询全部的父子菜单信息
    @Override
    public List<Menu> findSubMenuList() {
        return menuMapper.findSubMenuListByPid(-1);
    }

    // 查询所有菜单
    @Override
    public List<Menu> findAllMenu() {
        return menuMapper.findAllMenu();
    }

    // 根据id查询菜单
    @Override
    public Menu findMenuById(Integer id) {
        return menuMapper.findMenuById(id);
    }

    // 添加菜单
    @Override
    public void saveMenu(Menu menu) {
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menuMapper.saveMenu(menu);
    }

    // 更新菜单
    @Override
    public void updateMenu(Menu menu) {
        menu.setUpdatedTime(new Date());
        menu.setUpdatedBy("system");
        menuMapper.updateMenu(menu);
    }

}
