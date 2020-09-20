package com.kovaliv.security.services;

import com.kovaliv.security.dtos.LoginDto;
import com.kovaliv.security.models.User;
import com.kovaliv.security.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public UserService() {
        userRepo = new UserRepo();
    }

    public User getUserByLogin(String login) {
        return userRepo.getByLogin(login);
    }

    public User getUserById(Integer id) {
        return userRepo.getById(User.class, id);
    }

    public void save(User user) {
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        userRepo.save(user);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }

    public boolean login(LoginDto loginDto) {
        User user = getUserByLogin(loginDto.getLogin());
        if (user == null) {
            return false;
        }
        String decodedPassword = new String(Base64.getDecoder().decode(user.getPassword()));
        return decodedPassword.equals(loginDto.getPassword());
    }

    public boolean signup(LoginDto loginDto) {
        if (getUserByLogin(loginDto.getLogin()) != null) {
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
