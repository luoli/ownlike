package com.ownliked.dao.support;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

public abstract class BaseDaoImpl<Entity extends Serializable, Query extends Object> implements BaseDao {

	@Resource
	private SqlSessionTemplate sqlSessiontemplate;

	public SqlSessionTemplate getSqlSessiontemplate() {
		return sqlSessiontemplate;
	}

	public void setSqlSessiontemplate(SqlSessionTemplate sqlSessiontemplate) {
		this.sqlSessiontemplate = sqlSessiontemplate;
	}
	
	protected int insertEntity(String sqlMap, Query query) {
		return this.getSqlSessiontemplate().insert(sqlMap, query);
	}
	
	protected int deleteEntity(String sqlMap, Query query){
		return this.getSqlSessiontemplate().delete(sqlMap, query);
	}
	
	protected int updateEntity(String sqlMap, Query query){
		return this.getSqlSessiontemplate().update(sqlMap, query);
	}
	
	protected int countEntity(String sqlMap, Query query){
		return this.getSqlSessiontemplate().selectOne(sqlMap, query);
	}
	
	protected Entity findEntity(String sqlMap, Query query) {
		return this.getSqlSessiontemplate().selectOne(sqlMap, query);
	}
	
	protected List<Entity> queryEntity(String sqlMap, Query query){
		return this.getSqlSessiontemplate().selectList(sqlMap, query);
	}
	
}
