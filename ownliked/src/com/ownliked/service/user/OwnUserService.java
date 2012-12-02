package com.ownliked.service.user;

import java.util.List;

import com.ownliked.pojo.OwnUser;

/**
 * 用户管理服务层接口
 * @author jianglijia
 *
 */
public interface OwnUserService {

	/**
	 * 根据条件查询用户集合
	 * @param ownUser
	 * @return
	 */
	public List<OwnUser> queryOwnUser(OwnUser ownUser);
	
	/**
	 * 根据条件查询单个用户
	 * @param ownUser
	 * @return
	 */
	public OwnUser findOwnUser(OwnUser ownUser);
	
	/**
	 * 添加用户
	 * @param ownUser
	 * @return
	 */
	public int insertOwnUser(OwnUser ownUser);
	
	/**
	 * 更新用户
	 * @param ownUser
	 * @return
	 */
	public int updateOwnUser(OwnUser ownUser);
	
	/**
	 * 删除用户
	 * @param ownUser
	 * @return
	 */
	public int deleteOwnUser(OwnUser ownUser);
	
	/**
	 * 根据条件获得用户数量
	 * @param ownUser
	 * @return
	 */
	public int countOwnUser(OwnUser ownUser);
	
}
