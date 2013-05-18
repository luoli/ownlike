package com.ownliked.controller.ownBoard;

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
import com.ownliked.pojo.OwnBoard;
import com.ownliked.pojo.OwnUser;
import com.ownliked.service.board.OwnBoardService;
import com.ownliked.service.user.OwnUserService;
import com.ownliked.util.system.web.JsonStringBuilder;

@Controller
@RequestMapping("/ownBoard")
public class OwnBoardController extends BaseController {
	private static Logger log = Logger.getLogger(OwnBoardController.class);

	@Resource(name="ownUserService")
	private OwnUserService ownUserService;
	
	/**
	 * 查询用户信息，显示用户所有版块
	 * @param request
	 * @param map
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/searchBoardByOwnUser.h")
	public String searchBoardByOwnUser(HttpServletRequest request, ModelMap map, int userId)throws Exception{
		OwnUser ownUserSession = (OwnUser)request.getSession().getAttribute("OWNUSERLOGIN");
		if(null == ownUserSession){
			return "/user/login";
		}
		OwnUser ouParam = new OwnUser();
		ouParam.setId(userId);
		OwnUser ownUser = ownUserService.findOwnUser(ouParam);
		OwnBoard ownBoard = new OwnBoard();
		ownBoard.setUserId(userId);
		List<OwnBoard> ownBoards = ownBoardService.queryOwnUserBoardBy4Clip(ownBoard);
		map.put("ownBoards", ownBoards);
		map.put("ownUser", ownUser);
		map.put("myOwnBoards", JsonStringBuilder.getAjaxString(searchSessionBoard(ownUserSession)));
		map.put("selBar", "board");
		return "/owner/ownerBoard";
	}
	
	/**
	 * 根据用户获得板块信息
	 * @param response
	 * @param ownBoard
	 * @return
	 */
	@RequestMapping(value="/queryOwnBoardByUser.h")
	public String queryOwnBoardByUser(HttpServletResponse response, OwnBoard ownBoard, ModelMap map, HttpServletRequest request){
		OwnUser sessionUser = (OwnUser)request.getSession().getAttribute("OWNUSERLOGIN");
		ownBoard.setUserId(sessionUser.getId());
		List<OwnBoard> ownBoards = ownBoardService.queryOwnUserBoard(ownBoard);
		map.put("ownboards", ownBoards);
		return "";
	}
	
	/**
	 * 添加新板块
	 * @param ownBoard
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertOwnBoardByUser.h")
	public String insertOwnBoardByUser(OwnBoard ownBoard, HttpServletRequest request, HttpServletResponse response) throws Exception{
		OwnUser ownUser = (OwnUser)request.getSession().getAttribute("OWNUSERLOGIN");
		if(null == ownUser){
			response.getWriter().print("9999");	//用户未登录
			return null;
		}
		ownBoard.setFlag(1);
		if(ownBoard.getParentId() == 0){
			ownBoard.setParentId(1);
		}
		ownBoard.setUserId(ownUser.getId());
		log.debug("ownBoard data print.");
		log.debug("ownBoard.getParentId():"+ownBoard.getParentId());
		log.debug("ownBoard.getUserId():"+ownBoard.getUserId());
		log.debug("ownBoard.getBoardName():"+ownBoard.getBoardName());
		int result = ownBoardService.insertOwnBoard(ownBoard);
		log.debug("result:"+result);
		Map<String, Object> map = new HashMap<String, Object>();
		if(result > 0){
			map.put("id", ownBoard.getId());
			map.put("boardName", ownBoard.getBoardName());
			map.put("status", "seccess");
			this.sendAjaxResponse(response, map);	//添加成功
		}else{
			map.put("status", "error");
			response.getWriter().print(JsonStringBuilder.getAjaxString(map));	//添加失败
		}
		return null;
	}
	
	/**
	 * 删除板块
	 * @param ownBoard
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteOwnBoardByUser.h")
	public String deleteOwnBoardByUser(OwnBoard ownBoard, HttpServletResponse response)throws Exception{
		int result = ownBoardService.deleteOwnBoard(ownBoard);
		if(result > 0){
			response.getWriter().print("1");	//删除成功
		}else{
			response.getWriter().print("0");	//删除失败
		}
		return null;
	}
	
	/**
	 * 更新板块
	 * @param ownBoard
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateOwnBoardByUser.h")
	public String updateOwnBoardByUser(OwnBoard ownBoard, HttpServletResponse response)throws Exception{
		int result = ownBoardService.updateOwnBoard(ownBoard);
		if(result > 0){
			response.getWriter().print("1");	//修改成功
		}else{
			response.getWriter().print("0");	//修改失败
		}
		return null;
	}

	public OwnBoardService getOwnBoardService() {
		return ownBoardService;
	}

	public void setOwnBoardService(OwnBoardService ownBoardService) {
		this.ownBoardService = ownBoardService;
	}

	public OwnUserService getOwnUserService() {
		return ownUserService;
	}

	public void setOwnUserService(OwnUserService ownUserService) {
		this.ownUserService = ownUserService;
	}
	
}
