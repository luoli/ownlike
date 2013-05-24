<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${ownUser.firstName} ${ownUser.lastName} on Ownliked</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="/css/public.css" type="text/css"/>
	<link rel="stylesheet" href="/css/head/head.css" type="text/css"/>
	<link rel="stylesheet" href="/css/owner.css" type="text/css"/>
	<link rel="stylesheet" href="/css/indexPopular.css" type="text/css"/>
	<!--[if (gt IE 6)&(lt IE 9)]><link rel="stylesheet" href="css/ie.css" type="text/css" media="all" /><![endif]-->
	<script type="text/javascript">window.userIdLogin=${OWNUSERLOGIN!=null};window.currentUserId=${ownUser.id};</script>
  </head>
  
  <body id="profile">
  	<noscript><div id="noScript"><h1>You need to enable Javascript.</h1></div></noscript>
	<div id=header><%@include file="/view/jsp/include/head.jsp" %></div>
	<div id="ProfileHeader"><%@include file="/view/jsp/owner/include/profileHolder.jsp" %></div>
	<div id="ContextBar" class="container sticky"><jsp:include page="/view/jsp/owner/include/contextBar.jsp" /></div>
	<%@include file="/view/jsp/include/headAdd.jsp" %>
    <div id="wrapper" class="boardLayout" style="width: 1170px; opacity: 1; visibility: visible; ">
        <div id="columnContainer">
        	<c:forEach items="${ownActivityHistorys}" var="v">
	        	<div class="pin activity activity-5" data-id="${v.clip.id}" data-width="350" data-height="262">
	              <div class="info colorless">
	                Repinned&nbsp;&nbsp;to&nbsp;&nbsp;<a href="####" class="">${v.clip.boardName}</a>
	                <c:if test="${v.clip.previousId != 0}">via <a href="ownBoard/searchBoardByOwnUser.h?userId=${v.bUserId}" class="">${v.clip.previousUserName}</a></c:if>.
		    		<p class="time-since colorlight">
<%--		    			<c:choose>--%>
<%--		    				<c:when test="${v.createTime / 24 >= 1}">${v.createTime / 24}&nbsp;&nbsp;days ago</c:when>--%>
<%--		    				<c:when test="${v.createTime / 24 >= 0.1}">${v.createTime / 24 * 10}&nbsp;&nbsp;hours ago</c:when>--%>
<%--		    			</c:choose>--%>
		    		</p>
			      </div>
				  <div class="pinHolder">
			    	<div class="actions">
						<a class="button button11 whiteButton contrastButton repin_link" data-id="${v.clip.id}" href="javascript:void(0);"><em></em><span>Repin</span></a>
					  	<c:choose>
							<c:when test="${!empty(v.clip.ownLikeds) && OWNUSERLOGIN != null}">
								<c:forEach items="${v.clip.ownLikeds}" var="ol">
									<c:choose>
										<c:when test="${ol.userId == OWNUSERLOGIN.id }">
											<a href="javascript:void(0);" data-id="${v.clip.id}" data-userId="${v.clip.userId}" class="button whiteButton contrastButton button11 disabled unlikebutton">UnLike</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0);" data-id="${v.clip.id}" data-userId="${v.clip.userId}" class="button whiteButton contrastButton button11 likebutton"><em></em>Like</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0);" data-id="${v.clip.id}" data-userId="${v.clip.userId}" class="button whiteButton contrastButton button11 likebutton"><em></em>Like</a>
							</c:otherwise>
						</c:choose>
					  	<a class="button button11 whiteButton contrastButton comment" data-id="${v.clip.id}" data-userId="${v.clip.userId}" href="javascript:void(0);"><em></em>Comment</a>
			      	</div>
			      	<a href="javascript:void(0);" class="pinImage imgLink">
			        	<img src="${v.clip.image}" alt="${v.clip.description}" class="pinImageImg">
			      	</a>
				  </div>
				  <p class="description">${v.clip.description}</p>
				  <p class="stats colorless">
				  	<c:if test="${v.clip.reclipNum != 0}"><span class="repinsCount">${v.clip.reclipNum}&nbsp;&nbsp;转夹</span></c:if>
					<c:if test="${v.clip.likeNum != 0}"><span class="likesCount"><em>${v.clip.likeNum}</em>&nbsp;&nbsp;喜欢</span></c:if>
					<c:if test="${v.clip.commentNum != 0}"><span class="commentsCount"><em>${v.clip.commentNum}</em>&nbsp;&nbsp;评论</span></c:if>
				  </p>
				  <div class="convo attribution clearfix">
			          <p class="NoImage">
			          	Repinned onto <a href="####" class="">Tips and Tricks</a> from <a href="####" target="_blank" rel="nofollow">amazon.com</a>
			          </p>
				  </div>
			      <div class="write convo clearfix" style="display: none;">
			        <a href="####" class="imgLink">
			          <img src="${OWNUSERLOGIN.image}" alt="Profile picture of you">
			        </a>
			        <form action="" method="POST">
			          <input type="hidden" name="replies" class="pin_comment_replies">
			          <textarea class="gridComment" maxlength="1000" placeholder="添加 评论 或  @···"></textarea>
			          <button class="button whiteButton button11 grid_comment_button" type="button">Comment</button>
			        </form>
			      </div>
			  </div>
        	</c:forEach>
        </div><!-- #ColumnContainer -->
	</div><!-- #wrapper -->
	<%@ include file="/view/jsp/include/clipModel.jsp" %>
	<script type="text/javascript" src="/js/comm/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="/js/head/head.js"></script>
	<script type="text/javascript" src="/js/comm/ajaxfileupload.js"></script>
	<script type="text/javascript" src="/js/model.js"></script>
  </body>
