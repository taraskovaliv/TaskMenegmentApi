package com.kovaliv;

import com.kovaliv.config.LiquibaseUtil;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class App extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void run(Configuration c, Environment e) {
        log.info("Updating liquibase");
        LiquibaseUtil.update();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.setParent(new ClassPathXmlApplicationContext("spring_config.xml"));
        context.getBeanFactory().registerSingleton("appConfiguration", c);
        context.registerShutdownHook();
        context.refresh();

        log.info("Registering REST resources");
        e.jersey().packages("com.kovaliv");

    }
}
