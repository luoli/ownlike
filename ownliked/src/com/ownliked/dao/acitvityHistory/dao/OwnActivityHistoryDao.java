package com.ownliked.dao.acitvityHistory.dao;

import java.util.List;

import com.ownliked.dao.support.BaseDao;
import com.ownliked.pojo.OwnActivityHistory;

public interface OwnActivityHistoryDao extends BaseDao {

	/**
	 * 添加记录信息
	 * @param ownActivityHistory
	 * @return
	 */
	public int insertOwnActivityHistory(OwnActivityHistory ownActivityHistory);
	
	/**
	 * 删除记录信息
	 * @param ownActivityHistory
	 * @return
	 */
	public int deleteOwnActivityHistory(OwnActivityHistory ownActivityHistory);
	
	/**
	 * 获得用户参与过的clip操作(转夹或上传)
	 * @param ownActivityHistory
	 * @return
	 */
	public List<OwnActivityHistory> queryActiveAndClip(OwnActivityHistory ownActivityHistory);
	
}
