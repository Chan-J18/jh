package com.gdut.jh.demo.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "topic_article")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class topic_article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    int tid;
    int aid;
}
