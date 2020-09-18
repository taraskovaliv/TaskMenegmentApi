package com.kovaliv;

import com.kovaliv.security.services.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContextTest {

    @Test
    void testContext() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");
        LoginService loginService = context.getBean(LoginService.class);
        assertNotNull(loginService);
    }
}
