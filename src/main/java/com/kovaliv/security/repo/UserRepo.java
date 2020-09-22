package com.kovaliv.security.repo;

import com.kovaliv.config.HibernateUtil;
import com.kovaliv.repos.Repo;
import com.kovaliv.security.models.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Slf4j
@Repository
@Transactional
public class UserRepo extends Repo<User> {

    public User getByLogin(String login) {
        Session session = HibernateUtil.getSession();

        String hql = "CALL p_user(:login)";
        NativeQuery<User> query = session.createNativeQuery(hql, User.class);
        query.setParameter("login", login);

        User user = query.uniqueResult();
        log.info("Getted - " + user.toString());

        return user;
    }
}
