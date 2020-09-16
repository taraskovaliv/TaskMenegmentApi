package com.kovaliv.security;

import com.kovaliv.security.dtos.LoginDto;
import com.kovaliv.security.dtos.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {

    @Autowired
    private final LoginService loginService;

    @GET
    public Response get(LoginDto loginDto) {
        log.info("Logged");
        return loginService.login(loginDto) ? Response.ok().build() : Response.status(Response.Status.FORBIDDEN).build();
    }
}
