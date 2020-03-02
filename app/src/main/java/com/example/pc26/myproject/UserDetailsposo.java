package com.example.pc26.myproject;

import java.io.Serializable;

/**
 * Created by pc26 on 2/7/2020.
 */

public class UserDetailsposo implements Serializable {
    private String name;
    private String phone_no;
    private String address;
    private String gender;
    private String course;
    public UserDetailsposo() {

    }

    private  String key;
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_no() {
        return phone_no;
    }
    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString()
    {
        return "Organisation [course=" + course + ", address=" + address + ",name=" + name + ",gender=" + gender + ", phon e_no=" + phone_no + "]";
    }
}

