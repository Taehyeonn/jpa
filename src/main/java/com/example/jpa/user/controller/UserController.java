package com.example.jpa.user.controller;

import com.example.jpa.user.domain.User;
import com.example.jpa.user.domain.repository.UserRepository;
import com.example.jpa.user.dto.request.UserRequest;
import com.example.jpa.user.dto.response.UserResponse;
import com.example.jpa.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers() {

        List<UserResponse> userResponses = userService.getUsers();
        log.info("전체 조회 성공 = {}", userResponses);

        return ResponseEntity.ok(userResponses);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> insertUser(@RequestBody UserRequest userRequest) {

        User user = userService.join(new User(userRequest.getName()
                                            , userRequest.getEmail()));

        log.info("회원가입 성공 = {}", user);

        return ResponseEntity.created(URI.create("/users/"+user.getId())).build();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("userId") String userId) {
        log.info("userId = {}", userId);

        User user = userService.findByUserId(Long.valueOf(userId));

        UserResponse userResponse = userService.getUserResponse(user);

        log.info("userResponse = {}", userResponse);

        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") String userId) {

        userService.deleteByUserId(Long.parseLong(userId));

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/users/{userId}")
    public ResponseEntity<Object> updateName(@PathVariable("userId") String userId,
                                             @RequestBody UserRequest userRequest) {
        log.info("userId = {}", userId);
        log.info("userRequest = {}", userRequest);

        userService.updateName(Long.valueOf(userId), userRequest);

        return ResponseEntity.ok().build();
    }
}
