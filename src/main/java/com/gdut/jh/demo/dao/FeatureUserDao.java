package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.feature_user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeatureUserDao extends JpaRepository<feature_user,Integer> {
    feature_user findByUid(int uid);
    List<feature_user> findByFidIn(List<Integer> fids);
}
