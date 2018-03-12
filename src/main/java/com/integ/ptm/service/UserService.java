package com.integ.ptm.service;

import com.integ.ptm.DBService;
import com.integ.ptm.DataBaseHandle;
import com.integ.ptm.exceptionmapper.DBException;
import com.integ.ptm.module.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by bhavesh on 2/6/18.
 */
public class UserService implements DBService {
    int result;
    @Override
    public Object insert(Object obj) {
        DataBaseHandle.runDB(da -> {
            User user=new User();
            user=(User)obj;
            UUID uuidUser = UUID.randomUUID();
            result = da.executeUpdate("INSERT INTO users(user_id,u_first_name,u_last_name,u_email,u_phone,u_phone2,u_is_active,u_password,street_name1,street_name2,city,state,country,is_supervisor,is_admin,is_project_manager,is_employee,is_vendor,vendor_name)" +
                    " VALUES('" + uuidUser.toString() + "','" + user.getuFirstName() + "','" + user.getuLastName() + "','" + user.getuEmail() + "','" + user.getuPhone() + "','" + user.getuPhone2() + "','" + user.getuIsActive() + "','" + user.getuPassword() + "','" + user.getStreetName1() + "','" + user.getStreetName2() + "','" + user.getCity() + "','" + user.getState() + "','" + user.getCountry() + "',"+user.getIsSupervisor()+","+user.getIsAdmin()+","+user.getIsProjectManager()+","+user.getIsEmployee()+","+user.getIsVendor()+",'"+user.getVendorName()+"')");

        });
        return result;
    }

    @Override
    public Object update(Object obj) {
        DataBaseHandle.runDB(da -> {
            User user=new User();
            user=(User)obj;
            if(user.getIsProjectManager()==0&&user.getIsEmployee()==0&&user.getIsAdmin()==0&&user.getIsSupervisor()==0&&user.getIsVendor()==0&&user.getuPassword()==null)
                result = da.executeUpdate("UPDATE users SET u_first_name='"+user.getuFirstName()+"',u_last_name='"+user.getuLastName()+"',u_email='"+user.getuEmail()+"',u_phone='"+user.getuPhone()+"',u_phone2='"+user.getuPhone2()+"',street_name1='"+user.getStreetName1()+"',street_name2='"+user.getStreetName2()+"',city='"+user.getCity()+"',state='"+user.getState()+"',country='"+user.getCountry()+"' WHERE user_id='"+user.getUserId()+"'");
            else if(user.getuPassword()!=null)
            {
                System.out.println("user passowrd==="+user.getuPassword());
                result=da.executeUpdate("UPDATE users SET u_password='"+user.getuPassword()+"' WHERE user_id='"+user.getUserId()+"'");
            }
           else
            result = da.executeUpdate("UPDATE users SET u_first_name='"+user.getuFirstName()+"',u_last_name='"+user.getuLastName()+"',u_email='"+user.getuEmail()+"',u_phone='"+user.getuPhone()+"',u_phone2='"+user.getuPhone2()+"',street_name1='"+user.getStreetName1()+"',street_name2='"+user.getStreetName2()+"',city='"+user.getCity()+"',state='"+user.getState()+"',country='"+user.getCountry()+"',is_vendor="+user.getIsVendor()+",vendor_name='"+user.getVendorName()+"',is_supervisor="+user.getIsSupervisor()+",is_admin="+user.getIsAdmin()+",is_employee="+user.getIsEmployee()+",is_project_manager="+user.getIsProjectManager()+" WHERE user_id='"+user.getUserId()+"'");

        });
        return result;
    }
    public Object deleteUserPermanent(String userId) {
        DataBaseHandle.runDB(da -> {
            result = da.executeUpdate("DELETE from users WHERE user_id='"+userId+"'");

        });
        return result;
    }
    @Override
    public Object delete(Object obj) {
        DataBaseHandle.runDB(da -> {
            User user=new User();
            user=(User)obj;
            result = da.executeUpdate("UPDATE users SET u_is_active="+user.getuIsActive()+" WHERE user_id='"+user.getUserId()+"'");
        });
        return result;
    }
    List<Map<String, Object>> resultSetDataOfUser;
    List<Map<String, Object>> rolesId;
    List<Map<String, Object>> roleName;
    String rr;Number roleId;
    @Override
    public List<Map<String, Object>> retrieve() {

        DataBaseHandle.runDB(da -> {

            resultSetDataOfUser = da.executeQuery("SELECT * FROM USERS");
            for (Map<String, Object> row : resultSetDataOfUser) {
                for (Map.Entry<String, Object> rowEntry : row.entrySet()) {
                    if(rowEntry.getKey().equals("uIsActive"))
                    {
                        Number isActive=(Number)rowEntry.getValue();
                        if(isActive.intValue()==0)
                        {
                            rowEntry.setValue(false);
                        }
                        else
                        {
                            rowEntry.setValue(true);
                        }
                    }
                }
            }
        });
        return resultSetDataOfUser;
    }

    @Override
    public List<Map<String, Object>> retrieveById(String userId) {
        DataBaseHandle.runDB(da -> {
            resultSetDataOfUser = da.executeQuery("SELECT u_first_name,is_vendor,vendor_name,u_last_name,u_email,u_phone,u_phone2,u_is_active,street_name1,street_name2,city,state,country,is_supervisor,is_admin,is_project_manager,is_employee FROM USERS WHERE user_id='" +userId+ "'");
            for (Map<String, Object> row:resultSetDataOfUser) {
                Number isActive= (Number)row.get("isVendor");
                if(isActive.intValue()==0){
                    row.put("isVendor",false);
                }
                else
                {
                    row.put("isVendor",true);
                }
                Number isAdmin= (Number)row.get("isAdmin");
                if(isAdmin.intValue()==0){
                    row.put("isAdmin",false);
                }
                else
                {
                    row.put("isAdmin",true);
                }
                Number isSupervisor= (Number)row.get("isSupervisor");
                if(isSupervisor.intValue()==0){
                    row.put("isSupervisor",false);
                }
                else
                {
                    row.put("isSupervisor",true);
                }
                Number isEmployee= (Number)row.get("isEmployee");
                if(isEmployee.intValue()==0){
                    row.put("isEmployee",false);
                }
                else
                {
                    row.put("isEmployee",true);
                }
                Number isProjectManager= (Number)row.get("isProjectManager");
                if(isProjectManager.intValue()==0){
                    row.put("isProjectManager",false);
                }
                else
                {
                    row.put("isProjectManager",true);
                }
            }
        });
        return resultSetDataOfUser;
    }

    List<Map<String, Object>> authData;
    public List<Map<String, Object>> retrieveAuth() {
        DataBaseHandle.runDB(da -> {
            authData = da.executeQuery("SELECT user_id,u_email,u_password,is_supervisor,is_admin,is_project_manager,is_employee FROM USERS where u_is_active=1");
        });
        return authData;
    }
    List<Map<String, Object>> rolesData;
    List<Map<String, Object>> rolesName;
    ArrayList roles=new ArrayList<String>();
    public ArrayList retrieveRoles(String userId) {
        DataBaseHandle.runDB(da -> {
            rolesData = da.executeQuery("SELECT role_id_FK from roleToUser where user_id_FK='"+userId+"'");
            for (Map<String, Object> row : rolesData) {
                Number roleid = (Number) row.get("roleIdFk");
                rolesName = da.executeQuery("SELECT role_name from role where role_id=" + roleid);
                for (Map<String, Object> row2 : rolesName) {
                    String roleNm = row2.get("roleName").toString();
                    roles.add(roleNm);
                }
            }
            });
        return roles;
    }

}
