package com.example.erp.dao;

import com.example.erp.bean.Employees;
import com.example.erp.utils.SessionUtil;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.ws.rs.WebApplicationException;
import java.io.*;

public class EmployeeDaoImpl implements EmployeeDao{

    @Override
    public Employees verifyemail(Employees employee) {
        try (Session session = SessionUtil.getSession()) {
            Query query = session.createQuery("from Employees where email=:email");
            query.setParameter("email", employee.getEmail());
            for (final Object fetch : query.list()) {
                return (Employees) fetch;
            }
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }
        return null;
    }


    @Override
    public int updateProfile(Employees employees){
        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "update Employees set firstname=:first_name, lastname=:last_name, email=:email, title=:title where employeeid=:emp_id";
            Query query = session.createQuery(hql);
            query.setParameter("emp_id",employees.getEmployeeid());
            query.setParameter("first_name", employees.getFirstname());
            query.setParameter("last_name",employees.getLastname());
            query.setParameter("email",employees.getEmail());
            query.setParameter("title",employees.getTitle());
            int result = query.executeUpdate();
            transaction.commit();
            return result;

        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return 0;
        }
    }

    @Override
    public int updateProfilePicPath(String name, Integer emp_id){
        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            System.out.println("Name : "+ name);
            System.out.println("emp_id " + emp_id);
            String hql = "update Employees set photograph_path=:name where employeeid=:emp_id";
            Query query = session.createQuery(hql);
            query.setParameter("emp_id",emp_id);
            query.setParameter("name",name);
            int result = query.executeUpdate();
            transaction.commit();
            return result;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return 0;
        }
    }

    @Override
    public int uploadProfilePic(InputStream fileInputStream, FormDataContentDisposition fileMetaData){
        String UPLOAD_PATH = "/home/ubu/Documents/erp/";
        try
        {
            String currentDirectory = System.getProperty("user.dir");
            System.out.println("The current working directory is " + currentDirectory);

            int read = 0;
            byte[] bytes = new byte[4096];
            System.out.println("File Upload at location "+ UPLOAD_PATH + fileMetaData.getFileName());
            OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileMetaData.getFileName()));
            while ((read = fileInputStream.read(bytes)) != -1)
            {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e)
        {
            System.out.println("Exception at Dao");
            System.out.println(e);
            throw new WebApplicationException("Error while uploading file. Please try again !!");
        }
        return 1;
    }
    @Override
    public Employees getPhotoPath(Employees employees) {
        try (Session session = SessionUtil.getSession()) {

            Query query = session.createQuery("from Employees where employeeid=:emp_id");
            query.setParameter("emp_id", employees.getEmployeeid());
            for (final Object fetch : query.list()) {
                return (Employees) fetch;
            }
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }
        return null;
    }
}
