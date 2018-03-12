package com.integ.ptm.service;

import com.integ.ptm.DBService;
import com.integ.ptm.DataBaseHandle;
import com.integ.ptm.module.Department;
import com.integ.ptm.module.Holiday;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HolidayService implements DBService {
    int result;
    @Override
    public Object insert(Object obj) {
        DataBaseHandle.runDB(da -> {
            Holiday hday=new Holiday();
            hday=(Holiday)obj;
            UUID uuidDept = UUID.randomUUID();
            result = da.executeUpdate("INSERT INTO holidays(holiday_id,holiday_date,holiday_desc)" +
                    " VALUES('" + uuidDept.toString() + "','" + hday.getHolidayDate() + "','" + hday.getHolidayDesc()+ "')");
        });
        return result;
    }

    @Override
    public Object update(Object obj) {
        return null;
    }

    @Override
    public Object delete(Object obj) {
        String id= (String) obj;
        DataBaseHandle.runDB(da -> {
            result = da.executeUpdate("DELETE from holidays WHERE holiday_id='"+id+"'");

        });
        return result;
    }

    List<Map<String, Object>> resultSetDataOfHolidays;

    @Override
    public List<Map<String, Object>> retrieve() {

        DataBaseHandle.runDB(da->{
            resultSetDataOfHolidays= da.executeQuery("SELECT * FROM holidays");
        });
        return resultSetDataOfHolidays;
    }

    @Override
    public List<Map<String, Object>> retrieveById(String obj) {
        return null;
    }

}
