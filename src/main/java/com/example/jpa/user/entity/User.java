package com.example.jpa.user.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity //엔티티 정의
@Table(name="tb_user") //정의하지 않을시 클래스명이 테이블명
@Getter
@Setter
public class User {

    @Id //기본키를 의미. 반드시 기본키를 가져야함.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(unique = true, length=10)
    private String userName;

    @Column(name="user_age")
    private int age;
}