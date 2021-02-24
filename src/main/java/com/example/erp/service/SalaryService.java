package com.example.erp.service;

import com.example.erp.bean.Employee_Salary;
import com.example.erp.bean.Employees;
import com.example.erp.bean.GetSalary;
import com.example.erp.dao.EmployeeDao;
import com.example.erp.dao.EmployeeDaoImpl;
import com.example.erp.dao.SalaryDao;
import com.example.erp.dao.SalaryDaoImpl;

import java.util.List;

public class SalaryService {
    SalaryDao salaryDao= new SalaryDaoImpl();
    public List<Employee_Salary> fetchsalary(GetSalary e)
    {

            System.out.println("in services\n");
            return salaryDao.fetchsalary(e);


    }
}