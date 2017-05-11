package com.chachati.asistencia;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class AuthenFilter implements Filter {

    final static Logger logger = Logger.getLogger(AuthenFilter.class);

    public AuthenFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(true);
        String loginURI = request.getContextPath() + "/login.jsp";

        String URI = request.getRequestURI();

        if (URI.endsWith(".css") || URI.endsWith("auth") || URI.endsWith("grid")) {
            chain.doFilter(request, response);
            return;
        }

        boolean loggedIn = session != null && session.getAttribute("username") != null;
        boolean loginRequest = URI.equals(loginURI);

        if (loggedIn || loginRequest) {
            if (loggedIn)
                logger.info("User [" + session.getAttribute("username") + "] logged in, redirecting to requested page");
            // User is logged in, just continue request.
            chain.doFilter(request, response); 
        } else {
            logger.info("User not logged in, redirecting to login page");
            session.setAttribute("errorMessage", "Por favor iniciar sesión para continuar");
            // Not logged in, show login page.
            response.sendRedirect(request.getContextPath() + "/login.jsp"); 
        }
    }

    public void init(FilterConfig config) throws ServletException {}

}
