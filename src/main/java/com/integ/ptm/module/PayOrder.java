package com.integ.ptm.module;

/**
 * Author: mpanchal
 * Date: 2/16/18 1:22 PM
 */
public class PayOrder {

    protected String payOrderId;
    protected String projectId;
    protected String payOrderStartDate;
    protected String payOrderEndDate;
    protected String cost;

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPayOrderStartDate() {
        return payOrderStartDate;
    }

    public void setPayOrderStartDate(String payOrderStartDate) {
        this.payOrderStartDate = payOrderStartDate;
    }

    public String getPayOrderEndDate() {
        return payOrderEndDate;
    }

    public void setPayOrderEndDate(String payOrderEndDate) {
        this.payOrderEndDate = payOrderEndDate;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
