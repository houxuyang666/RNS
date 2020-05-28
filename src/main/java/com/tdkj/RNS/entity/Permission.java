package com.tdkj.RNS.entity;

import lombok.Data;

@Data
public class Permission {
    private Integer pid;  //ID

    private String permissionname;  //权限

    private Integer rid;  //角色ID

}