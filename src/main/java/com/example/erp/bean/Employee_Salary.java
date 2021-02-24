package com.example.erp.bean;

import org.hibernate.annotations.ColumnDefault;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Employee_Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Employees employee;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date payment_date;
    @Column(nullable = false)
    private Double amount;
    public Employee_Salary() {
    }
    @Column(nullable = false)
    private String description;

    public Date getPayment_date() {
        return this.payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @JsonbTransient
    public Employees getEmployee() {
        return employee;
    }
    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

}
