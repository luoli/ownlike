package com.ownliked.service.activeHistory.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ownliked.dao.acitvityHistory.dao.OwnActivityHistoryDao;
import com.ownliked.pojo.OwnActivityHistory;
import com.ownliked.service.activeHistory.OwnActivityHistoryService;

@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
@Service("ownActivityHistoryService")
public class OwnActivityHistoryServiceImpl implements OwnActivityHistoryService {

	@Resource(name="ownActivityHistoryDao")
	private OwnActivityHistoryDao ownActivityHistoryDao;

	public OwnActivityHistoryDao getOwnActivityHistoryDao() {
		return ownActivityHistoryDao;
	}

	public void setOwnActivityHistoryDao(OwnActivityHistoryDao ownActivityHistoryDao) {
		this.ownActivityHistoryDao = ownActivityHistoryDao;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int insertOwnActivityHistory(OwnActivityHistory ownActivityHistory) {
		return this.getOwnActivityHistoryDao().insertOwnActivityHistory(ownActivityHistory);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public int deleteOwnActivityHistory(OwnActivityHistory ownActivityHistory) {
		return this.getOwnActivityHistoryDao().deleteOwnActivityHistory(ownActivityHistory);
	}

	@Override
	public List<OwnActivityHistory> queryActiveAndClip(OwnActivityHistory ownActivityHistory) {
		return this.getOwnActivityHistoryDao().queryActiveAndClip(ownActivityHistory);
	}
	
}
