package com.ownliked.controller.comment;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ownliked.controller.BaseController;
import com.ownliked.pojo.OwnComment;
import com.ownliked.pojo.OwnUser;
import com.ownliked.service.comment.OwnCommentService;
import com.ownliked.util.system.web.JsonStringBuilder;

@Controller
@RequestMapping(value="/ownComment")
public class OwnCommentController extends BaseController {

	@Resource(name="ownCommentService")
	private OwnCommentService ownCommentService;
	
	public ModelAndView queryOwnCommentByClip(){
		
		return new ModelAndView();
	}
	
	/**
	 * 添加用户评论
	 * @param ownComment
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertComment.h")
	public String insertComment(OwnComment ownComment, HttpServletRequest request, HttpServletResponse response)throws Exception{
		OwnUser sessionUser = (OwnUser)request.getSession().getAttribute("OWNUSERLOGIN");
		ownComment.setUserId(sessionUser.getId());
		int result = this.getOwnCommentService().insertOwnComment(ownComment);
		Map<String, Object> mapResult = new HashMap<String, Object>();
		if(result > 0){
			mapResult.put("status", "success");
			mapResult.put("image", sessionUser.getImage());
			String firstName = sessionUser.getFirstName();
			mapResult.put("name", firstName!=null?firstName+"&nbsp;&nbsp;":""+ sessionUser.getLastName());
			mapResult.put("id", sessionUser.getId());
		}else{
			mapResult.put("status", "error");
		}
		String jo = JsonStringBuilder.getAjaxString(mapResult);
		response.getWriter().print(jo);
		return null;
	}

	public OwnCommentService getOwnCommentService() {
		return ownCommentService;
	}

	public void setOwnCommentService(OwnCommentService ownCommentService) {
		this.ownCommentService = ownCommentService;
	}
	
}
