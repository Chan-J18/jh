package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.role_menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleMenuDao extends JpaRepository<role_menu,Integer> {
    List<role_menu> findByRid(int rid);
}
