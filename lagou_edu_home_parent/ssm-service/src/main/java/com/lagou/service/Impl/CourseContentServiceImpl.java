package com.lagou.service.Impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    // 根据课程ID查询关联章节信息,及章节信息关联课时信息
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {
        List<CourseSection> sectionList = courseContentMapper.findSectionAndLessonByCourseId(courseId);
        return sectionList;
    }

    // 显章节对应的课程信息
    @Override
    public Course findCourseByCourseId(Integer courseId) {
        return courseContentMapper.findCourseByCourseId(courseId);
    }

    // 新增章节信息
    @Override
    public void saveSection(CourseSection courseSection) {
        // 1. 补全信息
        // 2. 调用mapper方法

        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        courseContentMapper.saveSection(courseSection);
    }

    // 更新章节信息
    @Override
    public void updateSection(CourseSection courseSection) {
        // 1. 补全信息
        // 2. 调用mapper方法
        courseSection.setUpdateTime(new Date());
        courseContentMapper.updateSection(courseSection);
    }

    // 修改章节状态
    @Override
    public void updateSectionStatus(int id, int status) {
        // 1. 封装数据
        // 2. 调用mapper方法

        CourseSection courseSection = new CourseSection();
        courseSection.setId(id);
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());

        courseContentMapper.updateSectionStatus(courseSection);
    }

    // 添加课时信息
    @Override
    public void saveLesson(CourseLesson courseLesson) {
        // 1 补全信息
        // 2 调用mapper

        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);

        courseContentMapper.saveLesson(courseLesson);
    }

    // 修改课时信息
    @Override
    public void updateLesson(CourseLesson courseLesson) {
        // 1 补全信息
        // 2 调用mapper
        courseLesson.setUpdateTime(new Date());
        courseContentMapper.updateLesson(courseLesson);
    }

}
