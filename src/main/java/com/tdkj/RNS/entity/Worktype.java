package com.tdkj.RNS.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Worktype {
    private Integer id; //ID

    private String worktypeName; //工种名称

    private Date createTime; //创建时间

    private Date modfiyTime; //修改时间

}