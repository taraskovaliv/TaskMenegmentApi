package com.kovaliv.services.swagerservicesimpl;

import com.kovaliv.api.DefaultApiService;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Service
public class MainApiServiceImpl extends DefaultApiService {
    @Override
    public Response get(SecurityContext securityContext) {
        return Response.ok().build();
    }
}