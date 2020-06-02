package com.example.javaee;

import com.example.javaee.controller.user.UserController;
import com.example.javaee.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class JavaeeApplicationTests {

    @Autowired
    UserController userController;

    @Test
    void contextLoads() throws Exception{
        System.out.println(userController.list());
    }

}
