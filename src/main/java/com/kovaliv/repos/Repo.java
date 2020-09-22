package com.kovaliv.repos;

import com.kovaliv.config.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
public class Repo<T> {

    public T getById(Class<T> clazz, Integer id) {
        Session session = HibernateUtil.getSession();

        T t = session.load(clazz, id);
        Hibernate.initialize(t);
        log.info("Getted - " + t.toString());

        return t;
    }

    @Transactional
    public void save(T toSave) {
        Session session = HibernateUtil.getSession();

        session.save(toSave);
        log.info("Saved - " + toSave.toString());
    }

    @Transactional
    public void delete(T toDelete) {
        Session session = HibernateUtil.getSession();

        session.delete(toDelete);
        log.info("Deleted - " + toDelete.toString());
    }

    public List<T> getAll(Class<T> clazz) {
        Session session = HibernateUtil.getSession();

        Query<T> t = session.createQuery("from " + clazz.getName(), clazz);
        log.info("Getted - " + t.getResultList().toString());

        return t.getResultList();
    }
}
