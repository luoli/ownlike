package com.ownliked.pojo;

import java.util.Date;

/**
 * 用户活动历史记录表(own_activate_history)
 * @author jianglijia
 *
 */
public class OwnActivityHistory extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8782253575755855000L;
	private int id;			//
	private int clipId;		//clipID
	private int aUserId;	//活动用户id
	private int bUserId;	//目标用户id
	private int flag;		//活动类型	1：为转夹 2：为喜欢 3：为上传 4：为评论 5：关注
	private Date createTime;//活动时间
	
	private OwnClip clip;
	
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
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
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
	public OwnClip getClip() {
		return clip;
	}
	public void setClip(OwnClip clip) {
		this.clip = clip;
	}

}
