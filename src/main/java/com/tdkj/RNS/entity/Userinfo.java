package com.tdkj.RNS.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Userinfo {
    private Integer id;  //主键

    private String name; //用户姓名

    private Integer sex=1; //性别  0为女 1为男

    private Integer age; //年龄

    private Integer status =0;//用户状态 0为在职 1为离职

    private String phone; //联系方式

    private String idNumber; //身份证号

    private String address; //通讯地址

    private String photo; //照片

    private String idUpPhoto; //身份证正面

    private String idDownPhoto; //身份证反面

    private String qualificationsPhoto; //从业资格证照片

    private Integer deptid; //所属部门编号

    private Date createTime; //创建时间

    private Date modifyTime; //修改时间

}