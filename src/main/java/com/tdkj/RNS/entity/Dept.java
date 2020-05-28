package com.tdkj.RNS.entity;

import java.util.Date;

public class Dept {
    private Integer id; //部门id

    private String deptName; //部门名称

    private String deptDesc; //部门描述

    private Date createTime; //创建时间

    private Date mofiyTime;  //修改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc == null ? null : deptDesc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getMofiyTime() {
        return mofiyTime;
    }

    public void setMofiyTime(Date mofiyTime) {
        this.mofiyTime = mofiyTime;
    }
}