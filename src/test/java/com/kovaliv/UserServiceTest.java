package com.kovaliv;

import com.kovaliv.security.models.User;
import com.kovaliv.security.services.UserService;
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
    void addAndDeleteUser() {
        User user = new User();
        user.setLogin("admin");
        user.setPassword("taras");

        UserService userService = new UserService();
        userService.save(user);
        userService.delete(user);
    }

    @Test
    void getByLoginTest() {
        UserService userService = new UserService();
        userService.getUserByLogin("taras");
    }

    @Test
    void getByIdTest() {
        UserService userService = new UserService();
        userService.getUserById(1);
    }
}
