package com.ownliked.service.clip.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ownliked.dao.acitvityHistory.dao.OwnActivityHistoryDao;
import com.ownliked.dao.clip.OwnClipDao;
import com.ownliked.dao.user.OwnUserDao;
import com.ownliked.pojo.OwnActivityHistory;
import com.ownliked.pojo.OwnClip;
import com.ownliked.pojo.OwnLiked;
import com.ownliked.pojo.OwnUser;
import com.ownliked.service.clip.OwnClipService;
import com.ownliked.service.like.OwnLikedService;

@Transactional(readOnly=true)
@Service("ownClipService")
public class OwnClipServiceImpl implements OwnClipService {

	@Resource(name="ownClipDao")
	private OwnClipDao ownClipDao;
	@Resource(name="ownLikedService")
	private OwnLikedService ownLikedService;
	@Resource(name="ownActivityHistoryDao")
	private OwnActivityHistoryDao ownActivityHistoryDao;
	@Resource(name="ownUserDao")
	private OwnUserDao ownUserDao;
	
	public OwnUserDao getOwnUserDao() {
		return ownUserDao;
	}

	public void setOwnUserDao(OwnUserDao ownUserDao) {
		this.ownUserDao = ownUserDao;
	}

	public OwnActivityHistoryDao getOwnActivityHistoryDao() {
		return ownActivityHistoryDao;
	}

	public void setOwnActivityHistoryDao(OwnActivityHistoryDao ownActivityHistoryDao) {
		this.ownActivityHistoryDao = ownActivityHistoryDao;
	}

	public OwnLikedService getOwnLikedService() {
		return ownLikedService;
	}

	public void setOwnLikedService(OwnLikedService ownLikedService) {
		this.ownLikedService = ownLikedService;
	}

	public OwnClipDao getOwnClipDao() {
		return ownClipDao;
	}

	public void setOwnClipDao(OwnClipDao ownClipDao) {
		this.ownClipDao = ownClipDao;
	}

	@Override
	public int countOwnClip(OwnClip ownClip) {
		return this.getOwnClipDao().countOwnClip(ownClip);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int deleteOwnClip(OwnClip ownClip) {
		return this.getOwnClipDao().deleteOwnClip(ownClip);
	}

	@Override
	public OwnClip findOwnClip(OwnClip ownClip) {
		return this.getOwnClipDao().findOwnClip(ownClip);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int insertOwnClip(OwnClip ownClip) {
		int result = this.getOwnClipDao().insertOwnClip(ownClip);
		if(result > 0){
			OwnClip ocParam = new OwnClip();
			OwnActivityHistory ownActivityHistory = new OwnActivityHistory();
			if(ownClip.getPreviousId() != 0){
				ocParam.setId(ownClip.getPreviousId());
				ocParam.setReclipNum(1);
				result = this.getOwnClipDao().updateOwnClip(ocParam);
				ownActivityHistory.setbUserId(ownClip.getPreviousId());
				ownActivityHistory.setFlag(1);
			}else{
				ownActivityHistory.setFlag(3);
			}
			ownActivityHistory.setaUserId(ownClip.getUserId());
			ownActivityHistory.setClipId(ownClip.getId());
			result = ownActivityHistoryDao.insertOwnActivityHistory(ownActivityHistory);
			OwnUser ownUser = new OwnUser();
			ownUser.setId(ownClip.getUserId());
			ownUser.setClipNum(1);
			ownUserDao.updateOwnUser(ownUser);
		}
		return result;
	}

	@Override
	public List<OwnClip> queryOwnClip(OwnClip ownClip) {
		return this.getOwnClipDao().queryOwnClip(ownClip);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int updateOwnClip(OwnClip ownClip) {
		return this.getOwnClipDao().updateOwnClip(ownClip);
	}

	@Override
	public List<OwnClip> queryOwnClipByComment(OwnClip ownClip) {
		return this.getOwnClipDao().queryOwnClipByComment(ownClip);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int likeOwnClip(OwnClip ownClip, int userId) {
		OwnLiked ownLiked = new OwnLiked();
		ownLiked.setClipId(ownClip.getId());
		ownLiked.setUserId(userId);
		ownLiked.setByUserId(ownClip.getUserId());
		OwnActivityHistory ownActivityHistory = new OwnActivityHistory();	//添加活动记录
		ownActivityHistory.setaUserId(userId);
		ownActivityHistory.setbUserId(ownClip.getUserId());
		ownActivityHistory.setClipId(ownClip.getId());
		ownActivityHistory.setFlag(2);
		OwnUser ownUser = new OwnUser();
		ownUser.setId(userId);
		ownUser.setLikeNum(ownClip.getLikeNum());
		if(ownClip.getLikeNum() == 1){
			ownLikedService.insertOwnLiked(ownLiked);
			ownActivityHistoryDao.insertOwnActivityHistory(ownActivityHistory);
			ownUserDao.updateOwnUser(ownUser);
		}else if(ownClip.getLikeNum() == -1){
			ownLikedService.deleteOwnLiked(ownLiked);
			ownActivityHistoryDao.deleteOwnActivityHistory(ownActivityHistory);
			ownUserDao.updateOwnUser(ownUser);
		}
		return this.getOwnClipDao().updateOwnClip(ownClip);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int updateOwnClipUser(OwnClip ownClip) {
		return ownClipDao.updateOwnClipUser(ownClip);
	}

	@Override
	public List<OwnClip> queryClip4ByBoard(OwnClip ownClip) {
		return this.ownClipDao.queryClip4ByBoard(ownClip);
	}

	@Override
	public List<OwnClip> queryOwnClipByUser(OwnClip ownClip) {
		return this.ownClipDao.queryOwnClipByUser(ownClip);
	}

	@Override
	public List<OwnClip> queryOwnClipByUserAndLiked(OwnClip ownClip) {
		return this.ownClipDao.queryOwnClipByUserAndLiked(ownClip);
	}

}
