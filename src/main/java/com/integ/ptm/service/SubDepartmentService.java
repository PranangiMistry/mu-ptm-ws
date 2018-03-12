package com.integ.ptm.service;

import com.integ.ptm.DBService;
import com.integ.ptm.DataBaseHandle;
import com.integ.ptm.module.Department;
import com.integ.ptm.module.SubDepartment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SubDepartmentService implements DBService{
    int result;
    @Override
    public Object insert(Object obj) {
        DataBaseHandle.runDB(da -> {
            SubDepartment dept=new SubDepartment();
            dept=(SubDepartment)obj;
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            UUID uuidDept = UUID.randomUUID();
            result = da.executeUpdate("INSERT INTO subdepartments(subdepartment_id,subdepartment_name,client_id,department_id,modified_date,d_is_active,report_to_name,report_to_email,report_to_cell,report_to_phone)" +
                    " VALUES('" + uuidDept.toString() + "','" + dept.getSubDepartmentName() + "','" + dept.getClientID() + "','" + dept.getDepartmentId() + "','" + dateFormat.format(date) + "','" + dept.getdIsActive() + "','"+ dept.getReportToName() +"','"+ dept.getReportToEmail() +"','"+ dept.getReportToCell() +"','"+ dept.getReportToPhone() +"')");
        });
        return result;
    }

    @Override
    public Object update(Object obj) {
        DataBaseHandle.runDB(da -> {
            SubDepartment department =new SubDepartment();
            department=(SubDepartment) obj;
            result = da.executeUpdate("UPDATE subdepartments SET d_is_active="+department.getdIsActive()+" WHERE subdepartment_id='"+department.getSubDepartmentId()+"'");
        });
        return result;
    }

    int isDeletefromDepartment=0;
    @Override
    public Object delete(Object obj) {

        final String id=(String)obj;
        DataBaseHandle.runDB(da -> {
            isDeletefromDepartment=da.executeUpdate("DELETE FROM subdepartments WHERE subdepartment_id='"+id+"'");
            System.out.println("delete from subdepartment"+isDeletefromDepartment);
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

        DataBaseHandle.runDB(da->{
            resultSetDataOfDepartment= da.executeQuery("SELECT subdepartments.subdepartment_id,subdepartments.subdepartment_name,departments.department_name,departments.department_id,client.client_name,client.client_id,subdepartments.report_to_name,subdepartments.report_to_email,subdepartments.report_to_cell,subdepartments.report_to_phone, subdepartments.d_is_active FROM subdepartments INNER JOIN client ON subdepartments.client_id = client.client_id INNER JOIN departments ON client.client_id = departments.client_id");
//            resultSetDataOfDepartment= da.executeQuery("SELECT * FROM departments");
            for (Map<String, Object> row:resultSetDataOfDepartment) {
                Number isActive= (Number)row.get("dIsActive");
                if(isActive.intValue()==0){
                    row.put("dIsActive",false);
                }
                else
                {
                    row.put("dIsActive",true);
                }
//                    }
//                }
            }
        });
        return resultSetDataOfDepartment;
    }


    List<Map<String, Object>> resultFromRetriveById;
    @Override
    public List<Map<String, Object>> retrieveById(String obj) {

        DataBaseHandle.runDB(da->{
            resultFromRetriveById=da.executeQuery("SELECT * FROM subdepartments WHERE subdepartment_id='"+obj+"'");

        });

        return resultFromRetriveById;
    }

    public Object updateSubDepartment(Object obj) {
        DataBaseHandle.runDB(da -> {
            SubDepartment department =new SubDepartment();
            department=(SubDepartment) obj;
            result = da.executeUpdate("UPDATE subdepartments SET subdepartment_name='"+department.getSubDepartmentName()+"',client_id='"+department.getClientID()+"',report_to_name='"+department.getReportToName()+"',report_to_email='"+department.getReportToEmail()+"',report_to_cell='"+department.getReportToCell()+"',report_to_phone='"+department.getReportToPhone()+"',department_id='"+department.getDepartmentId()+"' WHERE subdepartment_id='"+department.getSubDepartmentId()+"'");
        });
        return result;
    }

}
