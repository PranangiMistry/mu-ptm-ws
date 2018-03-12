package com.integ.ptm.service;

import com.integ.ptm.DBService;
import com.integ.ptm.DataBaseHandle;
import com.integ.ptm.module.Vendor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Author: mpanchal
 * Date: 2/13/18 5:01 PM
 */

public class VendorService implements DBService {
    int insertInVendor=0;
    @Override
    public Object insert(Object obj) {
        DataBaseHandle.runDB(da -> {
            UUID uuidVendor = UUID.randomUUID();
            Vendor vendor=new Vendor();
            vendor=(Vendor)obj;

            String vendorName=vendor.getVendorCompanyName();
            List<Map<String,Object>> vendorNameList;
            vendorNameList=da.executeQuery("SELECT vendor_company_name from vendor");
            for(Map<String,Object> row:vendorNameList)
            {
                String vendorNameFormDB=row.get("vendorCompanyName").toString();
                if(vendorNameFormDB.equals(vendorName))
                {
                    insertInVendor=-1;
                }
            }
            if(insertInVendor!=-1)
                insertInVendor= da.executeUpdate( "INSERT INTO vendor (vendor_id,vendor_company_name,vendor_street_name1,vendor_street_name2,vendor_city,vendor_state,vendor_country,vendor_email,vendor_phone,vendor_cell) VALUES('"+uuidVendor.toString()+"','"+vendor.getVendorCompanyName()+"','"+vendor.getVendorStreetName1()+"','"+vendor.getVendorStreetName2()+"','"+vendor.getVendorCity()+"','"+vendor.getVendorState()+"','"+vendor.getVendorCountry()+"','"+vendor.getVendorEmail()+"','"+vendor.getVendorPhone()+"','"+vendor.getVendorCell()+"')");

        });
        return insertInVendor;
    }

    List<Map<String,Object>> AllvendorData;
    @Override
    public List<Map<String, Object>> retrieve() {
        DataBaseHandle.runDB(da->{
            AllvendorData= da.executeQuery("SELECT * FROM vendor");

        });
        return AllvendorData;

    }
    int updateVendor=0;
    @Override
    public Object update(Object obj) {

        DataBaseHandle.runDB(da->{
            Vendor vendor=new Vendor();
            vendor=(Vendor)obj;
            System.out.println("||||||||||||||||||||||||||||||||"+vendor);
            updateVendor=da.executeUpdate("UPDATE VENDOR SET VENDOR_CELL='"+vendor.getVendorCell()+"', VENDOR_CITY='"+vendor.getVendorCity()+"' , VENDOR_COMPANY_NAME='"+vendor.getVendorCompanyName()+"' , VENDOR_EMAIL='"+vendor.getVendorEmail()+"' , VENDOR_COUNTRY='"+vendor.getVendorCountry()+"' , VENDOR_PHONE='"+vendor.getVendorPhone()+"' , VENDOR_STATE='"+vendor.getVendorState()+"' , VENDOR_STREET_NAME1='"+vendor.getVendorStreetName1()+"' , VENDOR_STREET_NAME2='"+vendor.getVendorStreetName2()+"'  WHERE VENDOR_ID='"+vendor.getVendorId()+"'");
        });
        return  updateVendor;
    }

    int isDelete=0;
    @Override
    public Object delete(Object obj) {
        System.out.println("object in delete=="+obj);
        final String id=(String)obj;
        System.out.println("String in delete=="+id);
        DataBaseHandle.runDB(da -> {
            isDelete=da.executeUpdate("DELETE FROM vendor WHERE vendor_id='"+id+"'");
            System.out.println("delete from vendor"+isDelete);

        });
        return  isDelete;
    }




    List<Map<String,Object>> resultFromRetriveById;
    @Override
    public List<Map<String, Object>> retrieveById(String vendorId)
    {
        DataBaseHandle.runDB(da->{
            resultFromRetriveById=da.executeQuery("SELECT * FROM vendor WHERE vendor_id='"+vendorId+"'");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+resultFromRetriveById);
        });

        return resultFromRetriveById;
    }
}
