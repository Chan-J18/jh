package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleDao extends JpaRepository<Article,Integer> {
    List<Article> findAll();
    Article findById(int id);
}
