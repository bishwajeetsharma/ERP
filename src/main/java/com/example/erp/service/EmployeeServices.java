package com.example.erp.service;

import com.example.erp.bean.Employees;
import com.example.erp.dao.EmployeeDao;
import com.example.erp.dao.EmployeeDaoImpl;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;

public class EmployeeServices {
    EmployeeDao employeeDao= new EmployeeDaoImpl();
    public Employees verifyemail(Employees employee)
    {
        return employeeDao.verifyemail(employee);
    }
//    public boolean saveImage(Employees employee) {
//        return employeeDao.saveImage(employee);
//    }
    public int uploadProfilePic(InputStream fileInputStream, FormDataContentDisposition fileMetaData, Integer emp_id) {
        //String currentDirectory = System.getProperty("user.dir");
        //System.out.println("The current working directory is " + currentDirectory);

        int upload_status = employeeDao.uploadProfilePic(fileInputStream,fileMetaData);

        if(upload_status==1) {
            int upload = employeeDao.updateProfilePicPath(fileMetaData.getFileName(),emp_id);
            if(upload != 1) return 1;
        }
        return 0;
    }

    public Employees getPhotoPath(Employees employees) { return employeeDao.getPhotoPath(employees);    }

}
