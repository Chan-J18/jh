package com.gdut.jh.demo.service;

import com.gdut.jh.demo.controller.ArticleController;
import com.gdut.jh.demo.dao.ArticleDao;
import com.gdut.jh.demo.dao.BrowseDao;
import com.gdut.jh.demo.dao.TopicArticleDao;
import com.gdut.jh.demo.dao.TopicDao;
import com.gdut.jh.demo.pojo.entity.Article;
import com.gdut.jh.demo.pojo.entity.Browse;
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
    @Autowired
    BrowseDao browseDao;

    //发布文章
    public int addArticle(Article article){
        return articleDao.save(article).getId();
    }

    //记录文章和标签关系
    public void saveAandT(topic_article ta){
        topicArticleDao.save(ta);
    }

    //分页获取文章
    public Page list(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return articleDao.findAll(PageRequest.of(page,size,sort));
    }
    //分页获取对应标签的文章
    public List<Article> getTopics(String type, int page,int size){
        Topic topic = topicDao.findByName(type);
        int tid = topic.getId();
        List<Integer> aids = topicArticleDao.findByTid(tid).stream()
                .map(topic_article::getAid).collect(Collectors.toList());
        return articleDao.findByAids(aids,(page-1)*size,size);
    }
    //获取头条文章
    public Page getHeaderArticles(){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return articleDao.findAll(PageRequest.of(0,6,sort));
    }
    //获取热点文章
    public Page getHotArticles(){
        Sort sort = Sort.by(Sort.Direction.DESC,"vtimes");
        return articleDao.findAll(PageRequest.of(0,10,sort));
    }
    //获取文章详细页内容
    public Article getArticle(int id){
        return articleDao.findById(id);
    }
    //获取文章对应标签项
    public List<String> getTypes(int aid) {
        List<Integer> tids = topicArticleDao.findByAid(aid).stream()
                .map(topic_article::getTid).collect(Collectors.toList());
        return topicDao.findByIdIn(tids).stream()
                .map(Topic::getName).collect(Collectors.toList());
    }
    //根据用户id获取 用户记录中的文章
    public List<Article> getArticleByUserId(List<Integer> uids,int size,int page){
        List<Integer> aids = browseDao.findByUidIn(uids).stream()
                .map(Browse::getAid).collect(Collectors.toList());
        return articleDao.findByAids(aids,page,size);
    }
}
