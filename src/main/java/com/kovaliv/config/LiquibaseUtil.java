package com.kovaliv.config;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LiquibaseUtil {
    private static final Liquibase liquibase = buildLiquibase();

    private static Liquibase buildLiquibase() {
        Database database = null;
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mariadb://172.17.0.2:3306/taskmenegment",
                    "admin", "123456");
            database = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(connection));
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }
        return new Liquibase("db.changelog/master.xml",
                new ClassLoaderResourceAccessor(), database);
    }

    public static void update() {
        try {
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (LiquibaseException e) {
            e.printStackTrace();
        }
    }
}
