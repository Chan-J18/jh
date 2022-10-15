package com.gdut.jh.demo.controller;

import com.gdut.jh.demo.Result.Result;
import com.gdut.jh.demo.Result.ResultFactory;
import com.gdut.jh.demo.pojo.entity.Article;
import com.gdut.jh.demo.service.ArticleService;
import com.gdut.jh.demo.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@CrossOrigin
@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping("/article/img")
    @ResponseBody
    public String addImg(@RequestBody MultipartFile file){
        String folder = "F:/Project/IDEAproject/Static/img";
        File imgFolder = new File(folder);
        File f = new File(imgFolder, MyUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdir();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8443/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @PostMapping("/article/publish")
    @ResponseBody
    public Result publish(@RequestBody Article article){
        int len = MyUtils.removeHtml(article.getHtml()).replaceAll("\r|\n","").length();
        article.setLen(len);
        article.setVtimes(0);
        article.setCtimes(0);
        article.setAtimes(0);
        articleService.addArticle(article);
        return ResultFactory.buildSuccessResult("发布成功！");
    }
}
