package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    User findById(int id);
    @Modifying
    @Transactional
    void deleteById(int  id);
}
