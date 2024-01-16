package com.example.jpa.user.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity //엔티티 정의
@Table(name="tb_user") //Default -> 클래스명 사용
@Getter
@Setter
@NoArgsConstructor( access = AccessLevel.PROTECTED) // 기본생성자를 만든다. 영속성을 지키기 위해서, 혹시 모를 개발자의 실수를 막기 위해 접근 제한자를 설정
@AllArgsConstructor //전체 필드에 대한 생성자를 만들어줍니다. (jpa는 setter를 사용하지 않는다)
@Builder
@ToString
public class User {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 설정 (id값이 null일 경우 자동 생성)
    private Long id;

    @NonNull
    @Column(unique = true, length=10)
    private String userName;

    @Column(name="user_age")
    private int age;
}