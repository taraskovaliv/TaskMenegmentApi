package com.kovaliv;

import com.kovaliv.security.services.JWTService;
import com.kovaliv.security.services.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JWTServiceTest {
    @Test
    void contextLoading() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");
        JWTService jwtService = (JWTService) context.getBean(TokenService.class);
        assertNotNull(jwtService);
    }

    @Test
    void encodingAndDecoding() {
        String login = "login";

        JWTService jwtService = new JWTService();

        String token = jwtService.encode(login);

        assertEquals(login, jwtService.decode(token));
    }
}
