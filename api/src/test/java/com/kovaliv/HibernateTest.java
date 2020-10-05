package com.kovaliv;

import com.kovaliv.models.Task;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@ExtendWith(DropwizardExtensionsSupport.class)
public class HibernateTest {

    @Autowired
    private SessionFactoryImpl sessionFactory;

    @Test
    @Disabled
    @Transactional
    void addAndDeleteTask() {
        Session session = sessionFactory.openSession();

        Task task = new Task();
        task.setTaskId(1);
        task.setText("Text");
        task.setHeader("Header");

        session.save(task);
        session.remove(task);
    }
}
