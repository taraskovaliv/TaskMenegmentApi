package com.kovaliv.controllers;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Component
@Produces(MediaType.APPLICATION_JSON)
public class MainController {
    @GET
    public Response get() {
        return Response.ok().build();
    }
}
