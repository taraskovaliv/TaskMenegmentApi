package com.kovaliv.services.swagerservicesimpl;

import com.kovaliv.api.ColumnsApiService;
import com.kovaliv.api.model.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Service
@RequiredArgsConstructor
public class ColumnApiServiceImpl extends ColumnsApiService {
    private final com.kovaliv.services.Service<Column> columnService;

    @Override
    public Response createColumn(Column column, SecurityContext securityContext) {
        return Response.ok(columnService.save(column)).build();
    }

    @Override
    public Response deleteColumn(Column column, SecurityContext securityContext) {
        columnService.delete(column);
        return Response.ok().build();
    }

    @Override
    public Response editColumn(Column column, SecurityContext securityContext) {
        columnService.update(column);
        return Response.ok().build();
    }

    @Override
    public Response getAllColumns(SecurityContext securityContext) {
        return Response.ok(columnService.getAll(Column.class)).build();
    }
}
