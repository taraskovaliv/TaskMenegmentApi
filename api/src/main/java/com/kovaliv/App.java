package com.kovaliv;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.exception.LiquibaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ws.rs.Path;
import javax.ws.rs.core.Feature;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.Arrays;

@Slf4j
public class App extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<Configuration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(Configuration configuration) {
                log.info("Configure swagger");
                SwaggerBundleConfiguration bundleConfiguration = new SwaggerBundleConfiguration();
                bundleConfiguration.setResourcePackage("com.kovaliv");
                bundleConfiguration.setHost("localhost:8080");
                return bundleConfiguration;
            }
        });
    }

    @Override
    public void run(Configuration c, Environment e) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");

        log.info("Adding resources");
        for (Object resource : context.getBeansWithAnnotation(Path.class).values()) {
            log.info("Registered " + resource.getClass().getName() + " class");
            e.jersey().register(resource);
        }
        for (Object resource : context.getBeansOfType(ExceptionMapper.class).values()) {
            log.info("Registered " + resource.getClass().getName() + " class");
            e.jersey().register(resource);
        }
        for (Object resource : context.getBeansOfType(Feature.class).values()) {
            log.info("Registered " + resource.getClass().getName() + " class");
            e.jersey().register(resource);
        }

        try {
            log.info("Updating liquibase");
            context.getBean(Liquibase.class).update(new Contexts());
        } catch (LiquibaseException liquibaseException) {
            log.error(Arrays.toString(liquibaseException.getStackTrace()));
        }
    }
}
