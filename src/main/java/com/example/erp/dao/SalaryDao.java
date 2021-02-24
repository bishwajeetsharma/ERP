package com.example.erp.dao;

import com.example.erp.bean.Employee_Salary;
import com.example.erp.bean.GetSalary;

import java.util.List;

public interface SalaryDao {
    List<Employee_Salary> fetchsalary(GetSalary e);
}
