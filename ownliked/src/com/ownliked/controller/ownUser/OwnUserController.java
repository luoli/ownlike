package com.ownliked.controller.ownUser;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ownliked.controller.BaseController;
import com.ownliked.pojo.OwnUser;
import com.ownliked.service.user.OwnUserService;
import com.ownliked.util.system.web.HtmlRegexp;

@Controller
@RequestMapping("/ownUser")
public class OwnUserController extends BaseController {

	@Resource(name="ownUserService")
	private OwnUserService ownUserService;
	
	/**
	 * 用户登录
	 * @param ownUser
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ownUserLogin.h")
	public String ownUserLogin(@Valid OwnUser ownUser, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response)throws Exception{
		if(bindingResult.hasErrors()){
			return "/user/login";
		}
		OwnUser resultOwnUser = this.getOwnUserService().findOwnUser(ownUser);
		if(resultOwnUser != null){
			request.getSession().setAttribute("OWNUSERLOGIN", resultOwnUser);
			return "redirect:/ownClip/checkUserLogin.h";
		}
		request.setAttribute("msg", "对不起，我们无法核实你的 email 和 password。");
		return "/user/login";
	}
	
	/**
	 * 用户注销
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ownUserLogout.h")
	public String ownUserLogout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Cookie  cookie = new Cookie("OWNLIKED_COOKIE_LOGIN_PASSWORD", "");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		HttpSession session = request.getSession();
		OwnUser ownUser = (OwnUser)session.getAttribute("OWNUSERLOGIN");
		if(null != ownUser){
			session.removeAttribute("OWNUSERLOGIN");
		}
		return "redirect:/";	//无登录页面
	}
	
	/**
	 * 用户注册
	 * @param ownUser
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ownUserRegister.h")
	public String ownUserRegister(@Valid OwnUser ownUser, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(bindingResult.hasErrors()){
			return "/user/register";
		}
		int result = ownUserService.insertOwnUser(ownUser);
		if(result > 0){
			OwnUser sessionUser = ownUserService.findOwnUser(ownUser);
			request.getSession().setAttribute("OWNUSERLOGIN", sessionUser);
//			response.getWriter().print("0");	//0为注册成功
			return "redirect:/ownBoard/queryOwnBoardByUser.h";
		}else{
//			response.getWriter().print("1");	//1为注册失败
			return "/user/register";
		}
	}
	
	/**
	 * 判断邮箱是否存在
	 * @param ownUser
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ownUserBycheckEmailExist.h")
	public String ownUserBycheckEmailExist(OwnUser ownUser, HttpServletResponse response) throws Exception{
		ownUser.setEmail(HtmlRegexp.escapeToHTML(ownUser.getEmail()));
		OwnUser resultOu = ownUserService.findOwnUser(ownUser);
		if(null != resultOu){
			response.getWriter().print("0");	//0为邮箱存在
		}else{
			response.getWriter().print("1");	//1为邮箱有效
		}
		return null;
	}
	
	/**
	 * 用户自动登录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ownUserAutoLogin.h")
	public String ownUserAutoLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Cookie[] cookies = request.getCookies();
		if(null != cookies && cookies.length > 0){
			String cookieEmail = "";
			String cookiePassword = "";
			for(Cookie cookie : cookies){
				if("OWNLIKED_COOKIE_LOGIN_EMAIL".equals(cookie.getName())){
					cookieEmail = cookie.getValue();
				}
				if("OWNLIKED_COOKIE_LOGIN_PASSWORD".equals(cookie.getName())){
					cookiePassword = cookie.getValue();
				}
			}
			if(null != cookieEmail && !"".equals(cookieEmail) && null != cookiePassword && !"".equals(cookiePassword)){
				OwnUser ownUser = new OwnUser();
				ownUser.setEmail(cookieEmail);
				ownUser.setPassword(cookiePassword);
				OwnUser loginResultOwnUser = ownUserService.findOwnUser(ownUser);
				if(null != loginResultOwnUser){
					request.getSession().setAttribute("OWNUSERLOGIN", loginResultOwnUser);
					return "redirect:/";		//登录成功
				}
			}
		}
		return "redirect:/";		//无登录
	}
	
	public OwnUserService getOwnUserService() {
		return ownUserService;
	}

	public void setOwnUserService(OwnUserService ownUserService) {
		this.ownUserService = ownUserService;
	}
	
}
