package com.ownliked.controller.ownClip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ownliked.controller.BaseController;
import com.ownliked.pojo.OwnBoard;
import com.ownliked.pojo.OwnClip;
import com.ownliked.pojo.OwnUser;
import com.ownliked.service.clip.OwnClipService;
import com.ownliked.service.like.OwnLikedService;
import com.ownliked.service.user.OwnUserService;
import com.ownliked.util.system.web.JsonStringBuilder;
import com.ownliked.util.system.web.NcgUtil;

@Controller
@RequestMapping(value="/ownClip")
public class OwnClipController extends BaseController {

	@Resource(name="ownClipService")
	private OwnClipService ownClipService;
	@Resource(name="ownUserService")
	private OwnUserService ownUserService;
	@Resource(name="ownLikedService")
	private OwnLikedService ownLikedService;
	
	/**
	 * 获得当前页面用户关注版块的clip
	 * @param request
	 * @param map
	 * @param userId	当前页面用户ID
	 * @param filter	是否过滤为喜欢的clip数据
	 * @return
	 */
	@RequestMapping(value="/searchClipByCurrentUser.h")
	public String searchClipByCurrentUser(HttpServletRequest request, ModelMap map, int userId, String filter){
		OwnUser ownUserSession = getSessionUser(request);
		if(null == ownUserSession){
			return "/user/login";
		}
		List<OwnClip> ownClips = new ArrayList<OwnClip>();
		OwnClip ownClipParam = new OwnClip();
		ownClipParam.setUserId(userId);
		if(null != filter && filter.equals("likes")){
			ownClips = ownClipService.queryOwnClipByUserAndLiked(ownClipParam);
			map.put("selBar", "like");
		}else{
			ownClips = ownClipService.queryOwnClipByUser(ownClipParam);
			map.put("selBar", "pin");
		}
		map.put("myOwnBoards", JsonStringBuilder.getAjaxString(searchSessionBoard(ownUserSession)));
		map.put("ownClips", ownClips);
		OwnUser ouParam = new OwnUser();
		ouParam.setId(userId);
		ouParam = ownUserService.findOwnUser(ouParam);
		map.put("ownUser", ouParam);
		return "/owner/ownerPins";
	}
	
	/**
	 * 根据clip板块大类型查询clip数据
	 * @param request
	 * @param map
	 * @param boardId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/searchClipByCommBoardType.h")
	public String searchClipByCommBoardType(HttpServletRequest request, ModelMap map, int boardId)throws Exception{
		OwnBoard ownBoard = new OwnBoard();
		ownBoard.setParentId(-1);
		OwnUser ownUserSession = getSessionUser(request);
		List<OwnBoard> ownBoards = ownBoardService.queryOwnUserBoard(ownBoard);
		map.put("ownBoards", ownBoards);
		OwnClip ownClip = new OwnClip();
		if(null != ownUserSession){
			map.put("myOwnBoards", JsonStringBuilder.getAjaxString(searchSessionBoard(ownUserSession)));
//			map.put("myOwnBoards", myOwnBoards);
			ownClip.setBoardId(boardId);
			return queryClipByPopular(request, map, ownClip);
		}
		return "/user/login";
	}
	
	/**
	 * 判断用户是否登录的页面呈现方式
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/checkUserLogin.h")
	public String checkUserLogin(HttpServletRequest request, ModelMap map)throws Exception{
		OwnBoard ownBoard = new OwnBoard();
		ownBoard.setParentId(-1);
		OwnUser ownUserSession = getSessionUser(request);
		List<OwnBoard> ownBoards = ownBoardService.queryOwnUserBoard(ownBoard);
		map.put("ownBoards", ownBoards);
		OwnClip ownClip = new OwnClip();
		if(null != ownUserSession){
			map.put("myOwnBoards", JsonStringBuilder.getAjaxString(searchSessionBoard(ownUserSession)));
			return queryClipByPopular(request, map, ownClip);
		}else{
			return queryClipByPopular(request, map, ownClip);
		}
	}
	
	/**
	 * 获得最流行的clip数据
	 * @return
	 */
	@RequestMapping(value="/queryClipByPopular.h")
	public String queryClipByPopular(HttpServletRequest request, ModelMap map, OwnClip ownClip){
		List<OwnClip> ownClips = ownClipService.queryOwnClipByComment(ownClip);
		map.put("ownClips", ownClips);
		return "/clip/popular";
	}
	
