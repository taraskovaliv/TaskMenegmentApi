package com.kovaliv.security.repo;

import com.kovaliv.config.HibernateUtil;
import com.kovaliv.repos.Repo;
import com.kovaliv.security.models.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepo extends Repo<User> {

    public User getByLogin(String login) {
        Session session = HibernateUtil.beginTransaction();

        String hql = "FROM User WHERE login = :login";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("login", login);

        User user = query.uniqueResult();
        log.info("Getted - " + user.toString());
        session.getTransaction().commit();

        return user;
    }
}
