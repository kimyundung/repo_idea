package com.lagou.service;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryService {
    // 查询资源分类
    public List<ResourceCategory> findAllResourceCategory();
}
