package com.tdkj.RNS.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = -40649777010256660L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 密码盐值
     */
    private String salt;
    /**
     * 账号状态 0为正常 1为锁定
     */
    private Integer status;
    /**
     * 角色id
     */
    private Integer rid;
    /**
     * 链接用户信息表
     */
    private Integer userinfoId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;


}