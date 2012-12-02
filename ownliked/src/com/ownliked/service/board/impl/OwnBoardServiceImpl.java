package com.ownliked.service.board.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ownliked.dao.board.OwnBoardDao;
import com.ownliked.dao.user.OwnUserDao;
import com.ownliked.pojo.OwnBoard;
import com.ownliked.pojo.OwnUser;
import com.ownliked.service.board.OwnBoardService;

@Transactional(readOnly=true)
@Service("ownBoardService")
public class OwnBoardServiceImpl implements OwnBoardService {

	@Resource(name="ownBoardDao")
	private OwnBoardDao ownBoardDao;
	@Resource(name="ownUserDao")
	private OwnUserDao ownUserDao;
	
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

	@Override
	public List<OwnBoard> queryOwnUserBoardBy4Clip(OwnBoard ownBoard) {
		return this.getOwnBoardDao().queryOwnUserBoardBy4Clip(ownBoard);
	}

	@Override
	public int countOwnBoard(OwnBoard ownBoard) {
		return this.getOwnBoardDao().countOwnBoard(ownBoard);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int deleteOwnBoard(OwnBoard ownBoard) {
		int result = 0;
		OwnUser ownUser = new OwnUser();
		ownUser.setId(ownBoard.getUserId());
		ownUser.setBoardNum(-1);
		result = ownUserDao.updateOwnUser(ownUser);
		if(result > 0){
			result = this.getOwnBoardDao().deleteOwnBoard(ownBoard);
		}
		return result;
	}

	@Override
	public OwnBoard findOwnBoard(OwnBoard ownBoard) {
		return this.getOwnBoardDao().findOwnBoard(ownBoard);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int insertOwnBoard(OwnBoard ownBoard) {
		int result = 0;
		OwnUser ownUser = new OwnUser();
		ownUser.setId(ownBoard.getUserId());
		ownUser.setBoardNum(1);
		result = ownUserDao.updateOwnUser(ownUser);
		if(result > 0){
			result = this.getOwnBoardDao().insertOwnBoard(ownBoard);
		}
		return result;
	}

	@Override
	public List<OwnBoard> queryOwnUserBoard(OwnBoard ownBoard) {
		return this.getOwnBoardDao().queryOwnUserBoard(ownBoard);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int updateOwnBoard(OwnBoard ownBoard) {
		return this.getOwnBoardDao().updateOwnBoard(ownBoard);
	}

}
