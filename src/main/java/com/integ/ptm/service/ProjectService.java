package com.integ.ptm.service;

import com.integ.ptm.DBService;
import com.integ.ptm.DataBaseHandle;
import com.integ.ptm.module.Project;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by bhavesh on 2/12/18.
 */
public class ProjectService implements DBService {
    int result;

    @Override
    public Object insert(Object obj) {
        DataBaseHandle.runDB(da -> {
            Project proj = new Project();
            proj = (Project) obj;
            UUID uuidPro = UUID.randomUUID();
          ///  System.out.println(proj.getSubdepartmentIdFk());
            System.out.println(proj.getSubdepartmentId());
            System.out.println(proj.getDepartmentId());
         //   System.out.println(proj.getDepartmentIdFk());

            result = da.executeUpdate("INSERT INTO projects(project_id,project_name,project_type,client_id_fk,department_id_fk,SUBDEPARTMENT_ID_FK,is_active,project_start_date,project_end_date)" +
                    " VALUES('" + uuidPro.toString() + "','" + proj.getProjectName() + "','" + proj.getProjectType() + "','" + proj.getClientId() + "','" + proj.getDepartmentId() + "','" + proj.getSubdepartmentId() + "','" + proj.getIsActive() + "','" + proj.getProjectStartDate() + "','" + proj.getProjectEndDate() + "')");
        });
        return result;
    }

    @Override
    public Object update(Object obj) {
        DataBaseHandle.runDB(da -> {
            Project proj = new Project();
            proj = (Project) obj;
            result = da.executeUpdate("UPDATE projects SET project_name='" + proj.getProjectName() + "',project_type='" + proj.getProjectType() + "',project_start_date='" + proj.getProjectStartDate() + "',project_end_date='" + proj.getProjectEndDate() + "',is_active='" + proj.getIsActive() + "',client_id_fk='" + proj.getClientIdFk() + "',department_id_fk='" + proj.getDepartmentIdFk() + "',subdepartment_id_fk='" + proj.getSubdepartmentIdFk() + "' WHERE project_id='" + proj.getProjectId() + "'");
        });
        return result;
    }

