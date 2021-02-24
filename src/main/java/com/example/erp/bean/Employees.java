package com.example.erp.bean;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeid;
    @Column(nullable = false)
    private String firstname;
    private String lastname;
    @Column(nullable = false,unique = true)
    private String email;
    private String title;
    private String photograph_path;
    @OneToMany(mappedBy = "employee",fetch = FetchType.EAGER)
    private List<Employee_Salary> emp_salary;
    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTitle() {
        return title;
    }

    public Employees() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotograph_path() {
        return photograph_path;
    }

    public void setPhotograph_path(String photograph_path) {
        this.photograph_path = photograph_path;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @JsonbTransient
    public List<Employee_Salary> getEmp_salary() {
        return emp_salary;
    }
    public void setEmp_salary(List<Employee_Salary> emp_salary) {
        this.emp_salary = emp_salary;
    }
}
