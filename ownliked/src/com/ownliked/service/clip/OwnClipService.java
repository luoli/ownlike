package com.ownliked.service.clip;

import java.util.List;

import com.ownliked.pojo.OwnClip;

/**
 * 夹子管理Service层接口
 * @author jianglijia
 *
 */
public interface OwnClipService {
	
	List<OwnClip> queryClip4ByBoard(OwnClip ownClip);
	
	/**
	 * ownClip表中更新用户信息
	 * @param ownClip
	 * @return
	 */
	int updateOwnClipUser(OwnClip ownClip);
	
	public List<OwnClip> queryOwnClipByUserAndLiked(OwnClip ownClip);
	
	public List<OwnClip> queryOwnClipByUser(OwnClip ownClip);

	/**
	 * 条件获取多个夹子数据
	 * @param ownClip
	 * @return
	 */
	public List<OwnClip> queryOwnClip(OwnClip ownClip);
	
	/**
	 * 获取夹子数据及相关评论
	 * @param ownClip
	 * @return
	 */
	public List<OwnClip> queryOwnClipByComment(OwnClip ownClip);
	
	/**
	 * 条件获取单个夹子板数据
	 * @param ownClip
	 * @return
	 */
	public OwnClip findOwnClip(OwnClip ownClip);
	
	/**
	 * 条件获取夹子数量
	 * @param ownClip
	 * @return
	 */
	public int countOwnClip(OwnClip ownClip);
	
	/**
	 * 更新夹子信息
	 * @param ownClip
	 * @return
	 */
	public int updateOwnClip(OwnClip ownClip);
	
	/**
	 * 添加夹子信息
	 * @param ownClip
	 * @return
	 */
	public int insertOwnClip(OwnClip ownClip);
	
	/**
	 * 删除夹子信息
	 * @param ownClip
	 * @return
	 */
	public int deleteOwnClip(OwnClip ownClip);
	
	public int likeOwnClip(OwnClip ownClip, int userId);
	
}
