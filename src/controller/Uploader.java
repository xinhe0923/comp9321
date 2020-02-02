package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import dto.User;
import http.utils.multipartrequest.MultipartRequest;
import util.JdbcUtil;

import java.io.File;


/**
 * Servlet implementation class Uploader
 */
@WebServlet("/Uploader")
public class Uploader extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Uploader() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		
		
		
		
		
	   PrintWriter out = response.getWriter();
	   if(!ServletFileUpload.isMultipartContent(request)){
		   out.println("Nothing to upload");
		   return;
		   
	   }
	   
	   
	   FileItemFactory itemfactory = new DiskFileItemFactory();
	   ServletFileUpload upload=new ServletFileUpload(itemfactory);
	   try{
		   List<FileItem> items= upload.parseRequest(new ServletRequestContext(request));
		   for(FileItem item:items){
			   String contentType=item.getContentType();
			   String fileName=item.getName();
			   System.out.println(fileName);
			   if(!contentType.equals("image/png")){
				   out.println("only png format image");
				   continue;
			   }
	            HttpSession session_image = request.getSession();
	            String image_url= (String)session_image.getAttribute("image_url");
			   try {
				item.write(new File("C:/image1/"+image_url+".png"));
				String sql="UPDATE `bookstore_dustin`.`item` SET `imageURL`=? WHERE `title`=?";
				JdbcUtil.getQueryRunner().update(sql,image_url+".png",image_url);

				out.println("File Saved Successfully");
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		   }
	   }catch(FileUploadException e){
		   out.println("upload fail");
	   }
	   
		
	}

}
