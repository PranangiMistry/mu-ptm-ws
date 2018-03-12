package com.integ.ptm.api;

import com.integ.ptm.DBService;
import com.integ.ptm.module.Timesheet;
import com.integ.ptm.service.DropdownService;
import com.integ.ptm.service.ProjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bhavesh on 2/21/18.
 */
@Path("dropdown")
public class DropdownAPI {

    @Inject
    protected List<DBService> db;
    @GET
    @Path("/departmentDropdown")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Object> retrieveDepartmentNames()
    {
        DropdownService ds = (DropdownService) db.get(0);
        ArrayList<Object> departmentArray=new ArrayList<>();
        List<Map<String, Object>> allDepartments=ds.retrieveDepartment();
        for (Map<String, Object> row : allDepartments) {
            Map<String,String> map = new HashMap<>();
            map.put("index",   row.getOrDefault("departmentId","").toString());
            map.put("id",   row.getOrDefault("clientId","").toString());
            map.put("value",   row.getOrDefault("departmentId","").toString());
            map.put("text",   row.getOrDefault("departmentName","").toString());
            departmentArray.add(map);
        }
        return departmentArray;
    }
    @GET
    @Path("/clientDropdown")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Object> retriveClientNames() {
        ArrayList<Object> clientarray = new ArrayList<>();
        DropdownService ds = (DropdownService) db.get(0);
        List<Map<String, Object>> allClients=ds.retrieveClients();
        for (Map<String, Object> row : allClients) {
            Map<String, String> map = new HashMap<>();
            map.put("index", row.getOrDefault("clientId", "").toString());
            map.put("value", row.getOrDefault("clientId", "").toString());
            map.put("text", row.getOrDefault("clientName", "").toString());
            clientarray.add(map);
        }
        return clientarray;
    }
    @GET
    @Path("/employeeDropdown")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getEmployee() {
        DropdownService ds = (DropdownService) db.get(0);
        ArrayList<Object> employeeArray=new ArrayList<>();
        List<Map<String, Object>> allEmployees=ds.retrieveEmployees();
        for (Map<String, Object> row : allEmployees) {
            Map<String,Object> map = new HashMap<>();
            map.put("index",   row.getOrDefault("userId","").toString());
            map.put("key",   row.getOrDefault("userId","").toString());
            map.put("value",   row.getOrDefault("uFirstName","").toString());
            map.put("text",   row.getOrDefault("uFirstName","").toString());
            employeeArray.add(map);
        }
        return employeeArray;
    }
    @GET
    @Path("/deliverableDropdown")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getDeliverables() {
        DropdownService ds = (DropdownService) db.get(0);
        ArrayList<Object> deliverableArray=new ArrayList<>();
        List<Map<String, Object>> allDeliverables=ds.getDeliverables();
        for (Map<String, Object> row : allDeliverables) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",   row.getOrDefault("deliverableId","").toString());
            map.put("label",   row.getOrDefault("deliverableName","").toString());
            map.put("key",   row.getOrDefault("projectIdFk","").toString());
            map.put("value",false);
            deliverableArray.add(map);
        }
        return deliverableArray;
    }
    @GET
    @Path("/subDepartmentDropdown")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getSubDepartments() {
        DropdownService ds = (DropdownService) db.get(0);
        ArrayList<Object> subDepartmentArray=new ArrayList<>();
        List<Map<String, Object>> allSubDepartments=ds.getSubDepartments();
        for (Map<String, Object> row : allSubDepartments) {
            Map<String,String> map = new HashMap<>();
            map.put("index",   row.getOrDefault("subdepartmentId","").toString());
            map.put("id",   row.getOrDefault("departmentId","").toString());
            map.put("value",   row.getOrDefault("subdepartmentName","").toString());
            map.put("text",   row.getOrDefault("subdepartmentName","").toString());
            subDepartmentArray.add(map);
        }
        return subDepartmentArray;
    }
    @GET
    @Path("/activityDropdown")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getActivity() {
        DropdownService ds = (DropdownService) db.get(0);
        ArrayList<Object> activityArray=new ArrayList<>();
        List<Map<String, Object>> allActivity=ds.retrieveActivity();
        for (Map<String, Object> row : allActivity) {
            Map<String,String> map = new HashMap<>();
            map.put("index",   row.getOrDefault("activityId","").toString());
            map.put("value",   row.getOrDefault("activityName","").toString());
            map.put("text",   row.getOrDefault("activityName","").toString());
            activityArray.add(map);
        }
        return activityArray;
    }
    @GET
    @Path("/projectDropdown")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getProjects() {
        DropdownService ds = (DropdownService) db.get(0);
        ArrayList<Object> projectArray=new ArrayList<>();
        List<Map<String, Object>> allProjects=ds.retrieveProjects();
        for (Map<String, Object> row : allProjects) {
            Map<String,String> map = new HashMap<>();
            map.put("index",   row.getOrDefault("projectId","").toString());
            map.put("key",   row.getOrDefault("projectId","").toString());
            map.put("value",   row.getOrDefault("projectName","").toString());
            map.put("text",   row.getOrDefault("projectName","").toString());
            projectArray.add(map);
        }
        return projectArray;
    }
    @GET
    @Path("/supervisorDropdown")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getSupervisors() {
        DropdownService ds = (DropdownService) db.get(0);
        ArrayList<Object> supervisorArray=new ArrayList<>();
        List<Map<String, Object>> allSupervisors=ds.retrieveSupervisor();
        for (Map<String, Object> row : allSupervisors) {
            Map<String,String> map = new HashMap<>();
            map.put("index",   row.getOrDefault("userId","").toString());
            map.put("value",   row.getOrDefault("uFirstName","").toString());
            map.put("text",   row.getOrDefault("uFirstName","").toString());
            supervisorArray.add(map);
        }
        return supervisorArray;
    }
    @GET
    @Path("/vendorDropdown")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Object> retrieveClientNames() {
        DropdownService ds = (DropdownService) db.get(0);
        ArrayList<Object> vendorArray = new ArrayList<>();
        List<Map<String, Object>> allClients = ds.retrieveVendor();
        for (Map<String, Object> row : allClients) {
            Map<String, String> map = new HashMap<>();
            map.put("index", row.getOrDefault("vendorId", "").toString());
            map.put("value", row.getOrDefault("vendorId", "").toString());
            map.put("text", row.getOrDefault("vendorCompanyName", "").toString());
            vendorArray.add(map);
        }
        return vendorArray;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/SuperviorSubstitute-supervisorDropDown")
    public ArrayList<Object> supervisorDropdown()
    {
        DropdownService ds = (DropdownService) db.get(0);
        ArrayList<Object> supervisorArray = new ArrayList<>();
        List<Map<String, Object>> supervisorlist =ds.supervisorDropDown();
        String supervisorName;
        for (Map<String, Object> row : supervisorlist) {
            String fName=row.get("uFirstName").toString();
            String lName=row.get("uLastName").toString();
            supervisorName=fName + "  " + lName;
            Map<String, String> map = new HashMap<>();
            map.put("index", row.getOrDefault("userId", "").toString());
            map.put("value", row.getOrDefault("userId", "").toString());
            map.put("text", supervisorName);
            supervisorArray.add(map);
        }
        return supervisorArray;
    }

    @GET
    @Path("/projectDropdownByUser/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getProjectsByUser(@PathParam("userId") String userId) {
        DropdownService ds = (DropdownService) db.get(0);
        ArrayList<Object> projectArray=new ArrayList<>();
        List<Map<String, Object>> allProjectsByUser=ds.retrieveProjectsByUser(userId);
        for (Map<String, Object> row : allProjectsByUser) {
            Map<String,String> map = new HashMap<>();
            map.put("index",   row.getOrDefault("projectIdFk","").toString());
            map.put("key",   row.getOrDefault("projectIdFk","").toString());
            map.put("value",   row.getOrDefault("projectName","").toString());
            map.put("text",   row.getOrDefault("projectName","").toString());
            projectArray.add(map);
        }
        return projectArray;
    }
    @POST
    @Path("/timesheetByProject")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object getTimesheetByProject(Timesheet obj) {
        DropdownService ds = (DropdownService) db.get(0);
        ArrayList<Object> tsArray=new ArrayList<>();
        List<Map<String, Object>> allTimesheet=ds.retrieveTimesheetByProject(obj);
        for (Map<String, Object> row : allTimesheet) {
            Map<String,String> map = new HashMap<>();
            String name=row.getOrDefault("tsType","").toString()+" ( "+row.getOrDefault("uFirstName","")+" )";

            map.put("index",   row.getOrDefault("projStaffId","").toString());
            map.put("key",   row.getOrDefault("projStaffId","").toString());
            map.put("value",   row.getOrDefault("projStaffId","").toString());
            map.put("text", name);
            tsArray.add(map);
        }
        return tsArray;
    }
}
