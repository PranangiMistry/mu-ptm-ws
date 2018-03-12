package com.integ.ptm.module;

/**
 * Author: mpanchal
 * Date: 2/2/18 2:47 PM
 */
public class MyResponse {

    String status;
    String stract;
    String msg;

    public MyResponse(){
        status="sucess"  ;
    };

    public MyResponse(String status, String stract, String msg) {
        this.status =status;
        this.stract = stract;
        this.msg = msg;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStract()
    {
        return stract;
    }

    public void setStract(String stract)
    {
        this.stract = stract;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
