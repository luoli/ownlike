package com.ownliked.pojo;

import java.util.Date;
import java.util.List;

/**
 * clip表(own_clip)
 * @author jianglijia
 *
 */
public class OwnClip extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2536638891288237875L;
	private int id;				//主键ID
	private int userId;			//所属用户
	private String description;	//描述
	private int boardId;		//所属板块
	private String image;		//图片
	private String link;		//图片连接
	private int cover;			//是否为板块封面	0：不是封面 1：是封面
	private int commentNum;		//评论数
	private int likeNum;		//喜欢数
	private int reclipNum;		//转夹数
	private Date createTime;	//创建时间
	private int deleted;		//逻辑删除	0：未删除 1：已删除
	
	private String viaUrl;		//来源地址
	private String reclipDescription;//来源地址描述
	private String boardName;	//所属板块名称
	private String userName;	//所属用户名称
	private String userImage;	//所属用户头像
	private long uuid;			//资源标示符
	private int clipType;		//资源来源 1：本站 2：外站
	private int previousId;		//上一级clip ID
	
	private List<OwnComment> ownCommentList;
	private List<OwnLiked> ownLikeds;	//用户like数据
	
	//----------------
	private String previousUserName;	//上一级用户名称
	private int previousUserId;			//上一级用户id
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getCover() {
		return cover;
	}
	public void setCover(int cover) {
		this.cover = cover;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}
	public int getReclipNum() {
		return reclipNum;
	}
	public void setReclipNum(int reclipNum) {
		this.reclipNum = reclipNum;
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
	public List<OwnComment> getOwnCommentList() {
		return ownCommentList;
	}
	public void setOwnCommentList(List<OwnComment> ownCommentList) {
		this.ownCommentList = ownCommentList;
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
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public long getUuid() {
		return uuid;
	}
	public void setUuid(long uuid) {
		this.uuid = uuid;
	}
	public int getClipType() {
		return clipType;
	}
	public void setClipType(int clipType) {
		this.clipType = clipType;
	}
	public int getPreviousId() {
		return previousId;
	}
	public void setPreviousId(int previousId) {
		this.previousId = previousId;
	}
	public List<OwnLiked> getOwnLikeds() {
		return ownLikeds;
	}
	public void setOwnLikeds(List<OwnLiked> ownLikeds) {
		this.ownLikeds = ownLikeds;
	}
	public String getPreviousUserName() {
		return previousUserName;
	}
	public void setPreviousUserName(String previousUserName) {
		this.previousUserName = previousUserName;
	}
	public int getPreviousUserId() {
		return previousUserId;
	}
	public void setPreviousUserId(int previousUserId) {
		this.previousUserId = previousUserId;
	}

}
