package com.ownliked.service.user;

import java.util.List;

import com.ownliked.pojo.OwnUserOther;

/**
 * 用户管理服务层接口
 * @author jianglijia
 *
 */
public interface OwnUserOtherService {

	/**
	 * 根据条件查询用户集合
	 * @param ownUserOther
	 * @return
	 */
	public List<OwnUserOther> queryOwnUserOther(OwnUserOther ownUserOther);
	
	/**
	 * 根据条件查询单个用户
	 * @param ownUserOther
	 * @return
	 */
	public OwnUserOther findOwnUserOtherAndOwnUser(OwnUserOther ownUserOther);
	
	/**
	 * 添加用户
	 * @param ownUserOther
	 * @return
	 */
	public int insertOwnUserOther(OwnUserOther ownUserOther);
	
	/**
	 * 更新用户
	 * @param ownUserOther
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
