package com.test;

import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

import com.mongodb.DBObject;

public class MyUser implements DBObject {
	
	private String id;
	
	private String name;
	
	private int age;
	
	private String sex;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public boolean isPartialObject() {
		return false;
	}

	@Override
	public void markAsPartialObject() {
	}

	@Override
	public boolean containsField(String s) {
		return false;
	}

	@Override
	public boolean containsKey(String s) {
		return false;
	}

	@Override
	public Object get(String key) {
		return null;
	}

	@Override
	public Set<String> keySet() {
		return null;
	}

	@Override
	public Object put(String key, Object v) {
		return null;
	}

	@Override
	public void putAll(BSONObject o) {
	}

	@Override
	public void putAll(Map m) {
	}

	@Override
	public Object removeField(String key) {
		return null;
	}

	@Override
	public Map toMap() {
		return null;
	}

}
