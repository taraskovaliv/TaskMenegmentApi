package com.kovaliv.repos;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
public class Repo<T> {

    protected final SessionFactoryImpl sessionFactory;

    protected Session session;

    @Autowired
    public Repo(SessionFactoryImpl sessionFactory) {
        this.sessionFactory = sessionFactory;
        session = sessionFactory.openSession();
    }

    public T getById(Class<T> clazz, Integer id) {
        T t = session.load(clazz, id);
        Hibernate.initialize(t);
        log.info("Getted - " + t.toString());

        return t;
    }

    @Transactional
    public T save(T toSave) {
        T t = (T) session.save(toSave);
        log.info("Saved - " + toSave.toString());
        return t;
    }

    @Transactional
    public void delete(T toDelete) {
        session.delete(toDelete);
        log.info("Deleted - " + toDelete.toString());
    }

    @Transactional
    public void update(T toUpdate) {
        session.update(toUpdate);
        log.info("Updated - " + toUpdate.toString());
    }

    public List<T> getAll(Class<T> clazz) {
        Query<T> t = session.createQuery("from " + clazz.getName(), clazz);
        log.info("Getted - " + t.getResultList().toString());

        return t.getResultList();
    }
}
