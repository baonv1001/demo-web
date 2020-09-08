package com.webapp.webdemo;

import com.webapp.webdemo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebdemoApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
        System.out.println(userRepository.findById(1L).get());
    }
}
