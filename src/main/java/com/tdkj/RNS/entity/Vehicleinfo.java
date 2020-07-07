package com.tdkj.RNS.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Vehicleinfo)实体类
 *
 * @author makejava
 * @since 2020-06-30 09:11:13
 */
@Data
public class Vehicleinfo implements Serializable {
    private static final long serialVersionUID = -82375104396015611L;
    /**
     * 车辆id
     */
    private Integer vehicleinfoId;
    /**
     * 车辆型号
     */
    private String vehicleType;
    /**
     * 车辆载人数量
     */
    private Integer vehicleSeatsNumber;
    /**
     * 车辆车牌号
     */
    private String vehicleNumber;
    /**
     * 车辆状态 0在用 1未用
     */
    private Integer vehicleStatus;
    /**
     * 车辆隶属公司
     */
    private Integer vehicleAffiliationCompany;
    /**
     * 车辆隶属个人
     */
    private Integer vehicleAffiliationPersonal;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;

}