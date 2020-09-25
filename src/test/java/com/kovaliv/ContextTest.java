package com.kovaliv;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.ws.rs.Path;
import java.util.Arrays;

@Slf4j
public class ContextTest {

    @Test
    @Disabled
    void testContext() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");
        LocalSessionFactoryBean sessionFactoryBean = context.getBean(LocalSessionFactoryBean.class);
        log.info(context.getBean("sessionFactory").getClass().toString());
        log.info(Arrays.toString(context.getBeanDefinitionNames()));

        for (Object resource : context.getBeansWithAnnotation(Path.class).values()) {
            log.info(resource.getClass().toString());
        }
    }
}