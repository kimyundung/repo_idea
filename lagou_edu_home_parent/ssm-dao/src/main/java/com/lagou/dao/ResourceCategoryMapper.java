package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.Role_resource_relation;

import java.util.List;

/**
 * 资源分类关联mapper接口
 */
public interface ResourceCategoryMapper {
    // 查询资源分类
    public List<ResourceCategory> findAllResourceCategory();
    // 添加资源分类
    public void saveResourceCategory(ResourceCategory resourceCategory);
    // 更新资源分类
    public void updateResourceCategory(ResourceCategory resourceCategory);
    // 根据id删除资源分类
    public void deleteResourceCategoryById(Integer id);
    // 根据角色id查询关联资源分类
    public List<ResourceCategory> findResourceCategoryIdsByRoleId(Integer roleId);
    // 根据获取到了资源分类, 查询资源
    public List<Resource> findResourceByResourceCategory(Integer roleId, Integer categoryId);
    // 根据角色id删除角色与资源中间表
    public void deleteRoleResourceRelationByRoleId(Integer roleId);
    // 根据角色id与资源id集合创建角色与资源关系
    public void RoleResourceRelation(Role_resource_relation role_resource_relation);
}
