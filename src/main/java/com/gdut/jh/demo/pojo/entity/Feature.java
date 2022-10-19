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
    float military;
    float amusement;
    float life;
    int len;
}
