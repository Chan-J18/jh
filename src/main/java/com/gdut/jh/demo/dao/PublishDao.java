package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.Publish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishDao extends JpaRepository<Publish,Integer> {
}
