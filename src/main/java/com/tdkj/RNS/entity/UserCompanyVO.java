package com.tdkj.RNS.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @ClassName UserCompanyVO
 * @Author Chang
 * @date 2020.07.02 19:02
 */
@Data
public class UserCompanyVO implements Serializable {
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
     * 所属公司
     */
    private String companyName;
}
