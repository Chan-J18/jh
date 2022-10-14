package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuDao extends JpaRepository<Menu,Integer> {
}
