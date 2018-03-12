package com.integ.ptm.api;

import com.integ.ptm.DBService;
import com.integ.ptm.module.Department;
import com.integ.ptm.module.MyResponse;
import com.integ.ptm.module.SubDepartment;
import com.integ.ptm.service.SubDepartmentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("setupSubDepartment")
public class SubDepartmentAPI {

    @Inject
    protected List<DBService> db;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String defaultMessage() {
        return "default";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object insertSubDept(SubDepartment obj) {
        return db.get(0).insert(obj);
    }

//===============================================================================

    @GET
    @Path("/allsubdepartments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> getAllSubDepartments() {
        return db.get(0).retrieve();
    }


    Object isAtiveUpdate=0;
    @PUT
    @Path("/updateActiveDeActive")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object updateActiveDeActive(SubDepartment obj)
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
    @Path("/{subDepartmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deleteSubDepartment(@PathParam("subDepartmentId") String subDepartmentId)
    {
        Object result=db.get(0).delete(subDepartmentId);
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
    @Path("/{subDepartmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>>  retriveById(@PathParam("subDepartmentId") String subDepartmentId)
    {
        return db.get(0).retrieveById(subDepartmentId);
    }


    @PUT
    @Path("/updateSubDepartment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object updateSubDepartment(SubDepartment obj)
    {
        Object res;
        MyResponse response = new MyResponse();
        SubDepartmentService dept= (SubDepartmentService) db.get(0);
        res=dept.updateSubDepartment(obj);
        if(res.equals(0)){
            response.setMsg("can not be updated");
            return response;
        }
        else {
            return db.get(0).retrieve();
        }
    }

}
