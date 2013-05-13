package com.ownliked.dao.user.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ownliked.dao.support.BaseDaoImpl;
import com.ownliked.dao.user.OwnUserOtherDao;
import com.ownliked.pojo.OwnUserOther;

@Repository("ownUserOtherDao")
public class OwnUserOtherDaoImpl extends BaseDaoImpl<OwnUserOther, OwnUserOther> implements OwnUserOtherDao {

	@Override
	public int countOwnUserOther(OwnUserOther ownUserOther) {
		return this.countEntity("ownUserOtherSqlMap.countOwnUserOther", ownUserOther);
	}

	@Override
	public int deleteOwnUserOther(OwnUserOther ownUserOther) {
		return this.deleteEntity("ownUserOtherSqlMap.deleteOwnUserOther", ownUserOther);
	}

	@Override
	public OwnUserOther findOwnUserOtherAndOwnUser(OwnUserOther ownUserOther) {
		return this.findEntity("ownUserOtherSqlMap.findOwnUserOtherAndOwnUser", ownUserOther);
	}

	@Override
	public int insertOwnUserOther(OwnUserOther ownUserOther) {
		return this.insertEntity("ownUserOtherSqlMap.insertOwnUserOther", ownUserOther);
	}

	@Override
	public List<OwnUserOther> queryOwnUserOther(OwnUserOther ownUserOther) {
		return this.queryEntity("ownUserOtherSqlMap.queryOwnUserOther", ownUserOther);
	}

	@Override
	public int updateOwnUserOther(OwnUserOther ownUserOther) {
		return this.updateEntity("ownUserOtherSqlMap.updateOwnUserOther", ownUserOther);
	}

}
