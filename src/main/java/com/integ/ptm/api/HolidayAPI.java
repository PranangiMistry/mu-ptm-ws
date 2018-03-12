package com.integ.ptm.api;

import com.integ.ptm.DBService;
import com.integ.ptm.module.Holiday;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("holidays")
public class HolidayAPI {
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
    public Object insertHoliday(Holiday obj) {
        return db.get(0).insert(obj);
    }

    @GET
    @Path("/viewholidays")
    @Produces(MediaType.APPLICATION_JSON)
    public Object aaHolidays() {
        return db.get(0).retrieve();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Object deleteHoliday(@PathParam("id") String id) {
        return db.get(0).delete(id);
    }
}
