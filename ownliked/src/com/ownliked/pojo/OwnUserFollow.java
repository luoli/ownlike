package com.ownliked.pojo;

import java.util.Date;

/**
 * 用户关注表(own_user_follow)
 * @author jianglijia
 *
 */
public class OwnUserFollow extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3675796517267340697L;
	private int id;				//主键ID
	private int aUserId;		//关注用户
	private int bUserId;		//被关注用户
	private int boardId;			//被关注板块
	private Date createTime;	//关注时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getaUserId() {
		return aUserId;
	}
	public void setaUserId(int aUserId) {
		this.aUserId = aUserId;
	}
	public int getbUserId() {
		return bUserId;
	}
	public void setbUserId(int bUserId) {
		this.bUserId = bUserId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
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

}
