package com.tdkj.RNS.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Vehicleorders)实体类
 *
 * @author makejava
 * @since 2020-06-30 09:11:13
 */
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


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleDriver() {
        return vehicleDriver;
    }

    public void setVehicleDriver(String vehicleDriver) {
        this.vehicleDriver = vehicleDriver;
    }

    public String getBeganAddress() {
        return beganAddress;
    }

    public void setBeganAddress(String beganAddress) {
        this.beganAddress = beganAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getMileageBeganUrl() {
        return mileageBeganUrl;
    }

    public void setMileageBeganUrl(String mileageBeganUrl) {
        this.mileageBeganUrl = mileageBeganUrl;
    }

    public String getMileageEndUrl() {
        return mileageEndUrl;
    }

    public void setMileageEndUrl(String mileageEndUrl) {
        this.mileageEndUrl = mileageEndUrl;
    }

    public Date getBeganTime() {
        return beganTime;
    }

    public void setBeganTime(Date beganTime) {
        this.beganTime = beganTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

}