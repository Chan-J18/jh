package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.topic_article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicArticleDao extends JpaRepository<topic_article,Integer> {
}
