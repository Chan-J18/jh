package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuDao extends JpaRepository<Menu,Integer> {
    List<Menu> findByParentId(int pid);
}
