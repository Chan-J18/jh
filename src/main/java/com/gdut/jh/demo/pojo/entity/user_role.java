package com.gdut.jh.demo.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_role")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class user_role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    int uid;
    int rid;
}
