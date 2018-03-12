package com.integ.ptm.module;

/**
 * Created by bhavesh on 2/27/18.
 */
public class Timesheet
{
    protected String timesheetId;
    protected String startDate;
    protected String endDate;
    protected String projectIdFk;
    protected String userIdFk;
    protected String projStaffIdFk;
    protected String tsType;
    protected String weekStartDay;
    protected String weekOff1;
    protected String weekOff2;

    public String getTimesheetId() {
        return timesheetId;
    }

    public void setTimesheetId(String timesheetId) {
        this.timesheetId = timesheetId;
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

    public String getProjectIdFk() {
        return projectIdFk;
    }

    public void setProjectIdFk(String projectIdFk) {
        this.projectIdFk = projectIdFk;
    }

    public String getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(String userIdFk) {
        this.userIdFk = userIdFk;
    }

    public String getProjStaffIdFk() {
        return projStaffIdFk;
    }

    public void setProjStaffIdFk(String projStaffIdFk) {
        this.projStaffIdFk = projStaffIdFk;
    }

    public String getTsType() {
        return tsType;
    }

    public void setTsType(String tsType) {
        this.tsType = tsType;
    }

    public String getWeekStartDay() {
        return weekStartDay;
    }

    public void setWeekStartDay(String weekStartDay) {
        this.weekStartDay = weekStartDay;
    }

    public String getWeekOff1() {
        return weekOff1;
    }

    public void setWeekOff1(String weekOff1) {
        this.weekOff1 = weekOff1;
    }

    public String getWeekOff2() {
        return weekOff2;
    }

    public void setWeekOff2(String weekOff2) {
        this.weekOff2 = weekOff2;
    }
}
