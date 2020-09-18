package com.kovaliv.config;

import com.kovaliv.models.Board;
import com.kovaliv.models.Column;
import com.kovaliv.models.Task;
import com.kovaliv.security.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure(new File("src/main/resources/hibernate.cfg.xml"));
            return addAnnotatedClasses(configuration).buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session beginTransaction() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

    private static Configuration addAnnotatedClasses(Configuration configuration) {
        configuration.addAnnotatedClass(Column.class);
        configuration.addAnnotatedClass(Board.class);
        configuration.addAnnotatedClass(Task.class);
        configuration.addAnnotatedClass(User.class);
        return configuration;
    }
}
