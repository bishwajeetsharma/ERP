package com.example.erp.dao;

import com.example.erp.bean.Employee_Salary;
import com.example.erp.bean.Employees;
import com.example.erp.bean.GetSalary;
import com.example.erp.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SalaryDaoImpl implements SalaryDao{
    @Override
    public List<Employee_Salary> fetchsalary(GetSalary e) {
        Session session = SessionUtil.getSession();
        List<Employee_Salary> empsal = new ArrayList<>();
        Query query=session.createQuery("from Employee_Salary where employee.employeeid=:eid and payment_date<=:date");
         query.setParameter("eid",e.getEmployeeid());
         query.setParameter("date",e.getDate());
        try {
            for (final Object fetch : query.list()) {
                Employee_Salary var=(Employee_Salary)fetch;
                System.out.println(var.getPayment_date());
                System.out.println(var.getAmount());
                System.out.println(var.getDescription());
                System.out.println(var.getEmployee().getEmployeeid());
                System.out.println(var.getId());
                empsal.add((Employee_Salary)fetch);
              }

        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
        }
        return empsal;



    }


}

