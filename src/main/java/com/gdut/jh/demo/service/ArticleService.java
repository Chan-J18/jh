package com.gdut.jh.demo.service;

import com.gdut.jh.demo.controller.ArticleController;
import com.gdut.jh.demo.dao.ArticleDao;
import com.gdut.jh.demo.dao.TopicArticleDao;
import com.gdut.jh.demo.pojo.entity.Article;
import com.gdut.jh.demo.pojo.entity.topic_article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    ArticleDao articleDao;
    @Autowired
    TopicArticleDao topicArticleDao;

    public int addArticle(Article article){
        return articleDao.save(article).getId();
    }

    public void saveAandT(topic_article ta){
        topicArticleDao.save(ta);
    }
}