    @Override
    public Object delete(Object obj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> retrieve() {
        return null;
    }


    List<Map<String, Object>> projectById;
    List<Map<String, Object>> pclientNm;
    List<Map<String, Object>> pdeptNm;
    List<Map<String, Object>> psubDeptNm;
    String cName = "", deName = "", subDeName = "";

    @Override
    public List<Map<String, Object>> retrieveById(String obj) {
        DataBaseHandle.runDB(da -> {
            projectById = da.executeQuery("SELECT project_id,project_name,project_type,project_start_date,project_end_date,client_id_fk,department_id_fk,subdepartment_id_fk,is_active FROM PROJECTS WHERE project_id='" + obj + "'");
            for (Map<String, Object> row : projectById) {
                String cId = row.get("clientIdFk").toString();
                String dId = row.get("departmentIdFk").toString();

                pclientNm = da.executeQuery("SELECT client_name from client WHERE client_id='" + cId + "'");
                for (Map<String, Object> cRow : pclientNm) {
                    cName = cRow.get("clientName").toString();
                }
                pdeptNm = da.executeQuery("SELECT department_name from departments WHERE department_id='" + dId + "'");
                for (Map<String, Object> dRow : pdeptNm) {
                    deName = dRow.get("departmentName").toString();
                }
                Object sdId = row.getOrDefault("subdepartmentIdFk", "");
                if (sdId != null) {
                    psubDeptNm = da.executeQuery("SELECT subdepartment_name from subdepartments WHERE subdepartment_id='" + sdId + "'");
                    for (Map<String, Object> sdRow : psubDeptNm) {
                        subDeName = sdRow.get("subdepartmentName").toString();
                    }
                }
                row.put("clientName", cName);
                row.put("departmentName", deName);
                row.put("subdepartmentName", subDeName);
                Number isActive = (Number) row.get("isActive");
                if (isActive.intValue() == 1) {
                    row.put("isActive", true);
                } else {
                    row.put("isActive", false);
                }
            }
        });
        return projectById;
    }

    public Object insertProjectEmp(Object obj) {
        DataBaseHandle.runDB(da -> {
            Project proj = new Project();
            proj = (Project) obj;
            List<Map> listDel = proj.getListDeliverables();
            UUID uuidStaff = UUID.randomUUID();
            String projectId = "";
            String deliverables = "";
            for (Map<String, Object> row : listDel) {
                if ((boolean) row.get("value").equals(true)) {
                    String deliverableId = row.get("id").toString();
                    projectId = row.get("key").toString();
                    deliverables = deliverables + "#" + deliverableId;
                }
            }
            result = da.executeUpdate("INSERT INTO projectStaff(proj_staff_id,user_id_fk,project_id_fk,deliverable_ids_fk,supervisor1,supervisor2,supervisor3,ts_type,ts_start_date,ts_end_date,week_start_day,week_off1,week_off2,ts_split_date)" +
                    " VALUES('" + uuidStaff + "','" + proj.getUserId() + "','" + projectId + "','" + deliverables + "','" + proj.getSupervisor1() + "','" + proj.getSupervisor2() + "','" + proj.getSupervisor3() + "','" + proj.getTimeSheetType() + "','" + proj.getTsStartDate() + "','" + proj.getTsEndDate() + "','" + proj.getWeekStartDay() + "','" + proj.getWeekOff1() + "','" + proj.getWeekOff2() + "','" + proj.getTsSplitDate() + "')");

            String tsType=proj.getTimeSheetType();
            List<String> startList=new ArrayList<String>();
            List<String> endList=new ArrayList<String>();
            String startDate=proj.getTsStartDate();
            String endDate=proj.getTsEndDate();
            String splitDate=proj.getTsSplitDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
            int week_start_day=proj.getDayNumber();
            System.out.println("week start day : "+week_start_day);
            Calendar c = Calendar.getInstance();
            Calendar cEnd = Calendar.getInstance();
            Calendar cSplit = Calendar.getInstance();
            Calendar cOld = Calendar.getInstance();
            Calendar cExtra = Calendar.getInstance();
            String oldD="",newD="";
            System.out.println("---------------------");
            if(tsType.equals("WEEKLY")||tsType.equals("BI-WEEKLY")){
                try{
                    c.setTime(sdf.parse(startDate));
                    cSplit.setTime(sdf.parse(splitDate));
                    if(endDate.equalsIgnoreCase("")) {
                        endDate=startDate;
                        cEnd.setTime(sdf.parse(endDate));
                        cEnd.add(Calendar.DAY_OF_MONTH, 180);
                    }
                    else {
                        cEnd.setTime(sdf.parse(endDate));
                    }
                    endDate = sdf.format(cEnd.getTime());
                }catch (Exception e){
                }
                int current_day=c.get(Calendar.DAY_OF_WEEK);
                int week=0;
                if(tsType.equals("WEEKLY")){
                    week=7;
                }else{ week=14; }
                int x=week-current_day+week_start_day-1;
                if(current_day<week_start_day)
                {
                    x=week_start_day-current_day-1;
                }
                c.add(Calendar.DAY_OF_MONTH, x);
                String newDate = sdf.format(c.getTime());
                startList.add(startDate);
                endList.add(newDate);
                c.add(Calendar.DAY_OF_MONTH, 1);
                newDate = sdf.format(c.getTime());
                String old="";

                while(c.before(cEnd)){
                    cOld= (Calendar) c.clone();
                    old=newDate;
                    int xx=0;
                    if(tsType.equals("WEEKLY")){
                        xx=6;
                    }else{ xx=13; }
                    c.add(Calendar.DAY_OF_MONTH, xx);
                    newDate = sdf.format(c.getTime());
                    if(cEnd.before(c)){
                        newDate=endDate;
                    }
                    if (cSplit.before(c)
                            && cSplit.after(cOld) ){
                        String st=old;
                        String ed=splitDate;
                        cSplit.add(Calendar.DAY_OF_MONTH, 1);
                        String st2 = sdf.format(cSplit.getTime());
                        String ed2=newDate;
                        startList.add(st);
                        endList.add(ed);
                        startList.add(st2);
                        endList.add(ed2);
                    }
                    else {
                        startList.add(old);
                        endList.add(newDate);
                    }
                    c.add(Calendar.DAY_OF_MONTH, 1);
                    newDate = sdf.format(c.getTime());

                }
            }
            if(tsType.equals("MONTHLY")||tsType.equals("SEMI-MONTHLY")) {
                try{
                    c.setTime(sdf.parse(startDate));
                    cSplit.setTime(sdf.parse(splitDate));

                    if(endDate.equalsIgnoreCase("")) {
                        endDate=startDate;
                        cEnd.setTime(sdf.parse(endDate));
                        cEnd.add(Calendar.DAY_OF_MONTH, 180);
                        cExtra.setTime(sdf.parse(endDate));
                        cExtra.add(Calendar.DAY_OF_MONTH, 181);

                    }
                    else {
                        cEnd.setTime(sdf.parse(endDate));
                        cExtra.setTime(sdf.parse(endDate));
                    }
                    cExtra.add(Calendar.DAY_OF_MONTH, 1);

                    while(c.before(cExtra)){
                        cOld= (Calendar) c.clone();

                        if(tsType.equals("SEMI-MONTHLY")) {
                            int xxx = c.get(Calendar.DAY_OF_MONTH);
                            System.out.println(xxx);

                            if (xxx < 15) {
                                c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 15);
                            } else {
                                c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
                            }
                        }else{
                            c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
                        }

                        if(!c.before(cEnd)){
                            c= (Calendar) cEnd.clone();
                        }
                        oldD=sdf.format(cOld.getTime());
                        newD=sdf.format(c.getTime());
                        if (cSplit.before(c)
                                && cSplit.after(cOld) ){
                            String st=oldD;
                            String ed=splitDate;
                            cSplit.add(Calendar.DAY_OF_MONTH, 1);
                            String st2 = sdf.format(cSplit.getTime());
                            String ed2=newD;
                            startList.add(st);
                            endList.add(ed);
                            startList.add(st2);
                            endList.add(ed2);
                        }
                        else {
                            startList.add(oldD);
                            endList.add(newD);
                        }
                        c.add(Calendar.DAY_OF_MONTH, 1);
                    }
                }catch (Exception e){
                }
            }
                int i=0;
            while(startList.size()>i){
                System.out.println();
                String start=startList.get(i);
                String end=endList.get(i);
                UUID uuidTS = UUID.randomUUID();
                result = da.executeUpdate("INSERT INTO timesheet(timesheet_id,project_id_fk,user_id_fk,proj_staff_id_fk,ts_type,start_date,end_date,week_start_day,week_off1,week_off2)" +
                              " VALUES('" + uuidTS + "','" + projectId + "','" + proj.getUserId() + "','" + uuidStaff + "','" + proj.getTimeSheetType() + "','" + start + "','" + end + "','" + proj.getWeekStartDay() + "','" + proj.getWeekOff1() + "','" + proj.getWeekOff2() + "')");
                i++;
            }
            result = da.executeUpdate("UPDATE timesheet SET status='open' WHERE start_Date='"+startDate+"'");


        });
        return result;
    }
    public Object updateProjectStaff(Object obj) {
        DataBaseHandle.runDB(da -> {
            Project proj = new Project();
            proj = (Project) obj;
            List<Map> listDel = proj.getListDeliverables();
            String projectId = "";
            String deliverables = "";
            for (Map<String, Object> row : listDel) {
                if ((boolean) row.get("value").equals(true)) {
                    String deliverableId = row.get("id").toString();
                    projectId = row.get("key").toString();
                    deliverables = deliverables + "#" + deliverableId;
                }
            }
            System.out.println(deliverables);
            result = da.executeUpdate("UPDATE projectStaff SET deliverable_ids_fk='"+deliverables+"',supervisor1='" + proj.getSupervisor1() + "',supervisor2='" + proj.getSupervisor2() + "',supervisor3='" + proj.getSupervisor3() + "',ts_type='" + proj.getTimeSheetType() + "',week_off1='" + proj.getWeekOff1() + "',week_off2='" + proj.getWeekOff2() + "',ts_split_date='" + proj.getTsSplitDate() + "',week_start_day='" + proj.getWeekStartDay() + "',ts_start_date='"+proj.getTsStartDate()+"',ts_end_date='"+proj.getTsEndDate()+"' WHERE proj_staff_id='" + proj.getProjStaffId() + "'");
        });
        return result;
    }
    public Object insertActivity(Object obj) {
        DataBaseHandle.runDB(da -> {
            Project proj = new Project();
            proj = (Project) obj;
            UUID uuidActivity = UUID.randomUUID();
            result = da.executeUpdate("INSERT INTO activity(activity_id,activity_name) VALUES('" + uuidActivity + "','" + proj.getActivityName() + "')");
        });
        return result;
    }

