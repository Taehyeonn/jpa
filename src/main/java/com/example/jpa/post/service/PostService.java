package com.example.jpa.post.service;

import com.example.jpa.post.domain.Post;
import com.example.jpa.post.domain.repository.PostRepository;
import com.example.jpa.post.dto.request.PostRequestDto;
import com.example.jpa.post.dto.response.PostResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    // 단일 조회
    public Post findByPostId(Long postId) {

        return postRepository.findById(postId)
                .orElseThrow(IllegalArgumentException::new);
    }

    // 단일 조회 결과를 ResponseDto로 변환
    public PostResponseDto getPost(Post post) {

        return new PostResponseDto(post);
    }

    // 다중 조회
    public List<PostResponseDto> getPosts() {

        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    // 작성
    @Transactional
    public Long writePost(PostRequestDto postRequestDto) {

        Post post = postRequestDto.toEntity();
        postRepository.save(post);

        log.info("post = {}", post);
        return post.getId();
    }

    // 소프트 삭제
    @Transactional
    public void deleteByPostId(long postId) {

        postRepository.deleteById(postId);
    }

    // 수정
    @Transactional
    public void updatePost(Long postId, PostRequestDto postRequestDto){

        Optional<Post> post = postRepository.findById(postId);

        post.orElseThrow(IllegalArgumentException::new);

        post.ifPresent(value -> value.update(postRequestDto.getTitle(),
                postRequestDto.getContent()));
    }

    public Page<PostResponseDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 3; // 한페이지에 보여줄 글 개수

        // 한 페이지당 3개식 글을 보여주고 정렬 기준은 ID기준으로 내림차순
        Page<Post> postsPages = postRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        // 목록 : id, title, content, author
        Page<PostResponseDto> postsResponseDtos = postsPages.map(
                postPage -> new PostResponseDto(postPage));

        return postsResponseDtos;
    }
}
