package com.kovaliv.security;

import com.kovaliv.App;
import com.kovaliv.security.dtos.LoginDto;
import com.kovaliv.security.services.TokenService;
import com.kovaliv.security.services.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {

    private final TokenService tokenService;
    private final UserService userService;

    public LoginController() {
        userService = App.context.getBean(UserService.class);
        tokenService = App.context.getBean(TokenService.class);
    }

    @POST
    @Path("/login")
    public Response signIn(LoginDto loginDto) {
        if (userService.login(loginDto)) {
            log.info("Logged " + loginDto.getLogin());
            return Response.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenService.encode(loginDto.getLogin()))
                    .build();
        } else {
            log.info("NOT authorized login - " + loginDto.getLogin() + ", password - " + loginDto.getPassword());
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("/signup")
    public Response singIn(LoginDto loginDto) {
        if (userService.signup(loginDto)) {
            return signIn(loginDto);
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
