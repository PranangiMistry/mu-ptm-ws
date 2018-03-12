package com.integ.ptm.service;

import com.integ.ptm.DBService;
import com.integ.ptm.DataBaseHandle;
import com.integ.ptm.module.PayOrder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Author: mpanchal
 * Date: 2/16/18 1:35 PM
 */
public class PayOrderService implements DBService {
    int isInsert=0;
    @Override
    public Object insert(Object obj) {

        DataBaseHandle.runDB(da->{
            PayOrder payOrder=new PayOrder();
            payOrder=(PayOrder)obj;
            UUID uuidProjectPO=UUID.randomUUID();
            isInsert=da.executeUpdate("INSERT INTO projectPO(project_po_id,project_id_fk,po_start_date,po_end_date,cost) VALUES('"+uuidProjectPO.toString()+"','"+payOrder.getProjectId()+"','"+payOrder.getPayOrderStartDate()+"','"+payOrder.getPayOrderEndDate()+"','"+payOrder.getCost()+"')");
        });

        return isInsert;
    }

    @Override
    public Object update(Object obj) {
        return null;
    }

    @Override
    public Object delete(Object obj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> retrieve() {
        return null;
    }

    @Override
    public List<Map<String, Object>> retrieveById(String obj) {
        return null;
    }
}
