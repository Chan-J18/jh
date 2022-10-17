package com.gdut.jh.demo.service;

import com.gdut.jh.demo.controller.ArticleController;
import com.gdut.jh.demo.dao.ArticleDao;
import com.gdut.jh.demo.dao.TopicArticleDao;
import com.gdut.jh.demo.dao.TopicDao;
import com.gdut.jh.demo.pojo.entity.Article;
import com.gdut.jh.demo.pojo.entity.Topic;
import com.gdut.jh.demo.pojo.entity.topic_article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    ArticleDao articleDao;
    @Autowired
    TopicArticleDao topicArticleDao;
    @Autowired
    TopicDao topicDao;

    public int addArticle(Article article){
        return articleDao.save(article).getId();
    }

    public void saveAandT(topic_article ta){
        topicArticleDao.save(ta);
    }

    public List<Article> getArticles() { return articleDao.findAll(); }

    public Page list(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return articleDao.findAll(PageRequest.of(page,size,sort));
    }
    public int getTopics(String type, int page,int size){
        Topic topic = topicDao.findByName(type);
        int tid = topic.getId();
        List<Integer> aids = topicArticleDao.findByTid(tid).stream()
                .map(topic_article::getAid).collect(Collectors.toList());
        return 0;
    }
    public Page getHeaderArticles(){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return articleDao.findAll(PageRequest.of(0,6,sort));
    }
    public Page getHotArticles(){
        Sort sort = Sort.by(Sort.Direction.DESC,"vtimes");
        return articleDao.findAll(PageRequest.of(0,10,sort));
    }
    public Article getArticle(int id){
        return articleDao.findById(id);
    }
}
