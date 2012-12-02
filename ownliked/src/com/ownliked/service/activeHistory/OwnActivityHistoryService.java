package com.ownliked.service.activeHistory;

import java.util.List;

import com.ownliked.pojo.OwnActivityHistory;

public interface OwnActivityHistoryService {

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
