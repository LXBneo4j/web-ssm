package com.heitian.ssm.model;

import java.util.Date;

/**
 * Created by lxb on 2017/5/19.
 */
public class Sms {

    protected String phone;

    protected Integer number;

    protected Date date;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
