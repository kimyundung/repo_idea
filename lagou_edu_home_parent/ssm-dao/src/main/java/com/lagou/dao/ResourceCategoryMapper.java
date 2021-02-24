package com.lagou.dao;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {
    // 查询资源分类
    public List<ResourceCategory> findAllResourceCategory();
}
