package com.lijia.exmple.meitu;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

@WebServlet("/meitu")
public class ImageUtil extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try{
			List<FileItem> list = upload.parseRequest(req);
			for(int i = 0; i < list.size(); i++){
				FileItem fileItem = list.get(i);
				String name = "";
				if(fileItem.isFormField()){
					String filedName = fileItem.getFieldName();
					System.out.println(fileItem.getString("utf-8"));
				}else{
					if(null != fileItem.getName() && !fileItem.getName().equals("")){
						name = fileItem.getName().substring(fileItem.getName().lastIndexOf("\\")+1);
						String path = req.getSession().getServletContext().getRealPath("/");
						fileItem.write(new File(path+"\\pics\\"+name));
						System.out.println("====="+name);
					}else{
						System.out.println(fileItem.getFieldName());
					}
				}
			}
		}catch(FileUploadException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
