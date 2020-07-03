package com.tdkj.RNS.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserinfoVO {
    private static final long serialVersionUID = 670553959012156814L;


    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 账号状态
     */
    private Integer status;
    /**
     * 角色id
     */
    private Integer rid;
    /**
     * 角色名称
     */
    private String rolename;
    /**
     * 链接用户信息表
     */
    private Integer userinfoId;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 性别 1为男 0为女
     */
    private Integer sex;
    /**
     * 电话
     */
    private String tel;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 所属公司编号
     */
    private Integer companyId;
    /**
     * 所属公司
     */
    private String companyName;

}