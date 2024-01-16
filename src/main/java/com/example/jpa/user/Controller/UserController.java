package com.example.jpa.user.Controller;

import com.example.jpa.user.entity.User;
import com.example.jpa.user.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/users/save")
    public void userSave(@RequestBody User user) {
        userRepository.save(user);
    }
}