<%--	zoom clip holder	--%>
<script type="text/javascript">
	BoardLayout.setup();
	PinEvent.initBind();
	var url = "ownBoard/searchSessionBoard.h";
	$.ajax({
		url : url,
		data : {},
		type : "POST",
		dataType : "json"
	}).done(function(data){
		window.myBoards = data.obList;
	}).fail(function(e){console.log(e);});
</script>
  <script type="text/javascript" charset="utf-8">
  	$("#ContextBar").on("click", ".followuserbutton", function(){
  		var t = $(this);
  		userFollow($(this), "1");
  		t.html(t.attr("data-text-unfollow"));
  		t.removeClass("followuserbutton").addClass("disabled unfollowuserbutton clickable");
  		var unf = $("#columnContainer").find(".followbutton");
  		unf.html(unf.attr("data-text-unfollow"));
  		unf.removeClass("followbutton").addClass("unfollowbutton disabled clickable");
  	});
  	$("#ContextBar").on("click", ".unfollowuserbutton", function(){
  		var t = $(this);
  		userFollow($(this), "0");
  		t.html(t.attr("data-text-follow"));
  		t.removeClass("disabled unfollowuserbutton clickable").addClass("followuserbutton");
  		var f = $("#columnContainer").find(".unfollowbutton");
  		f.html(f.attr("data-text-follow"));
  		f.removeClass("unfollowbutton disabled clickable").addClass("followbutton");
  	});
  	/**添加关注事件*/
  	$("#columnContainer").on("click", ".followbutton", function(){
  		var t = $(this);
  		userFollow(t, "1");
  		t.html(t.attr("data-text-unfollow"));
  		t.removeClass("followbutton").addClass("unfollowbutton disabled clickable");
  	});
  	/**解除关注*/
  	$("#columnContainer").on("click", ".unfollowbutton", function(){
  		var t = $(this);
  		userFollow($(this), "0");
  		t.html(t.attr("data-text-follow"));
  		t.removeClass("unfollowbutton disabled clickable").addClass("followbutton");
  	});
  	/**执行方法*/
  	function userFollow(t, flag){
  		var url;
  		if(flag == "1"){
  			url = "userFollow/userFollowing.h";
  		}else if(flag == "0"){
  			url = "userFollow/userUnFollowing.h"
  		}else{
  			return;
  		}
  		var boardId = t.parents(".pinBoard").attr("id");
  		console.log("boardId: " + boardId + ",currentUserId: " + currentUserId);
  		$.post(url, {"boardId":boardId, "bUserId":currentUserId}, function(data){
			console.log("data: " + data);
  			if(data.status == "success"){
  				console.log("data: " + data);
  			}
  		});
  	}
    BoardLayout.setup();
  </script>
    <div id="SearchAutocompleteHolder"></div>
    <button id="ScrollToTop" class="Button WhiteButton Offscreen Indicator" type="button">
    	Scroll to Top
	</button>
</html>
