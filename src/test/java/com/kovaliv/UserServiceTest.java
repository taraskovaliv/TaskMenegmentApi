package com.kovaliv;

import com.kovaliv.security.models.User;
import com.kovaliv.security.services.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {
    @Test
    void contextLoading() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");
        UserService userService = context.getBean(UserService.class);
        assertNotNull(userService);
    }

    @Test
    @Disabled
    void addUser() {
        User user = new User();
        user.setLogin("taras");
        user.setPassword("taras");
        UserService userService = new UserService();
        userService.save(user);
    }

    @Test
    void getByLoginTest() {
        UserService userService = new UserService();
        userService.getUserByLogin("taras");
    }
}
