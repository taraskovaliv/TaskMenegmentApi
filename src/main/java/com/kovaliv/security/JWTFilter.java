package com.kovaliv.security;

import com.kovaliv.security.services.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
@Component
@RequiredArgsConstructor
public class JWTFilter implements ContainerRequestFilter {
    private final TokenService tokenService;

    private static final String[] loginRequiredURLs = {
            "/comment"
    };

    @Override
    public void filter(ContainerRequestContext context) {
        log.info("Do filter auth");

        String url = context.getUriInfo().getRequestUri().getPath();
        if (isLoginRequired(url)) {
            String token = context.getHeaders()
                    .getFirst(HttpHeaders.AUTHORIZATION);
            try {
                token = token.substring("Bearer".length()).trim();
                tokenService.decode(token);
            } catch (Exception e) {
                e.printStackTrace();
                context.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .type(MediaType.TEXT_PLAIN_TYPE)
                        .entity("Unauthorized")
                        .build());
            }
        }
    }

    private boolean isLoginRequired(String requestURL) {
        for (String loginRequiredURL : loginRequiredURLs) {
            if (requestURL.contains(loginRequiredURL)) {
                return true;
            }
        }
        return false;
    }
}
