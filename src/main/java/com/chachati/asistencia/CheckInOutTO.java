package com.chachati.asistencia;

public class CheckInOutTO {
    private String checkDate;
    private String checkTime;
    private String checkType;
    private String machineAlias;
    private String sn;
    private String verifyCode;
    
    public CheckInOutTO() {
        // TODO Auto-generated constructor stub
    }
    
    public CheckInOutTO(String checkDate, String checkTime, String checkType, String machineAlias, String sn, String verifyCode) {
        this.checkDate = checkDate;
        this.checkTime = checkTime;
        this.checkType = checkType;
        this.machineAlias = machineAlias;
        this.sn = sn;
        this.verifyCode = verifyCode;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getMachineAlias() {
        return machineAlias;
    }

    public void setMachineAlias(String machineAlias) {
        this.machineAlias = machineAlias;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
