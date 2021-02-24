package com.lagou.service.Impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.ResourceCategory;
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
}
