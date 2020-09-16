package com.kovaliv.security;

import com.kovaliv.security.dtos.LoginDto;
import com.kovaliv.security.dtos.LoginService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GET
    public Response get(LoginDto loginDto) {
        return loginService.login(loginDto) ? Response.ok().build() : Response.status(Response.Status.FORBIDDEN).build();
    }
}
