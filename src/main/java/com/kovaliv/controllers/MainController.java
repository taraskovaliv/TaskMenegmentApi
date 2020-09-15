package com.kovaliv.controllers;

import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class MainController {

    private final Validator validator;

    public MainController(Validator validator) {
        this.validator = validator;
    }

    @GET
    public Response getEmployees() {
        return Response.ok().build();
    }
}
