package com.ownliked.pojo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用户表(own_user)
 * @author jianglijia
 *
 */
public class OwnUser extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2624188638748702560L;
	private int id;				//用户ID
	@NotEmpty
	private String email;		//email登陆账号
	@Length(max=20, min=6)
	private String password;	//密码
	@NotEmpty
	@NotNull
	private String firstName;	//姓
	@NotEmpty
	@NotNull
	private String lastName;	//名
	private int gender;			//性别	1：男2：女 3：unspecified 
	private String about;		//用户描述
	private String location;	//位置
	private String webSite;		//主页
	private String image;		//头像
	private int language;		//语言	1：中文2：英文
	private int activate;		//是否激活	1：未激活 2：已激活
	private int hideProfile;	//是否隐藏用户属性	1：不隐藏 2：隐藏
	private int deactivate;		//是否停用账号	1：使用  2：停用
	private Date createTime;	//创建时间
	private int follow;			//是否有关注
	private int boardNum;		//板块数量
	private int clipNum;		//Clip数量
	private int likeNum;		//喜欢数量
	private int followerNum;	//关注我的数量
	private int followingNum;	//我关注的数量
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getLanguage() {
		return language;
	}
	public void setLanguage(int language) {
		this.language = language;
	}
	public int getActivate() {
		return activate;
	}
	public void setActivate(int activate) {
		this.activate = activate;
	}
	public int getHideProfile() {
		return hideProfile;
	}
	public void setHideProfile(int hideProfile) {
		this.hideProfile = hideProfile;
	}
	public int getDeactivate() {
		return deactivate;
	}
	public void setDeactivate(int deactivate) {
		this.deactivate = deactivate;
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
	public int getFollow() {
		return follow;
	}
	public void setFollow(int follow) {
		this.follow = follow;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public int getClipNum() {
		return clipNum;
	}
	public void setClipNum(int clipNum) {
		this.clipNum = clipNum;
	}
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}
	public int getFollowerNum() {
		return followerNum;
	}
	public void setFollowerNum(int followerNum) {
		this.followerNum = followerNum;
	}
	public int getFollowingNum() {
		return followingNum;
	}
	public void setFollowingNum(int followingNum) {
		this.followingNum = followingNum;
	}

}
