package com.integ.ptm.service;

import com.integ.ptm.DBService;
import com.integ.ptm.DataBaseHandle;
import com.integ.ptm.module.Client;
import com.integ.ptm.module.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 * Author: mpanchal
 * Date: 1/30/18 1:16 PM
 */
public class ClientService implements DBService{

    int result;

    @Override
    public Object insert(Object obj)
    {
        DataBaseHandle.runDB(da -> {
            UUID uuidClient = UUID.randomUUID();
            UUID uuidReportTo = UUID.randomUUID();

            Client client=new Client();
            client=(Client)obj;

            String newClient=client.getClientName();

            List<Map<String,Object>> clientNames;
            clientNames=da.executeQuery("SELECT client_name from client");

            for(Map<String,Object> row:clientNames) {
                String clientNameFromDB = row.get("clientName").toString();
                if (clientNameFromDB.equals(newClient)) {
                    result = -1;
                }
            }
            if(result!=-1) {
                result = da.executeUpdate("INSERT INTO client(client_ID,client_Name,client_Code,street_Name1,street_Name2,city,state,country,zip_Code,is_Active) " +
                        "VALUES('" + uuidClient.toString() + "','" + client.getClientName() + "','" + client.getClientCode() + "','" + client.getStreetName1() + "','" + client.getStreetName2() + "','" + client.getCity() + "','" + client.getState() + "','" + client.getCountry() + "','" + client.getZipCode() + "'," + client.getIsActive() + ")");
                if (result != 0) {
                    da.executeUpdate("INSERT INTO reportTo(report_ID,client_ID,report_To_Name,report_To_Email,report_To_Cell,report_To_Phone) VALUES('" + uuidReportTo.toString() + "','" + uuidClient.toString() + "','" + client.getReportToName() + "','" + client.getReportToEmail() + "','" + client.getReportToCell() + "','" + client.getReportToPhone() + "')");
                }
            }
        });
        return result;
    }

    @Override
    public Object update(Object obj) {

        System.out.println("updateeeeeeee");
        DataBaseHandle.runDB(da -> {
           Client client =new Client();
            client=(Client) obj;
            result = da.executeUpdate("UPDATE client SET is_active="+client.getIsActive()+" WHERE client_id='"+client.getClientID()+"'");
        });
        return result;
    }


    int isDeletefromClient=0,isDeleteFromRoleTo=0;
    @Override
    public Object delete(Object obj) {

        System.out.println("object in delete=="+obj);
       final String id=(String)obj;
        System.out.println("String in delete=="+id);
        DataBaseHandle.runDB(da -> {
                isDeletefromClient=da.executeUpdate("DELETE FROM client WHERE client_id='"+id+"'");
                 System.out.println("delete from client"+isDeletefromClient);
                if(isDeletefromClient!=0){
                    isDeleteFromRoleTo= da.executeUpdate("DELETE FROM reportTo WHERE client_id='"+id+"'");
                    System.out.println("delete from role to"+isDeleteFromRoleTo);
                }

            });
    return  isDeleteFromRoleTo;
    }
    List<Map<String, Object>> resultSetDataOfClient;

    @Override
    public List<Map<String, Object>> retrieve() {
        DataBaseHandle.runDB(da->{
            resultSetDataOfClient= da.executeQuery("SELECT * FROM client");
            for (Map<String, Object> row:resultSetDataOfClient) {
                Number isActive= (Number)row.get("isActive");
                if(isActive.intValue()==0){
                    row.put("isActive",false);
                }
                else
                {
                    row.put("isActive",true);
                }
            }
        });
        return resultSetDataOfClient;
    }
    List<Map<String, Object>> resultFromRetriveById,resultfromReportTo;
    @Override
    public List<Map<String, Object>> retrieveById(String obj) {

        DataBaseHandle.runDB(da->{
          resultFromRetriveById=da.executeQuery("SELECT * FROM client WHERE client_id='"+obj+"'");
          resultfromReportTo=da.executeQuery("SELECT report_to_name,report_to_email,report_to_cell,report_to_phone FROM reportTo WHERE client_id='"+obj+"'");
            System.out.println(resultfromReportTo);


            for (Map<String, Object> row:resultfromReportTo) {
                resultFromRetriveById.add(row);
            }
        });

        return resultFromRetriveById;
    }

    int updateClient=0,updateReportTo=0;
    public int updateClient(Client client){

        DataBaseHandle.runDB(da->{
            updateClient=da.executeUpdate("UPDATE client SET client_name='"+client.getClientName()+"',client_code='"+client.getClientCode()+"',street_name1='"+client.getStreetName1()+"',street_name2='"+client.getStreetName2()+"',city='"+client.getCity()+"',state='"+client.getState()+"',country='"+client.getCountry()+"',zip_code='"+client.getZipCode()+"' WHERE CLIENT_ID='"+client.getClientID()+"'");
            if(updateClient!=0){
                updateReportTo=da.executeUpdate("UPDATE reportTo SET report_to_name='"+client.getReportToName()+"',report_to_email='"+client.getReportToEmail()+"',report_to_cell='"+client.getReportToCell()+"',report_to_phone='"+client.getReportToPhone()+"' WHERE client_id='"+client.getClientID()+"' ");
            }
        });
       return  updateReportTo;
    }

}
