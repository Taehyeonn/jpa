package com.example.jpa.user.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name="tb_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자를 만든다. 영속성을 지키기 위해서, 혹시 모를 개발자의 실수를 막기 위해 접근 제한자를 설정
@SQLDelete(sql = "UPDATE tb_user SET status = 'DELETED' WHERE id = ?")
@ToString
public class User {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 설정 (id값이 null일 경우 자동 생성)
    private Long id;

    @Column(nullable = false, length=10)
    private String name;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Enumerated(value = EnumType.STRING)
    private UserState status;

    @CreatedDate
    @Column(updatable = false, length = 50)
    private LocalDateTime createdDate;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.status = UserState.ACTIVE;
        this.createdDate = LocalDateTime.now();
    }

    public void setName(String name) {
        this.name = name;
    }
}