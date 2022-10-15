package com.gdut.jh.demo.service;

import com.gdut.jh.demo.controller.ArticleController;
import com.gdut.jh.demo.dao.ArticleDao;
import com.gdut.jh.demo.pojo.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    ArticleDao articleDao;

    public String addArticle(Article article){
        articleDao.save(article);
        return "添加成功";
    }
}
