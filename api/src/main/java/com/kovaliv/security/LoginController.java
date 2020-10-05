package com.kovaliv.security;

import com.kovaliv.security.dtos.LoginDto;
import com.kovaliv.security.services.TokenService;
import com.kovaliv.security.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Component
@Path("/login")
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {
    private final TokenService tokenService;
    private final UserService userService;

    @POST
    @Path("/signin")
    public Response signIn(LoginDto loginDto) {
        log.info("login");
        if (userService.login(loginDto)) {
            log.info("Logged " + loginDto.getLogin());
            return Response.ok(userService.getByLogin(loginDto.getLogin()))
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenService.encode(loginDto.getLogin()))
                    .build();
        } else {
            log.info("NOT authorized login - " + loginDto.getLogin() + ", password - " + loginDto.getPassword());
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("/signup")
    public Response singUp(LoginDto loginDto) {
        if (userService.signup(loginDto)) {
            return signIn(loginDto);
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
