package com.kovaliv.security.services;

import com.kovaliv.aspect.CountTime;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Service
public class JWTService implements TokenService {

    private final Key key;
    private String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

    public JWTService() {
        key = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());
    }


    @Override
    @CountTime
    public String encode(String login) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(login)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(5, ChronoUnit.DAYS)))
                .signWith(key)
                .compact();
    }

    @Override
    @CountTime
    public String decode(String token) {
        if (token.isEmpty()) {
            throw new NullPointerException("Token was empty");
        }

        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

        return jwt.getBody().getSubject();
    }
}
