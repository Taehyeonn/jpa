package com.example.jpa.post.dto.request;

import com.example.jpa.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class PostRequestDto {

    private Long id;
    private String title;
    private String content;

//    Dto -> Entity
    public Post toEntity() {

        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();

        return post;
    }
}
