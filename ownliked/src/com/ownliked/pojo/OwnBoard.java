package com.ownliked.pojo;

import java.util.Date;
import java.util.List;

import javax.sound.sampled.Clip;

/**
 * clip板块表(own_borad)
 * @author jianglijia
 *
 */
public class OwnBoard extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2325309299895844425L;
	private int id;				//主键ID
	private int parentId;		//board大类型id
	private int userId;			//用户ID
	private String boardName;	//类型名称
	private int flag;			//标记：系统与用户自定义	0：为系统 1：为用户自定义
	private int xPixel;			//封面X位置
	private int yPixel;			//封面Y位置
	private int ordering;		//排序
	private Date createTime;	//创建时间
	private int deleted;		//逻辑删除	0：未删除 1：已删除
	private int clipNum;		//Clip数量
	private int isFollow;		//是否被关注
	
//	private List<OwnUserFollow> followList;//关注类表
	
	//-------------/
	private int a_userId;		//关注用户
	private int boardId;		//板块ID
	
	private List<Clip>clipList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getxPixel() {
		return xPixel;
	}
	public void setxPixel(int xPixel) {
		this.xPixel = xPixel;
	}
	public int getyPixel() {
		return yPixel;
	}
	public void setyPixel(int yPixel) {
		this.yPixel = yPixel;
	}
	public int getOrdering() {
		return ordering;
	}
	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getClipNum() {
		return clipNum;
	}
	public void setClipNum(int clipNum) {
		this.clipNum = clipNum;
	}
	public List<Clip> getClipList() {
		return clipList;
	}
	public void setClipList(List<Clip> clipList) {
		this.clipList = clipList;
	}
	public int getIsFollow() {
		return isFollow;
	}
	public void setIsFollow(int isFollow) {
		this.isFollow = isFollow;
	}
	public int getA_userId() {
		return a_userId;
	}
	public void setA_userId(int aUserId) {
		a_userId = aUserId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

}
