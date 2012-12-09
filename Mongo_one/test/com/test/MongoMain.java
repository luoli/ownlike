package com.test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class MongoMain {
	
	private Mongo mg = null;
	private DB db = null;
	private  DBCollection dbCollection = null;
	
	@Before
	public void init(){
		try {
			mg = new Mongo();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		db = mg.getDB("test");
		dbCollection = db.getCollection("users");
	}
	
	@After
	public void destory(){
		if(mg != null){
			mg.close();
		}
		mg = null;
		db = null;
		dbCollection = null;
		System.gc();
	}
	
	public void print(Object o){
		System.out.println(o);
	}
	
	@Test
	public void queryAll(){
		print("查询users的所有数据！");
		DBCursor cur = dbCollection.find();
		while(cur.hasNext()){
			print(cur.next());
//			print("JSON:"+JSON.serialize(cur));
		}
	}
	
	@Test
	public void add(){
		queryAll();
		print("count:"+dbCollection.count());
		DBObject user = new BasicDBObject();
		user.put("name", "hoojo");
		user.put("sex", "男");
		user.put("age", 25);
		print("save:"+dbCollection.save(user).getN());
		
		print("insert:"+dbCollection.insert(user, new BasicDBObject("name", "tom")).getN());
		
		List<DBObject> list = new ArrayList<DBObject>();
		list.add(user);
		DBObject user2 = new BasicDBObject("name", "lucy");
		user.put("age", 22);
		list.add(user2);
		print("insert.list:"+dbCollection.insert(list).getN());
		print("count:"+dbCollection.count());
		queryAll();
	}
	
	@Test
	public void remove(){
		queryAll();
		print("删除id = ："+dbCollection.remove(new BasicDBObject("_id", new ObjectId("50b7433999aa83f478bbc167"))).getN());
		print("删除age >=24 : "+dbCollection.remove(new BasicDBObject("age", new BasicDBObject("$gte", 24))).getN());
		queryAll();
	}
	
	@Test
	public void update(){
//		print("修改："+dbCollection.update(new BasicDBObject("_id", new ObjectId("50b744a199aae3dc111c4208")), new BasicDBObject("age", 99)).getN());
		print("修改："+dbCollection.update(new BasicDBObject("_id", new ObjectId("50b744a199aae3dc111c4208")), new BasicDBObject("age", 90), true, false).getN());
		query();
	}
	
	@Test
	public void query(){
		print("find id = 50b744a199aae3dc111c4208 : "+dbCollection.find(new BasicDBObject("_id", new ObjectId("50b744a199aae3dc111c4208"))).toArray());
		dbCollection.setObjectClass(MyUser.class);
		MyUser param = new MyUser();
		param.put("_id", "50b744a199aae3dc111c4208");
		MyUser myUser = (MyUser)dbCollection.findOne(param);
		System.out.println("name:===="+myUser.getAge());
	}

	public Mongo getMg() {
		return mg;
	}

	public void setMg(Mongo mg) {
		this.mg = mg;
	}

	public DB getDb() {
		return db;
	}

	public void setDb(DB db) {
		this.db = db;
	}

	public DBCollection getDbCollection() {
		return dbCollection;
	}

	public void setDbCollection(DBCollection dbCollection) {
		this.dbCollection = dbCollection;
	}

	public static void main(String[] args) {
		try {
			Mongo mg = new Mongo();
			for(String name : mg.getDatabaseNames()){
				System.out.println("dbName:" + name);
			}
			DB db = mg.getDB("test");
			for(String name : db.getCollectionNames()){
				System.out.println("collectionName:"+name);
			}
			DBCollection users = db.getCollection("users");
			DBCursor cur = users.find();
			while(cur.hasNext()){
				System.out.println(cur.next());
			}
			System.out.println(cur.count());
			System.out.println(cur.size());
			System.out.println(JSON.serialize(cur));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
}
