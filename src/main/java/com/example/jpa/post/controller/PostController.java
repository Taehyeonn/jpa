package com.example.jpa.post.controller;

import com.example.jpa.post.domain.Post;
import com.example.jpa.post.dto.request.PostRequestDto;
import com.example.jpa.post.dto.response.PostResponseDto;
import com.example.jpa.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    // 게시글 리스트 조회
    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        log.info("get");

        List<PostResponseDto> posts = postService.getPosts();

        return ResponseEntity.ok(posts);
    }

    // 게시글 단일 조회
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable("postId") String postId) {
        log.info("getPost = {}", postId);

        Post post = postService.findByPostId(Long.valueOf(postId));
        PostResponseDto postResponseDto = postService.getPost(post);

        return ResponseEntity.ok(postResponseDto);
    }

    // 게시글 작성
    @PostMapping("/posts")
    public ResponseEntity<Object> writePost(@RequestBody PostRequestDto PostRequestDto) {
        log.info("writePost");

        Long postId = postService.writePost(PostRequestDto);
        return ResponseEntity.created(URI.create("/post/"+postId)).build();
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Object> deletePost(@PathVariable("postId") String postId) {
        log.info("deletePost = {}", postId);

        postService.deleteByPostId(Long.parseLong(postId));
        System.out.println();

        return ResponseEntity.ok().build();
    }

    // 게시글 수정
    @PatchMapping("/posts/{postId}")
    public ResponseEntity<Object> updatePost(@PathVariable("postId") String postId,
                                             @RequestBody PostRequestDto postRequestDto) {
        log.info("updatePost = {}", postId);
        log.info("postRequestDto = {}", postRequestDto);

        postService.updatePost(Long.valueOf(postId), postRequestDto);

        return ResponseEntity.ok().build();
    }

    // @PageableDefault(page = 1) : page는 기본으로 1페이지를 보여준다.
    @GetMapping("/posts/paging")
    public ResponseEntity<Object> paging(@PageableDefault(page = 1) Pageable pageable) {
        Page<PostResponseDto> postsPages = postService.paging(pageable);

        /**
         * blockLimit : page 개수 설정
         * 현재 사용자가 선택한 페이지 앞 뒤로 3페이지씩만 보여준다.
         * ex : 현재 사용자가 4페이지라면 2, 3, (4), 5, 6
         */
        int blockLimit = 3;
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), postsPages.getTotalPages());

        log.info("postsPages = {}", postsPages);
        log.info("startPage = {}", startPage);
        log.info("endPage = {}", endPage);
        return ResponseEntity.ok(postsPages);
    }
}
