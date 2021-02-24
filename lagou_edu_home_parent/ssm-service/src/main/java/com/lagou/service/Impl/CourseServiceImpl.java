package com.lagou.service.Impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    // 条件课程列表查询
    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {
        List<Course> list = courseMapper.findCourseByCondition(courseVO);
        return list;
    }

    // 添加课程及讲师信息
    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        // 1. 封装课程信息
        // 2. 补全课程信息
        // 3. 保存课程信息
        // 4. 获取新插入数据的id值
        // 5. 封装讲师信息
        // 6. 补全讲师信息
        // 7. 保存讲师信息

        Course course = new Course();
        BeanUtils.copyProperties(course,courseVO);

        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        courseMapper.saveCourse(course);

        int id = course.getId();

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);

        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);

        courseMapper.saveTeacher(teacher);
    }

    // 回显课程信息(根据ID查询对应的课程信息和讲师信息)
    @Override
    public CourseVO findCourseById(Integer id) {
        return courseMapper.findCourseById(id);
    }

    // 更新课程及讲师信息
    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        // 1. 封装课程信息
        // 2. 补全课程信息 (updateTime)
        // 3. 更新课程信息
        // 4. 封装讲师信息
        // 5. 补全讲师信息 (courseId, updateTime)
        // 6. 更新讲师信息

        Course course = new Course();
        BeanUtils.copyProperties(course,courseVO);

        Date date = new Date();
        course.setUpdateTime(date);

        courseMapper.updateCourse(course);

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);

        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(date);

        courseMapper.updateTeacher(teacher);

    }

    // 课程状态管理
    @Override
    public void updateCourseStatus(int courseId, int status) {
        // 1. 封装数据
        // 2. 调用mapper

        Course course = new Course();
        course.setId(courseId);
        course.setStatus(status);
        course.setUpdateTime(new Date());

        courseMapper.updateCourseStatus(course);
    }
}
