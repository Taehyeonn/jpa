//package com.example.jpa.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.experimental.SuperBuilder;
//import org.antlr.v4.runtime.misc.NotNull;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//
//import java.util.Date;
//
//@SuperBuilder
//@NoArgsConstructor
//@Setter
//@Getter
////@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // JPA에서 lazy관련 에러 날 경우 사용
//@Entity  // 객체와 테이블 매핑
//@Table(name = "MEMBER")  // 테이블 지정
////@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) //DB 연결안하고 임시로 실행
//public class Member {
//
//    @Id  // Primary Key 지정
//    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AUTO_INCREMENT 설정 (id값이 null일 경우 자동 생성)
//    @Column(name = "ID")  // 컬럼 지정
//    private Long id;
//
//    @NotNull
//    @Column(name = "NAME")
//    private String name;
//
//    @NotNull
//    @Column(name = "email")
//    private String email;
//
//    @Column(name = "NICKNAME")
//    private String nickname;
//
//    @Column(name = "AGE")
//    private Integer age;
//
//    @Column(name = "BIRTHDAY")
//    private Date birthday;
//}
