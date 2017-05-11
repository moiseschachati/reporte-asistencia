package com.chachati.asistencia;

public class Company {
    private String companyName;
    private String companyRut;
    private String companyAddress;
    private String businessName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyRut() {
        return companyRut;
    }

    public void setCompanyRut(String companyRut) {
        this.companyRut = companyRut;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Company() {}

    public Company(String companyName, String companyRut, String companyAddress) {
        this.companyName = companyName;
        this.companyRut = companyRut;
        this.companyAddress = companyAddress;
    }
}
