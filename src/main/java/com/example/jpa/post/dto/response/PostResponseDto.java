package com.example.jpa.post.dto.response;

import com.example.jpa.post.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {


    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
//    private boolean deleted;
//    private Long userId;

//    Entity -> Dto
    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdDate = post.getCreatedDate();
//        this.deleted = post.isDeleted();
//        this.userId = (post.getUser() != null) ? post.getUser().getId() : null;
    }
}
