package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment,Integer> {
}
