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

    private final Session session = HibernateUtil.getSession();

    public T getById(Class<T> clazz, Integer id) {
        T t = session.load(clazz, id);
        Hibernate.initialize(t);
        log.info("Getted - " + t.toString());

        return t;
    }

    @Transactional
    public void save(T toSave) {
        session.save(toSave);
        log.info("Saved - " + toSave.toString());
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
