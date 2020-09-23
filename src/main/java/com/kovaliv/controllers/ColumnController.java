package com.kovaliv.controllers;

import com.kovaliv.models.Column;
import com.kovaliv.services.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/column")
@Produces(MediaType.APPLICATION_JSON)
public class ColumnController {
    private final Service<Column> columnService;

    public ColumnController() {
        columnService = new Service<>();
    }

    @GET
    public Response getColumn(Integer id) {
        return Response.ok(columnService.getById(Column.class, id)).build();
    }

    @GET
    @Path("/all")
    public Response getColumns() {
        return Response.ok(columnService.getAll(Column.class)).build();
    }

    @POST
    public Response addColumn(Column column) {
        columnService.save(column);
        return Response.ok().build();
    }

    @PUT
    public Response updateColumn(Column column) {
        columnService.update(column);
        return Response.ok().build();
    }

    @DELETE
    public Response deleteColumn(Column column) {
        columnService.delete(column);
        return Response.ok().build();
    }
}
