package com.gdut.jh.demo.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "menu")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id ;
    String path;
    String name;
    String nameZh;
    String iconCls;
    String component;
    int parentId;
    @Transient
    List<Menu> children;
}
