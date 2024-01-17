package com.example.jpa.user.repository;

import com.example.jpa.user.domain.User;
import com.example.jpa.user.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void 정상회원가입() {

        //given
        User insertUser = userRepository.save(new User("test155", "test155@naver.com"));

        //when
        User saveUser = userRepository.findById(insertUser.getId()).get();

        //then
        assertThat(insertUser).isEqualTo(saveUser);
    }

    @Test
    @Transactional
    public void 이메일로회원조회() {

        //given
        User insertUser = userRepository.save(new User("test200", "test200@naver.com"));

        //when
        User findUser = userRepository.findById(insertUser.getId()).get();

        //then
        assertThat(insertUser.getId()).isEqualTo(findUser.getId());
        assertThat(insertUser.getEmail()).isEqualTo(findUser.getEmail());

    }

    @Test
    @Transactional
    public void 없는회원조회() {

        //given
        long userId = 109L;

        //when
        Optional<User> findUser = userRepository.findById(userId);

        //then
        assertThatThrownBy(findUser::get)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @Transactional
    public void 특정회원삭제() {

        //given
        User insertUser = userRepository.save(new User("test100", "test100@naver.com"));
        User insertUser2 = userRepository.save(new User("test101", "test101@naver.com"));
        System.out.println("insertUser = " + insertUser);
        System.out.println("insertUser2 = " + insertUser2);

        //when
        userRepository.deleteByUserId(insertUser.getId());
        User deletedUser = userRepository.findById(insertUser.getId()).get();

        System.out.println("deletedUser = " + deletedUser);
        System.out.println("insertUser = " + insertUser);

        //then
        assertThat(insertUser.getStatus()).isEqualTo(deletedUser.getStatus());

    }

    @Test
    @Transactional
    public void 회원정보수정() {

        //given
        User insertUser = userRepository.save(new User("test150", "test150@naver.com"));
        String newName = "변경된이름";

        //when
        Optional<User> findUser = userRepository.findById(insertUser.getId());

        findUser.ifPresent(value -> value.setName(newName));

        //then
        assertThat(insertUser.getName()).isEqualTo(newName);
    }
}