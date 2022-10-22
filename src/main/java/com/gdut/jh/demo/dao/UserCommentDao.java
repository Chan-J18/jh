package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.user_comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserCommentDao extends JpaRepository<user_comment,Integer> {
    user_comment findByCid(int cid);
    @Modifying
    @Transactional
    void deleteByUid(int uid);
    List<user_comment> findByUid(int uid);
}
