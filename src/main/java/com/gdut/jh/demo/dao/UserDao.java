package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    User findById(int id);
}
