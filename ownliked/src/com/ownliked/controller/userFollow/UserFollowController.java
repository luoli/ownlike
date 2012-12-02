package com.ownliked.controller.userFollow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ownliked.controller.BaseController;
import com.ownliked.pojo.OwnUser;
import com.ownliked.pojo.OwnUserFollow;
import com.ownliked.service.userFollow.OwnUserFollowService;
import com.ownliked.util.system.web.JsonStringBuilder;

@Controller
@RequestMapping(value="/userFollow")
public class UserFollowController extends BaseController {

	private Logger log = Logger.getLogger(UserFollowController.class.getName());
	
	@Resource(name="ownUserFollowService")
	private OwnUserFollowService ownUserFollowService;
	
	/**
	 * 用户关注数据显示
	 * @param request
	 * @param ownUserFollow
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String searchMyFollowBoard(HttpServletRequest request, OwnUserFollow ownUserFollow, ModelMap map) throws Exception{
		OwnUser ownUserSession = getSessionUser(request);
		if(null == ownUserSession){
			return "/user/login";
		}
		List<OwnUserFollow> ownUesrFollows = ownUserFollowService.queryOwnUserFollow(ownUserFollow);
		map.put("ownUesrFollows", ownUesrFollows);
		return "/owner/ownerFollowing";
	}
	
	/**
	 * 解除版块关注
	 * @param request
	 * @param response
	 * @param ownUserFollow
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userUnFollowing.h")
	public String userUnFollowing(HttpServletRequest request, HttpServletResponse response, OwnUserFollow ownUserFollow) throws Exception{
		OwnUser ownUserSession = getSessionUser(request);
		if(null == ownUserSession){
			return "9999";
		}
		ownUserFollow.setaUserId(ownUserSession.getId());
		int result = 0;
		if(0 == ownUserFollow.getBoardId()){	//为解除关注单个版块，否则为解除关注用户
			result = ownUserFollowService.unFollowUserAllBoard(ownUserFollow);
		}else{
			result = ownUserFollowService.deleteOwnUserFollow(ownUserFollow);
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String resultData = "";
		if(result > 0){
			resultMap.put("status", "success");
			resultData = JsonStringBuilder.getAjaxString(resultMap);
		}else{
			resultMap.put("status", "failure");
			resultData = JsonStringBuilder.getAjaxString(resultMap);
		}
		response.getWriter().print(resultData);
		return null;
	}
	
	/**
	 * 添加用户版块关注
	 * @param request
	 * @param response
	 * @param ownUserFollow
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userFollowing.h")
	public String userFollowing(HttpServletRequest request, HttpServletResponse response, OwnUserFollow ownUserFollow) throws Exception{
		OwnUser ownUserSession = getSessionUser(request);
		if(null == ownUserSession){
			return "9999";
		}
		ownUserFollow.setaUserId(ownUserSession.getId());
		int result = 0;
		if(0 == ownUserFollow.getBoardId()){	//为关注单个版块，否则为关注用户
			result = ownUserFollowService.followUserAllBoard(ownUserFollow);
		}else{
			result = ownUserFollowService.insertOwnUserFollow(ownUserFollow);
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String resultData = "";
		if(result > 0){
			resultMap.put("status", "success");
			resultData = JsonStringBuilder.getAjaxString(resultMap);
		}else{
			resultMap.put("status", "failure");
			resultData = JsonStringBuilder.getAjaxString(resultMap);
		}
		response.getWriter().print(resultData);
		return null;
	}
	
	public OwnUserFollowService getOwnUserFollowService() {
		return ownUserFollowService;
	}
	public void setOwnUserFollowService(OwnUserFollowService ownUserFollowService) {
		this.ownUserFollowService = ownUserFollowService;
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}
	
}
