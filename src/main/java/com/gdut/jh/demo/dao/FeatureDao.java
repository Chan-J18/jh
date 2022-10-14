package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureDao extends JpaRepository<Feature,Integer> {
}
