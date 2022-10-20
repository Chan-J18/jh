package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment,Integer> {
    List<Comment> findAllByIdInOrderByIdDesc(List<Integer> cids);
}
