package com.ownliked.controller.oauth;

import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import weibo4j.Account;
import weibo4j.Users;
import weibo4j.model.User;
import weibo4j.org.json.JSONObject;

import com.ownliked.controller.BaseController;
import com.ownliked.pojo.OwnClip;
import com.ownliked.pojo.OwnUser;
import com.ownliked.pojo.OwnUserFollow;
import com.ownliked.pojo.OwnUserOther;
import com.ownliked.service.clip.OwnClipService;
import com.ownliked.service.user.OwnUserOtherService;
import com.ownliked.service.user.OwnUserService;
import com.ownliked.service.userFollow.OwnUserFollowService;
import com.ownliked.util.system.web.NcgUtil;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

@Controller
@RequestMapping(value="/oauth")
public class OauthServlet extends BaseController {

	private static Logger log = Logger.getLogger(OauthServlet.class);
	
	@Resource(name="ownUserOtherService")
	private OwnUserOtherService ownUserOtherService;
	@Resource(name="ownUserService")
	private OwnUserService ownUserService;
	@Resource(name="ownClipService")
	private OwnClipService ownClipService;
	@Resource(name="ownUserFollowService")
	private OwnUserFollowService ownUserFollowService;
	
	@RequestMapping(value="/oauth.h")
	public String oauth(HttpServletRequest req, ModelMap map, String oauthType) throws Exception {
    	if(null != oauthType){
    		if(oauthType.equals("qzone")){
    			return "redirect:"+new Oauth().getAuthorizeURL(req);
    		}else if(oauthType.equals("weibo")){
    			return "redirect:"+new weibo4j.Oauth().authorize("code","3689717415");
    		}
    	}
    	return null;
	}

	@RequestMapping(value="/redirect.h")
	public String redirect(HttpServletRequest req, HttpServletResponse resp, ModelMap map, String oauthType, String code) throws Exception{
		analyToken("", oauthType, req, resp, code);
		return "/user/oauthSuccess";
	}
	
