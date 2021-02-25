package com.lagou.service.Impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.RoleResourceVo;
import com.lagou.domain.Role_resource_relation;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 资源分类关联service接口实现类
 */
@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    // 查询资源分类
    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        return resourceCategoryMapper.findAllResourceCategory();
    }

    // 添加资源分类
    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {

        // 1.补全数据
        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");

        // 2.调用mapper方法
        resourceCategoryMapper.saveResourceCategory(resourceCategory);
    }

    // 更新资源分类
    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {

        // 1.补全数据
        resourceCategory.setUpdatedTime(new Date());
        resourceCategory.setUpdatedBy("system");

        // 2.调用service方法
        resourceCategoryMapper.updateResourceCategory(resourceCategory);
    }

    // 根据id删除资源分类
    @Override
    public void deleteResourceCategoryById(Integer id) {
        // 调用mapper方法
        resourceCategoryMapper.deleteResourceCategoryById(id);
    }

    // 根据角色id获取关联资源信息
    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId) {
        // test
        List<ResourceCategory> resourceCategoryList = resourceCategoryMapper.findResourceCategoryIdsByRoleId(roleId);
        for (ResourceCategory resourceCategory : resourceCategoryList) {
            List<Resource> resourceList = resourceCategoryMapper.findResourceByResourceCategory(roleId, resourceCategory.getId());
            resourceCategory.setResourceList(resourceList);
        }
        return resourceCategoryList;
    }

    // 为角色分配菜单
    @Override
    public void RoleResourceRelation(RoleResourceVo roleResourceVo) {
        // 1. 删除角色与资源中间表
        resourceCategoryMapper.deleteRoleResourceRelationByRoleId(roleResourceVo.getRoleId());
        // 2. 重新建立角色与资源关系
        List<Integer> resourceIdList = roleResourceVo.getResourceIdList();
        Date date = new Date();
        for (Integer resourceId : resourceIdList) {
            Role_resource_relation role_resource_relation = new Role_resource_relation();
            role_resource_relation.setRoleId(roleResourceVo.getRoleId());
            role_resource_relation.setResourceId(resourceId);
            role_resource_relation.setCreatedTime(date);
            role_resource_relation.setUpdatedTime(date);
            role_resource_relation.setCreatedBy("system");
            role_resource_relation.setUpdatedBy("system");
            resourceCategoryMapper.RoleResourceRelation(role_resource_relation);
        }
    }
}
