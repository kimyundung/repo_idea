package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 分页查询用户
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO){
        PageInfo<User> userInfo = userService.findAllUserByPage(userVO);
        return new ResponseResult(true,200,"成功获取用户列表", userInfo);
    }

    // 修改用户状态
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(User user){
        userService.updateUserStatus(user);
        return new ResponseResult(true,200,"成功修改用户状态",user.getStatus());
    }

    // 用户登陆
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User login = userService.login(user);
        ResponseResult result = null;
        if(login!=null){
            // 保存access_token和用户id到session中
            String access_token = UUID.randomUUID().toString();
            HttpSession session = request.getSession();
            session.setAttribute("user_id",login.getId());
            session.setAttribute("access_token",access_token);

            // 将查询出来的信息响应非后台
            Map<String,Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",login.getId());

            // 将查询出来的user, 也存到map中 (用于退出)
            map.put("user",login);

            result = new ResponseResult(true,1,"响应成功",map);
        } else {
            result = new ResponseResult(true,1,"用户名或密码错误",null);
        }
        return result;
    }
    // 分配角色(回显)
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){
        List<Role> roleList = userService.findUserRelationRoleById(id);
        return new ResponseResult(true,200,"分配角色成功",roleList);
    }

    // 分配角色
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO){
        userService.UserContextRole(userVO);
        return new ResponseResult(true,200,"成功分配角色",null);
    }

    // 获取用户权限进行菜单动态显示
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        // 1.获取请求头中的token
        // 2.获取session中token
        // 3.判断token是否一致

        String header_token = request.getHeader("Authorization");

        HttpSession session = request.getSession();

        String session_token = (String) request.getSession().getAttribute("access_token");

        if(header_token.equals(session_token)){
            // 获取用户id
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");
            // 调用service方法, 进行菜单和资源信息查询
            ResponseResult responseResult = userService.getUserPermissions(user_id);
            return responseResult;
        } else {
            return new ResponseResult(false,400,"获取菜单信息失败",null);
        }

    }
}
