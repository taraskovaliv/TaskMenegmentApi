package com.kovaliv.controllers;

import com.kovaliv.models.Comment;
import com.kovaliv.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/comment")
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
public class CommentController {
    private final CommentService commentService;

    @GET
    @Path("/{id}")
    public Response getComment(@PathParam("id") Integer id) {
        return Response.ok(commentService.getById(Comment.class, id)).build();
    }

    @POST
    public Response addComment(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, Comment comment) {
        return Response.ok(commentService.save(token, comment)).build();
    }

    @PUT
    public Response updateComment(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, Comment comment) {
        commentService.update(token, comment);
        return Response.ok().build();
    }

    @DELETE
    public Response deleteComment(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, Comment comment) {
        commentService.delete(token, comment);
        return Response.ok().build();
    }
}
