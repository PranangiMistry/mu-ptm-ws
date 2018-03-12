package com.integ.ptm.api;

import com.integ.ptm.DBService;
import com.integ.ptm.module.User;
import com.integ.ptm.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bhavesh on 2/6/18.
 */
@Path("setupUser")
public class UserAPI {

        @Inject
        protected List<DBService> db;

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Object getUserProfile() {
            return db.get(0).retrieve();
        }

        @GET
        @Path("/{userId}")
        @Produces(MediaType.APPLICATION_JSON)
        public Object getUserProfile(@PathParam("userId") String userId) {
            return db.get(0).retrieveById(userId);
        }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Object insertNewUser(User obj) {
            return db.get(0).insert(obj);
        }

        @PUT
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Object updateUserProfile(User obj) {
            return db.get(0).update(obj);
        }

        @PUT
        @Path("/deleteUser")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Object deleteUser(User obj) {
            return db.get(0).delete(obj);
        }

        @DELETE
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/{id}")
        public Object deleteUserPermanent(@PathParam("id") String userId) {
            UserService us = (UserService) db.get(0);
            return us.deleteUserPermanent(userId);
        }
    @POST
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> auth(User user) {
        UserService us = (UserService) db.get(0);
        List<Map<String, Object>> ob = us.retrieveAuth();

       Map<String, Object> data = new HashMap<String,Object>();
       ArrayList roleListR=new ArrayList<String>();
        for (Map<String, Object> row : ob) {
            String password = row.get("uPassword").toString();
            String email = row.get("uEmail").toString();
            if (user.getuEmail().equals(email) && user.getuPassword().equals(password)) {
                String userId = row.get("userId").toString();
                String employee = row.get("isEmployee").toString();
                String projectManager = row.get("isProjectManager").toString();
                String admin = row.get("isAdmin").toString();
                String supervisor = row.get("isSupervisor").toString();
                data.put("userId",userId);
                data.put("isEmployee",employee);
                data.put("isProjectManager",projectManager);
                data.put("isAdmin",admin);
                data.put("isSupervisor",supervisor);
                data.put("password",password);
                return data;
            }
        }

//        @Path("/supervisorSubstitute")
//        @GET
//        @Produces(MediaType.APPLICATION_JSON)
//        public ArrayList<Object> dropdownsubstitute()
//        {
//            ArrayList<Object> approver = new ArrayList<>();
//            List<Map<String, Object>> allUsers = db.get(0).retrieve();
//            System.out.println("+++++"+allUsers);
//            return approver;
//        }






        System.out.println(data);
        data.put("userId",null);
        data.put("isEmployee",0);
        data.put("isProjectManager",0);
        data.put("isAdmin",0);
        data.put("isSupervisor",0);
        return data;
    }

}