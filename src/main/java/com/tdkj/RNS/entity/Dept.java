package com.tdkj.RNS.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Dept {
    private Integer id; //部门id

    private String deptName; //部门名称

    private String deptDesc; //部门描述

    private Date createTime; //创建时间

    private Date mofiyTime;  //修改时间

}