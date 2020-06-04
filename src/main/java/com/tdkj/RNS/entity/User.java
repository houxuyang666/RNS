package com.tdkj.RNS.entity;

import lombok.Data;

@Data
public class User {
    private Integer id; //ID

    private String username; //用户名

    private String password; //密码

    private String salt; //密码盐值

    private Integer status =0; //账号状态 0为正常 默认正常  1为锁定

    private Integer rid; //角色ID

    private Integer userinfoId; //外键  用于链接 用户信息表


}