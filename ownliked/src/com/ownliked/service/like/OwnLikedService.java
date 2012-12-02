package com.ownliked.service.like;

import java.util.List;

import com.ownliked.pojo.OwnLiked;

public interface OwnLikedService {

	public List<OwnLiked> queryOwnLiked(OwnLiked ownLiked);
	
	public OwnLiked findOwnLiked(OwnLiked ownLiked);
	
	public int insertOwnLiked(OwnLiked ownLiked);
	
	public int updateOwnLiked(OwnLiked ownLiked);
	
	public int deleteOwnLiked(OwnLiked ownLiked);
	
	public int countOwnLiked(OwnLiked ownLiked);
	
}
