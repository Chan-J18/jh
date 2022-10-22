package com.gdut.jh.demo.dao;

import com.gdut.jh.demo.pojo.entity.Browse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BrowseDao extends JpaRepository<Browse,Integer> {
    List<Browse> findByUid(int uid);
    List<Browse> findByUidIn(List<Integer> ids);
    @Modifying
    @Transactional
    void deleteByUid(int uid);
}
