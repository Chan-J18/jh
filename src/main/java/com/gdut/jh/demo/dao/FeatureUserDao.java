package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.feature_user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureUserDao extends JpaRepository<feature_user,Integer> {
}
