package com.ownliked.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.ownliked.pojo.OwnUser;
import com.ownliked.service.board.OwnBoardService;
import com.ownliked.util.system.web.JsonStringBuilder;

public class BaseController {

	@Resource(name="ownBoardService")
	protected OwnBoardService ownBoardService;
	public OwnBoardService getOwnBoardService() {
		return ownBoardService;
	}
	public void setOwnBoardService(OwnBoardService ownBoardService) {
		this.ownBoardService = ownBoardService;
	}
	
	/**
	 * 获得session用户
	 * @param request
	 * @return
	 */
	protected OwnUser getSessionUser(HttpServletRequest request){
		return (OwnUser)request.getSession().getAttribute("OWNUSERLOGIN");
	}
	
	/**
	 * 返回Ajax响应字符串，将处理结果返回
	 * text/json
	 */
	protected void sendAjaxResponse(HttpServletResponse res, String message) {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/json");
		try {
			res.getWriter().print(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回Ajax响应字符串，将处理结果返回
	 * text/json
	 */
	protected void sendAjaxResponse(HttpServletResponse res, Object obj) throws Exception {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/json");
		res.getWriter().print(JsonStringBuilder.getAjaxString(obj));
	}

	/**
	 * 返回Ajax响应字符串，将处理结果返回
	 * text/json
	 */
	@SuppressWarnings("unchecked")
	protected void sendAjaxResponse(HttpServletResponse res, List list) throws Exception {
		JSONArray array = JSONArray.fromObject(list);
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/json");
		res.getWriter().print(array.toString());
	}
	
	/**
	 * 返回Ajax响应字符串，将处理结果返回
	 * 根据type为text/html类型
	 */
	protected void sendAjaxResponseByType(HttpServletResponse res, String message) throws Exception {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		res.getWriter().print(message);
	}
	
}
