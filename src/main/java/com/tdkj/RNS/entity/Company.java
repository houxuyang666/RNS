package com.tdkj.RNS.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Company)实体类
 *
 * @author makejava
 * @since 2020-06-30 09:11:13
 */
public class Company implements Serializable {
    private static final long serialVersionUID = 265067661669792934L;
    /**
    * 主键
    */
    private Integer companyId;
    /**
    * 公司名称
    */
    private String companyName;
    /**
    * 公司地址
    */
    private String companyAddress;
    /**
    * 车辆负责人名称
    */
    private String vehicleOfficerName;
    /**
    * 车辆负责人联系电话
    */
    private String vehicleOfficerTel;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 修改时间
    */
    private Date modifyTime;


    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getVehicleOfficerName() {
        return vehicleOfficerName;
    }

    public void setVehicleOfficerName(String vehicleOfficerName) {
        this.vehicleOfficerName = vehicleOfficerName;
    }

    public String getVehicleOfficerTel() {
        return vehicleOfficerTel;
    }

    public void setVehicleOfficerTel(String vehicleOfficerTel) {
        this.vehicleOfficerTel = vehicleOfficerTel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}