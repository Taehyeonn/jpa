package com.example.jpa.user.dto.response;

import com.example.jpa.user.domain.User;
import com.example.jpa.user.domain.UserState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@ToString
public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private UserState status;

    private LocalDateTime createdDate;

    public UserResponse(Long id, String name, String email, UserState status, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
        this.createdDate = createdDate;
    }

    public static UserResponse of(User user) {
        return new UserResponse(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getStatus(),
                user.getCreatedDate());
    }
}
