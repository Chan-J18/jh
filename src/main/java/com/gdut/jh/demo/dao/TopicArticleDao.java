package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.topic_article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TopicArticleDao extends JpaRepository<topic_article,Integer> {
    List<topic_article> findByTid(int tid);
    List<topic_article> findByAid(int aid);
    List<topic_article> findByAidIn(List<Integer> aids);
    @Modifying
    @Transactional
    void deleteByAid(int aid);
}
