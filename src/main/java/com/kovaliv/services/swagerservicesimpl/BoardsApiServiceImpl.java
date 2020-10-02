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
public class BoardsApiServiceImpl extends BoardsApiService {
    private final com.kovaliv.services.Service<com.kovaliv.models.Board> boardService;
    private final ModelMapper modelMapper;

    @Override
    public Response getAllBoards(SecurityContext securityContext) {
        return Response.ok(boardService.getAll(com.kovaliv.models.Board.class)
                .stream()
                .map(b -> modelMapper.map(b, Board.class))
                .collect(Collectors.toList())
        ).build();
    }
}
