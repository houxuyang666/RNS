package com.tdkj.RNS.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Vehicleorders)实体类
 *
 * @author makejava
 * @since 2020-06-30 09:11:13
 */
@Data
public class Vehicleorders implements Serializable {
    private static final long serialVersionUID = -35190928921008585L;
    /**
    * 订单编号
    */
    private String orderId;
    /**
    * 车辆id
    */
    private Integer vehicleId;
    /**
    * 车辆司机
    */
    private String vehicleDriver;
    /**
    * 开始地址
    */
    private String beganAddress;
    /**
    * 目的地地址
    */
    private String destinationAddress;
    /**
    * 结束地址
    */
    private String endAddress;
    /**
    * 里程数
    */
    private String mileage;
    /**
    * 开始里程数照片
    */
    private String mileageBeganUrl;
    /**
    * 结束里程数照片
    */
    private String mileageEndUrl;
    /**
    * 开始时间
    */
    private Date beganTime;
    /**
    * 结束时间
    */
    private Date endTime;
    /**
    * 0 在用 1未用 2已申请
    */
    private Integer orderStatus;
    /**
    * 订单描述
    */
    private String orderDesc;

}