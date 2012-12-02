package com.ownliked.dao.user.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ownliked.dao.support.BaseDaoImpl;
import com.ownliked.dao.user.OwnUserDao;
import com.ownliked.pojo.OwnUser;

@Repository("ownUserDao")
public class OwnUserDaoImpl extends BaseDaoImpl<OwnUser, OwnUser> implements OwnUserDao {

	@Override
	public int countOwnUser(OwnUser ownUser) {
		return this.countEntity("ownUserSqlMap.countOwnUser",ownUser);
	}

	@Override
	public int deleteOwnUser(OwnUser ownUser) {
		return this.deleteEntity("ownUserSqlMap.deleteOwnUser", ownUser);
	}

	@Override
	public OwnUser findOwnUser(OwnUser ownUser) {
		return this.findEntity("ownUserSqlMap.findOwnUser", ownUser);
	}

	@Override
	public int insertOwnUser(OwnUser ownUser) {
		return this.insertEntity("ownUserSqlMap.insertOwnUser", ownUser);
	}

	@Override
	public List<OwnUser> queryOwnUser(OwnUser ownUser) {
		return this.queryEntity("ownUserSqlMap.queryOwnUser", ownUser);
	}

	@Override
	public int updateOwnUser(OwnUser ownUser) {
		return this.updateEntity("ownUserSqlMap.updateOwnUser", ownUser);
	}

}
