package com.integ.ptm.api;

import javax.inject.Inject;
import javax.ws.rs.*;

import com.integ.ptm.DBService;
import com.integ.ptm.exceptionmapper.*;
import com.integ.ptm.module.Client;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.integ.ptm.module.MyResponse;
import com.integ.ptm.service.ClientService;

/**
 * Author: mpanchal
 * Date: 1/29/18 3:08 PM
 */
@Path("setupclient")
public class ClientAPI {

    @Inject
    protected List<DBService> db;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse insertAllDetails(Client client)
    {
        System.out.println(client);
        Object obj =db.get(0).insert(client);
        MyResponse response = new MyResponse();
        if (obj.equals(0))
        {
            return response;
        }
        else if(obj.equals(-1))
        {
            response.setMsg("client Name alredy exsist");
            return response;
        }
        else
        {
            response.setMsg("value inserted");
            return response;
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> retrieveDetails()
    {
       return db.get(0).retrieve();
    }

    @DELETE
    @Path("/{clientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deleteClient(@PathParam("clientId") String clientId)
    {
        Object result=db.get(0).delete(clientId);
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
//
//    @GET
//    @Path("/clientnames")
//    @Produces(MediaType.APPLICATION_JSON)
//    public ArrayList<Object> retriveClientNames() {
//        ArrayList<Object> clientarray = new ArrayList<>();
//        List<Map<String, Object>> allClients = db.get(0).retrieve();
//        for (Map<String, Object> row : allClients) {
//            Map<String, String> map = new HashMap<>();
//            map.put("index", row.getOrDefault("clientId", "").toString());
//            map.put("id",   row.getOrDefault("clientId","").toString());
//            map.put("value", row.getOrDefault("clientId", "").toString());
//            map.put("text", row.getOrDefault("clientName", "").toString());
//            clientarray.add(map);
//        }
//        return clientarray;
//    }

    Object isAtiveUpdate=0;
    @PUT
    @Path("/updateActiveDeActive")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object updateActiveDeActive(Client obj)
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

    @GET
    @Path("/{clientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>>  retrieveById(@PathParam("clientId") String clientId)
    {
        return db.get(0).retrieveById(clientId);
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public  Object updateClient (Client updateData)
    {
        ClientService cs=(ClientService)db.get(0);
        int updateall=cs.updateClient(updateData);
        MyResponse response = new MyResponse();
         if(updateall!=0)
         {
             return db.get(0).retrieve();
         }

         else {
             response.setMsg("can not be updated");
             return response;
         }
    }

}