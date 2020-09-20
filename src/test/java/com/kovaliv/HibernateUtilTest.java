package com.kovaliv;

import com.kovaliv.config.HibernateUtil;
import com.kovaliv.models.Task;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

public class HibernateUtilTest {

    @Test
    void addAndDeleteTask() {
        Session session = HibernateUtil.beginTransaction();

        Task task = new Task();
        task.setTaskId(1);
        task.setText("Text");
        task.setHeader("Header");

        session.save(task);
        session.getTransaction().commit();

        session.beginTransaction();
        session.remove(task);
        session.getTransaction().commit();
    }
}
