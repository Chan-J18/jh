package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Integer> {
    Role findById(int id);
}
