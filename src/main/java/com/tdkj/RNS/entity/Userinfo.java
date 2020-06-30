package com.tdkj.RNS.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Userinfo {
    private static final long serialVersionUID = 670553959012156814L;
    /**
     * 主键
     */
    private Integer id;
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
     * 用户状态 0为在职 1为离职
     */
    private Integer status;
    /**
     * 所属公司编号
     */
    private Integer companyId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;

}