package com.gdut.jh.demo.controller;

import com.gdut.jh.demo.pojo.entity.Menu;
import com.gdut.jh.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class MenuController {
    @Autowired
    MenuService menuService;

    @GetMapping("/menu")
    public List<Menu> menu(){
        return menuService.getMenusByCurrentUser();
    }

}
