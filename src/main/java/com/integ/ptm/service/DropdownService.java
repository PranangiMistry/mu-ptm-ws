package com.integ.ptm.service;

import com.integ.ptm.DBService;
import com.integ.ptm.DataBaseHandle;
import com.integ.ptm.module.Timesheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bhavesh on 2/21/18.
 */
public class DropdownService implements DBService{
    List<Map<String, Object>> resultSetDataOfDepartment;
    public List<Map<String, Object>> retrieveDepartment() {

        DataBaseHandle.runDB(da->{
            resultSetDataOfDepartment= da.executeQuery("SELECT * FROM departments");
        });
        return resultSetDataOfDepartment;
    }
    List<Map<String, Object>> employeeData=new ArrayList<Map<String, Object>>();

    public List<Map<String, Object>> retrieveEmployees() {
        DataBaseHandle.runDB(da -> {

            employeeData=da.executeQuery("SELECT user_id,u_first_name FROM users WHERE is_employee=1");

        });
        return employeeData;
    }
    List<Map<String, Object>> resultSetDataOfClient;
    public List<Map<String, Object>> retrieveClients() {

        DataBaseHandle.runDB(da->{
            resultSetDataOfClient= da.executeQuery("SELECT * FROM client");
            for (Map<String, Object> row:resultSetDataOfClient) {
                Number isActive= (Number)row.get("isActive");
                if(isActive.intValue()==0){
                    row.put("isActive",false);
                }
                else
                {
                    row.put("isActive",true);
                }
            }
        });
        return resultSetDataOfClient;
    }
    List<Map<String, Object>> deliverableData;
    public List<Map<String, Object>> getDeliverables() {
        DataBaseHandle.runDB(da -> {
            deliverableData = da.executeQuery("SELECT deliverable_id,project_id_fk,deliverable_name FROM projectDeliverables");
        });
        return deliverableData;
    }
    List<Map<String, Object>> subdepartmentData;
    public List<Map<String, Object>> getSubDepartments() {
        DataBaseHandle.runDB(da -> {
            subdepartmentData = da.executeQuery("SELECT subdepartment_id,subdepartment_name,department_id FROM subdepartments");
        });
        return subdepartmentData;
    }
    List<Map<String, Object>> activityData;
    public List<Map<String, Object>> retrieveActivity() {
        DataBaseHandle.runDB(da -> {
            activityData = da.executeQuery("SELECT activity_id,activity_name FROM activity");
        });
        return activityData;
    }

    List<Map<String, Object>> projectData;
    public List<Map<String, Object>> retrieveProjects() {
        DataBaseHandle.runDB(da -> {
            projectData = da.executeQuery("SELECT project_id,project_name FROM projects where is_active=1");
        });
        return projectData;
    }
    List<Map<String, Object>> projectDataByUser;

    public List<Map<String, Object>> retrieveProjectsByUser(String userId) {
        DataBaseHandle.runDB(da -> {
            projectDataByUser = da.executeQuery("SELECT DISTINCT projectstaff.project_id_fk,projects.project_name FROM projectstaff INNER JOIN projects ON projectstaff.project_id_fk=projects.project_id where projectstaff.user_id_fk='"+userId+"'");
        });
        return projectDataByUser;
    }
    List<Map<String, Object>> timesheetByProject;

    public List<Map<String, Object>> retrieveTimesheetByProject(Timesheet obj) {
        DataBaseHandle.runDB(da -> {
            timesheetByProject = da.executeQuery("SELECT projectstaff.proj_staff_id,projectstaff.ts_type,users.u_first_name FROM projectstaff INNER JOIN users ON projectstaff.supervisor1=users.user_id where projectstaff.user_id_fk='"+obj.getUserIdFk()+"' AND projectstaff.project_id_fk='"+obj.getProjectIdFk()+"'");
        });
        return timesheetByProject;
    }

    List<Map<String, Object>> listOfSupervisors;

    public List<Map<String, Object>> retrieveSupervisor() {
        DataBaseHandle.runDB(da -> {
            listOfSupervisors = da.executeQuery("SELECT u_first_name,user_id from users where is_supervisor=1");
        });
        return listOfSupervisors;
    }


    List<Map<String,Object>> AllvendorData;
    public List<Map<String, Object>> retrieveVendor() {
        DataBaseHandle.runDB(da->{
            AllvendorData= da.executeQuery("SELECT * FROM vendor");

        });
        return AllvendorData;

    }

    List<Map<String,Object>> supervisorList;
    public List<Map<String,Object>> supervisorDropDown()
    {
        DataBaseHandle.runDB(da->{
            supervisorList=da.executeQuery("SELECT user_id,u_first_name,u_last_name FROM users WHERE is_supervisor=1");
        });

        return supervisorList;
    }

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

    @Override
    public List<Map<String, Object>> retrieveById(String obj) {
        return null;
    }
}
