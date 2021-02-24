package com.example.erp.controller;

import com.example.erp.bean.Employees;
import com.example.erp.service.EmployeeServices;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import javax.activation.MimetypesFileTypeMap;
import javax.validation.constraints.Null;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Path("employee")
public class EmployeeController {
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginemployee(Employees employee) {
        EmployeeServices emps = new EmployeeServices();
        Employees e = emps.verifyemail(employee);
        if (e == null) {
            System.out.println("not verified");
            return Response.noContent().build();
        } else {
            System.out.println("verified");
            return Response.ok().entity(e).build();
        }
    }

//    @POST
//    @Path("/saveImage")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response employeeImage(Employees employee) throws URISyntaxException {
//        EmployeeServices employeeServices = new EmployeeServices();
//
//        if (employeeServices.saveImage(employee)) {
//            return Response.ok().build(); // Response 200
//        } else {
//            return Response.status(203).build();  //Response 203
//        }
//    }

//    @POST
//    @Path("/image")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response imageUpload(@FormDataParam("file") InputStream fileInputStream,
//                                @FormDataParam("file") FormDataContentDisposition fileMetaData,
//                                @FormDataParam("emp_id") String employee_id) throws URISyntaxException {
//
//        String upload_path = "file://///home/ubu/Documents/erp/" +employee_id + "-" + fileMetaData.getFileName();
//        try {
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            OutputStream out = new FileOutputStream(new File(upload_path));
//            while ((read = fileInputStream.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//
//        StreamingOutput fileStream = new StreamingOutput() {
//            @Override
//            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
//                java.nio.file.Path path = Paths.get(upload_path);
//                byte[] data = Files.readAllBytes(path);
//                outputStream.write(data);
//                outputStream.flush();
//            }
//        };
//
//        return Response
//                .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
//                .header("content-disposition", "attachment; filename = " + fileMetaData.getFileName())
//                .build();
//    }
    @POST
    @Path("/uploadImage")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public Response uploadImage(  @FormDataParam("file") InputStream fileInputStream,
                                  @FormDataParam("file") FormDataContentDisposition fileMetaData, @FormDataParam("emp_id") Integer emp_id) throws Exception {
        System.out.println("File Upload Initiated at Controller for emp "+ emp_id);

        String currentDirectory = System.getProperty("user.dir");
        System.out.println("The current working directory is " + currentDirectory);
         EmployeeServices employeeservices=new EmployeeServices();
        int result = employeeservices.uploadProfilePic(fileInputStream, fileMetaData, emp_id);
        if (result == 0) {
            System.out.println("File Upload Failed");
            return Response.noContent().build();
        }
        System.out.println("File Upload Response OK ");
        return Response.ok().build();
    }
    @GET
    @Path("/images/{filename}")
    @Produces("image/*")
    public Response getImage(@PathParam("filename") String filename) {
        System.out.println("Fetch image Request Received");
        String path = "/home/ubu/Documents/erp/" + filename;
        File f = new File(path);
        if (!f.exists()) {
            throw new WebApplicationException(404);
        }
        String mt = new MimetypesFileTypeMap().getContentType(f);
        return Response.ok(f, mt).build();
    }
}