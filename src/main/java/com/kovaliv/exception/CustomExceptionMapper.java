package com.kovaliv.exception;

import io.dropwizard.jersey.errors.ErrorMessage;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.io.PrintWriter;
import java.io.StringWriter;

@Component
public class CustomExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable ex) {
        StringWriter errorStackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(errorStackTrace));
        ErrorMessage errorMessage = new ErrorMessage(
                getStatusCode(ex),
                ex.getMessage(),
                errorStackTrace.toString()
        );

        return Response.status(getStatusCode(ex))
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private int getStatusCode(Throwable ex) {
        if (ex instanceof WebApplicationException) {
            return ((WebApplicationException) ex).getResponse().getStatus();
        } else {
            return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        }
    }
}