	/**
	 * 获得用户关注的clip数据
	 * @return
	 */
	@RequestMapping(value="/queryClipByFollow.h")
	public String queryClipByFollow(ModelMap map){
		OwnClip ownClip = new OwnClip();
		List<OwnClip> ownClips = ownClipService.queryOwnClipByComment(ownClip);
		map.put("ownClips", ownClips);
		return "/clip/follow";
	}
	
	/**
	 * like执行方法
	 * @param response
	 * @param request
	 * @param ownClip
	 * @param like
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/likeClip.h")
	public String likeClip(HttpServletResponse response, HttpServletRequest request, OwnClip ownClip, int like)throws Exception{
		OwnUser sessionUser = (OwnUser)request.getSession().getAttribute("OWNUSERLOGIN");
		ownClip.setLikeNum(like);
		int result = this.getOwnClipService().likeOwnClip(ownClip, sessionUser.getId());
		response.getWriter().print(result);
		return null;
	}
	
	/**
	 * 转夹查找clip信息
	 * @param ownClip
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/findClipByReClip.h")
	public String findClipByReClip(OwnClip ownClip,HttpServletRequest request, HttpServletResponse response, ModelMap map) throws IOException{
		OwnUser ownUserSession = (OwnUser)request.getSession().getAttribute("OWNUSERLOGIN");
		if(!NcgUtil.blankObject(ownUserSession)){
			response.getWriter().print("9999");	//session用户不存在
		}
		OwnClip oc = this.getOwnClipService().findOwnClip(ownClip);
//		OwnBoard ownBoard = new OwnBoard();
//		ownBoard.setUserId(ownUser.getId());
//		List<OwnBoard> ownBoards = this.getOwnBoardService().queryOwnUserBoard(ownBoard);
		map.put("ownClip", oc);
//		map.put("ownBoards", ownBoards);
		String jo = JsonStringBuilder.getAjaxString(map);
		response.getWriter().print(jo);
		return null;
	}
	
	/**
	 * 转夹clip
	 * @param response
	 * @param request
	 * @param ownClip
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/reClip.h")
	public String reClip(HttpServletResponse response, HttpServletRequest request, OwnClip ownClip) throws Exception{
		OwnUser sessionUser = (OwnUser)request.getSession().getAttribute("OWNUSERLOGIN");
		ownClip.setUserId(sessionUser.getId());
		ownClip.setUserName(sessionUser.getFirstName() +" "+ sessionUser.getLastName());
		ownClip.setUserImage(sessionUser.getImage());
		int result = this.getOwnClipService().insertOwnClip(ownClip);
		if(result > 0){
			OwnClip paramOwnClip = new OwnClip();
			List<OwnClip> ownClips = this.getOwnClipService().queryOwnClip(paramOwnClip);
			String jo = JsonStringBuilder.getAjaxString(ownClips);
			response.getWriter().print(jo);
		}else{
			response.getWriter().print("9999");
		}
		return null;
	}

	/**
	 * 上传clip数据
	 * @param response
	 * @param request
	 * @param ownClip
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/uploadClip.h")
	public String uploadClip(HttpServletResponse response, HttpServletRequest request, OwnClip ownClip) throws Exception{
		OwnUser sessionUser = (OwnUser)request.getSession().getAttribute("OWNUSERLOGIN");
		if(sessionUser == null){
			return null;
		}
		ownClip.setUserId(sessionUser.getId());
		ownClip.setUserName(sessionUser.getFirstName() +" "+ sessionUser.getLastName());
		ownClip.setUserImage(sessionUser.getImage());
		int result = this.getOwnClipService().insertOwnClip(ownClip);
		if(result > 0){
			OwnClip paramOwnClip = new OwnClip();
			List<OwnClip> ownClips = this.getOwnClipService().queryOwnClip(paramOwnClip);
			String jo = JsonStringBuilder.getAjaxString(ownClips);
			response.getWriter().print(jo);
		}else{
			response.getWriter().print("9999");
		}
		return null;
	}
	
	/**
	 * 上传图片
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/uploadImg.h")
	@ResponseBody
	public Object uploadImg(HttpServletResponse response, HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		MultipartFile imgFile = multipartRequest.getFile("img");
		List<String> fileTypes = new ArrayList<String>();  
        fileTypes.add("jpg");
        fileTypes.add("jpeg"); 
        fileTypes.add("bmp"); 
        fileTypes.add("gif");
        fileTypes.add("png");
        String resultPath = "";
        Map<String, Object> map = new HashMap<String, Object>();
        if(!(imgFile.getOriginalFilename() == null || "".equals(imgFile.getOriginalFilename()))){
        	String path = request.getSession().getServletContext().getRealPath("/");
        	File file = getFile(imgFile, path, "clipImgUpload", fileTypes);
        	if(null != file){
        		resultPath = file.getPath().substring(file.getPath().indexOf("ownliked") + 9);
            	InputStream input = imgFile.getInputStream();
            	OutputStream out = new FileOutputStream(file);
            	byte[] data = new byte[1024];
            	int len = 0;
            	while((len = input.read(data)) > 0){
            		out.write(data, 0, len);
            	}
            	out.close();
            	input.close();
            	map.put("status", "success");
            	map.put("path", resultPath);
        	}else{
        		map.put("status", "error");
        		map.put("msg", "图片格式不被支持！");
        	}
        }
		return map;
	}
	private File getFile(MultipartFile imgFile,String typeName,String brandName,List<String> fileTypes){
		File file = null;
		String fileName = imgFile.getOriginalFilename();
		String ext = fileName.substring(fileName.indexOf(".") + 1);
		ext = ext.toLowerCase();
		if(fileTypes.contains(ext)){
			fileName = System.currentTimeMillis() +"."+ ext;
			file = createFolder(typeName, brandName, fileName);
		}
		return file;
	}
	private File createFolder(String typeName,String brandName,String fileName){
		File file = null;
		File firstFolder = new File(typeName);         //一级文件夹  
        if(firstFolder.exists()) {                             //如果一级文件夹存在，则检测二级文件夹  
            File secondFolder = new File(firstFolder,brandName);  
            if(secondFolder.exists()) {                        //如果二级文件夹也存在，则创建文件  
                file = new File(secondFolder,fileName);  
            }else {                                            //如果二级文件夹不存在，则创建二级文件夹  
                secondFolder.mkdir();  
                file = new File(secondFolder,fileName);        //创建完二级文件夹后，再合建文件  
            }  
        }else {                                                //如果一级不存在，则创建一级文件夹  
            firstFolder.mkdir();  
            File secondFolder = new File(firstFolder,brandName);  
            if(secondFolder.exists()) {                        //如果二级文件夹也存在，则创建文件  
                file = new File(secondFolder,fileName);  
            }else {                                            //如果二级文件夹不存在，则创建二级文件夹  
                secondFolder.mkdir();  
                file = new File(secondFolder,fileName);  
            }  
        }  
        return file;
	}
	
	/**
	 * 添加回形针
	 * @param response
	 * @param ownClip
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertOwnClip.h")
	public String insertOwnClip(HttpServletResponse response, OwnClip ownClip)throws Exception{
		int result = this.getOwnClipService().insertOwnClip(ownClip);
		if(result > 0){
			response.getWriter().print("1");	//添加成功
		}else{
			response.getWriter().print("0");	//添加失败
		}
		return null;
	}
	/**
	 * 更新回形针
	 * @param response
	 * @param ownClip
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateOwnClip.h")
	public String updateOwnClip(HttpServletResponse response, OwnClip ownClip)throws Exception{
		int result = this.getOwnClipService().updateOwnClip(ownClip);
		if(result > 0){
			response.getWriter().print("1");	//更新成功
		}else{
			response.getWriter().print("0");	//更新失败
		}
		return null;
	}
	/**
	 * 删除回形针
	 * @param response
	 * @param ownClip
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteOwnClip.h")
	public String deleteOwnClip(HttpServletResponse response, OwnClip ownClip)throws Exception{
		int result = this.getOwnClipService().deleteOwnClip(ownClip);
		if(result > 0){
			response.getWriter().print("1");	//删除成功
		}else{
			response.getWriter().print("0");	//删除失败
		}
		return null;
	}

	public OwnClipService getOwnClipService() {
		return ownClipService;
	}

	public void setOwnClipService(OwnClipService ownClipService) {
		this.ownClipService = ownClipService;
	}

	public OwnUserService getOwnUserService() {
		return ownUserService;
	}

	public void setOwnUserService(OwnUserService ownUserService) {
		this.ownUserService = ownUserService;
	}

	public OwnLikedService getOwnLikedService() {
		return ownLikedService;
	}

	public void setOwnLikedService(OwnLikedService ownLikedService) {
		this.ownLikedService = ownLikedService;
	}
	
}