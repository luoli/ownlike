package com.ownliked.controller.activateHistory;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ownliked.controller.BaseController;
import com.ownliked.pojo.OwnActivityHistory;
import com.ownliked.pojo.OwnBoard;
import com.ownliked.pojo.OwnUser;
import com.ownliked.service.activeHistory.OwnActivityHistoryService;
import com.ownliked.service.board.OwnBoardService;
import com.ownliked.service.user.OwnUserService;
import com.ownliked.util.system.web.JsonStringBuilder;

@Controller
@RequestMapping(value="/ownActivateHistory")
public class OwnActivityHistoryController extends BaseController {
	
	@Resource(name="ownActivityHistoryService")
	private OwnActivityHistoryService ownActivityHistoryService;
	@Resource(name="ownUserService")
	private OwnUserService ownUserService;
	@Resource(name="ownBoardService")
	private OwnBoardService ownBoardService;

	/**
	 * 查询用户历史活动信息
	 * @param request
	 * @param userId
	 * @param map
	 * @return
	 */
	@RequestMapping(value="getCurrentUserActive")
	public String getCurrentUserActive(HttpServletRequest request, int userId, ModelMap map){
		OwnUser ownUserSession = getSessionUser(request);
		if(null == ownUserSession){
			return "9999";
		}
		OwnActivityHistory ownActivityHistory = new OwnActivityHistory();
		ownActivityHistory.setaUserId(userId);
		List<OwnActivityHistory> ownActivityHistorys = ownActivityHistoryService.queryActiveAndClip(ownActivityHistory);
		OwnBoard myOwnBoard = new OwnBoard();
		myOwnBoard.setUserId(ownUserSession.getId());
		OwnUser ouParam = new OwnUser();
		ouParam.setId(userId);
		ouParam = ownUserService.findOwnUser(ouParam);
		map.put("ownActivityHistorys", ownActivityHistorys);
		map.put("ownUser", ouParam);
		map.put("myOwnBoards", JsonStringBuilder.getAjaxString(searchSessionBoard(ownUserSession)));
		map.put("selBar", "active");
		return "/owner/ownerActivity";
	}

	public OwnActivityHistoryService getOwnActivityHistoryService() {
		return ownActivityHistoryService;
	}

	public void setOwnActivityHistoryService(
			OwnActivityHistoryService ownActivityHistoryService) {
		this.ownActivityHistoryService = ownActivityHistoryService;
	}

	public OwnUserService getOwnUserService() {
		return ownUserService;
	}

	public void setOwnUserService(OwnUserService ownUserService) {
		this.ownUserService = ownUserService;
	}

	public OwnBoardService getOwnBoardService() {
		return ownBoardService;
	}

	public void setOwnBoardService(OwnBoardService ownBoardService) {
		this.ownBoardService = ownBoardService;
	}

}
