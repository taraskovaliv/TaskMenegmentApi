package com.kovaliv.repos;

import com.kovaliv.config.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

@Slf4j
public class Repo<T> {

    public T getById(Class<T> clazz, Integer id) {
        Session session = HibernateUtil.beginTransaction();

        T t = session.load(clazz, id);
        Hibernate.initialize(t);
        log.info("Getted - " + t.toString());

        return t;
    }

    public void save(T toSave) {
        Session session = HibernateUtil.beginTransaction();

        session.save(toSave);
        session.getTransaction().commit();
        log.info("Saved - " + toSave.toString());
    }

    public void delete(T toDelete) {
        Session session = HibernateUtil.beginTransaction();

        session.delete(toDelete);
        session.getTransaction().commit();
        log.info("Deleted - " + toDelete.toString());
    }

    public List<T> getAll(Class<T> clazz) {
        Session session = HibernateUtil.beginTransaction();

        Query<T> t = session.createQuery("from " + clazz.getName(), clazz);
        log.info("Getted - " + t.getResultList().toString());

        return t.getResultList();
    }
}
