package com.chachati.asistencia;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.chachati.asistencia.dao.CheckInOutDAO;
import com.google.gson.Gson;

public class GridController extends HttpServlet {

    private static final long serialVersionUID = -5848110067998008895L;
    final static Logger logger = Logger.getLogger(GridController.class);

    public GridController() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("username");
        StringBuilder dateRange = new StringBuilder(request.getQueryString());
        logger.info("Showing info for user [" + userId + "]");
        String startDate = dateRange.substring(0, dateRange.indexOf("."));
        String endDate = dateRange.substring(dateRange.indexOf(".") + 1);
        CheckInOutDAO checkInOutDao = new CheckInOutDAO();

        LinkedList<CheckInOutTO> checkInOutTO = checkInOutDao.getEmployeeData(userId, startDate, endDate);

        session.setAttribute("fromDate", startDate);
        session.setAttribute("toDate", endDate);

        session.setAttribute("test2", checkInOutTO);

        String json = new Gson().toJson(checkInOutTO);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
