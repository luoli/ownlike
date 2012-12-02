package com.ownliked.dao.userFollow.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ownliked.dao.support.BaseDaoImpl;
import com.ownliked.dao.userFollow.OwnUserFollowDao;
import com.ownliked.pojo.OwnUserFollow;

@Repository("ownUserFollowDao")
public class OwnUserFollowDaoImpl extends BaseDaoImpl<OwnUserFollow, Object> implements
		OwnUserFollowDao {

	@Override
	public int countOwnUserFollow(OwnUserFollow ownUserFollow) {
		return this.countEntity("ownUserFollowSqlMap.countOwnUserFollow", ownUserFollow);
	}

	@Override
	public int deleteOwnUserFollow(OwnUserFollow ownUserFollow) {
		return this.deleteEntity("ownUserFollowSqlMap.deleteOwnUserFollow", ownUserFollow);
	}

	@Override
	public OwnUserFollow findOwnUserFollow(OwnUserFollow ownUserFollow) {
		return this.findEntity("ownUserFollowSqlMap.findOwnUserFollow", ownUserFollow);
	}

	@Override
	public int insertOwnUserFollow(OwnUserFollow ownUserFollow) {
		return this.insertEntity("ownUserFollowSqlMap.insertOwnUserFollow", ownUserFollow);
	}

	@Override
	public List<OwnUserFollow> queryOwnUserFollow(OwnUserFollow ownUserFollow) {
		return this.queryEntity("ownUserFollowSqlMap.queryOwnUserFollow", ownUserFollow);
	}

	@Override
	public int updateOwnUserFollow(OwnUserFollow ownUserFollow) {
		return this.updateEntity("ownUserFollowSqlMap.updateOwnUserFollow", ownUserFollow);
	}

}
