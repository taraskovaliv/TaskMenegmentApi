package com.kovaliv.security.services;

import com.kovaliv.security.dtos.LoginDto;
import com.kovaliv.security.models.User;
import com.kovaliv.security.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService extends com.kovaliv.services.Service<User> {
    private final UserRepo userRepo;

    public User getByLogin(String login) {
        return userRepo.getByLogin(login);
    }

    public void save(User user) {
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        repo.save(user);
    }

    public boolean login(LoginDto loginDto) {
        User user = getByLogin(loginDto.getLogin());
        if (user == null) {
            return false;
        }
        String decodedPassword = new String(Base64.getDecoder().decode(user.getPassword()));
        return decodedPassword.equals(loginDto.getPassword());
    }

    public boolean signup(LoginDto loginDto) {
        if (getByLogin(loginDto.getLogin()) != null) {
            return false;
        }

        User user = new User();
        user.setLogin(loginDto.getLogin());
        user.setPassword(Base64.getEncoder().encodeToString(loginDto.getPassword().getBytes()));
        save(user);

        log.info("Sign up - " + user.toString());
        return true;
    }
}
