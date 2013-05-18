package com.ownliked.util.system.web;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * json 构建类
 * @author jianglijia
 *
 */
public class JsonStringBuilder {

	/**
	 * 从list对象获得字符集
	 * @param list
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public static String getAjaxString(List list) throws UnsupportedEncodingException{
		JSONArray ja = JSONArray.fromObject(list);
		return ja.toString();
	}
	
	/**
	 * 从map对象中获得字符集
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getAjaxString(Map map){
		JSONObject jo = JSONObject.fromObject(map);
		return jo.toString();
	}
	
	/**
	 * 从object对象中获得字符集
	 * @param object
	 * @return
	 */
	public static String getAjaxString(Object object){
		JSONObject jo = JSONObject.fromObject(object);
		return jo.toString();
	}
	
}
