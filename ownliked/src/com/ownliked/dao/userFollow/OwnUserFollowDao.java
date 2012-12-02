package com.ownliked.dao.userFollow;

import java.util.List;

import com.ownliked.pojo.OwnUserFollow;

/**
 * 用户关注管理Dao层接口
 * @author jianglijia
 *
 */
public interface OwnUserFollowDao {
	
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
