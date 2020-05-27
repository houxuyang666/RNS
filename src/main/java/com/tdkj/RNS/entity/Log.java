package com.tdkj.RNS.entity;

import java.util.Date;

public class Log {
    private Integer id;  //ID

    private String operateor;  //当前用户

    private String operatetype; //操作

    private Date operatedate; //时间

    private String operateresult;  //状态

    private String ip;  //用户ip地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperateor() {
        return operateor;
    }

    public void setOperateor(String operateor) {
        this.operateor = operateor == null ? null : operateor.trim();
    }

    public String getOperatetype() {
        return operatetype;
    }

    public void setOperatetype(String operatetype) {
        this.operatetype = operatetype == null ? null : operatetype.trim();
    }

    public Date getOperatedate() {
        return operatedate;
    }

    public void setOperatedate(Date operatedate) {
        this.operatedate = operatedate;
    }

    public String getOperateresult() {
        return operateresult;
    }

    public void setOperateresult(String operateresult) {
        this.operateresult = operateresult == null ? null : operateresult.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
}