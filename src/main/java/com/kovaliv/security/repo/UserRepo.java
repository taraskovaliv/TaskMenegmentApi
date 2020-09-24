package com.kovaliv.security.repo;

import com.kovaliv.repos.Repo;
import com.kovaliv.security.models.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepo extends Repo<User> {

    public UserRepo() {
        super();
    }

    public User getByLogin(String login) {
        String hql = "CALL p_user(:login)";
        NativeQuery<User> query = session.createNativeQuery(hql, User.class);
        query.setParameter("login", login);

        User user = query.uniqueResult();
        log.info("Getted - " + user.toString());

        return user;
    }
}
