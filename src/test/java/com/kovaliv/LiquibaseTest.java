package com.kovaliv;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.exception.LiquibaseException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class LiquibaseTest {

    @Test
    @Disabled
    void testUpate() {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");
            Liquibase liquibase = context.getBean(Liquibase.class);
            liquibase.update(new Contexts());
        } catch (LiquibaseException e) {
            e.printStackTrace();
        }
    }
}
