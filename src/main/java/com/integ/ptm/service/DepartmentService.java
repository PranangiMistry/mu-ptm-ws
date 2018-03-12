package com.integ.ptm.service;

import com.integ.ptm.DBService;
import com.integ.ptm.DataBaseHandle;
import com.integ.ptm.module.Department;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DepartmentService implements DBService{
    int result;
    @Override
    public Object insert(Object obj) {
        DataBaseHandle.runDB(da -> {
            Department dept=new Department();
            dept=(Department)obj;
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            UUID uuidDept = UUID.randomUUID();
            result = da.executeUpdate("INSERT INTO departments(department_id,department_name,client_id,modified_date,report_to_name,report_to_email,report_to_cell,report_to_phone)" +
                    " VALUES('" + uuidDept.toString() + "','" + dept.getDepartmentName() + "','" + dept.getClientID() + "','" + dateFormat.format(date) + "','"+ dept.getReportToName() +"','"+ dept.getReportToEmail() +"','"+ dept.getReportToCell() +"','"+ dept.getReportToPhone() +"')");
        });
        return result;
    }

    @Override
    public Object update(Object obj) {
        DataBaseHandle.runDB(da -> {
            Department department =new Department();
            department=(Department) obj;
            result = da.executeUpdate("UPDATE departments SET d_is_active="+department.getdIsActive()+" WHERE department_id='"+department.getDepartmentId()+"'");
        });
        return result;
    }

    int isDeletefromDepartment=0,isDeleteFromRoleTo=0;
    @Override
    public Object delete(Object obj) {

        final String id=(String)obj;
        DataBaseHandle.runDB(da -> {
            isDeletefromDepartment=da.executeUpdate("DELETE FROM departments WHERE department_id='"+id+"'");
            System.out.println("delete from department"+isDeletefromDepartment);
//            if(isDeletefromDepartment!=0){
//                isDeleteFromRoleTo= da.executeUpdate("DELETE FROM reportTo WHERE department_id='"+id+"'");
//                System.out.println("delete from reportTo"+isDeleteFromRoleTo);
//            }

        });
        return  isDeletefromDepartment;
    }

    List<Map<String, Object>> resultSetDataOfDepartment;

    @Override
    public List<Map<String, Object>> retrieve() {

        DataBaseHandle.runDB(da -> {
            resultSetDataOfDepartment = da.executeQuery("SELECT departments.department_id,departments.department_name,client.client_id, client.client_name,departments.report_to_name,departments.report_to_email,departments.report_to_cell,departments.report_to_phone, departments.d_is_active FROM departments INNER JOIN client ON departments.client_id = client.client_id");
            for (Map<String, Object> row : resultSetDataOfDepartment) {
                Number isActive = (Number) row.get("dIsActive");
                if (isActive.intValue() == 0) {
                    row.put("dIsActive", false);
                } else {
                    row.put("dIsActive", true);
                }
            }
        });
        return resultSetDataOfDepartment;
    }


    List<Map<String, Object>> resultFromRetriveById;
    @Override
    public List<Map<String, Object>> retrieveById(String obj) {

        DataBaseHandle.runDB(da->{
            resultFromRetriveById=da.executeQuery("SELECT * FROM departments WHERE department_id='"+obj+"'");

        });

        return resultFromRetriveById;
    }

    public Object updateDepartment(Object obj) {
        DataBaseHandle.runDB(da -> {
            Department department =new Department();
            department=(Department) obj;
            result = da.executeUpdate("UPDATE departments SET department_name='"+department.getDepartmentName()+"',client_id='"+department.getClientID()+"',report_to_name='"+department.getReportToName()+"',report_to_email='"+department.getReportToEmail()+"',report_to_cell='"+department.getReportToCell()+"',report_to_phone='"+department.getReportToPhone()+"' WHERE department_id='"+department.getDepartmentId()+"'");
        });
        return result;
    }

}
