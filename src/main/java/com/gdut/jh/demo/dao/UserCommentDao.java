package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.user_comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCommentDao extends JpaRepository<user_comment,Integer> {
}
