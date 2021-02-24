package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    // 查询所有菜单
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> allMenu = menuService.findAllMenu();
        return new ResponseResult(true,200,"成功查询菜单",allMenu);
    }

    // 回显菜单信息
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuById(Integer id){
        // 添加 回显不需要查询 menu信息
        List<Menu> subMenuList = menuService.findSubMenuList();
        // 封装数据
        Map<String,Object> map = new HashMap<>();

        // 根据id值判断新增还是更新, 是否为-1
        if(id == -1) {
            map.put("menuInfo",null);
            map.put("parentMenuList",subMenuList);
            return new ResponseResult(true,200,"添加回显成功",map);
        } else {
            // 修改操作 回显
            Menu menu = menuService.findMenuById(id);
            map.put("menuInfo",menu);
            map.put("parentMenuList",subMenuList);
            return new ResponseResult(true,200,"修改回显成功",map);
        }
    }

    // 添加&更新菜单
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){
        if(menu.getId()==null){
            menuService.saveMenu(menu);
            return new ResponseResult(true,200,"添加菜单成功",null);
        } else {
            menuService.updateMenu(menu);
            return new ResponseResult(true,200,"更新菜单成功",null);
        }

    }

}
