package com.example.demo.bean;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class User implements Serializable {
    //id：user表的主键
    private Integer id;
    //姓名
    private String name;
    //性别
    private String gender;
    //年龄
    private Integer age;
    //入职日期
    private String entryDate;
    //个人简介
    private String orientation;
    //个人计划
    private String plan;
    //个人介绍
    private String introduction;
    //个人邮箱
    private String email;
    //个人电话
    private String phone;
    //所在省份
    private String provinceValue;
    //所在城市
    private String cityValue;
    //所在区
    private String regionValue;

    public String getProvinceValue() {
        return provinceValue;
    }

    public void setProvinceValue(String provinceValue) {
        this.provinceValue = provinceValue;
    }

    public String getCityValue() {
        return cityValue;
    }

    public void setCityValue(String cityValue) {
        this.cityValue = cityValue;
    }

    public String getRegionValue() {
        return regionValue;
    }

    public void setRegionValue(String regionValue) {
        this.regionValue = regionValue;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
