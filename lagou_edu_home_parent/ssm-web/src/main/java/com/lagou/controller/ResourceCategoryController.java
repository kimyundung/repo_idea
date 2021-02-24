package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 资源分类关联controller
 */
@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {
    @Autowired
    private ResourceCategoryService resourceCategoryService;

    // 查询资源分类
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        // 调用service方法并响应数据
        List<ResourceCategory> resourceCategoryList = resourceCategoryService.findAllResourceCategory();
        return new ResponseResult(true,200,"查询所有资源分类成功",resourceCategoryList);
    }

    // 添加&更新资源分类
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){
        // 添加资源分类
        if(resourceCategory.getId()==null){
            // 调用service方法并响应数据
            resourceCategoryService.saveResourceCategory(resourceCategory);
            return new ResponseResult(true,200,"添加资源分类成功",null);
        }
        // 更新资源分类
        else {
            // 调用service方法并响应数据
            resourceCategoryService.updateResourceCategory(resourceCategory);
            return new ResponseResult(true,200,"更新资源分类成功",null);
        }
    }

    // 删除资源分类
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){
        // 调用service方法并响应数据
        resourceCategoryService.deleteResourceCategoryById(id);
        return new ResponseResult(true,200,"删除资源分类成功",null);
    }
}
