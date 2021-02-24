package com.lagou.controller;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    // 据课程ID查询关联章节信息,及章节信息关联课时信息
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){
        // 1. 调用service
        // 2. 响应数据

        List<CourseSection> sectionList = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult result = new ResponseResult(true, 200, "章节及课时内容查询成功", sectionList);
        return result;

    }

    // 显章节对应的课程信息
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        // 1. 调用service
        // 2. 响应数据

        Course course = courseContentService.findCourseByCourseId(courseId);

        ResponseResult result = new ResponseResult(true, 200, "查询课程信息成功", course);
        return result;
    }

    // 新增&更新章节信息
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        // 0. 判断是否携带了章节id
        // 1. 调用service
        // 2. 响应数据

        if(courseSection.getId() == null){
            courseContentService.saveSection(courseSection);
            ResponseResult result = new ResponseResult(true, 200, "新增章节成功", null);
            return result;
        } else {
            courseContentService.updateSection(courseSection);
            ResponseResult result = new ResponseResult(true, 200, "更新章节成功", null);
            return result;
        }
    }

    // 修改章节状态
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id, int status){
        // 1 调用service
        // 2 响应数据
        courseContentService.updateSectionStatus(id,status);

        Map<String,Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "修改章节状态成功", map);
        return result;
    }

    // 新建&修改课时信息
    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson courseLesson){
        // 0 根据id条件判断
        // 1 调用mapper
        // 2 响应数据
        if(courseLesson.getId() == null){
            courseContentService.saveLesson(courseLesson);
            ResponseResult result = new ResponseResult(true, 200, "添加课时成功", null);
            return result;
        } else{
            courseContentService.updateLesson(courseLesson);
            ResponseResult result = new ResponseResult(true, 200, "修改课时成功", null);
            return result;
        }
    }
}
