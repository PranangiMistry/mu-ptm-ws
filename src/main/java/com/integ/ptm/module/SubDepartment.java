package com.integ.ptm.module;

public class SubDepartment {
    protected String subDepartmentId;
    protected String subDepartmentName;
    protected String clientID;
    protected String departmentId;
    protected int dIsActive;
    protected String reportToName;
    protected String reportToEmail;
    protected String reportToCell;
    protected String reportToPhone;

    public String getSubDepartmentId() {
        return subDepartmentId;
    }

    public void setSubDepartmentId(String subDepartmentId) {
        this.subDepartmentId = subDepartmentId;
    }

    public String getSubDepartmentName() {
        return subDepartmentName;
    }

    public void setSubDepartmentName(String subDepartmentName) {
        this.subDepartmentName = subDepartmentName;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public int getdIsActive() {
        return dIsActive;
    }

    public void setdIsActive(int dIsActive) {
        this.dIsActive = dIsActive;
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
