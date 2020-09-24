package com.kovaliv;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class ContextTest {

    @Test
    @Disabled
    void testContext() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");
        LocalSessionFactoryBean sessionFactoryBean = context.getBean(LocalSessionFactoryBean.class);
        assertNotNull(sessionFactoryBean.getObject());
    }
}