package com.gdut.jh.demo.service;

import com.gdut.jh.demo.dao.BrowseDao;
import com.gdut.jh.demo.dao.UserDao;
import com.gdut.jh.demo.pojo.entity.Browse;
import com.gdut.jh.demo.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    BrowseDao browseDao;
    public User getUserByUsername(String username){
        return userDao.findByUsername(username);
    }

    public void addUser(User user){
        userDao.save(user);
    }
    public void addUserRecord(int uid,int aid){
        Browse browse = new Browse();
        browse.setAid(aid);
        browse.setUid(uid);
        browseDao.save(browse);
    }
}
