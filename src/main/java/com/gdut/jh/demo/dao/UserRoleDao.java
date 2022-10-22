package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.user_role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRoleDao extends JpaRepository<user_role,Integer> {
    user_role findByUid(int uid);
    @Modifying
    @Transactional
    void deleteByUid(int uid);
}
