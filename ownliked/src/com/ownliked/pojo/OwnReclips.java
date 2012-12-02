package com.ownliked.pojo;

import java.util.Date;

/**
 * 7.	转夹表(own_reclips)
 * @author jianglijia
 *
 */
public class OwnReclips extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1022967368233633442L;
	
	private int id;					//主键ID
	private int clipId;				//原所属clip
	private int userId;				//原所属用户
	private String viaUrl;			//原所属地址
	private String reclipDescription;//原所属地址描述
	private int type;				//被转类型
	private Date createTime;		//创建时间
	private int reClipId;			//转夹clip
	private int reUserId;			//转夹用户Id
	private String reUserName;		//转夹用户名称
	
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
	public String getViaUrl() {
		return viaUrl;
	}
	public void setViaUrl(String viaUrl) {
		this.viaUrl = viaUrl;
	}
	public String getReclipDescription() {
		return reclipDescription;
	}
	public void setReclipDescription(String reclipDescription) {
		this.reclipDescription = reclipDescription;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public int getReUserId() {
		return reUserId;
	}
	public void setReUserId(int reUserId) {
		this.reUserId = reUserId;
	}
	public String getReUserName() {
		return reUserName;
	}
	public void setReUserName(String reUserName) {
		this.reUserName = reUserName;
	}
	public int getReClipId() {
		return reClipId;
	}
	public void setReClipId(int reClipId) {
		this.reClipId = reClipId;
	}

}
