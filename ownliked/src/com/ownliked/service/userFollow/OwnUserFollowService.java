package com.ownliked.service.userFollow;

import java.util.List;

import com.ownliked.pojo.OwnUserFollow;

/**
 * 用户关注管理Service层接口
 * @author jianglijia
 *
 */
public interface OwnUserFollowService {
	
	/**
	 * 根据用户id删除关注用户下全部板块
	 * @param ownUserFollow
	 * @return
	 */
	public int unFollowUserAllBoard(OwnUserFollow ownUserFollow);
	
	/**
	 * 根据用户id添加关注用户下全部板块
	 * @param ownUserFollow
	 * @return
	 */
	public int followUserAllBoard(OwnUserFollow ownUserFollow);
	
	/**
	 * 条件查询多个用户关注数据
	 * @param ownUserFollow
	 * @return
	 */
	public List<OwnUserFollow> queryOwnUserFollow(OwnUserFollow ownUserFollow);
	
	/**
	 * 条件查询单个用户关注数据
	 * @param ownUserFollow
	 * @return
	 */
	public OwnUserFollow findOwnUserFollow(OwnUserFollow ownUserFollow);
	
	/**
	 * 添加用户关注数据
	 * @param ownUserFollow
	 * @return
	 */
	public int insertOwnUserFollow(OwnUserFollow ownUserFollow);
	
	/**
	 * 更新用户关注数据信息
	 * @param ownUserFollow
	 * @return
	 */
	public int updateOwnUserFollow(OwnUserFollow ownUserFollow);
	
	/**
	 * 删除用户关注
	 * @param ownUserFollow
	 * @return
	 */
	public int deleteOwnUserFollow(OwnUserFollow ownUserFollow);
	
	/**
	 * 条件查询用户关注数据数量
	 * @param ownUserFollow
	 * @return
	 */
	public int countOwnUserFollow(OwnUserFollow ownUserFollow);
	
}
