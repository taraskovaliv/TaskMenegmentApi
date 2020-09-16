package com.kovaliv.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class JWTFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        String loginURI = httpRequest.getContextPath() + "/login";

        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);

        if (isLoggedIn && isLoginRequest) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/");
            dispatcher.forward(request, response);
        } else if (isLoggedIn || isLoginRequest) {
            chain.doFilter(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
