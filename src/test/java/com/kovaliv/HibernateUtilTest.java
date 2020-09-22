package com.kovaliv;

import com.kovaliv.config.HibernateUtil;
import com.kovaliv.models.Task;
import org.hibernate.Session;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

public class HibernateUtilTest {

    @Test
    @Disabled
    @Transactional
    void addAndDeleteTask() {
        Session session = HibernateUtil.getSession();

        Task task = new Task();
        task.setTaskId(1);
        task.setText("Text");
        task.setHeader("Header");

        session.save(task);
        session.remove(task);
    }
}
