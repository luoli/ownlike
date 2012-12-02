package com.ownliked.dao.board.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ownliked.dao.board.OwnBoardDao;
import com.ownliked.dao.support.BaseDaoImpl;
import com.ownliked.pojo.OwnBoard;

@Repository("ownBoardDao")
public class OwnBoardDaoImpl extends BaseDaoImpl<OwnBoard, Object> implements OwnBoardDao {

	@Override
	public List<OwnBoard> queryOwnUserBoardAndIsFollow(OwnBoard ownBoard) {
		return this.queryEntity("ownBoardSqlMap.queryOwnUserBoardAndIsFollow", ownBoard);
	}

	@Override
	public List<OwnBoard> queryOwnUserBoardAndNotFollow(OwnBoard ownBoard) {
		return this.queryEntity("ownBoardSqlMap.queryOwnUserBoardAndNotFollow", ownBoard);
	}

	@Override
	public List<OwnBoard> queryOwnUserBoardBy4Clip(OwnBoard ownBoard) {
		return this.queryEntity("ownBoardSqlMap.queryOwnUserBoardBy4Clip", ownBoard);
	}

	@Override
	public int countOwnBoard(OwnBoard ownBoard) {
		return this.countEntity("ownBoardSqlMap.countOwnBoard", ownBoard);
	}

	@Override
	public int deleteOwnBoard(OwnBoard ownBoard) {
		return this.deleteEntity("ownBoardSqlMap.deleteOwnBoard", ownBoard);
	}

	@Override
	public OwnBoard findOwnBoard(OwnBoard ownBoard) {
		return this.findEntity("ownBoardSqlMap.findOwnBoard", ownBoard);
	}

	@Override
	public int insertOwnBoard(OwnBoard ownBoard) {
		return this.insertEntity("ownBoardSqlMap.insertOwnBoard", ownBoard);
	}

	@Override
	public List<OwnBoard> queryOwnUserBoard(OwnBoard ownBoard) {
		return this.queryEntity("ownBoardSqlMap.queryOwnBoard", ownBoard);
	}

	@Override
	public int updateOwnBoard(OwnBoard ownBoard) {
		return this.updateEntity("ownBoardSqlMap.updateOwnBoard", ownBoard);
	}

}
