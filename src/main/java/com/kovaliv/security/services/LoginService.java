package com.kovaliv.security.services;

import com.kovaliv.security.dtos.LoginDto;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public boolean login(LoginDto loginDto) {
        return true;
    }
}