    public Object insertProjectDeliverables(Object obj) {

        DataBaseHandle.runDB(da -> {
            Project proj = new Project();
            proj = (Project) obj;
//            Date startDate = new SimpleDateFormat("mm/dd/yyyy").parse(proj.getDelStartDate());
//            Date endDate = new SimpleDateFormat("mm/dd/yyyy").parse(proj.getDelEndDate());
//            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
            UUID uuidDel = UUID.randomUUID();
            result = da.executeUpdate("INSERT INTO projectDeliverables(deliverable_id,project_id_fk,deliverable_name,del_start_date,del_end_date) VALUES('" + uuidDel + "','" + proj.getProjectId() + "','" + proj.getDeliverableName() + "','" + proj.getDelStartDate()+ "','" + proj.getDelEndDate()+ "')");
        });
        return result;
    }

    List<Map<String, Object>> allProjects;
    List<Map<String, Object>> clientNm;
    List<Map<String, Object>> deptNm;
    List<Map<String, Object>> subDeptNm;
    String clientName = "";
    String departmentName = "";
    String subdepartmentName = "";

    public List<Map<String, Object>> retrieveAllProjects() {

        DataBaseHandle.runDB(da -> {
            allProjects = da.executeQuery("SELECT * FROM PROJECTS");
            for (Map<String, Object> row : allProjects) {
                String cId = row.get("clientIdFk").toString();
                String dId = row.get("departmentIdFk").toString();
                clientNm = da.executeQuery("SELECT client_name from client WHERE client_id='" + cId + "'");
                for (Map<String, Object> cRow : clientNm) {
                    clientName = cRow.get("clientName").toString();
                }
                deptNm = da.executeQuery("SELECT department_name from departments WHERE department_id='" + dId + "'");
                for (Map<String, Object> dRow : deptNm) {
                    departmentName = dRow.get("departmentName").toString();
                }
                Object sdId = row.getOrDefault("subdepartmentIdFk", "");
                if (sdId != null) {
                    subDeptNm = da.executeQuery("SELECT subdepartment_name from subdepartments WHERE subdepartment_id='" + sdId + "'");
                    for (Map<String, Object> sdRow : subDeptNm) {
                        subdepartmentName = sdRow.get("subdepartmentName").toString();
                    }
                }
                row.put("clientName", clientName);
                row.put("departmentName", departmentName);
                row.put("subdepartmentName", subdepartmentName);
                clientName = "";
                departmentName = "";
                subdepartmentName = "";
            }
        });
        return allProjects;
    }

