package com.ownliked.dao.like;

import java.util.List;

import com.ownliked.dao.support.BaseDao;
import com.ownliked.pojo.OwnLiked;

public interface OwnLikedDao extends BaseDao {

	public List<OwnLiked> queryOwnLiked(OwnLiked ownLiked);
	
	public OwnLiked findOwnLiked(OwnLiked ownLiked);
	
	public int insertOwnLiked(OwnLiked ownLiked);
	
	public int updateOwnLiked(OwnLiked ownLiked);
	
	public int deleteOwnLiked(OwnLiked ownLiked);
	
	public int countOwnLiked(OwnLiked ownLiked);
	
}
