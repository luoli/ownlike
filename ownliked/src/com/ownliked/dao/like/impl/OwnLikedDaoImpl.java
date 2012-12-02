package com.ownliked.dao.like.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ownliked.dao.like.OwnLikedDao;
import com.ownliked.dao.support.BaseDaoImpl;
import com.ownliked.pojo.OwnLiked;

@Repository(value="ownLikedDao")
public class OwnLikedDaoImpl extends BaseDaoImpl<OwnLiked, Object> implements OwnLikedDao {

	@Override
	public int countOwnLiked(OwnLiked ownLiked) {
		return this.countEntity("ownLikedSqlMap.countOwnLiked", ownLiked);
	}

	@Override
	public int deleteOwnLiked(OwnLiked ownLiked) {
		return this.deleteEntity("ownLikedSqlMap.deleteOwnLiked", ownLiked);
	}

	@Override
	public OwnLiked findOwnLiked(OwnLiked ownLiked) {
		return this.findEntity("ownLikedSqlMap.findOwnLiked", ownLiked);
	}

	@Override
	public int insertOwnLiked(OwnLiked ownLiked) {
		return this.insertEntity("ownLikedSqlMap.insertOwnLiked", ownLiked);
	}

	@Override
	public List<OwnLiked> queryOwnLiked(OwnLiked ownLiked) {
		return this.queryEntity("ownLikedSqlMap.queryOwnLiked", ownLiked);
	}

	@Override
	public int updateOwnLiked(OwnLiked ownLiked) {
		return this.updateEntity("ownLikedSqlMap.updateOwnLiked", ownLiked);
	}

}
