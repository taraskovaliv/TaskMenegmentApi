package com.kovaliv.controllers;

import com.kovaliv.models.Board;
import com.kovaliv.services.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/board")
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
public class BoardController {
    private final Service<Board> boardService;

    @GET
    @Path("/{id}")
    public Response getBoard(@PathParam("id") Integer id) {
        return Response.ok(boardService.getById(Board.class, id)).build();
    }

    @GET
    @Path("/all")
    public Response getBoards() {
        return Response.ok(boardService.getAll(Board.class)).build();
    }

    @POST
    public Response addBoard(Board board) {
        boardService.save(board);
        return Response.ok().build();
    }

    @PUT
    public Response updateBoard(Board board) {
        boardService.update(board);
        return Response.ok().build();
    }

    @DELETE
    public Response deleteBoard(Board board) {
        boardService.delete(board);
        return Response.ok().build();
    }
}
