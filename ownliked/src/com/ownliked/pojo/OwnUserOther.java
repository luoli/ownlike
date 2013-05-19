package com.ownliked.pojo;


/**
 * 用户表(own_userOther)
 * @author jianglijia
 *
 */
public class OwnUserOther extends BasePojo {

	private static final long serialVersionUID = 2624188638748702560L;
	private int id;				
	private int userId;				
	private String username;	//第三方昵称
	private int fromType;		//第三方来源
	private String figureurlSm;	//小头像
	private String figureurlBig;	//大头像
	private String accessToken;
	private String tokenExpireIn;
	private String openId;
	
	private OwnUser ownUser;
	
	public OwnUser getOwnUser() {
		return ownUser;
	}
	public void setOwnUser(OwnUser ownUser) {
		this.ownUser = ownUser;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTokenExpireIn() {
		return tokenExpireIn;
	}
	public void setTokenExpireIn(String tokenExpireIn) {
		this.tokenExpireIn = tokenExpireIn;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getFromType() {
		return fromType;
	}
	public void setFromType(int fromType) {
		this.fromType = fromType;
	}
	public String getFigureurlSm() {
		return figureurlSm;
	}
	public void setFigureurlSm(String figureurlSm) {
		this.figureurlSm = figureurlSm;
	}
	public String getFigureurlBig() {
		return figureurlBig;
	}
	public void setFigureurlBig(String figureurlBig) {
		this.figureurlBig = figureurlBig;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
