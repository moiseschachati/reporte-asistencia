package com.chachati.asistencia;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.chachati.asistencia.dao.LoginDao;
import com.chachati.asistencia.utils.PasswordEncryption;

public class CheckCredentials extends HttpServlet {

    private static final long serialVersionUID = -6087445187686337512L;
    final static Logger logger = Logger.getLogger(CheckCredentials.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        logger.info("Verifying existance of user [" + userId + "]");
        try {
            if (!LoginDao.userExists(userId)) {
                wrongUserPasswordRedirect(session, request, response, userId);
            }
        } catch (SQLException e1) {
            logger.info("Error verifying existance of user [" + userId + "]");
            e1.printStackTrace();
        }
        
        logger.info("Checking credentials for user [" + userId + "]");
        
        String realPassword = userId.substring(userId.indexOf('-')-4, userId.indexOf('-'));
        logger.info("realPassword = [" + realPassword + "]");
        
        if (StringUtils.equals(password, realPassword)) {
            logger.info("Valid credentials for user [" + userId + "]");
            session.setAttribute("username", userId);
            response.sendRedirect(request.getContextPath() + "/userinfo");
        } else {
            wrongUserPasswordRedirect(session, request, response, userId);
        }
    }
    
    private void wrongUserPasswordRedirect(HttpSession session, HttpServletRequest request, HttpServletResponse response, String userId) throws IOException{
        logger.info("Invalid credentials for user [" + userId + "]");
        session.setAttribute("errorMessage", "Usuario o contraseña invalida, intente nuevamente");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
