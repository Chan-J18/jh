package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.feature_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FeatureUserDao extends JpaRepository<feature_user,Integer> {
    feature_user findByUid(int uid);
    List<feature_user> findByFidIn(List<Integer> fids);
    @Modifying
    @Transactional
    void deleteByUid(int uid);
}
