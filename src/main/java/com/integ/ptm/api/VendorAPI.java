package com.integ.ptm.api;

import com.integ.ptm.DBService;
import com.integ.ptm.module.Client;
import com.integ.ptm.module.MyResponse;
import com.integ.ptm.module.Vendor;
import com.integ.ptm.service.ClientService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: mpanchal
 * Date: 2/13/18 5:20 PM
 */
@Path("setupvendor")
public class VendorAPI {
    @Inject
    protected List<DBService> db;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse insertVendor(Vendor vendor)
    {
        System.out.println(vendor);
        Object obj =db.get(0).insert(vendor);
        MyResponse response = new MyResponse();
        if (obj.equals(0))
        {
            return response;
        }
        else if(obj.equals(-1))
        {
            response.setMsg("vendor Name alredy exsist");
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
    public List<Map<String, Object>> retrieveFromVendor()
    {
        return db.get(0).retrieve();
    }

    @DELETE
    @Path("/{vendorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deleteVendor(@PathParam("vendorId") String vendorId)
    {
        Object result=db.get(0).delete(vendorId);
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

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public  Object updateVendor (Vendor vendor)
    {
        Object object=new Object();
        object =(Object)vendor;
        Object result=db.get(0).update(object);
        MyResponse response = new MyResponse();
        if (result.equals(0))
        {
            response.setMsg("value not updated");
            return response;

        }
        else
        {
            return  db.get(0).retrieve();
        }
    }

    @GET
    @Path("/{vendorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>>  retrieveById(@PathParam("vendorId") String vendorId)
    {
        return db.get(0).retrieveById(vendorId);
    }
}
