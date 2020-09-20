package com.kovaliv;

import com.kovaliv.security.models.User;
import com.kovaliv.security.repo.UserRepo;
import com.kovaliv.security.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    UserRepo userRepo;

    UserService userService;

    User user;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setLogin("admin");
        user.setPassword("taras");

        userService = new UserService(userRepo);

    }

    @Test
    void contextLoading() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");
        UserService userService = context.getBean(UserService.class);

        assertNotNull(userService);
    }

    @Test
    void addAndDeleteUser() {
        doNothing().when(userRepo).save(user);
        doNothing().when(userRepo).delete(user);

        userService.save(user);
        userService.delete(user);

        verify(userRepo).save(user);
        verify(userRepo).delete(user);
    }

    @Test
    void getByLoginTest() {
        String login = "taras";

        when(userRepo.getByLogin(login)).thenReturn(user);

        User actual = userService.getUserByLogin(login);

        assertEquals(user, actual);
    }

    @Test
    void getByIdTest() {
        when(userRepo.getById(User.class, 1)).thenReturn(user);

        User actual = userService.getUserById(1);

        assertEquals(user, actual);
    }
}
