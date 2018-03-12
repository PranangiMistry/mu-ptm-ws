package com.integ.ptm.api;

import com.integ.ptm.DBService;
import com.integ.ptm.module.MyResponse;
import com.integ.ptm.module.PayOrder;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * Author: mpanchal
 * Date: 2/16/18 1:25 PM
 */
@Path("payOrder")
public class PayOrderAPI {

    @Inject
    protected List<DBService> db;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMethod()
    {
        return "get";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse insertIntoPayOrder(PayOrder payOrder){

        System.out.println(payOrder.getCost());
        Object obj=new Object();
        obj=(Object)payOrder;
        Object result=db.get(0).insert(obj);
        MyResponse myResponse=new MyResponse();
        if(result.equals(0))
        {
            myResponse.setMsg("insert fail");
            return myResponse;
        }
        else
        {
            myResponse.setMsg("value inserted");
            return myResponse;
        }
    }

}
