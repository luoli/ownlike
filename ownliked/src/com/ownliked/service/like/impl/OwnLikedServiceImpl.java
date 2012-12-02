package com.ownliked.service.like.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ownliked.dao.like.OwnLikedDao;
import com.ownliked.pojo.OwnLiked;
import com.ownliked.service.like.OwnLikedService;

@Service(value="ownLikedService")
public class OwnLikedServiceImpl implements OwnLikedService {

	@Resource(name="ownLikedDao")
	private OwnLikedDao ownLikedDao;
	
	public OwnLikedDao getOwnLikedDao() {
		return ownLikedDao;
	}

	public void setOwnLikedDao(OwnLikedDao ownLikedDao) {
		this.ownLikedDao = ownLikedDao;
	}

	@Override
	public int countOwnLiked(OwnLiked ownLiked) {
		return this.getOwnLikedDao().countOwnLiked(ownLiked);
	}

	@Override
	public int deleteOwnLiked(OwnLiked ownLiked) {
		return this.getOwnLikedDao().deleteOwnLiked(ownLiked);
	}

	@Override
	public OwnLiked findOwnLiked(OwnLiked ownLiked) {
		return this.getOwnLikedDao().findOwnLiked(ownLiked);
	}

	@Override
	public int insertOwnLiked(OwnLiked ownLiked) {
		return this.getOwnLikedDao().insertOwnLiked(ownLiked);
	}

	@Override
	public List<OwnLiked> queryOwnLiked(OwnLiked ownLiked) {
		return this.getOwnLikedDao().queryOwnLiked(ownLiked);
	}

	@Override
	public int updateOwnLiked(OwnLiked ownLiked) {
		return this.getOwnLikedDao().updateOwnLiked(ownLiked);
	}

}
