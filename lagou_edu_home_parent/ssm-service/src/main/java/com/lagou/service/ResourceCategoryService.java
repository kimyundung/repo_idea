package com.lagou.service;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.RoleResourceVo;

import java.util.List;

/**
 * 资源分类关联service接口
 */
public interface ResourceCategoryService {
    // 查询资源分类
    public List<ResourceCategory> findAllResourceCategory();
    // 添加资源分类
    public void saveResourceCategory(ResourceCategory resourceCategory);
    // 更新资源分类
    public void updateResourceCategory(ResourceCategory resourceCategory);
    // 根据id删除资源分类
    public void deleteResourceCategoryById(Integer id);
    // 根据角色id获取关联资源信息
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId);
    // 为角色分配菜单
    public void RoleResourceRelation(RoleResourceVo roleResourceVo);
}
