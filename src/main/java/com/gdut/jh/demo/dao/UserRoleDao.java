package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.user_role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleDao extends JpaRepository<user_role,Integer> {
}
