package com.gdut.jh.demo.controller;

import com.gdut.jh.demo.Result.Result;
import com.gdut.jh.demo.Result.ResultFactory;
import com.gdut.jh.demo.pojo.entity.User;
import com.gdut.jh.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser){
        String username = requestUser.getUsername();
        String password = requestUser.getPassword();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        usernamePasswordToken.setRememberMe(true);
        try {
            subject.login(usernamePasswordToken);
            return ResultFactory.buildSuccessResult("登录成功",username);
        } catch (AuthenticationException e) {
            String message = "账号密码错误";
            return ResultFactory.buildFailResult(message);
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestBody User requestUser) {
        String username = requestUser.getUsername();
        User user = userService.getUserByUsername(username);
        if (null != user) return ResultFactory.buildFailResult("该账号已被注册");
        else {
            String password = requestUser.getPassword();
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            int times = 2;
            String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
            requestUser.setPassword(encodedPassword);
            requestUser.setSalt(salt);
            userService.addUser(requestUser);
            return ResultFactory.buildSuccessResult("注册成功",requestUser);
        }
    }
}
