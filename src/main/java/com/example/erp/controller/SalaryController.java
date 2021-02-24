package com.example.erp.controller;

import com.example.erp.bean.Employee_Salary;
import com.example.erp.bean.GetSalary;
import com.example.erp.service.SalaryService;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

@Path("salary")
public class SalaryController {
    @POST
    @Path("/getsalary")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response fetchsalary(GetSalary empsal) {
        SalaryService s= new SalaryService();
        List<Employee_Salary> esal=s.fetchsalary(empsal);
        if(esal== null) {
            System.out.println("in if at controller");
            return Response.noContent().build();
        }
        else{
            System.out.println("in controller");
           for(int i=0;i<esal.size();i++)
           {
               Employee_Salary var=esal.get(i);
               System.out.println(var.getPayment_date());
               System.out.println(var.getAmount());
               System.out.println(var.getDescription());
               System.out.println(var.getEmployee().getEmployeeid());
               System.out.println(var.getId());
           }
//            System.out.println(esal.get(0).getPayment_date());
            return Response.ok().entity(esal).build();}
    }

}



