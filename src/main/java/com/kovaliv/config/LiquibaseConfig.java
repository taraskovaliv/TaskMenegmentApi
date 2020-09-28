package com.kovaliv.config;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.integration.spring.SpringLiquibase;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class LiquibaseConfig {

    @Bean
    public Liquibase getLiquibase(DataSource dataSource, SpringLiquibase springLiquibase) {
        Liquibase liquibase = null;

        try {
            Database database = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(dataSource.getConnection()));
            liquibase = new Liquibase(springLiquibase.getChangeLog(), new ClassLoaderResourceAccessor(), database);
        } catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        }

        return liquibase;
    }
}
