package com.example.cinema.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = \"movies\")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int duration;
    private double rating;

    @Column(length = 1000)
    private String actors; // 格式: 姓名1,姓名2

    @OneToMany(mappedBy = \"movie\", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Object> screenings = new ArrayList<>(); // 可替换为 Screening 实体

    public Movie() {}

    // getters / setters 省略，IDE 可生成
}
