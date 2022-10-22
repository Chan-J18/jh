package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FeatureDao extends JpaRepository<Feature,Integer> {
    Feature findById(int id);
    List<Feature> findByOrderByLen();
    @Modifying
    @Transactional
    void deleteById(int id);
}
