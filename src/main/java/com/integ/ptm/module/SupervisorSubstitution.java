package com.integ.ptm.module;

/**
 * Author: mpanchal
 * Date: 2/22/18 3:31 PM
 */
public class SupervisorSubstitution {

    String supervisorSubstitutionId;
    String supervisorId;
    String userId;
    String startDate;
    String endDate;
    int sendEmail;

    public String getSupervisorSubstitutionId() {
        return supervisorSubstitutionId;
    }

    public void setSupervisorSubstitutionId(String supervisorSubstitutionId) {
        this.supervisorSubstitutionId = supervisorSubstitutionId;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(int sendEmail) {
        this.sendEmail = sendEmail;
    }
}