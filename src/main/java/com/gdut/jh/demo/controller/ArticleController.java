package com.gdut.jh.demo.controller;

import com.gdut.jh.demo.Result.Result;
import com.gdut.jh.demo.Result.ResultFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@Controller
public class ArticleController {
    @PostMapping("/article/img")
    @ResponseBody
    public Result addImg(@RequestBody MultipartFile file){
        return ResultFactory.buildSuccessResult("添加封面成功");
    }
}
