package com.gdut.jh.demo.service;

import com.gdut.jh.demo.dao.UserRoleDao;
import com.gdut.jh.demo.pojo.entity.Role;
import com.gdut.jh.demo.pojo.entity.user_role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    @Autowired
    UserRoleDao userRoleDao;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    public user_role getByUid(int uid){
        return userRoleDao.findByUid(uid);
    }

    public String update(user_role ur){
        userRoleDao.save(ur);
        return roleService.getByRid(ur.getRid()).getName();
    }

    public List<user_role> list(){
        List<user_role> urs = userRoleDao.findAll();
        urs.forEach(ur -> {
            String username = userService.getUserByUid(ur.getUid()).getUsername();
            ur.setUsername(username);
            String rname = roleService.getByRid(ur.getRid()).getName();
            ur.setRolename(rname);
        });
        return urs;
    }

}
