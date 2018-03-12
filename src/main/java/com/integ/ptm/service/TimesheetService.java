package com.integ.ptm.service;

import com.integ.ptm.DBService;
import com.integ.ptm.DataBaseHandle;

import java.sql.Array;
import java.util.*;

/**
 * Created by bhavesh on 2/27/18.
 */
public class TimesheetService implements DBService {
    @Override
    public Object insert(Object obj) {
        return null;
    }

    @Override
    public Object update(Object obj) {
        return null;
    }

    @Override
    public Object delete(Object obj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> retrieve() {
        return null;
    }

    List<Map<String, Object>> timesheetByUser,deliverableData,projName,staffName,userName,super1,super2,super3;
    String pName="",sName="",sup1="",sup2="",sup3="",uNm="",del="";

    @Override
    public List<Map<String, Object>> retrieveById(String userId) {
        DataBaseHandle.runDB(da -> {
            timesheetByUser = da.executeQuery("SELECT timesheet_id,project_id_fk,user_id_fk,proj_staff_id_fk,ts_type,start_date,end_date,week_start_day,week_off1,week_off2 FROM TIMESHEET WHERE user_id_fk='" + userId + "' AND status='open'");
            for (Map<String, Object> row : timesheetByUser) {

                String pId = row.get("projectIdFk").toString();
                String sId = row.get("projStaffIdFk").toString();

                projName = da.executeQuery("SELECT project_name from projects WHERE project_id='" + pId + "'");
                for (Map<String, Object> cRow : projName) {
                    pName = cRow.get("projectName").toString();
                }
                ArrayList<Object>  delDropdown=new ArrayList<Object>();
                staffName = da.executeQuery("SELECT user_id_fk,supervisor1,supervisor2,supervisor3,deliverable_ids_fk from projectStaff WHERE proj_staff_id='" + sId + "'");
                for (Map<String, Object> cRow : staffName) {
                    sName = cRow.get("userIdFk").toString();
                    sup1 = cRow.get("supervisor1").toString();
                    del = cRow.get("deliverableIdsFk").toString();
                    System.out.println("DEL  :  "+del);
                    String[] data = del.split("#");
                    delDropdown=new ArrayList<Object>();
                    String delNm="";
                    for(int i=1;i<data.length;i++){
                        deliverableData = da.executeQuery("SELECT deliverable_name FROM projectDeliverables where deliverable_id='"+data[i]+"'");
                        for (Map<String, Object> x : deliverableData) {
                            delNm = x.get("deliverableName").toString();
                        }
                        Map<String,Object> map = new HashMap<>();
                        map.put("index",   data[i]);
                        map.put("value",   delNm);
                        map.put("text",  delNm );
                        delDropdown.add(map);
                    }
                    Object sup2Obj = cRow.getOrDefault("supervisor2","");
                    Object sup3Obj = cRow.getOrDefault("supervisor3","");

                    userName = da.executeQuery("SELECT u_first_name from users WHERE user_id='" + sName + "'");
                    for (Map<String, Object> x : userName) {
                        uNm = x.get("uFirstName").toString();
                    }
                    super1 = da.executeQuery("SELECT u_first_name from users WHERE user_id='" + sup1 + "'");
                    for (Map<String, Object> x : super1) {
                        sup1 = x.get("uFirstName").toString();
                    }
                    if(sup2Obj!=null){
                        super2= da.executeQuery("SELECT u_first_name from users WHERE user_id='" + sup2Obj.toString() + "'");
                        for (Map<String, Object> x : super2) {
                            sup2 = x.get("uFirstName").toString();
                        }
                    }
                    if(sup3Obj!=null){
                        super3= da.executeQuery("SELECT u_first_name from users WHERE user_id='" + sup3Obj.toString() + "'");
                        for (Map<String, Object> x : super3) {
                            sup3 = x.get("uFirstName").toString();
                        }
                    }
                }
                System.out.println("data : === : "+uNm+"  "+sup1+"  "+sup2+"  "+sup3+"  ");
                row.put("supervisor1", sup1);
                row.put("supervisor2", sup2);
                row.put("supervisor3", sup3);
                row.put("userName", uNm);
                row.put("projectName", pName);
                row.put("deliverables",delDropdown);
                System.out.println(delDropdown);
                uNm="";
                sup1="";
                sup2="";
                sup3="";
                pName="";
            }
        });
        return timesheetByUser;
    }
}
