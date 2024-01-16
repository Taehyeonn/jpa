package com.example.jpa.user.Service;

import com.example.jpa.user.Repository.UserRepository;
import com.example.jpa.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Transactional // 트랙잭션으로 가둬줘야 변경감지가 가능하다.
    public void userUpdate(Long id, String userName){

        // DB에서 id값을 기준으로 데이터를 찾는다 (영속화)
        Optional<User> user = userRepository.findById(id);

        // 만약 해당 값이 존재한다면 전달받은 name으로 set을 해준다.
        user.ifPresent(value -> value.setUserName(userName));
    }
}
