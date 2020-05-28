package com.tdkj.RNS.entity;

public class Worktype {
    private Integer id; //ID

    private String worktypeName; //工种名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorktypeName() {
        return worktypeName;
    }

    public void setWorktypeName(String worktypeName) {
        this.worktypeName = worktypeName == null ? null : worktypeName.trim();
    }
}