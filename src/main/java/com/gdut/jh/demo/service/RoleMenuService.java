package com.gdut.jh.demo.service;

import com.gdut.jh.demo.dao.RoleMenuDao;
import com.gdut.jh.demo.pojo.entity.role_menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMenuService {

    @Autowired
    RoleMenuDao roleMenuDao;
    public List<role_menu> findAllByRid(int rid){
        return roleMenuDao.findByRid(rid);
    }
}
