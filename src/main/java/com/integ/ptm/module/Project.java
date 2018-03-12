package com.integ.ptm.module;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by bhavesh on 2/12/18.
 */
public class Project {
    protected String projectId;
    protected String userId;
    protected String departmentId;
    protected String subdepartmentId;
    protected String departmentIdFk;
    protected String subdepartmentIdFk;
    protected String clientIdFk;
    protected String clientId;
    protected String projectName;
    protected String projectType;
    protected String projectStartDate;
    protected String projectEndDate;
    protected String deliverableId;
    protected String deliverableName;
    protected String delStartDate;
    protected String delEndDate;
    protected String activityName;
    protected String activityId;
    protected String projStaffId;
    protected int isActive;
    protected List<Map> listDeliverables;
    protected String supervisor1;
    protected String supervisor2;
    protected String supervisor3;
    protected String timeSheetType;
    protected String tsStartDate;
    protected String weekStartDay;
    protected String weekOff1;
    protected String weekOff2;
    protected String tsEndDate;
    protected String tsSplitDate;
    protected String departmentName;
    protected String subdepartmentName;
    protected String clientName;
    protected int dayNumber;

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getDepartmentIdFk() {
        return departmentIdFk;
    }

    public String getProjStaffId() {
        return projStaffId;
    }

    public void setProjStaffId(String projStaffId) {
        this.projStaffId = projStaffId;
    }

    public void setDepartmentIdFk(String departmentIdFk) {
        this.departmentIdFk = departmentIdFk;
    }

    public String getSubdepartmentIdFk() {
        return subdepartmentIdFk;
    }

    public void setSubdepartmentIdFk(String subdepartmentIdFk) {
        this.subdepartmentIdFk = subdepartmentIdFk;
    }

    public String getClientIdFk() {
        return clientIdFk;
    }

    public void setClientIdFk(String clientIdFk) {
        this.clientIdFk = clientIdFk;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getSubdepartmentName() {
        return subdepartmentName;
    }

    public void setSubdepartmentName(String subdepartmentName) {
        this.subdepartmentName = subdepartmentName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getTsSplitDate() {
        return tsSplitDate;
    }

    public void setTsSplitDate(String tsSplitDate) {
        this.tsSplitDate = tsSplitDate;
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

    public String getTimeSheetType() {
        return timeSheetType;
    }

    public void setTimeSheetType(String timeSheetType) {
        this.timeSheetType = timeSheetType;
    }

    public String getTsStartDate() {
        return tsStartDate;
    }

    public void setTsStartDate(String tsStartDate) {
        this.tsStartDate = tsStartDate;
    }

    public String getTsEndDate() {
        return tsEndDate;
    }

    public void setTsEndDate(String tsEndDate) {
        this.tsEndDate = tsEndDate;
    }

    public List<Map> getListDeliverables() {
        return listDeliverables;
    }

    public void setListDeliverables(List<Map> listDeliverables) {
        this.listDeliverables = listDeliverables;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSupervisor1() {
        return supervisor1;
    }

    public void setSupervisor1(String supervisor1) {
        this.supervisor1 = supervisor1;
    }

    public String getSupervisor2() {
        return supervisor2;
    }

    public void setSupervisor2(String supervisor2) {
        this.supervisor2 = supervisor2;
    }

    public String getSupervisor3() {
        return supervisor3;
    }

    public void setSupervisor3(String supervisor3) {
        this.supervisor3 = supervisor3;
    }

    public String getSubdepartmentId() {
        return subdepartmentId;
    }

    public void setSubdepartmentId(String subdepartmentId) {
        this.subdepartmentId = subdepartmentId;
    }

    public String getDeliverableId() {
        return deliverableId;
    }

    public void setDeliverableId(String deliverableId) {
        this.deliverableId = deliverableId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getDeliverableName() {
        return deliverableName;
    }

    public void setDeliverableName(String deliverableName) {
        this.deliverableName = deliverableName;
    }

    public String getDelStartDate() {
        return delStartDate;
    }

    public void setDelStartDate(String delStartDate) {
        this.delStartDate = delStartDate;
    }

    public String getDelEndDate() {
        return delEndDate;
    }

    public void setDelEndDate(String delEndDate) {
        this.delEndDate = delEndDate;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }



    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public String getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(String projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}
