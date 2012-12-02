package com.ownliked.service.userFollow.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ownliked.dao.acitvityHistory.dao.OwnActivityHistoryDao;
import com.ownliked.dao.board.OwnBoardDao;
import com.ownliked.dao.user.OwnUserDao;
import com.ownliked.dao.userFollow.OwnUserFollowDao;
import com.ownliked.pojo.OwnActivityHistory;
import com.ownliked.pojo.OwnBoard;
import com.ownliked.pojo.OwnUser;
import com.ownliked.pojo.OwnUserFollow;
import com.ownliked.service.userFollow.OwnUserFollowService;

@Transactional(readOnly=true)
@Service("ownUserFollowService")
public class OwnUserFollowServiceImpl implements OwnUserFollowService {

	@Resource(name="ownUserFollowDao")
	private OwnUserFollowDao ownUserFollowDao;
	@Resource(name="ownBoardDao")
	private OwnBoardDao ownBoardDao;
	@Resource(name="ownUserDao")
	private OwnUserDao ownUserDao;
	@Resource(name="ownActivityHistoryDao")
	private OwnActivityHistoryDao ownActivityHistoryDao;
	
	public OwnActivityHistoryDao getOwnActivityHistoryDao() {
		return ownActivityHistoryDao;
	}

	public void setOwnActivityHistoryDao(OwnActivityHistoryDao ownActivityHistoryDao) {
		this.ownActivityHistoryDao = ownActivityHistoryDao;
	}

	public OwnUserDao getOwnUserDao() {
		return ownUserDao;
	}

	public void setOwnUserDao(OwnUserDao ownUserDao) {
		this.ownUserDao = ownUserDao;
	}

	public OwnBoardDao getOwnBoardDao() {
		return ownBoardDao;
	}

	public void setOwnBoardDao(OwnBoardDao ownBoardDao) {
		this.ownBoardDao = ownBoardDao;
	}

	public OwnUserFollowDao getOwnUserFollowDao() {
		return ownUserFollowDao;
	}

	public void setOwnUserFollowDao(OwnUserFollowDao ownUserFollowDao) {
		this.ownUserFollowDao = ownUserFollowDao;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int unFollowUserAllBoard(OwnUserFollow ownUserFollow) {
		OwnBoard ownBoard = new OwnBoard();
		ownBoard.setUserId(ownUserFollow.getbUserId());
		ownBoard.setA_userId(ownUserFollow.getaUserId());
		List<OwnBoard> resultOwnBoards = ownBoardDao.queryOwnUserBoardAndIsFollow(ownBoard);
		int result = 1;
		if(null != resultOwnBoards){	//判断是否已经有过关注，否则减少关注数
			result = ownUserChangeFollow(ownUserFollow, -1);
			OwnActivityHistory ownActivityHistory = new OwnActivityHistory();
			ownActivityHistory.setaUserId(ownUserFollow.getaUserId());
			ownActivityHistory.setbUserId(ownUserFollow.getbUserId());
			ownActivityHistoryDao.deleteOwnActivityHistory(ownActivityHistory);
			for(OwnBoard ob : resultOwnBoards){
				ownUserFollow.setBoardId(ob.getId());
				result = this.ownUserFollowDao.deleteOwnUserFollow(ownUserFollow);
			}
		}
		return result;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int followUserAllBoard(OwnUserFollow ownUserFollow) {
		int result = 1;
		OwnUserFollow ofParam = new OwnUserFollow();
		ofParam.setaUserId(ownUserFollow.getaUserId());
		ofParam.setbUserId(ownUserFollow.getbUserId());
		int count = ownUserFollowDao.countOwnUserFollow(ofParam);
		if(count <= 0){	//判断是否已经有过关注，否则增加关注数
			result = ownUserChangeFollow(ownUserFollow, 1);
			OwnActivityHistory ownActivityHistory = new OwnActivityHistory();
			ownActivityHistory.setaUserId(ownUserFollow.getaUserId());
			ownActivityHistory.setbUserId(ownUserFollow.getbUserId());
			ownActivityHistoryDao.insertOwnActivityHistory(ownActivityHistory);
		}
		
		OwnBoard ownBoard = new OwnBoard();
		ownBoard.setUserId(ownUserFollow.getbUserId());
		ownBoard.setA_userId(ownUserFollow.getaUserId());
		List<OwnBoard> resultOwnBoards = ownBoardDao.queryOwnUserBoardAndNotFollow(ownBoard);
		for(OwnBoard ob : resultOwnBoards){
			ownUserFollow.setBoardId(ob.getId());
			result = this.ownUserFollowDao.insertOwnUserFollow(ownUserFollow);
		}
		return result;
	}

	@Override
	public int countOwnUserFollow(OwnUserFollow ownUserFollow) {
		return this.getOwnUserFollowDao().countOwnUserFollow(ownUserFollow);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int deleteOwnUserFollow(OwnUserFollow ownUserFollow) {
		int result = this.getOwnUserFollowDao().deleteOwnUserFollow(ownUserFollow);
		if(result > 0){
			OwnUserFollow ofParam = new OwnUserFollow();
			ofParam.setaUserId(ownUserFollow.getaUserId());
			ofParam.setbUserId(ownUserFollow.getbUserId());
			int count = ownUserFollowDao.countOwnUserFollow(ofParam);
			if(count <= 0){	//判断是否已经有过关注，否则减少关注数
				result = ownUserChangeFollow(ownUserFollow, -1);
				OwnActivityHistory ownActivityHistory = new OwnActivityHistory();
				ownActivityHistory.setaUserId(ownUserFollow.getaUserId());
				ownActivityHistory.setbUserId(ownUserFollow.getbUserId());
				ownActivityHistoryDao.deleteOwnActivityHistory(ownActivityHistory);
			}
		}
		return result;
	}

	@Override
	public OwnUserFollow findOwnUserFollow(OwnUserFollow ownUserFollow) {
		return this.getOwnUserFollowDao().findOwnUserFollow(ownUserFollow);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int insertOwnUserFollow(OwnUserFollow ownUserFollow) {
		OwnUserFollow ofParam = new OwnUserFollow();
		ofParam.setaUserId(ownUserFollow.getaUserId());
		ofParam.setbUserId(ownUserFollow.getbUserId());
		int count = ownUserFollowDao.countOwnUserFollow(ofParam);
		if(count <= 0){	//判断是否已经有过关注，否则增加关注数
			ownUserChangeFollow(ownUserFollow, 1);
		}
		return this.getOwnUserFollowDao().insertOwnUserFollow(ownUserFollow);
	}

	@Override
	public List<OwnUserFollow> queryOwnUserFollow(OwnUserFollow ownUserFollow) {
		return this.getOwnUserFollowDao().queryOwnUserFollow(ownUserFollow);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int updateOwnUserFollow(OwnUserFollow ownUserFollow) {
		return this.getOwnUserFollowDao().updateOwnUserFollow(ownUserFollow);
	}
	
	/**
	 * 进行用户关注更新内部方法
	 * @param ownUserFollow
	 * @param num
	 * @return
	 */
	private int ownUserChangeFollow(OwnUserFollow ownUserFollow, int num){
		int result = 0;
		OwnUser ouFing = new OwnUser();
		ouFing.setId(ownUserFollow.getaUserId());
		ouFing.setFollowingNum(num);
		result = ownUserDao.updateOwnUser(ouFing);
		OwnUser ouFer = new OwnUser();
		ouFer.setId(ownUserFollow.getbUserId());
		ouFer.setFollowerNum(num);
		result = ownUserDao.updateOwnUser(ouFer);
		return result;
	}

}
