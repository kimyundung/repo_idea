package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {
    // 根据课程ID查询关联章节信息,及章节信息关联课时信息
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);
    // 回显章节对应的课程信息
    public Course findCourseByCourseId(Integer courseId);
    // 新增章节信息
    public void saveSection(CourseSection courseSection);
    // 更新章节信息
    public void updateSection(CourseSection courseSection);
    // 修改章节状态
    public void updateSectionStatus(int id, int status);
    // 添加课时信息
    public void saveLesson(CourseLesson courseLesson);
    // 修改课时信息
    public void updateLesson(CourseLesson courseLesson);
}

