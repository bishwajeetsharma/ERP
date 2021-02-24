package com.example.erp.dao;

import com.example.erp.bean.Employees;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;

public interface EmployeeDao {
    Employees verifyemail(Employees employee);
 //   boolean saveImage(Employees employees);

    int uploadProfilePic(InputStream fileInputStream, FormDataContentDisposition fileMetaData);

    int updateProfilePicPath(String name,Integer emp_id);

    Employees getPhotoPath(Employees employees);
     int updateProfile(Employees employees);
}
