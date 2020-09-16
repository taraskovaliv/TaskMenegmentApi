package com.kovaliv;

import com.kovaliv.controllers.MainController;
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
    public void initialize(Bootstrap<Configuration> b) {
        context = new ClassPathXmlApplicationContext("spring_config.xml");
    }

    @Override
    public void run(Configuration c, Environment e) throws Exception {
        log.info("Registering REST resources");
        e.jersey().register(new MainController());
        e.jersey().register(new LoginController());
    }
}
