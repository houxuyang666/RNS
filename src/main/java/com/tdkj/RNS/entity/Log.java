package com.tdkj.RNS.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Log {
    private Integer id;  //ID

    private String operateor;  //当前用户

    private String operatetype; //操作

    private Date operatedate; //时间

    private String operateresult;  //状态

    private String ip;  //用户ip地址

}