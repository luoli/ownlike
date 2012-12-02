package com.ownliked.util.system.web;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NcgUtil {

//	static String contextPath = ServletActionContext.getRequest().getContextPath();

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");

	/**
	 * 把日期变成 yyyyMMddhhmmssSSS 字符串返回
	 * 
	 * @return
	 */
	public static String getFullDate() {
		Date date = new Date();
		return sdf.format(date);
	}
	
	//////////////////////////////////////////////////////////////////////////////////

	public static boolean blankObject(Object temp) {
		if (null != temp && !"".equals(temp) && "" != temp) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean blankNumber(Object temp) {
		boolean resType = false;
		if (blankObject(temp)) {
			if (temp.toString().matches("\\d+")) {
				resType = true;
			}
		}
		return resType;
	}

	 /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     * 
     * @param value
     *            指定的字符串
     * @return 字符串的长度
     */
	public static int valideTextLength(String queryText) {
		int valueLength = 0;
		try {
			if(null!=queryText && !"".equals(queryText)&& queryText.length()>0){
				String chinese = "[\u0391-\uFFE5]";
				/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
				for (int i = 0; i < queryText.length(); i++) {
					/* 获取一个字符 */
					String temp = queryText.substring(i, i + 1);
					/* 判断是否为中文字符 */
					if (temp.matches(chinese)) {
						/* 中文字符长度为2 */
						valueLength += 2;
					} else {
						/* 其他字符长度为1 */
						valueLength += 1;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valueLength;
	}

}
