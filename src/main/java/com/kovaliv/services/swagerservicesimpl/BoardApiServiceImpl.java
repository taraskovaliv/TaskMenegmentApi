package com.kovaliv.services.swagerservicesimpl;

import com.kovaliv.api.BoardApiService;
import com.kovaliv.api.model.Board;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Service
@RequiredArgsConstructor
public class BoardApiServiceImpl extends BoardApiService {
    private final com.kovaliv.services.Service<com.kovaliv.models.Board> boardService;
    private final ModelMapper modelMapper;

    @Override
    public Response createBoard(Board board, SecurityContext securityContext) {
        return Response.status(Response.Status.CREATED).entity(modelMapper.map(
                boardService.save(modelMapper.map(board, com.kovaliv.models.Board.class)),
                Board.class)
        ).build();
    }

    @Override
    public Response deleteBoard(Board board, SecurityContext securityContext) {
        boardService.delete(modelMapper.map(board, com.kovaliv.models.Board.class));
        return Response.ok().build();
    }

    @Override
    public Response editBoard(Board board, SecurityContext securityContext) {
        boardService.update(modelMapper.map(board, com.kovaliv.models.Board.class));
        return Response.ok().build();
    }

    @Override
    public Response getBoardById(@Min(1) Integer boardId, SecurityContext securityContext) {
        return Response.ok(modelMapper.map(
                boardService.getById(com.kovaliv.models.Board.class, boardId),
                Board.class)
        ).build();
    }
}
