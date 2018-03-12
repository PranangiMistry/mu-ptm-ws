package com.integ.ptm.api;


import com.integ.ptm.DBService;
import com.integ.ptm.module.MyResponse;
import com.integ.ptm.module.SupervisorSubstitution;
import com.integ.ptm.service.SupervisorSubstitutionService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: mpanchal
 * Date: 2/22/18 3:31 PM
 */
@Path("SupervisorSubstitution")
public class SupervisorSubstitutionAPI {

    @Inject
    protected List<DBService> db;

    /*@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String Demo(){
        return "ok";
    }*/

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse insert(SupervisorSubstitution ss)
    {
        System.out.println("______________"+ss);
        Object obj=new Object();
        obj=(Object)ss;
        Object result=db.get(0).insert(obj);
        MyResponse myResponse=new MyResponse();
        if(result.equals(0))
        {
            myResponse.setMsg("value not inserted");
            return myResponse;
        }
        else
        {
            myResponse.setMsg("valus inserted");
            return myResponse;
        }
    }

    @GET
    @Path("/approverlist")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Object> approverList(){
        SupervisorSubstitutionService ss=(SupervisorSubstitutionService) db.get(0);
        ArrayList<Object> approverArray = new ArrayList<>();
        List<Map<String,Object>> approverlist=ss.ApprovarDropdown();
        for (Map<String, Object> row : approverlist) {
           String fName=row.get("uFirstName").toString();
           String lName=row.get("uLastName").toString();
            Number isSupervisor=(Number)row.get("isSupervisor");
            Number isAdmin=(Number)row.get("isAdmin");
            String myValue="";

            if(isAdmin.intValue()==1){
                myValue=fName+"       "+lName+"     "+"Admin";
            }
            if(isSupervisor.intValue()==1){
                myValue=fName+"       "+lName+"      "+"Supervisor";
            }
            if(isAdmin.intValue()==1 && isSupervisor.intValue()==1){
                myValue=fName+"       "+lName+"     "+"Admin"+","+"Supervisor";
            }
            Map<String, String> map = new HashMap<>();
            map.put("index", row.getOrDefault("userId", "").toString());
            map.put("value", row.getOrDefault("userId", "").toString());
            map.put("text", myValue);
            approverArray.add(map);
        }
        return approverArray;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String,Object>> retrive()
    {
        return db.get(0).retrieve();
    }

    @GET
    @Path("/{supervisorID}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String,Object>> substituteByMe(@PathParam("supervisorID") String supervisorID){
      return db.get(0).retrieveById(supervisorID);
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse update(SupervisorSubstitution ss)
    {
        System.out.println("update-API==="+ss);
        Object obj=db.get(0).update(ss);
        MyResponse myResponse= new MyResponse();
        if(obj.equals(0))
        {
            myResponse.setMsg("fail to update supervisor substitute");
        }
        else
        {
            myResponse.setMsg("update supervisor substitute successfully");
        }
        return myResponse;
    }

    @GET
    @Path("/retriveBy/{substituteSupervisorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String,Object>> substituteByAdmin(@PathParam("substituteSupervisorId") String substituteSupervisorId){
        SupervisorSubstitutionService supervisorSubstitutionService=(SupervisorSubstitutionService) db.get(0);
       return supervisorSubstitutionService.retriveForOneSubstitute(substituteSupervisorId);

    }

    @DELETE
    @Path("/{supervisorSubstituteId}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deleteClient(@PathParam("supervisorSubstituteId") String supervisorSubstituteId)
    {
        Object result=db.get(0).delete(supervisorSubstituteId);
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
 }
