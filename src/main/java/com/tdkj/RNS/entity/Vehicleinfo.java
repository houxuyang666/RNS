package com.tdkj.RNS.entity;

import java.io.Serializable;

/**
 * (Vehicleinfo)实体类
 *
 * @author makejava
 * @since 2020-06-30 09:11:13
 */
public class Vehicleinfo implements Serializable {
    private static final long serialVersionUID = 141058992214694579L;
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
    * 车辆状态 0在用 1未用 2已申请
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


    public Integer getVehicleinfoId() {
        return vehicleinfoId;
    }

    public void setVehicleinfoId(Integer vehicleinfoId) {
        this.vehicleinfoId = vehicleinfoId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getVehicleSeatsNumber() {
        return vehicleSeatsNumber;
    }

    public void setVehicleSeatsNumber(Integer vehicleSeatsNumber) {
        this.vehicleSeatsNumber = vehicleSeatsNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Integer getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(Integer vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public Integer getVehicleAffiliationCompany() {
        return vehicleAffiliationCompany;
    }

    public void setVehicleAffiliationCompany(Integer vehicleAffiliationCompany) {
        this.vehicleAffiliationCompany = vehicleAffiliationCompany;
    }

    public Integer getVehicleAffiliationPersonal() {
        return vehicleAffiliationPersonal;
    }

    public void setVehicleAffiliationPersonal(Integer vehicleAffiliationPersonal) {
        this.vehicleAffiliationPersonal = vehicleAffiliationPersonal;
    }

}