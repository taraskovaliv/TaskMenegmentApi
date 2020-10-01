package com.kovaliv.services.swagerservicesimpl;

import com.kovaliv.api.ColumnsApiService;
import com.kovaliv.api.model.Column;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColumnApiServiceImpl extends ColumnsApiService {
    private final com.kovaliv.services.Service<com.kovaliv.models.Column> columnService;
    private final ModelMapper modelMapper;

    @Override
    public Response createColumn(Column column, SecurityContext securityContext) {
        return Response.ok(modelMapper.map(
                columnService.save(modelMapper.map(column, com.kovaliv.models.Column.class)),
                Column.class)
        ).build();
    }

    @Override
    public Response deleteColumn(Column column, SecurityContext securityContext) {
        columnService.delete(modelMapper.map(column, com.kovaliv.models.Column.class));
        return Response.ok().build();
    }

    @Override
    public Response editColumn(Column column, SecurityContext securityContext) {
        columnService.update(modelMapper.map(column, com.kovaliv.models.Column.class));
        return Response.ok().build();
    }

    @Override
    public Response getAllColumns(SecurityContext securityContext) {
        return Response.ok(columnService.getAll(com.kovaliv.models.Column.class)
                .stream()
                .map(c -> modelMapper.map(c, Column.class))
                .collect(Collectors.toList())
        ).build();
    }
}
