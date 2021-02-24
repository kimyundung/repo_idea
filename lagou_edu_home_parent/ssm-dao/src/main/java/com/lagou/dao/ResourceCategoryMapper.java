package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;

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
}
