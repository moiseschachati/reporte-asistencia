package com.chachati.asistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.chachati.asistencia.Company;
import com.chachati.asistencia.EmployeeDataTO;
import com.chachati.asistencia.utils.ConnectionProvider;

public class EmployeeDataDAO {
    final static Logger logger = Logger.getLogger(EmployeeDataDAO.class);
    
    private static final String EMPLOYEE_NAME_COLUMN_LABEL = "NAME";
    private static final String EMPLOYEE_SSN_COLUMN_LABEL = "SSN";
    private static final String EMPLOYEE_DEFAULTDEPTID_COLUMN_LABEL = "DEPTNAME";
    private static final String COMPANY_RUT_COLUMN_LABEL = "rut";
    private static final String COMPANY_ADDRESS_COLUMN_LABEL = "direccion";
    private static final String COMPANY_NAME_COLUMN_LABEL = "nombre";
    private static final String EMPLOYEE_DATA_DEFAULT_VALUE = "Desconocido";

    public EmployeeDataDAO() {

    }

    public EmployeeDataTO getEmployeeData(String userId) {
        EmployeeDataTO employeeData = new EmployeeDataTO();
        String companyName = EMPLOYEE_DATA_DEFAULT_VALUE;
        String companyRut = EMPLOYEE_DATA_DEFAULT_VALUE;
        String companyAddress = EMPLOYEE_DATA_DEFAULT_VALUE;
        String employeeName = EMPLOYEE_DATA_DEFAULT_VALUE;
        String employeeRut = EMPLOYEE_DATA_DEFAULT_VALUE;
        String employeeDepartment = EMPLOYEE_DATA_DEFAULT_VALUE;
        
        logger.info("Getting user data for user: [" + userId + "]");
        try {
            Connection con = ConnectionProvider.getCon();

            PreparedStatement ps = con.prepareStatement("SELECT ui.NAME, ui.SSN, dep.DEPTNAME "
                    + "FROM dbo.USERINFO ui,  dbo.DEPARTMENTS dep "
                    + "WHERE ui.USERID = (SELECT USERID FROM dbo.USERINFO WHERE SSN = ? ) "
                    + "AND ui.DEFAULTDEPTID = dep.DEPTID ");

            ps.setString(1, userId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employeeName = rs.getString(EMPLOYEE_NAME_COLUMN_LABEL);
                employeeRut = rs.getString(EMPLOYEE_SSN_COLUMN_LABEL);
                employeeDepartment = rs.getString(EMPLOYEE_DEFAULTDEPTID_COLUMN_LABEL);
            }
            employeeData.setEmployeeName(employeeName);
            employeeData.setEmployeeRut(employeeRut);
            employeeData.setEmployeeDepartment(employeeDepartment);

            logger.info("Getting employee data for user: [" + userId + "]");
            
            ps = con.prepareStatement("SELECT bi.rut, bi.direccion, bi.nombre "
                    + "FROM dbo.USERINFO ui, dbo.Business bi,  dbo.user_business bu "
                    + " WHERE ui.USERID = (SELECT USERID FROM dbo.USERINFO WHERE SSN = ? ) "
                    + "AND ui.USERID = bu.userid " + "AND bi.id = bu.id ");

            ps.setString(1, userId);

            List<Company> companies = new ArrayList<Company>();
            
            rs = ps.executeQuery();
            if (rs.next()) {
                companyName = rs.getString(COMPANY_NAME_COLUMN_LABEL);
                companyRut = rs.getString(COMPANY_RUT_COLUMN_LABEL);
                companyAddress = rs.getString(COMPANY_ADDRESS_COLUMN_LABEL);
                companies.add(new Company(companyName, companyRut, companyAddress));
                
                companyName = EMPLOYEE_DATA_DEFAULT_VALUE;
                companyRut = EMPLOYEE_DATA_DEFAULT_VALUE;
                companyAddress = EMPLOYEE_DATA_DEFAULT_VALUE;
            }

            if (companies.isEmpty()) {
                companies.add(new Company(companyName, companyRut, companyAddress));
            }
            
            employeeData.setCompanies(companies);

        } catch (Exception e) {
            logger.info("ERROR Getting employee data from db for user: [" + userId + "]");
        }

        return employeeData;
    }

}
