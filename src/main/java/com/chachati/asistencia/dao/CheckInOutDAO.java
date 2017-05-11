package com.chachati.asistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.log4j.Logger;

import com.chachati.asistencia.CheckInOutTO;
import com.chachati.asistencia.utils.ConnectionProvider;
import com.chachati.asistencia.utils.DateUtils;

public class CheckInOutDAO {

    final static Logger logger = Logger.getLogger(CheckInOutDAO.class);
    private static final String CHECKINOUT_DATA_DEFAULT_VALUE = "Desconocido";
    private static final Map<Integer, String> verifyMethodMap;
    
    static {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "Huella");
        map.put(2, "Clave");
        map.put(4, "Tarjeta");
        verifyMethodMap = Collections.unmodifiableMap(map);
    }
    public CheckInOutDAO() {
    }

    public LinkedList<CheckInOutTO> getEmployeeData(String userId, String fromDate, String toDate) {
        StringBuilder checkTimeBuilder;
        String checkTime = CHECKINOUT_DATA_DEFAULT_VALUE;
        String checkType = CHECKINOUT_DATA_DEFAULT_VALUE;
        String sn = CHECKINOUT_DATA_DEFAULT_VALUE;
        String machineAlias = CHECKINOUT_DATA_DEFAULT_VALUE;
        String verifyCode = CHECKINOUT_DATA_DEFAULT_VALUE;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        Date parsedFromDate = null;
        Date parsedToDate = null;
        try {
            parsedFromDate = format.parse(fromDate);
            parsedToDate = format.parse(toDate);
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            logger.debug(e1);
        }
        java.sql.Date sqlFromDate = new java.sql.Date(parsedFromDate.getTime());
        java.sql.Date sqlToDate = new java.sql.Date(parsedToDate.getTime());

        LinkedList<CheckInOutTO> checkInOutList = new LinkedList<CheckInOutTO>();

        try {
            Connection con = ConnectionProvider.getCon();

            logger.info("Getting asistance data for user: [" + userId + "]");
            
            PreparedStatement ps = con.prepareStatement("SELECT cio.CHECKTIME, cio.CHECKTYPE, mac.MachineAlias, cio.sn, cio.VERIFYCODE "
                    + "FROM dbo.CHECKINOUT cio, dbo.Machines mac "
                    + "WHERE cio.USERID = (SELECT USERID FROM dbo.USERINFO WHERE SSN = ?) "
                    + "AND mac.MachineNumber = cio.SENSORID "
                    + "AND cio.CHECKTIME BETWEEN ? AND ? " + "ORDER BY CHECKTIME ASC ");

            ps.setString(1, userId);
            ps.setDate(2, sqlFromDate);
            ps.setDate(3, sqlToDate);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp ts = rs.getTimestamp("CHECKTIME");
                
                LocalDate ldt = DateUtils.localDateFromTimestamp(ts).toLocalDate();
                checkTimeBuilder = new StringBuilder(ts.toString());
                checkTime = checkTimeBuilder.substring(checkTimeBuilder.indexOf(" "), checkTimeBuilder.indexOf("."));
                checkType = rs.getString("CHECKTYPE");
                sn = rs.getString("sn");
                machineAlias = rs.getString("MachineAlias");
                verifyCode= verifyMethodMap.get(rs.getInt("VERIFYCODE"));

                checkInOutList
                        .add(new CheckInOutTO(ldt.format(formatter), checkTime, checkType.matches("(o|O|0)") ? "Salida" : "Entrada", machineAlias, sn, verifyCode));
            }

        } catch (Exception e) {
            logger.info("ERROR Getting asistance data from db for user: [" + userId + "]");
        }

        return checkInOutList;
    }

}
