package com.ownliked.pojo;

import java.util.Date;

/**
 * clip评论表(own_comment)
 * @author jianglijia
 *
 */
public class OwnComment extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4370234766839451608L;
	private int id;				//主键ID
	private int clipId;			//clipID
	private int userId;			//评论用户id
	private int byUserId;		//被评论用户id
	private String commentText;	//评论内容
	private Date createTime;	//评论时间
	private int deleted;		//逻辑删除	0：未删除 1：已删除
	
	private OwnUser ownUser;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClipId() {
		return clipId;
	}
	public void setClipId(int clipId) {
		this.clipId = clipId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public OwnUser getOwnUser() {
		return ownUser;
	}
	public void setOwnUser(OwnUser ownUser) {
		this.ownUser = ownUser;
	}
	public int getByUserId() {
		return byUserId;
	}
	public void setByUserId(int byUserId) {
		this.byUserId = byUserId;
	}

}
