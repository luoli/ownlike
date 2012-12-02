package com.ownliked.service.comment.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ownliked.dao.acitvityHistory.dao.OwnActivityHistoryDao;
import com.ownliked.dao.clip.OwnClipDao;
import com.ownliked.dao.comment.OwnCommentDao;
import com.ownliked.pojo.OwnActivityHistory;
import com.ownliked.pojo.OwnClip;
import com.ownliked.pojo.OwnComment;
import com.ownliked.service.comment.OwnCommentService;

@Transactional(readOnly=true)
@Service("ownCommentService")
public class OwnCommentServiceImpl implements OwnCommentService {
	
	@Resource(name="ownCommentDao")
	private OwnCommentDao ownCommentDao;
	
	@Resource(name="ownClipDao")
	private OwnClipDao ownClipDao;
	
	@Resource(name="ownActivityHistoryDao")
	private OwnActivityHistoryDao ownActivityHistoryDao;

	public OwnActivityHistoryDao getOwnActivityHistoryDao() {
		return ownActivityHistoryDao;
	}

	public void setOwnActivityHistoryDao(OwnActivityHistoryDao ownActivityHistoryDao) {
		this.ownActivityHistoryDao = ownActivityHistoryDao;
	}

	public OwnClipDao getOwnClipDao() {
		return ownClipDao;
	}

	public void setOwnClipDao(OwnClipDao ownClipDao) {
		this.ownClipDao = ownClipDao;
	}

	public OwnCommentDao getOwnCommentDao() {
		return ownCommentDao;
	}

	public void setOwnCommentDao(OwnCommentDao ownCommentDao) {
		this.ownCommentDao = ownCommentDao;
	}

	@Override
	public int countOwnComment(OwnComment ownComment) {
		return ownCommentDao.countOwnComment(ownComment);
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public int deleteOwnComment(OwnComment ownComment) {
		return ownCommentDao.deleteOwnComment(ownComment);
	}

	@Override
	public OwnComment findOwnComment(OwnComment ownComment) {
		return ownCommentDao.findOwnComment(ownComment);
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public int insertOwnComment(OwnComment ownComment) {
		OwnClip ownClip = new OwnClip();
		ownClip.setId(ownComment.getClipId());
		ownClip.setCommentNum(1);
		int result = this.getOwnClipDao().updateOwnClip(ownClip);
		if(result > 0){
			result = ownCommentDao.insertOwnComment(ownComment);
			OwnActivityHistory ownActivityHistory = new OwnActivityHistory();	//添加历史记录
			ownActivityHistory.setaUserId(ownComment.getUserId());
			ownActivityHistory.setbUserId(ownComment.getByUserId());
			ownActivityHistory.setClipId(ownComment.getClipId());
			ownActivityHistory.setFlag(4);
			result = ownActivityHistoryDao.insertOwnActivityHistory(ownActivityHistory);
		}
		return result;
	}

	@Override
	public List<OwnComment> queryOwnComment(OwnComment ownComment) {
		return ownCommentDao.queryOwnComment(ownComment);
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public int updateOwnComment(OwnComment ownComment) {
		return ownCommentDao.updateOwnComment(ownComment);
	}

}
