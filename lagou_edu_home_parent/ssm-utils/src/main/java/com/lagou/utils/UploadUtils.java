package com.lagou.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UploadUtils {
    public static Map<String,String> fileUpload(MultipartFile file, HttpServletRequest request) throws IOException {
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
        return map;
    }
}
