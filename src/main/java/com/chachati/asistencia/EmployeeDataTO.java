package com.chachati.asistencia;

import java.util.List;

public class EmployeeDataTO {
    private List<Company> companies;
    private String employeeName;
    private String employeeRut;
    private String employeeDepartment;

    public EmployeeDataTO() {
        // TODO Auto-generated constructor stub
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeRut() {
        return employeeRut;
    }

    public void setEmployeeRut(String employeeRut) {
        this.employeeRut = employeeRut;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }
}
