package com.ownliked.service.board;

import java.util.List;

import com.ownliked.pojo.OwnBoard;

/**
 * 夹子板块管理Service层接口
 * @author jianglijia
 *
 */
public interface OwnBoardService {
	
	public List<OwnBoard> queryOwnUserBoardBy4Clip(OwnBoard ownBoard);

	/**
	 * 条件获取多个夹子板块数据
	 * @param ownBoard
	 * @return
	 */
	public List<OwnBoard> queryOwnUserBoard(OwnBoard ownBoard);
	
	/**
	 * 条件获取单个夹子板数据
	 * @param ownBoard
	 * @return
	 */
	public OwnBoard findOwnBoard(OwnBoard ownBoard);
	
	/**
	 * 条件获取夹子板块数量
	 * @param ownBoard
	 * @return
	 */
	public int countOwnBoard(OwnBoard ownBoard);
	
	/**
	 * 更新夹子板块信息
	 * @param ownBoard
	 * @return
	 */
	public int updateOwnBoard(OwnBoard ownBoard);
	
	/**
	 * 添加夹子板块信息
	 * @param ownBoard
	 * @return
	 */
	public int insertOwnBoard(OwnBoard ownBoard);
	
	/**
	 * 删除夹子板块信息
	 * @param ownBoard
	 * @return
	 */
	public int deleteOwnBoard(OwnBoard ownBoard);
	
}
