package com.example.jpa;

import com.example.jpa.user.Repository.UserRepository;
import com.example.jpa.user.Service.UserService;
import com.example.jpa.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
class JpaApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
	}

	@Test
	void userSave() {
		for (long i = 0L; i < 10; i++) {
			User user = User.builder().userName("Hi" + i).build();
			userRepository.save(user);
		}
	}

	@Test
	void userFind() {
		Optional<User> user = userRepository.findById(3L);
		System.out.println(user.isPresent() ? user.get().toString() : "Nothing");
	}

	@Test
	void userDelete() {
//		userRepository.delete(User.builder().userName("Hi2").id(5L).build());
		userRepository.deleteById(5L); // findById 후 delete
	}

	@BeforeEach
	void insertData() {
		userSave();
	}

	// update 방법1. 변경 감지
	@Test
	void dirtyChecking(){
		userService.userUpdate(3L, "변경된 이름");
	}

	// update 방법2. 병합
	@Test
	void userMerge() {
		User user = User.builder().userName("mergeTest").id(9L).build();
		userRepository.save(user);
	}

	@Test
	void findAllTest() {
		List<User> userList = userRepository.findAll();
		for(User user : userList) log.info("[FindAll]: " + user.getId() + " | " +user.getUserName());
	}
}
