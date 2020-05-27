package com.tdkj.RNS.entity;

import java.util.Date;

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

    private Date createTime;

    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getIdUpPhoto() {
        return idUpPhoto;
    }

    public void setIdUpPhoto(String idUpPhoto) {
        this.idUpPhoto = idUpPhoto == null ? null : idUpPhoto.trim();
    }

    public String getIdDownPhoto() {
        return idDownPhoto;
    }

    public void setIdDownPhoto(String idDownPhoto) {
        this.idDownPhoto = idDownPhoto == null ? null : idDownPhoto.trim();
    }

    public String getQualificationsPhoto() {
        return qualificationsPhoto;
    }

    public void setQualificationsPhoto(String qualificationsPhoto) {
        this.qualificationsPhoto = qualificationsPhoto == null ? null : qualificationsPhoto.trim();
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
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