package com.lijia.exmple;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonTest {

	public static void main(String[] args) {
		GsonBuilder gb = new GsonBuilder();
		Gson g = gb.create();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", 1);
		map.put("b", "");
		map.put("c", null);
		String str = g.toJson(map);
		System.out.println("str="+str);
	}
	
}
