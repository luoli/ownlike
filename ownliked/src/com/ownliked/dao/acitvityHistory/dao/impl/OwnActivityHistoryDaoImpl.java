package com.ownliked.dao.acitvityHistory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ownliked.dao.acitvityHistory.dao.OwnActivityHistoryDao;
import com.ownliked.dao.support.BaseDaoImpl;
import com.ownliked.pojo.OwnActivityHistory;

@Repository("ownActivityHistoryDao")
public class OwnActivityHistoryDaoImpl extends BaseDaoImpl<OwnActivityHistory, Object> implements OwnActivityHistoryDao {

	@Override
	public int insertOwnActivityHistory(OwnActivityHistory ownActivityHistory) {
		return this.getSqlSessiontemplate().insert("ownActivityHistorySqlMap.insertOwnActivityHistory", ownActivityHistory);
	}

	@Override
	public int deleteOwnActivityHistory(OwnActivityHistory ownActivityHistory) {
		return this.getSqlSessiontemplate().delete("ownActivityHistorySqlMap.deleteOwnActivityHistory", ownActivityHistory);
	}

	@Override
	public List<OwnActivityHistory> queryActiveAndClip(
			OwnActivityHistory ownActivityHistory) {
		return this.getSqlSessiontemplate().selectList("ownActivityHistorySqlMap.queryActiveAndClip", ownActivityHistory);
	}

}
