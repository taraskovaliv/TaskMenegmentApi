package com.kovaliv;

import com.kovaliv.repos.Repo;
import com.kovaliv.security.models.User;
import com.kovaliv.security.repo.UserRepo;
import com.kovaliv.security.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@Slf4j
class UserServiceTest {

    @Mock
    UserRepo userRepo;

    @Mock
    Repo<User> repo;

    @InjectMocks
    UserService userService;

    User user;


    void init() {
        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setLogin("admin");
        user.setPassword("taras");

        userService.setRepo(repo);
    }

    @Test
    @Disabled
    void contextLoading() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");
        UserService userService = context.getBean(UserService.class);

        assertNotNull(userService);
    }

    @Test
    @Disabled
    void addAndDeleteUser() {
        doNothing().when(repo).save(user);
        doNothing().when(repo).delete(user);

        userService.save(user);
        userService.delete(user);

        verify(repo).save(user);
        verify(repo).delete(user);
    }

    @Test
    @Disabled
    void getByLoginTest() {
        String login = "taras";

        when(userRepo.getByLogin(login)).thenReturn(user);

        User actual = userService.getByLogin(login);

        assertEquals(user, actual);
    }

    @Test
    @Disabled
    void getByIdTest() {
        when(repo.getById(User.class, 1)).thenReturn(user);

        User actual = userService.getById(User.class, 1);

        assertEquals(user, actual);
    }
}
