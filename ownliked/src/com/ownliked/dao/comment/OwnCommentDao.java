package com.ownliked.dao.comment;

import java.util.List;

import com.ownliked.pojo.OwnComment;

public interface OwnCommentDao {

	/**
	 * 获得评论数量
	 * 
	 * @param ownComment
	 * @return
	 */
	public int countOwnComment(OwnComment ownComment);

	/**
	 * 获得评论集合
	 * 
	 * @param ownComment
	 * @return
	 */
	public List<OwnComment> queryOwnComment(OwnComment ownComment);

	/**
	 * 获得单个评论
	 * 
	 * @param ownComment
	 * @return
	 */
	public OwnComment findOwnComment(OwnComment ownComment);

	/**
	 * 添加评论
	 * 
	 * @param ownComment
	 * @return
	 */
	public int insertOwnComment(OwnComment ownComment);

	/**
	 * 更新评论
	 * 
	 * @param ownComment
	 * @return
	 */
	public int updateOwnComment(OwnComment ownComment);

	/**
	 * 删除评论
	 * 
	 * @param ownComment
	 * @return
	 */
	public int deleteOwnComment(OwnComment ownComment);

}
