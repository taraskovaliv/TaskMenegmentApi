package com.kovaliv;

import com.kovaliv.config.LiquibaseUtil;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ws.rs.Path;

@Slf4j
public class App extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void run(Configuration c, Environment e) {
        log.info("Updating liquibase");
        LiquibaseUtil.update();

        ApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");

        log.info("Adding resources");
        for (Object resource : context.getBeansWithAnnotation(Path.class).values()) {
            e.jersey().register(resource);
        }
    }
}
