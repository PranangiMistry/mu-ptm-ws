package com.integ.ptm.service;

import com.integ.ptm.DBService;
import com.integ.ptm.DataBaseHandle;
import com.integ.ptm.module.SupervisorSubstitution;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Author: mpanchal
 * Date: 2/22/18 3:32 PM
 */
public class SupervisorSubstitutionService implements DBService {
    int isInserted=0;
    @Override
    public Object insert(Object obj) {

        DataBaseHandle.runDB(da -> {
            UUID uuidSupervisorSubstitution = UUID.randomUUID();
            SupervisorSubstitution supervisorSubstitution=new SupervisorSubstitution();
            supervisorSubstitution=(SupervisorSubstitution)obj;
            isInserted=da.executeUpdate("INSERT INTO SUPERVISORSUBSTITUTE(supervisor_substitute_id,supervisor_id,user_id,start_date,end_date,send_email) VALUES('"+uuidSupervisorSubstitution.toString()+"','"+supervisorSubstitution.getSupervisorId()+"','"+supervisorSubstitution.getUserId()+"','"+supervisorSubstitution.getStartDate()+"','"+supervisorSubstitution.getEndDate()+"',"+supervisorSubstitution.getSendEmail()+")");
        });

        return isInserted;
    }

    int SSUpdate;
    @Override
    public Object update(Object obj) {
        DataBaseHandle.runDB(da->{
            System.out.println("service-obj"+obj);
            SupervisorSubstitution ss=new SupervisorSubstitution();
            ss=(SupervisorSubstitution)obj;
            System.out.println("update-api-ss==="+ss);
            System.out.println("+++++"+ss.getSupervisorSubstitutionId());
           SSUpdate=da.executeUpdate("UPDATE SUPERVISORSUBSTITUTE SET supervisor_id='"+ss.getSupervisorId()+"',user_id='"+ss.getUserId()+"',start_date='"+ss.getStartDate()+"',end_date='"+ss.getEndDate()+"',send_email="+ss.getSendEmail()+" WHERE supervisor_substitute_id='"+ss.getSupervisorSubstitutionId()+"'");

        });
        return SSUpdate;
    }

   int delete;
    @Override
    public Object delete(Object obj) {

        System.out.println("object in delete=="+obj);
        final String id=(String)obj;
        System.out.println("String in delete=="+id);
        DataBaseHandle.runDB(da -> {
            delete=da.executeUpdate("DELETE FROM SUPERVISORSUBSTITUTE WHERE supervisor_substitute_id='"+id+"'");
            System.out.println("delete from client"+delete);

        });
        return  delete;

    }

    List<Map<String,Object>> substituters;
    @Override
    public List<Map<String, Object>> retrieve()
    {
//        DataBaseHandle.runDB(da->{
//            substituters=da.executeQuery("SELECT * FROM SUPERVISORSUBSTITUTE");
//        });
            DataBaseHandle.runDB(da->{
                substituters= da.executeQuery("SELECT  S.*, U1.U_FIRST_NAME AS SUP_FIRST_NAME, U1.U_LAST_NAME SUP_LAST_NAME, U2.U_FIRST_NAME, U2.U_LAST_NAME \n" +
                        "FROM SUPERVISORSUBSTITUTE S INNER JOIN USERS U1 ON S.SUPERVISOR_ID = U1.USER_ID\n" +
                        "LEFT OUTER JOIN USERS U2 ON S.USER_ID = U2.USER_ID");
            });
        for (Map<String, Object> row:substituters) {
//            Number isActive= (Number)row.get("sendEmail");
//            if(isActive.intValue()==0){
//                row.put("sendEmail",false);
//            }
//            else
//            {
//                row.put("sendEmail",true);
//            }
            String supervisorName;
            String SFN=row.get("supFirstName").toString();
            String SLN=row.get("supLastName").toString();
            supervisorName=SFN + "  " +SLN;

            //System.out.println("supervisorName"+supervisorName);
            row.put("supervisorName",supervisorName);

            String userName;
            String UFN=row.get("uFirstName").toString();
            String ULN=row.get("uLastName").toString();
            userName=UFN + "  " +ULN;

            //System.out.println("supervisorName"+supervisorName);
            row.put("userName",userName);
        }


         System.out.println(substituters);
//
//        }

      //  System.out.println("+++"+allDetails);
        return substituters;
    }
    List<Map<String, Object>> substituteByMe;
    @Override
    public List<Map<String, Object>> retrieveById(String supervisorId) {

        DataBaseHandle.runDB(da->{
            substituteByMe= da.executeQuery("SELECT s.*,u.U_FIRST_NAME,u.U_LAST_NAME FROM SUPERVISORSUBSTITUTE s INNER JOIN USERS u ON s.USER_ID=u.USER_ID WHERE s.SUPERVISOR_ID='"+supervisorId+"'");
        });
        return substituteByMe;
    }

    List<Map<String,Object>> approverlist;
    public List<Map<String,Object>> ApprovarDropdown()
    {
        DataBaseHandle.runDB(da->{
            approverlist=da.executeQuery("SELECT user_id,u_first_name,u_last_name,is_Admin,is_supervisor FROM users WHERE is_admin=1 OR is_supervisor=1");
        });

        return approverlist;
    }

    List<Map<String,Object>> substitutor,supDetail,userDeatail;
    public List<Map<String,Object>> retriveForOneSubstitute(String id)
    {
        DataBaseHandle.runDB(da->{
            substitutor=da.executeQuery("SELECT * FROM SUPERVISORSUBSTITUTE WHERE supervisor_substitute_id='"+id+"' ");
            for (Map<String, Object> row:substitutor) {
                String supervisorId=row.get("supervisorId").toString();
                String userId=row.get("userId").toString();

                Number sendEmail=(Number)row.get("sendEmail");
                if(sendEmail.intValue()==0)
                {
                    row.put("sendEmail",false);
                }
                else
                {
                    row.put("sendEmail",true);
                }

                supDetail=da.executeQuery("SELECT u_first_name,u_last_name from users WHERE user_id='"+supervisorId+"'");
                System.out.println("supDetail"+supDetail);
                userDeatail=da.executeQuery("SELECT u_first_name,u_last_name from users WHERE user_id='"+userId+"'");
                System.out.println("userDeatail"+userDeatail);

                for(Map<String,Object> rowSupervisor:supDetail)
                {
                    String firstName=rowSupervisor.get("uFirstName").toString();
                    String lastName=rowSupervisor.get("uLastName").toString();
                    String supervisorName=firstName+ "  " + lastName;
                    row.put("supervisorName",supervisorName);
                }
                for(Map<String,Object> rowUser:userDeatail)
                {
                    String firstName=rowUser.get("uFirstName").toString();
                    String lastName=rowUser.get("uLastName").toString();
                    String userName=firstName+ "  " + lastName;
                    row.put("userName",userName);
                }
            }
        });
        return  substitutor;
    }

}


