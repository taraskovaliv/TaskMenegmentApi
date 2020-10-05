package com.kovaliv.security.repo;

import com.kovaliv.repos.Repo;
import com.kovaliv.security.models.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepo extends Repo<User> {

    @Autowired
    public UserRepo(SessionFactoryImpl sessionFactory) {
        super(sessionFactory);
    }

    public User getByLogin(String login) {
        String hql = "FROM User WHERE login = :login";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("login", login);

        User user = query.uniqueResult();
        log.info("Getted - " + user.toString());

        return user;
    }
}
