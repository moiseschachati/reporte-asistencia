package com.chachati.asistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import com.chachati.asistencia.CheckInOutTO;
import com.chachati.asistencia.utils.ConnectionProvider;

public class CheckInOutDAO {

    private static final String CHECKINOUT_DATA_DEFAULT_VALUE = "Desconocido";

    public CheckInOutDAO() {
        // TODO Auto-generated constructor stub
    }

    public LinkedList<CheckInOutTO> getEmployeeData(String userId, String fromDate, String toDate) {
        StringBuilder checkDateTimeBuilder;
        String checkDate;
        String checkTime = CHECKINOUT_DATA_DEFAULT_VALUE;
        String checkType = CHECKINOUT_DATA_DEFAULT_VALUE;
        String machineAlias = CHECKINOUT_DATA_DEFAULT_VALUE;

        LinkedList<CheckInOutTO> checkInOutList = new LinkedList<CheckInOutTO>();

        try {
            Connection con = ConnectionProvider.getCon();

            PreparedStatement ps = con.prepareStatement("SELECT cio.CHECKTIME, cio.CHECKTYPE, mac.MachineAlias "
                    + "FROM dbo.CHECKINOUT cio, dbo.Machines mac "
                    + "WHERE cio.USERID = (SELECT USERID FROM dbo.USERINFO WHERE SSN = ?) "
                    + "AND mac.MachineNumber = cio.SENSORID "
                    + "AND cio.CHECKTIME BETWEEN ? AND ? " + "ORDER BY CHECKTIME ASC ");

            ps.setString(1, userId);
            ps.setString(2, fromDate);
            ps.setString(3, toDate);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                checkDateTimeBuilder = new StringBuilder(rs.getString("CHECKTIME"));
                checkDate = checkDateTimeBuilder.substring(0, checkDateTimeBuilder.indexOf(" "));
                checkTime = checkDateTimeBuilder.substring(checkDateTimeBuilder.indexOf(" "), checkDateTimeBuilder.indexOf("."));
                checkType = rs.getString("CHECKTYPE");
                machineAlias = rs.getString("MachineAlias");

                checkInOutList
                        .add(new CheckInOutTO(checkDate, checkTime, checkType.contains("o") ? "Salida" : "Entrada", machineAlias));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return checkInOutList;
    }

}
