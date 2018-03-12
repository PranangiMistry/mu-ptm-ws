package com.integ.ptm.exceptionmapper;

import com.integ.ptm.exceptionmapper.DBException;
import com.integ.ptm.module.MyResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Author: mpanchal
 * Date: 2/1/18 6:23 PM
 */
@Provider
public class DBExceptionMapper implements ExceptionMapper<DBException> {



    @Override
    public Response toResponse(DBException e) {

        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        String stractrace= errors.toString();

        MyResponse myResponse=new MyResponse();
        myResponse.setStatus("fail");
        myResponse.setStract(stractrace);
        myResponse.setMsg(e.getMessage());

        try
        {
            errors.close();
        }
        catch (IOException e1)
        {
            throw new DBException("string writer has exception",e1);
        }
        return  Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                .type(MediaType.APPLICATION_JSON)
                .entity(myResponse)
                .build();
    }
}
