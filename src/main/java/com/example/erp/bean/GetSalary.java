package com.example.erp.bean;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class GetSalary {
    private String employeeid;
    private String date;
    public Integer getEmployeeid() {
        return Integer.parseInt(employeeid);
    }
    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public GetSalary() {
    }

    public Date getDate() {

        System.out.println(java.sql.Date.valueOf(this.date));
        return java.sql.Date.valueOf(this.date);
    }
    public void setDate(String date) {
        this.date = date;
    }

}
