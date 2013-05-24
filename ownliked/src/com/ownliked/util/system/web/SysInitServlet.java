package com.ownliked.util.system.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class SysInitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(SysInitServlet.class);
	public static String clip_upload_path;
	public static String clip_upload_path_folder;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		log.info("sysInit run.");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		clip_upload_path = config.getInitParameter("clip_upload_path");
		clip_upload_path_folder = config.getInitParameter("clip_upload_path_folder");
	}

}
