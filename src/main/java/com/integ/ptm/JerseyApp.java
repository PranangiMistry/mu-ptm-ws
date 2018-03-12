package com.integ.ptm;

/*
* Author: Manan
* Date: 18-01-2018 13:48
*/

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.integ.ptm.exceptionmapper.DBExceptionMapper;
import com.integ.ptm.api.*;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ApplicationPath("webapp")
public class JerseyApp extends ResourceConfig {

    public JerseyApp() {
        System.getProperties().setProperty("oracle.jdbc.J2EE13Compliant", "true");
        register(CorsFilter.class);
        register(DBExceptionMapper.class);
        register(DBServiceFactory.getBinder());
        register(new JacksonJsonProvider());
        packages("com.integ.ptm.api");
        }
    }
