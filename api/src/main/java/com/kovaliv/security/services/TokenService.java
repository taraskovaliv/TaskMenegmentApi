package com.kovaliv.security.services;

public interface TokenService {
    String encode(String login);

    String decode(String token);
}
