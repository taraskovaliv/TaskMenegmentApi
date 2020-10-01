package com.kovaliv.services.swagerservicesimpl;

import com.kovaliv.api.BoardsApiService;
import com.kovaliv.api.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Service
@RequiredArgsConstructor
public class BoardApiServiceImpl extends BoardsApiService {
    private final com.kovaliv.services.Service<Board> boardService;

    @Override
    public Response createBoard(Board board, SecurityContext securityContext) {
        return Response.ok(boardService.save(board)).build();
    }

    @Override
    public Response deleteBoard(Board board, SecurityContext securityContext) {
        boardService.delete(board);
        return Response.ok().build();
    }

    @Override
    public Response editBoard(Board board, SecurityContext securityContext) {
        boardService.update(board);
        return Response.ok().build();
    }

    @Override
    public Response getAllBoards(SecurityContext securityContext) {
        return Response.ok(boardService.getAll(Board.class)).build();
    }
}
