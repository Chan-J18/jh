package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.Menu;
import com.gdut.jh.demo.pojo.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicDao extends JpaRepository<Topic,Integer> {
    Topic findByName(String name);
    List<Topic> findByIdIn(List<Integer> tids);
}
