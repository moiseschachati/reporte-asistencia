package com.chachati.asistencia;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class LogOutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    final static Logger logger = Logger.getLogger(CheckCredentials.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        logger.info("Invalid credentials for user [" + session.getAttribute("username") + "]");
        session.invalidate();

        request.setAttribute("successMessage", "Ha cerrado sesi&oacute;n exitosamente!");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