    public Object deleteProjectPermanent(String projectId) {
        DataBaseHandle.runDB(da -> {
            result = da.executeUpdate("DELETE from projects WHERE project_id='" + projectId + "'");
        });
        return result;
    }

    public Object deleteProjectStaff(String projectStaffId) {
        DataBaseHandle.runDB(da -> {
            result = da.executeUpdate("DELETE from projectStaff WHERE proj_staff_id='" + projectStaffId + "'");
        });
        return result;
    }

    List<Map<String, Object>> projectStaff;
    List<Map<String, Object>> projectNm;
    List<Map<String, Object>> supervisor1Nm;
    List<Map<String, Object>> supervisor2Nm;
    List<Map<String, Object>> supervisor3Nm;
    List<Map<String, Object>> userNm;
    List<Map<String, Object>> delNm;
    String supervisor1Name = "";
    String supervisor2Name = "";
    String supervisor3Name = "";
    String userName = "";
    String deliverableName = "";
    String projectName = "";

    public List<Map<String, Object>> retrieveProjectStaffDetails() {
        DataBaseHandle.runDB(da -> {
            projectStaff = da.executeQuery("SELECT proj_staff_id,user_id_fk,project_id_fk,deliverable_ids_fk,supervisor1,supervisor2,supervisor3,ts_type,ts_start_date,ts_end_date,ts_split_date from projectStaff");
            for (Map<String, Object> row : projectStaff) {
                String uId = row.get("userIdFk").toString();
                String pId = row.get("projectIdFk").toString();
                projectNm = da.executeQuery("SELECT project_name from projects WHERE project_id='" + pId + "'");
                for (Map<String, Object> cRow : projectNm) {
                    projectName = cRow.get("projectName").toString();
                }
                userNm = da.executeQuery("SELECT u_first_name from users WHERE user_id='" + uId + "'");
                for (Map<String, Object> cRow : userNm) {
                    userName = cRow.get("uFirstName").toString();
                }
                String s1Id = row.get("supervisor1").toString();
                supervisor1Nm = da.executeQuery("SELECT u_first_name from users WHERE user_id='" + s1Id + "'");
                for (Map<String, Object> cRow : supervisor1Nm) {
                    supervisor1Name = cRow.get("uFirstName").toString();
                }

                Object s2Id = row.getOrDefault("supervisor2", "");
                if (s2Id != null) {
                    supervisor2Nm = da.executeQuery("SELECT u_first_name from users WHERE user_id='" + s2Id + "'");
                    for (Map<String, Object> sdRow : supervisor2Nm) {
                        supervisor2Name = sdRow.get("uFirstName").toString();
                    }
                }
                Object s3Id = row.getOrDefault("supervisor3", "");
                if (s3Id != null) {
                    supervisor3Nm = da.executeQuery("SELECT u_first_name from users WHERE user_id='" + s3Id + "'");
                    for (Map<String, Object> sdRow : supervisor3Nm) {
                        supervisor3Name = sdRow.get("uFirstName").toString();
                    }
                }
                String dels = row.get("deliverableIdsFk").toString();
                String[] words = dels.split("#");
                List<String> delList = new ArrayList<String>();
                for (String w : words) {
                    if (w.length() > 1) {
                        delNm = da.executeQuery("SELECT deliverable_name from projectDeliverables WHERE deliverable_id='" + w + "'");
                        for (Map<String, Object> sdRow : delNm) {
                            deliverableName = sdRow.get("deliverableName").toString();
                            delList.add(deliverableName);
                        }
                    }
                }
                row.put("userName", userName);
                row.put("projectName", projectName);
                row.put("supervisor1Name", supervisor1Name);
                row.put("supervisor2Name", supervisor2Name);
                row.put("supervisor3Name", supervisor3Name);
                row.put("deliverables", delList);

                projectName = "";
                userName = "";
                supervisor1Name = "";
                supervisor2Name = "";
                supervisor3Name = "";
            }
        });
        return projectStaff;
    }

