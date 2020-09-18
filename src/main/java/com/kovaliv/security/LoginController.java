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
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {

    private final TokenService tokenService;
    private final UserService userService;

    public LoginController() {
        userService = App.context.getBean(UserService.class);
        tokenService = App.context.getBean(TokenService.class);
    }

    @POST
    public Response post(LoginDto loginDto) {
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
}
