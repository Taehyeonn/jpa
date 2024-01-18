package com.example.jpa.post.domain;

import com.example.jpa.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name="tb_post")
@Getter
@NoArgsConstructor( access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(exclude = "user")
@SQLDelete(sql = "UPDATE tb_post SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Post {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @CreatedDate
    @Column(updatable = false, length = 50)
    private LocalDateTime createdDate;

    @Column
    private int viewCount;

    @Column
    private boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Post(String title, String content) {
        this.id = null;
        this.title = title;
        this.content = content;
        this.createdDate = LocalDateTime.now();
        this.viewCount = 0;
        this.deleted = false;
        this.user = null;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
