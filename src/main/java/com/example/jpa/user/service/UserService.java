package com.example.jpa.user.service;

import com.example.jpa.user.domain.repository.UserRepository;
import com.example.jpa.user.domain.User;
import com.example.jpa.user.dto.request.UserRequest;
import com.example.jpa.user.dto.response.UserResponse;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원 가입
    @Transactional
    public User join(User user) {

        User saveUser = userRepository.save(user);
        return saveUser;
    }

    // 회원 검색
    public User findByUserId(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(IllegalArgumentException::new);
    }

    // 회원 Response로 변환
    public UserResponse getUserResponse(User user) {

        return UserResponse.of(user);
    }

    // 전체 회원 조회
    public List<UserResponse> getUsers() {

        List<User> users = userRepository.findAll();

        List<UserResponse> userResponses = users.stream()
                .map(UserResponse::of)
                .collect(Collectors.toList());

        return userResponses;
    }

    // 회원 소프트 삭제
    @Transactional
    public void deleteByUserId(long userId) {
        userRepository.deleteById(userId);
    }

    // 회원 이름 변경
    @Transactional
    public void updateName(Long userId, UserRequest userRequest){

        Optional<User> user = userRepository.findById(userId);

        user.ifPresent(value -> value.setName(userRequest.getName()));
    }
}
