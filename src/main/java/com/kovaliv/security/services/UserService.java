package com.kovaliv.security.services;

import com.kovaliv.security.dtos.LoginDto;
import com.kovaliv.security.models.User;
import com.kovaliv.security.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService() {
        userRepo = new UserRepo();
    }

    public User getUserByLogin(String login) {
        return userRepo.getByLogin(login);
    }

    public User getUserById(Integer id) {
        return userRepo.getById(id);
    }

    public void save(User user) {
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        userRepo.save(user);
    }

    public boolean login(LoginDto loginDto) {
        User user = getUserByLogin(loginDto.getLogin());
        if (user == null) {
            return false;
        }
        String decodedPassword = new String(Base64.getDecoder().decode(user.getPassword()));
        return decodedPassword.equals(loginDto.getPassword());
    }
}
