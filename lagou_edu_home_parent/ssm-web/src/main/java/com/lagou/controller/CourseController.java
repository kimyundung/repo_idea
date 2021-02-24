package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController     // @Controller + @ResponseBody
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // 条件课程列表查询
    @RequestMapping("findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){
        List<Course> list = courseService.findCourseByCondition(courseVO);
        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",list);
        return responseResult;
    }

    // 课程图片上传
    @RequestMapping("courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        // 1. 判断接收到的文件是否为空
        // 2. 获取项目部署路径 (ssm-web在tomcat上的部署路径 需要HttpServletRequest)
        // 3. 获取原文件名
        // 4. 生成新文件名   (防止重复文件)
        // 5. 文件上传       (存到服务器)
        // 6. 数据响应 : 返回文件名和文件路径

        if(file.isEmpty()){
            throw new RuntimeException();
        }

        //D:\apache-tomcat-8.5.56\webapps\ssm-web\
        String realPath = request.getServletContext().getRealPath("/");
        //D:\apache-tomcat-8.5.56\webapps\
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));

        //lagou.jpg
        String originalFilename = file.getOriginalFilename();


        //34532452435.jpg
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //D:\apache-tomcat-8.5.56\webapps\Upload 因为转义问题写成\U原来是小写
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);
        //如果目录不存在就创建目录
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录: " + filePath);
        }
        //图片进行了真正的上传
        file.transferTo(filePath);

        Map<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);
        //"filePath" : http://localhost:8080/upload/34532452435.jpg
        map.put("filePath","http://localhost:8888/upload/" + newFileName);
        ResponseResult responseResult = new ResponseResult(true, 200, "上传成功", map);

        return responseResult;
    }

    // 新增课程信息和讲师信息: 新增和修改课程信息要写在同一个方法中
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        // 调用service
        if(courseVO.getId() == null){
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult result = new ResponseResult(true, 200, "新增成功", null);
            return result;
        } else {
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult result = new ResponseResult(true, 200, "修改成功", null);
            return result;
        }

    }

    // 回显课程信息(根据ID查询对应的课程信息和讲师信息)
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVO courseVO = courseService.findCourseById(id);
        ResponseResult result = new ResponseResult(true, 200, "根据id查询课程信息成功", courseVO);
        return result;
    }

    // 课程状态管理
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id, Integer status){
        // 1. 调用service
        // 2. 响应数据

        courseService.updateCourseStatus(id,status);

        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        ResponseResult result = new ResponseResult(true, 200, "课程状态变更成功", map);
        return result;

    }
}
