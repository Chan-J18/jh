package com.gdut.jh.demo.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "feature")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    boolean military;
    boolean amusement;
    boolean life;
    int len;
}
