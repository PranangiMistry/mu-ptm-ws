package com.integ.ptm.api;

import com.integ.ptm.DBService;
import com.integ.ptm.module.Department;
import com.integ.ptm.module.MyResponse;
import com.integ.ptm.service.DepartmentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("setupDepartment")
public class DepartmentAPI {

    @Inject
    protected List<DBService> db;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String defaultM() {
        return "default";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object insertDept(Department obj) {
        return db.get(0).insert(obj);
    }

//    @GET
//    @Path("/departmentnames")
//    @Produces(MediaType.APPLICATION_JSON)
//    public ArrayList<Object> retriveDepartmentNames()
//    {
//        ArrayList<Object> departmentarray=new ArrayList<>();
//        List<Map<String, Object>> allDepartments=db.get(0).retrieve();
//        for (Map<String, Object> row : allDepartments) {
//            Map<String,String> map = new HashMap<>();
//            map.put("index",   row.getOrDefault("departmentId","").toString());
//            map.put("id",   row.getOrDefault("clientId","").toString());
//            map.put("value",   row.getOrDefault("departmentId","").toString());
//            map.put("text",   row.getOrDefault("departmentName","").toString());
//            departmentarray.add(map);
//        }
//        return departmentarray;
//    }

    @GET
    @Path("/alldepartments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> getAllDepartments() {
        return db.get(0).retrieve();
    }


    Object isAtiveUpdate=0;
    @PUT
    @Path("/updateActiveDeActive")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object updateActiveDeActive(Department obj)
    {
        MyResponse response = new MyResponse();
        isAtiveUpdate=db.get(0).update(obj);
        if(isAtiveUpdate.equals(0)){
            response.setMsg("can not be updated");
            return response;
        }
        else {
            return db.get(0).retrieve();
        }
    }

    @DELETE
    @Path("/{departmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deleteDepartment(@PathParam("departmentId") String departmentId)
    {
        Object result=db.get(0).delete(departmentId);
        MyResponse response = new MyResponse();
        if (result.equals(0))
        {
            return response;
        }
        else
        {
            response.setMsg("value deleted");
            return response;
        }

    }

    @GET
    @Path("/{departmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>>  retriveById(@PathParam("departmentId") String departmentId)
    {
        return db.get(0).retrieveById(departmentId);
    }


    @PUT
    @Path("/updateDepartment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object updateDepartment(Department obj)
    {
        Object res;
        MyResponse response = new MyResponse();
        DepartmentService dept= (DepartmentService) db.get(0);
        res=dept.updateDepartment(obj);
        if(res.equals(0)){
            response.setMsg("can not be updated");
            return response;
        }
        else {
            return db.get(0).retrieve();
        }
    }
}
