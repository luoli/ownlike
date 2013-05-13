package com.ownliked.dao.user;

import java.util.List;

import com.ownliked.pojo.OwnUserOther;

/**
 * 用户管理Dao层接口
 * @author jianglijia
 *
 */
public interface OwnUserOtherDao {

	/**
	 * 根据条件查询用户集合
	 * @param ownUser
	 * @return
	 */
	public List<OwnUserOther> queryOwnUserOther(OwnUserOther ownUser);
	
	/**
	 * 根据条件查询单个用户
	 * @param ownUser
	 * @return
	 */
	public OwnUserOther findOwnUserOtherAndOwnUser(OwnUserOther ownUser);
	
	/**
	 * 添加用户
	 * @param ownUser
	 * @return
	 */
	public int insertOwnUserOther(OwnUserOther ownUser);
	
	/**
	 * 更新用户
	 * @param ownUser
	 * @return
	 */
	public int updateOwnUserOther(OwnUserOther ownUserOther);
	
	/**
	 * 删除用户
	 * @param ownUserOther
	 * @return
	 */
	public int deleteOwnUserOther(OwnUserOther ownUserOther);
	
	/**
	 * 根据条件获得用户数量
	 * @param ownUserOther
	 * @return
	 */
	public int countOwnUserOther(OwnUserOther ownUserOther);
	
}
