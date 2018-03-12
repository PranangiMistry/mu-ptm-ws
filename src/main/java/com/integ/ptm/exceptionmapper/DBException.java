package com.integ.ptm.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Author: mpanchal
 * Date: 2/1/18 6:01 PM
 */

public class DBException extends RuntimeException {


    public DBException() {}

    public DBException(String message)
    {
        super(message);
    }

    public DBException(String message, Throwable cause)
    {
        super(message, cause);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("getcause" +cause.getCause());
        System.out.println("getcause-msg "+cause.getMessage());
        System.out.println("getcause-stackttace"+cause.getStackTrace());
       // System.out.println("getcaus-pritstack"+cause.getCause().getStackTrace());

    }

    public DBException(Throwable cause)
    {
        super(cause);
    }

}
