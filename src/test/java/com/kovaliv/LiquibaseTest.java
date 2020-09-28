package com.kovaliv;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MariaDBContainer;

@Slf4j
public class LiquibaseTest {

    @Rule
    public MariaDBContainer mariaDB = new MariaDBContainer();

    @Test
    @Disabled
    void testUpate() {
        try {
            Database database = DatabaseFactory.getInstance().openDatabase(
                    mariaDB.getJdbcUrl(),
                    mariaDB.getUsername(),
                    mariaDB.getPassword(),
                    mariaDB.getDriverClassName(),
                    new ClassLoaderResourceAccessor()
            );
            new Liquibase(
                    "db.changelog/master.xml",
                    new ClassLoaderResourceAccessor(),
                    database
            ).update(new Contexts());
        } catch (LiquibaseException e) {
            e.printStackTrace();
        }
    }
}
