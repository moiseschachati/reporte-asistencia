package com.chachati.asistencia;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.chachati.asistencia.dao.EmployeeDataDAO;

public class UserInfo extends HttpServlet {

    private static final long serialVersionUID = -6973035721157686970L;
    final static Logger logger = Logger.getLogger(UserInfo.class);

    public UserInfo() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("username");
        logger.info("Showing info for user [" + userId + "]");

        EmployeeDataDAO employeeDataDao = new EmployeeDataDAO();

        EmployeeDataTO employeeData = employeeDataDao.getEmployeeData(userId);

        session.setAttribute("employeeName", employeeData.getEmployeeName());
        session.setAttribute("employeeRut", employeeData.getEmployeeRut());
        session.setAttribute("employeeDepartment", employeeData.getEmployeeDepartment());
        session.setAttribute("companies", employeeData.getCompanies());

        response.sendRedirect("info.jsp");
    }
}
