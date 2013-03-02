package com.lijia.exmple.pjax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ajax前进/后退
 * @author root
 *
 */
@WebServlet("/pjaxServlet")
public class PjaxDemo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bool = req.getParameter("pjax");
		PrintWriter out = resp.getWriter();
		if(null != bool && bool.equals("true")){
			out.print("<a class=\"pjax\" href=\"GServlet\">this change</a>");
		}else{
//			out.print("<a href=\"GServlet\">============this all==========</a>");
			req.setAttribute("pjax", "true");
			req.getRequestDispatcher("pjax/index.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
