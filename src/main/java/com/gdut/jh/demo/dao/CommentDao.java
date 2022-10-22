package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment,Integer> {
    List<Comment> findAllByIdInOrderByIdDesc(List<Integer> cids);
    @Modifying
    @Transactional
    void deleteByIdIn(List<Integer> ids);
}
