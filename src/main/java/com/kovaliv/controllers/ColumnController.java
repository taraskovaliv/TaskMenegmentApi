package com.kovaliv.controllers;

import com.kovaliv.models.Column;
import com.kovaliv.services.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/column")
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
public class ColumnController {
    private final Service<Column> columnService;

    @GET
    @Path("/{id}")
    public Response getColumn(@PathParam("id") Integer id) {
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
