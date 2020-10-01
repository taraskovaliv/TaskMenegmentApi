package com.kovaliv.services.swagerservicesimpl;

import com.kovaliv.api.BoardsApiService;
import com.kovaliv.api.model.Board;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardApiServiceImpl extends BoardsApiService {
    private final com.kovaliv.services.Service<com.kovaliv.models.Board> boardService;
    private final ModelMapper modelMapper;

    @Override
    public Response createBoard(Board board, SecurityContext securityContext) {
        return Response.ok(modelMapper.map(
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
    public Response getAllBoards(SecurityContext securityContext) {
        return Response.ok(boardService.getAll(com.kovaliv.models.Board.class)
                .stream()
                .map(b -> modelMapper.map(b, Board.class))
                .collect(Collectors.toList())
        ).build();
    }
}
