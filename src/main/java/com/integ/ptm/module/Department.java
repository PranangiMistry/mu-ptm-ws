package com.integ.ptm.module;

public class Department {
    protected String departmentId;
    protected String departmentName;
    protected String clientID;
    protected int dIsActive;
    protected String reportToName;
    protected String reportToEmail;
    protected String reportToCell;
    protected String reportToPhone;


    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getClientID() {
        return clientID;
    }

    public int getdIsActive() {
        return dIsActive;
    }

    public void setdIsActive(int dIsActive) {
        this.dIsActive = dIsActive;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getReportToName() {
        return reportToName;
    }

    public void setReportToName(String reportToName) {
        this.reportToName = reportToName;
    }

    public String getReportToEmail() {
        return reportToEmail;
    }

    public void setReportToEmail(String reportToEmail) {
        this.reportToEmail = reportToEmail;
    }

    public String getReportToCell() {
        return reportToCell;
    }

    public void setReportToCell(String reportToCell) {
        this.reportToCell = reportToCell;
    }

    public String getReportToPhone() {
        return reportToPhone;
    }

    public void setReportToPhone(String reportToPhone) {
        this.reportToPhone = reportToPhone;
    }
}
