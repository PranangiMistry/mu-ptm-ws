package com.integ.ptm.api;

import com.integ.ptm.DBService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * Created by bhavesh on 2/27/18.
 */
@Path("timesheet")
public class TimesheetAPI {
    @Inject
    protected List<DBService> db;

    @GET
    @Path("/getTimesheetByUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> getTimesheetByUser(@PathParam("id") String userId)
    {
        return db.get(0).retrieveById(userId);
    }

}
