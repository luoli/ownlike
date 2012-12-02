package com.ownliked.pojo;

import java.util.Date;

/**
 * 用户like表(own_liked)
 * @author jianglijia
 *
 */
public class OwnLiked extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;			//
	private int clipId;		//clip外键
	private int userId;		//user外键
	private int byUserId;		//被like user外键
	private Date createTime;//创建时间
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getByUserId() {
		return byUserId;
	}
	public void setByUserId(int byUserId) {
		this.byUserId = byUserId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
