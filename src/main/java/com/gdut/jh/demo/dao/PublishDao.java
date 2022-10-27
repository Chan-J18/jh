package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.Publish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PublishDao extends JpaRepository<Publish,Integer> {
    @Modifying
    @Transactional
    void deleteByUid(int uid);
    List<Publish> findByUid(int uid);
    @Modifying
    @Transactional
    void deleteByAid(int aid);
}
