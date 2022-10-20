package com.gdut.jh.demo.controller;

import com.gdut.jh.demo.dao.UserDao;
import com.gdut.jh.demo.pojo.entity.User;
import com.gdut.jh.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
public class UserController {
    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;

    @GetMapping("/user/getUserInfo")
    @ResponseBody
    public User getUserInfo(){
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userDao.findByUsername(username);
        return userDao.findById(user.getId());
    }

    @PostMapping("/user/updateUserInfo")
    @ResponseBody
    public void updateUserInfo(@RequestBody User requestUser){
       userService.updateInfo(requestUser);
    }
}
