package com.ownliked.dao.clip.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ownliked.dao.clip.OwnClipDao;
import com.ownliked.dao.support.BaseDaoImpl;
import com.ownliked.pojo.OwnClip;

@Repository("ownClipDao")
public class OwnClipDaoImpl extends BaseDaoImpl<OwnClip, Object> implements OwnClipDao {

	@Override
	public int updateOwnClipUser(OwnClip ownClip) {
		return this.updateEntity("ownClipSqlMap.updateOwnClipUser", ownClip);
	}

	@Override
	public int countOwnClip(OwnClip ownClip) {
		return this.countEntity("ownClipSqlMap.countOwnClip", ownClip);
	}

	@Override
	public int deleteOwnClip(OwnClip ownClip) {
		return this.deleteEntity("ownClipSqlMap.deleteOwnClip", ownClip);
	}

	@Override
	public OwnClip findOwnClip(OwnClip ownClip) {
		return this.findEntity("ownClipSqlMap.findOwnClip", ownClip);
	}

	@Override
	public int insertOwnClip(OwnClip ownClip) {
		return this.insertEntity("ownClipSqlMap.insertOwnClip", ownClip);
	}

	@Override
	public List<OwnClip> queryOwnClip(OwnClip ownClip) {
		return this.queryEntity("ownClipSqlMap.queryOwnClip", ownClip);
	}

	@Override
	public List<OwnClip> queryOwnClipByComment(OwnClip ownClip) {
		return this.queryEntity("ownClipSqlMap.queryOwnClipByComment", ownClip);
	}
	
	@Override
	public int updateOwnClip(OwnClip ownClip) {
		return this.updateEntity("ownClipSqlMap.updateOwnClip", ownClip);
	}

	@Override
	public List<OwnClip> queryOwnClipByUser(OwnClip ownClip) {
		return this.queryEntity("ownClipSqlMap.queryOwnClipByUser", ownClip);
	}

	@Override
	public List<OwnClip> queryOwnClipByUserAndLiked(OwnClip ownClip) {
		return this.queryEntity("ownClipSqlMap.queryOwnClipByUserAndLiked", ownClip);
	}

	@Override
	public List<OwnClip> queryClip4ByBoard(OwnClip ownClip) {
		return this.queryEntity("ownClipSqlMap.queryClip4ByBoard", ownClip);
	}

}
