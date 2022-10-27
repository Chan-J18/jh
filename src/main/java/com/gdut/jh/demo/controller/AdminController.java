package com.gdut.jh.demo.controller;

import com.gdut.jh.demo.pojo.entity.Article;
import com.gdut.jh.demo.pojo.entity.User;
import com.gdut.jh.demo.pojo.entity.user_role;
import com.gdut.jh.demo.service.ArticleService;
import com.gdut.jh.demo.service.UserRoleService;
import com.gdut.jh.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    ArticleService articleService;

    @GetMapping("/admin/user/list")
    public List<User> listUser(){
        return userService.list();
    }

    @GetMapping("/admin/user/delete/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userService.delete(id);
    }

    @PostMapping("/admin/user/update")
    public void update(@RequestBody User user){
        userService.updateInfo(user);
    }

    @GetMapping("/admin/role/list")
    public List<user_role> list(){
        return userRoleService.list();
    }

    @PostMapping("/admin/role/update")
    public String updateRole(@RequestBody user_role ur){
        return userRoleService.update(ur);
    }

    @GetMapping("/admin/article/list")
    public List<Article> listArticle(){
        return articleService.list();
    }

    @GetMapping("/admin/article/delete/{id}")
    public void deleteArticle(@PathVariable("id") int id){
        articleService.delete(id);
    }
}
