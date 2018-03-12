package com.integ.ptm;

/**
 * Author: mpanchal
 * Date: 1/31/18 3:37 PM
 */
import com.integ.ptm.exceptionmapper.DBException;
public class DataBaseHandle {
    public static Object runDB(InterfaceConnectDB interfaceConnectDB) {
        //create connection
        DataBaseAccess access = new DataBaseAccess();
        Object obj=new Object();
        access.getDBConnection();
        try {
             interfaceConnectDB.ExcecuteMyQuary(access);
           // System.out.println("object===="+obj);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new DBException("exception in rundb ", e);
        }
        finally
        {
            access.closeDBConnection();
        }

        //close connection
        return obj;
    }
}
