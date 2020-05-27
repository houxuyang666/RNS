package com.tdkj.RNS.entity;

public class Permission {
    private Integer pid;  //ID

    private String permissionname;  //权限

    private Integer rid;  //角色ID

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname == null ? null : permissionname.trim();
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}