	public void analyToken(String token, String red, HttpServletRequest req, HttpServletResponse resp, String code)throws Exception{
		try {
			String accessToken   = token,
					openID        = null,
					tokenExpireIn = null,
					name = null,
					himg = null,
					simg = null;;
			int fromType = 0;
			if(null == red || red.equals("qzone") || red.equals("")){
				if(null == accessToken || accessToken.equals("")){
			        AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(req);
			        if (accessTokenObj.getAccessToken().equals("")) {
			          log.info("Qzone website login : 没有获取到响应参数");
			        } else {
			    		accessToken = accessTokenObj.getAccessToken();
			    		tokenExpireIn = accessTokenObj.getExpireIn()+"";
			    	}
			    }
			    if(null != accessToken && !accessToken.equals("")){
			    	OpenID openIDObj =  new OpenID(accessToken);
			    	openID = openIDObj.getUserOpenID();
			    	UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
			    	UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
			    	name = userInfoBean.getNickname();
			    	fromType = 1;
			    }
			}else if(red.equals("weibo")){
				if(null == accessToken || accessToken.equals("")){
					weibo4j.Oauth oauth = new weibo4j.Oauth();
					weibo4j.http.AccessToken accessTokenObj = oauth.getAccessTokenByCode(code);
					if (accessTokenObj.getAccessToken().equals("")) {
			          log.info("Weibo website login : 没有获取到响应参数");
			        } else {
			        	accessToken = accessTokenObj.getAccessToken();
			        	tokenExpireIn = accessTokenObj.getExpireIn();
			    	}
			    }
				if(null != accessToken && !accessToken.equals("")){
			    	Account account = new Account();
			    	account.setToken(accessToken);
			    	JSONObject uidJob = account.getUid();
			    	openID = uidJob.getString("uid");
			    	Users users = new Users();
			    	users.setToken(accessToken);
			    	User user = users.showUserById(openID);
			    	name = user.getName();
			    	simg = user.getProfileImageUrl();
			    	himg = user.getAvatarLarge();
			    	fromType = 2;
				}
			}
			setValue(accessToken, name, fromType, openID, tokenExpireIn, himg, simg, req, resp);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
	}
	
	public void setValue(String accessToken, String name, int fromType, String openId, String tokenExpireIn, String himg, String simg, HttpServletRequest req, HttpServletResponse resp) throws Exception{
		if(NcgUtil.blankObject(accessToken)){
			OwnUserOther ownUserOther = new OwnUserOther();
			ownUserOther.setAccessToken(accessToken);
			ownUserOther.setFromType(fromType);
			ownUserOther.setFigureurlSm(simg);
			ownUserOther.setFigureurlBig(himg);
			if(fromType == 1){
				ownUserOther.setOpenId(openId);
			}
			ownUserOther.setTokenExpireIn(tokenExpireIn);
			OwnUserOther result = ownUserOtherService.findOwnUserOtherAndOwnUser(ownUserOther);
			if(null != result){
				OwnUser ownUser = result.getOwnUser();
				ownUser.setLastName(name);
				ownUser.setPassword(accessToken);
				ownUser.setImage(himg);
				int rowCount = ownUserService.updateOwnUser(ownUser);
				if(rowCount > 0){
					OwnClip ownClip = new OwnClip();
					ownClip.setUserId(ownUser.getId());
					ownClip.setUserImage(himg);
					ownClip.setUserName(name);
					ownClipService.updateOwnClipUser(ownClip);
					insertUserDefaultFollow(ownUser.getId());
				}
				req.getSession().setAttribute("OWNUSERLOGIN", ownUser);
				req.getSession().setAttribute("OWNUSEROTHERLOGIN", result);
			}else{
				OwnUser ownUser = new OwnUser();
				ownUser.setEmail("email");
				ownUser.setLastName(name);
				ownUser.setPassword(accessToken);
				ownUser.setImage(himg);
				int oId = ownUserService.insertOwnUser(ownUser);
				if(NcgUtil.blankNumber(oId)){
					ownUserOther.setUserId(ownUser.getId());
					req.getSession().setAttribute("OWNUSEROTHERLOGIN", ownUserOther);
				}
				ownUserOtherService.insertOwnUserOther(ownUserOther);
				OwnUser resultOwnUser = this.getOwnUserService().findOwnUser(ownUser);
				if(resultOwnUser != null){
					req.getSession().setAttribute("OWNUSERLOGIN", resultOwnUser);
				}
			}
		}
	}
	
	/**
	 * 添加默认关注
	 * @param aUserId
	 * @return
	 * @throws Exception
	 */
	public int insertUserDefaultFollow(int aUserId)throws Exception{
		int result = 0;
		Properties pro = new Properties();
		pro.load(OauthServlet.class.getClassLoader().getResourceAsStream("user_follow_config.properties"));
		String proUid = pro.getProperty("userId");
		if(NcgUtil.blankObject(proUid)){
			String[] userIds = proUid.split(",");
			for(int i=0; i<userIds.length; i++){
				OwnUserFollow ownUserFollow = new OwnUserFollow();
				ownUserFollow.setaUserId(aUserId);
				try {
					ownUserFollow.setbUserId(Integer.valueOf(userIds[i]));
					ownUserFollowService.followUserAllBoard(ownUserFollow);
				} catch (Exception e) {
					log.error("user default follow fail.", e);
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public OwnUserOtherService getOwnUserOtherService() {
		return ownUserOtherService;
	}

	public void setOwnUserOtherService(OwnUserOtherService ownUserOtherService) {
		this.ownUserOtherService = ownUserOtherService;
	}

	public OwnUserService getOwnUserService() {
		return ownUserService;
	}

	public void setOwnUserService(OwnUserService ownUserService) {
		this.ownUserService = ownUserService;
	}

	public OwnClipService getOwnClipService() {
		return ownClipService;
	}

	public void setOwnClipService(OwnClipService ownClipService) {
		this.ownClipService = ownClipService;
	}

	public OwnUserFollowService getOwnUserFollowService() {
		return ownUserFollowService;
	}

	public void setOwnUserFollowService(OwnUserFollowService ownUserFollowService) {
		this.ownUserFollowService = ownUserFollowService;
	}
}
