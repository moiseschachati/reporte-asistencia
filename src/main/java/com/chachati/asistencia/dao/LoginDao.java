package com.chachati.asistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.chachati.asistencia.utils.ConnectionProvider;
import com.chachati.asistencia.utils.PasswordEncryption;

public class LoginDao {
    final static Logger logger = Logger.getLogger(EmployeeDataDAO.class);

    public LoginDao() {}

    public static String getPassword(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectionProvider.getCon();

            ps = con.prepareStatement(
                    "SELECT encrypted_password FROM dbo.user_login WHERE user_id = (SELECT USERID FROM dbo.USERINFO WHERE SSN = ? ) ");
            ps.setString(1, userId);

            ResultSet rs = ps.executeQuery();
            logger.info("Verifying password for user: [" + userId + "]");
            if (rs.next()) {
                return rs.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ps != null) {
                ps.close();
            }

            if (con != null) {
                con.close();
            }

        }

        return "";
    }

    public static void setPassword(String userId, String password) {
        String encryptedPassword = PasswordEncryption.hashPassword(password);

        try {
            Connection con = ConnectionProvider.getCon();
            PreparedStatement ps = con.prepareStatement("INSERT INTO dbo.user_login (user_id, encrypted_password) "
                    + "VALUES ((SELECT USERID FROM dbo.USERINFO WHERE SSN = ? ), ?)");
            ps.setString(1, userId);
            ps.setString(2, encryptedPassword);
            ps.executeUpdate();

            logger.info("Created new password for user: [" + userId + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Boolean userExists(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectionProvider.getCon();

            ps = con.prepareStatement("SELECT USERID FROM dbo.USERINFO WHERE SSN = ?");
            ps.setString(1, userId);

            ResultSet rs = ps.executeQuery();
            logger.info("Verifying existance of user: [" + userId + "]");

            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public static Boolean passwordExists(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectionProvider.getCon();

            ps = con.prepareStatement(
                    "SELECT user_id FROM dbo.user_login ul WHERE user_id IN (SELECT USERID FROM dbo.USERINFO WHERE SSN = ?)");
            ps.setString(1, userId);

            ResultSet rs = ps.executeQuery();
            logger.info("Verifying existance of password for user: [" + userId + "]");

            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

}
