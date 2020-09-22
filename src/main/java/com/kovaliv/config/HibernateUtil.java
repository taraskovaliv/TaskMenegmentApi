package com.kovaliv.config;

import com.kovaliv.models.Board;
import com.kovaliv.models.Column;
import com.kovaliv.models.Comment;
import com.kovaliv.models.Task;
import com.kovaliv.security.models.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

@Slf4j
public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure(new File("src/main/resources/hibernate.cfg.xml"));
            return addAnnotatedClasses(configuration).buildSessionFactory();
        } catch (Throwable ex) {
            log.error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void shutdown() {
        getSession().close();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    private static Configuration addAnnotatedClasses(Configuration configuration) {
        configuration.addAnnotatedClass(Comment.class);
        configuration.addAnnotatedClass(Column.class);
        configuration.addAnnotatedClass(Board.class);
        configuration.addAnnotatedClass(Task.class);
        configuration.addAnnotatedClass(User.class);
        return configuration;
    }
}
