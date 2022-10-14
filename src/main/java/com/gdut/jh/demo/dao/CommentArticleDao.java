package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.comment_article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentArticleDao extends JpaRepository<comment_article,Integer> {
}
