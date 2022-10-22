package com.gdut.jh.demo.service;

import com.gdut.jh.demo.dao.RoleDao;
import com.gdut.jh.demo.pojo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;

    public Role getByRid(int rid){
        return roleDao.findById(rid);
    }
}
