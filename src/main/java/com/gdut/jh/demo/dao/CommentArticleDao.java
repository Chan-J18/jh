package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.comment_article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentArticleDao extends JpaRepository<comment_article,Integer> {
    List<comment_article> findAllByAid(int aid);
    @Modifying
    @Transactional
    void deleteByAidIn(List<Integer> aids);
}
