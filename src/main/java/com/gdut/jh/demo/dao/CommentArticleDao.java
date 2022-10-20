package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.comment_article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentArticleDao extends JpaRepository<comment_article,Integer> {
    List<comment_article> findAllByAid(int aid);
}
