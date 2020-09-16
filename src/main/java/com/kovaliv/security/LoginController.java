package com.kovaliv.security;

import com.kovaliv.App;
import com.kovaliv.security.dtos.LoginDto;
import com.kovaliv.security.dtos.LoginService;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {

    private final LoginService loginService;

    public LoginController() {
        loginService = App.context.getBean(LoginService.class);
    }

    @POST
    public Response post(LoginDto loginDto) {
        if (loginService.login(loginDto)) {
            log.info("Logged " + loginDto.getLogin());
            return Response.ok().build();
        } else {
            log.info("NOT authorized login - " + loginDto.getLogin() + ", password - " + loginDto.getPassword());
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
