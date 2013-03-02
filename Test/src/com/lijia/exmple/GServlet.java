package com.lijia.exmple;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/GServlet")
public class GServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GsonBuilder gb = new GsonBuilder();
		Gson g = gb.create();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", 1);
		map.put("b", "");
		map.put("c", null);
		String str = g.toJson(map);
		System.out.println("str="+str);
		response.getWriter().print("<a href=\"GServlet\">index.jsp</a>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
