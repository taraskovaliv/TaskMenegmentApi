package com.kovaliv.security;

import com.kovaliv.security.dtos.LoginDto;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public boolean login(LoginDto loginDto) {
        return true;
    }
}