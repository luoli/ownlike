package com.ownliked.controller.oauth;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import weibo4j.Account;
import weibo4j.Users;
import weibo4j.model.User;
import weibo4j.org.json.JSONObject;

import com.ownliked.controller.BaseController;
import com.ownliked.pojo.OwnUser;
import com.ownliked.pojo.OwnUserOther;
import com.ownliked.service.user.OwnUserOtherService;
import com.ownliked.service.user.OwnUserService;
import com.ownliked.util.system.web.NcgUtil;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

@Controller
@RequestMapping(value="/oauth")
public class OauthServlet extends BaseController {
	
	@Resource(name="ownUserOtherService")
	private OwnUserOtherService ownUserOtherService;
	@Resource(name="ownUserService")
	private OwnUserService ownUserService;

	
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
	public String redirect(HttpServletRequest req, HttpServletResponse resp, ModelMap map, String oauthType) throws Exception{
		analyToken("", oauthType, req, resp);
		req.getRequestDispatcher("view/member/oauthSuccess.jsp").forward(req, resp);
		return "redirect:/ownBoard/queryOwnBoardByUser.h";
	}
	
	public void analyToken(String token, String red, HttpServletRequest req, HttpServletResponse resp)throws Exception{
		String accessToken   = token,
				openID        = null,
				tokenExpireIn = null,
				name = null;
		int fromType = 0;
		if(null == red || red.equals("qzone") || red.equals("")){
//				String queryString = req.getQueryString();
//	            String state = (String)req.getSession().getAttribute("qq_connect_state");
//		        AccessToken accessTokenObj = (new Oauth()).getAccessTokenByQueryString(queryString,state);
			if(null == accessToken || accessToken.equals("")){
	            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(req);
		        if (accessTokenObj.getAccessToken().equals("")) {
	              System.out.println("Qzone website login : 没有获取到响应参数");
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
				String code = req.getParameter("code");
				weibo4j.Oauth oauth = new weibo4j.Oauth();
				weibo4j.http.AccessToken accessTokenObj = oauth.getAccessTokenByCode(code);
				if (accessTokenObj.getAccessToken().equals("")) {
	              System.out.println("Weibo website login : 没有获取到响应参数");
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
	        	fromType = 2;
			}
		}
		setValue(accessToken, name, fromType, openID, tokenExpireIn, req, resp);
	}
	
	public void setValue(String accessToken, String name, int fromType, String openId, String tokenExpireIn, HttpServletRequest req, HttpServletResponse resp) throws Exception{
		if(NcgUtil.blankObject(accessToken)){
			OwnUserOther ownUserOther = new OwnUserOther();
			ownUserOther.setAccessToken(accessToken);
			ownUserOther.setFromType(fromType);
			ownUserOther.setOpenId(openId);
			ownUserOther.setTokenExpireIn(tokenExpireIn);
			OwnUserOther result = ownUserOtherService.findOwnUserOtherAndOwnUser(ownUserOther);
			if(null != result){
				req.getSession().setAttribute("OWNUSERLOGIN", result.getOwnUser());
				req.getSession().setAttribute("OWNUSEROTHERLOGIN", result);
			}else{
				OwnUser ownUser = new OwnUser();
				ownUser.setEmail("email");
				ownUser.setLastName(name);
				ownUser.setPassword(accessToken);
				int oId = ownUserService.insertOwnUser(ownUser);
				if(NcgUtil.blankNumber(oId)){
					ownUserOther.setUserId(oId);
				}
				ownUserOtherService.insertOwnUserOther(ownUserOther);
			}
		}
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
}
