package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleDao extends JpaRepository<Article,Integer> {
    List<Article> findAll();
    Article findById(int id);
    @Query(value="select * from article where id in ?1 order by id desc limit ?3 offset ?2",nativeQuery = true)
    List<Article> findByAids(List<Integer> aids,int offset,int size);
}
