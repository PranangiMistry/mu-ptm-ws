package com.integ.ptm.api;

import com.integ.ptm.DBService;
import com.integ.ptm.module.Project;
import com.integ.ptm.service.ProjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bhavesh on 2/12/18.
 */
@Path("setupProject")
public class ProjectAPI {
    @Inject
    protected List<DBService> db;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object insertNewProject(Project obj) {
       return db.get(0).insert(obj);
    }
    @GET
    @Path("/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getProjectById(@PathParam("projectId") String projectId) {
        return db.get(0).retrieveById(projectId);
    }

    @GET
    @Path("/allProjects")
    @Produces(MediaType.APPLICATION_JSON)
    public Object retrieveAllProjects() {
        ProjectService ps = (ProjectService) db.get(0);
       return ps.retrieveAllProjects();
    }
    @GET
    @Path("/projectStaffDisplay")
    @Produces(MediaType.APPLICATION_JSON)
    public Object retrieveProjectStaffDetails() {
        ProjectService ps = (ProjectService) db.get(0);
        return ps.retrieveProjectStaffDetails();
    }
    @GET
    @Path("/editProjectStaff/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object retrieveProjectStaffDetailsByID(@PathParam("id") String projStaffId) {
        ProjectService ps = (ProjectService) db.get(0);
        return ps.retrieveProjectStaffDetailsById(projStaffId);
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object updateProject(Project obj) {
        return db.get(0).update(obj);
    }

    @PUT
    @Path("/updateProjectStaff")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object updateProjectStaff(Project obj) {
        ProjectService ps = (ProjectService) db.get(0);
        return ps.updateProjectStaff(obj);
    }

    @POST
    @Path("/projectStaff")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object insertProjectEmployees(Project obj) {
        ProjectService ps = (ProjectService) db.get(0);
        return ps.insertProjectEmp(obj);
    }
    @POST
    @Path("/activity")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object createActivity(Project obj) {
        ProjectService ps = (ProjectService) db.get(0);
        return ps.insertActivity(obj);
    }
    @POST
    @Path("/projectDeliverable")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object insertProjectDeliverables(Project obj) {
        ProjectService ps = (ProjectService) db.get(0);
        return ps.insertProjectDeliverables(obj);
    }
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Object deleteProjectPermanent(@PathParam("id") String projectId) {
        ProjectService ps = (ProjectService) db.get(0);
        return ps.deleteProjectPermanent(projectId);
    }
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delStaff/{id}")
    public Object deleteProjectStaff(@PathParam("id") String projStaffId) {
        ProjectService ps = (ProjectService) db.get(0);
        return ps.deleteProjectStaff(projStaffId);
    }
}
