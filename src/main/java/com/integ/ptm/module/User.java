package com.integ.ptm.module;
import java.util.*;

/**
 * Created by bhavesh on 2/1/18.
 */
public class User{

    protected String userId;
    protected String uFirstName;
    protected String uLastName;
    protected String uEmail;
    protected String uPhone;
    protected String uPhone2;
    protected int uIsActive;
    protected String uPassword;
    protected String streetName1;
    protected String streetName2;
    protected String city;
    protected String state;
    protected String country;
    protected int isVendor;
    protected String vendorName;
    protected int isSupervisor;
    protected int isAdmin;
    protected int isEmployee;
    protected int isProjectManager;
    protected String modifiedDate;
    protected String modifier;

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public int getIsSupervisor() {
        return isSupervisor;
    }

    public void setIsSupervisor(int isSupervisor) {
        this.isSupervisor = isSupervisor;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getIsEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(int isEmployee) {
        this.isEmployee = isEmployee;
    }

    public int getIsProjectManager() {
        return isProjectManager;
    }

    public void setIsProjectManager(int isProjectManager) {
        this.isProjectManager = isProjectManager;
    }

    protected ArrayList<String> roleList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getuFirstName() {
        return uFirstName;
    }

    public void setuFirstName(String uFirstName) {
        this.uFirstName = uFirstName;
    }

    public String getuLastName() {
        return uLastName;
    }

    public void setuLastName(String uLastName) {
        this.uLastName = uLastName;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuPhone2() {
        return uPhone2;
    }

    public void setuPhone2(String uPhone2) {
        this.uPhone2 = uPhone2;
    }


    public int getuIsActive() {
        return uIsActive;
    }

    public void setuIsActive(int uIsActive) {
        this.uIsActive = uIsActive;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
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

    public ArrayList<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(ArrayList<String> roleList) {
        this.roleList = roleList;
    }

    public int getIsVendor() {
        return isVendor;
    }

    public void setIsVendor(int isVendor) {
        this.isVendor = isVendor;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}


