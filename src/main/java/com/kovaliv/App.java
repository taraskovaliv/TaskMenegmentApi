package com.kovaliv;

import com.kovaliv.config.LiquibaseUtil;
import com.kovaliv.security.LoginController;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class App extends Application<Configuration> {

    public static ApplicationContext context;

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        context = new ClassPathXmlApplicationContext("spring_config.xml");
    }

    @Override
    public void run(Configuration c, Environment e) {
        log.info("Updating liquibase");
        LiquibaseUtil.update();

        log.info("Registering REST resources");
        e.jersey().packages("com.kovaliv");
        e.jersey().register(LoginController.class);

    }
}
