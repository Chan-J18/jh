package com.gdut.jh.demo.controller;

import com.gdut.jh.demo.Result.Result;
import com.gdut.jh.demo.Result.ResultFactory;
import com.gdut.jh.demo.pojo.entity.Article;
import com.gdut.jh.demo.pojo.entity.topic_article;
import com.gdut.jh.demo.pojo.tmp.Url;
import com.gdut.jh.demo.pojo.tmp.atopics;
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
import java.util.List;

@CrossOrigin
@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping("/article/img/add")
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

    @PostMapping("/article/img/remove")
    @ResponseBody
    public Result removeImg(@RequestBody Url url){
        String folder = "F:/Project/IDEAproject/Static/img";
        String fName = url.getUrl();
        String fileName = fName.substring(fName.lastIndexOf("/")+1);
        File f = new File(folder+"/"+fileName);
        System.out.println(f.getName());
        if (f.exists()) {
            f.delete();
            return ResultFactory.buildSuccessResult("封面删除成功！");
        } else return ResultFactory.buildFailResult("删除封面失败！");
    }

    @PostMapping("/article/publish")
    @ResponseBody
    public int publish(@RequestBody Article article){
        int len = MyUtils.removeHtml(article.getHtml()).replaceAll("\r|\n","").length();
        article.setLen(len);
        article.setVtimes(0);
        article.setCtimes(0);
        article.setAtimes(0);
        return articleService.addArticle(article);
    }

    @PostMapping("/article/types")
    @ResponseBody
    public Result types(@RequestBody atopics ats){
        int aid = ats.getAid();
        List<Integer> types = ats.getTypes();
        topic_article ta = new topic_article();
        ta.setAid(aid);
        for(int type: types){
            ta.setId(type);
            articleService.saveAandT(ta);
        }
        return ResultFactory.buildSuccessResult("主题保存成功！");
    }
}
