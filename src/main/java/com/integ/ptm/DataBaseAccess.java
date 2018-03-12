package com.integ.ptm;

import javassist.bytecode.stackmap.BasicBlock;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import com.integ.ptm.exceptionmapper.*;

/**
 * Author: mpanchal
 * Date: 1/31/18 3:21 PM
 */
public class DataBaseAccess {

    Connection connection = null;
    String fileName = "src/files/propfile.properties";
    String driver,URL,username,password;

    public void readFile()
    {
        Properties properties =new Properties();
        try
        {
            properties.load(new FileInputStream(fileName));
            driver=properties.getProperty("driver");
            URL=properties.getProperty("URL");
            username=properties.getProperty("username");
            password=properties.getProperty("password");
        }
        catch (Exception e)
        {
            System.out.println("properties file has error");
            System.out.println(e);
            throw  new DBException("properties file has error",e);
        }
    }

    public void getDBConnection()
     {
        readFile();
        try
        {
            Class.forName(driver);
            System.out.println("connect successfully driver");
        }
        catch (Exception e)
        {
            System.out.println("driver connection error");
            System.out.println(e);
            throw  new DBException("can not connect to driver",e);
        }

        try
        {
            connection = DriverManager.getConnection(URL, username, password);
            System.out.println("connetion done");
        }
        catch (Exception e)
        {
            System.out.println("db connection error");
            System.out.println(e);
            throw  new DBException("can not connect to database",e);
        }

        if (connection != null)
        {
            System.out.println("db connection succesful");
        }
        else
        {
            System.out.println("Failed to make connection!");
        }

    }

    public int executeUpdate(String query)
    {
        int result=0;
        try
        {
            Statement statement=connection.createStatement();
            System.out.println("try of execute update");
            result=statement.executeUpdate(query);
            statement.close();
        }
        catch (Exception e)
        {
            System.out.println("catch of exceute update");
            throw new DBException("exception in executeUpdate Method of Class DataBaseAccess",e);
        }
            return result;
    }

    public List<Map<String, Object>>  executeQuery(String query)
    {
        List<Map<String, Object>> resultSetData = new ArrayList<Map<String, Object>>();
        ResultSet resultSet=null;
        try
        {
            Statement statement=connection.createStatement();
            resultSet=statement.executeQuery(query);
             resultSetData=resultSetToList(resultSet);
            System.out.println("resultsetdata from function");
            resultSet.close();
            statement.close();
        }
        catch (Exception e)
        {
            throw new DBException("exception in executeQuery method of class ",e);
        }

        finally {
            return resultSetData;
        }
    }

    public void closeDBConnection()
    {
        if(connection!=null)
        {
            try
            {
                connection.close();
            }
            catch (Exception e)
            {
                System.out.println("error while closing connection" );
                System.out.println(e);
                throw  new DBException("Can not close connection ",e);
            }
        }
    }

    public String ToCemalCase(String loweCase)
    {
        String result = "";
        for(int i=0;i<loweCase.length();i++)
        {
            char current=loweCase.charAt(i);

            if(current=='_')
            {
                i++;
                char newChar=loweCase.charAt(i);
                result=result+Character.toUpperCase(newChar);
            }
            else
            {
                result=result+Character.toLowerCase(current);
            }
        }

        return result;
    }



    public List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException
    {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        while (rs.next()){
            Map<String, Object> row = new HashMap<String, Object>(columns);
            for(int i = 1; i <= columns; ++i)
            {
                if(md.getColumnName(i).equals("MODIFIED_DATE")){
                    Date d= (Date) rs.getObject(i);
                    row.put(ToCemalCase(md.getColumnName(i)), d);
                }else {
                    row.put(ToCemalCase(md.getColumnName(i)), rs.getObject(i));
                }
            }
            rows.add(row);
        }
        return rows;
    }

}
