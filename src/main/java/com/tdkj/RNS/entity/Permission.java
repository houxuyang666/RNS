package com.tdkj.RNS.entity;

import java.io.Serializable;

/**
 * 权限表(PermissionVO)实体类
 *
 * @author makejava
 * @since 2020-06-04 14:32:46
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = 734688791036642118L;
    /**
    * 主键
    */
    private Integer pid;
    /**
    * 权限名称
    */
    private String permissionName;
    /**
    * 权限code
    */
    private String permissionUrl;


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

}