    public List<Map<String, Object>> retrieveProjectStaffDetailsById(String projStaffId) {
        DataBaseHandle.runDB(da -> {
            projectStaff = da.executeQuery("SELECT proj_staff_id,week_off1,week_off2,week_start_day,user_id_fk,project_id_fk,deliverable_ids_fk,supervisor1,supervisor2,supervisor3,ts_type,ts_start_date,ts_end_date,ts_split_date from projectStaff WHERE proj_staff_id='"+projStaffId+"'");
            for (Map<String, Object> row : projectStaff) {
                String uId = row.get("userIdFk").toString();
                String pId = row.get("projectIdFk").toString();
                projectNm = da.executeQuery("SELECT project_name from projects WHERE project_id='" + pId + "'");
                for (Map<String, Object> cRow : projectNm) {
                    projectName = cRow.get("projectName").toString();
                }
                userNm = da.executeQuery("SELECT u_first_name from users WHERE user_id='" + uId + "'");
                for (Map<String, Object> cRow : userNm) {
                    userName = cRow.get("uFirstName").toString();
                }
                String s1Id = row.get("supervisor1").toString();
                supervisor1Nm = da.executeQuery("SELECT u_first_name from users WHERE user_id='" + s1Id + "'");
                for (Map<String, Object> cRow : supervisor1Nm) {
                    supervisor1Name = cRow.get("uFirstName").toString();
                }

                Object s2Id = row.getOrDefault("supervisor2", "");
                if (s2Id != null) {
                    supervisor2Nm = da.executeQuery("SELECT u_first_name from users WHERE user_id='" + s2Id + "'");
                    for (Map<String, Object> sdRow : supervisor2Nm) {
                        supervisor2Name = sdRow.get("uFirstName").toString();
                    }
                }
                Object s3Id = row.getOrDefault("supervisor3", "");
                if (s3Id != null) {
                    supervisor3Nm = da.executeQuery("SELECT u_first_name from users WHERE user_id='" + s3Id + "'");
                    for (Map<String, Object> sdRow : supervisor3Nm) {
                        supervisor3Name = sdRow.get("uFirstName").toString();
                    }
                }
                String dels = row.get("deliverableIdsFk").toString();
                String[] words = dels.split("#");
                List<String> delList = new ArrayList<String>();
                for (String w : words) {
                    if (w.length() > 1) {
                        delNm = da.executeQuery("SELECT deliverable_name from projectDeliverables WHERE deliverable_id='" + w + "'");
                        for (Map<String, Object> sdRow : delNm) {
                            deliverableName = sdRow.get("deliverableName").toString();
                            delList.add(deliverableName);
                        }
                    }
                }
                row.put("userName", userName);
                row.put("projectName", projectName);
                row.put("supervisor1Name", supervisor1Name);
                row.put("supervisor2Name", supervisor2Name);
                row.put("supervisor3Name", supervisor3Name);
                row.put("deliverables", delList);

                projectName = "";
                userName = "";
                supervisor1Name = "";
                supervisor2Name = "";
                supervisor3Name = "";
            }
        });
        return projectStaff;
    }
}
