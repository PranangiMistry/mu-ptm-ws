package com.integ.ptm.module;

/**
 * Author: mpanchal
 * Date: 1/30/18 1:06 PM
 */

import com.integ.ptm.DBService;
import com.integ.ptm.DataBaseHandle;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class Client  {

    protected String clientID;
    protected String clientName;
    protected String clientCode;
    protected String streetName1;
    protected String streetName2;
    protected String city;
    protected String state;
    protected String country;
    protected String zipCode;
    protected String reportToName;
    protected String reportToEmail;
    protected String reportToCell;
    protected String reportToPhone;
    protected int isActive;


    public Client() {
    }


//    public Client(String clientID,String clientName, String clientCode, String streetName1, String streetName2, String city, String state, String country, String zipCode, String reportToName, String reportToEmail, String reportToCell, String reportToPhone, int isActive) {
//        this.clientID=clientID;
//        this.clientName = clientName;
//        this.clientCode = clientCode;
//        this.streetName1 = streetName1;
//        this.streetName2 = streetName2;
//        this.city = city;
//        this.state = state;
//        this.country = country;
//        this.zipCode = zipCode;
//        this.reportToName = reportToName;
//        this.reportToEmail = reportToEmail;
//        this.reportToCell = reportToCell;
//        this.reportToPhone = reportToPhone;
//        this.isActive = isActive;
//    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getStreetName1() {
        return streetName1;
    }

    public void setStreetName1(String streetName1) {
        this.streetName1 = streetName1;
    }

    public String getStreetName2() {
        return streetName2;
    }

    public void setStreetName2(String streetName2) {
        this.streetName2 = streetName2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}