<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${ownUser.firstName} ${ownUser.lastName} on Ownliked</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="view/css/public.css" type="text/css"/>
	<link rel="stylesheet" href="view/css/head/head.css" type="text/css"/>
	<link rel="stylesheet" href="view/jsp/owner/css/owner.css" type="text/css"/>
	<link rel="stylesheet" href="view/jsp/clip/css/indexPopular.css" type="text/css"/>
	<!--[if (gt IE 6)&(lt IE 9)]><link rel="stylesheet" href="view/css/ie.css" type="text/css" media="all" /><![endif]-->
	<script type="text/javascript">window.userIdLogin=${OWNUSERLOGIN!=null};window.currentUserId="${ownUser.id}";</script>
	<script type="text/javascript" src="view/js/comm/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="view/js/head/head.js"></script>
		<script type="text/javascript" src="view/js/comm/ajaxfileupload.js"></script>
	<script type="text/javascript" src="view/jsp/clip/js/model.js"></script>
  </head>
  
  <body id="profile">
  	<noscript><div id="noScript"><h1>You need to enable Javascript.</h1></div></noscript>
	<div id=header><%@include file="/view/jsp/include/head.jsp" %></div>
	<div id="ProfileHeader"><%@include file="/view/jsp/owner/include/profileHolder.jsp" %></div>
	<div id="ContextBar" class="container sticky">
		<jsp:include page="/view/jsp/owner/include/contextBar.jsp">
			<jsp:param value="viewType" name="1"/>
		</jsp:include>
	</div>
	<%@include file="/view/jsp/include/headAdd.jsp" %>
    <div id="wrapper" class="boardLayout" style="width: 1170px; opacity: 1; visibility: visible; ">
        <div id="columnContainer">
            <c:forEach items="${ownClips}" var="v">
            	<div class="pin">
					<div class="pinHolder">
						<div class="actions">
							<a href="javascript:void(0);" data-id="${v.id}" class="button button11 whiteButton contrastButton repin_link"><em></em>Clip</a>
							<c:choose>
								<c:when test="${!empty(v.ownLikeds) && OWNUSERLOGIN != null}">
									<c:forEach items="${v.ownLikeds}" var="ol">
										<c:choose>
											<c:when test="${ol.userId == OWNUSERLOGIN.id }">
												<a href="javascript:;" data-id="${v.id}" data-userId="${v.userId}" class="button whiteButton contrastButton button11 disabled unlikebutton">UnLike</a>
											</c:when>
											<c:otherwise>
												<a href="javascript:;" data-id="${v.id}" data-userId="${v.userId}" class="button whiteButton contrastButton button11 likebutton"><em></em>Like</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<a href="javascript:;" data-id="${v.id}" data-userId="${v.userId}" class="button whiteButton contrastButton button11 likebutton"><em></em>Like</a>
								</c:otherwise>
							</c:choose>
							<a href="javascript:;" data-id="${v.id}" data-userId="${v.userId}" class="button button11 whiteButton contrastButton comment"><em></em>Comment</a>
						</div>
						<a href="javascript:void(0);" onclick="ZoomHolder.openHolder(this);" class="pinImage imgLink"><img src="${v.image}" alt="${v.description}"/></a>
					</div>
					<p class="description">${v.description }</p>
					<p class="stats colorless">
						<c:if test="${v.reclipNum != 0}"><span class="repinsCount">${v.reclipNum}&nbsp;&nbsp;转夹</span></c:if>
						<c:if test="${v.likeNum != 0}"><span class="likesCount"><em>${v.likeNum}</em>&nbsp;&nbsp;喜欢</span></c:if>
						<c:if test="${v.commentNum != 0}"><span class="commentsCount"><em>${v.commentNum}</em>&nbsp;&nbsp;评论</span></c:if>
					</p>
					<div class="convo attribution clearfix">
						<a href="ownBoard/searchBoardByOwnUser.h?userId=${v.userId}" class="imgLink"><img src="${ v.userImage}" alt="${v.userName }" title="${v.userName }"/></a>
						<p>
							<a href="ownBoard/searchBoardByOwnUser.h?userId=${v.userId}" data-id="${v.userId}">${v.userName }</a>&nbsp;onto&nbsp;
							<a href="###" data-id="${v.boardId}">${v.boardName }</a>
						</p>
					</div>
					<c:forEach items="${v.ownCommentList}" var="vc">
						<div class="comments colormuted">
							<div class="comment convo clearfix">
								<a href="ownBoard/searchBoardByOwnUser.h?userId=${v.userId}" class="imgLink"><img class="profile user_image" src="${vc.ownUser.image }" alt="${vc.ownUser.lastName }"/></a>
								<p><a href="ownBoard/searchBoardByOwnUser.h?userId=${v.userId}">${vc.ownUser.firstName }&nbsp;&nbsp;${vc.ownUser.lastName}</a>&nbsp;${vc.commentText }</p>
							</div>
						</div>
					</c:forEach>
					<c:if test="${v.commentNum > 5}">
						<div class="comments colormuted">
							<div class="comment convo clearfix">
								<p><a href="###">全部 ${v.commentNum } 条评论</a></p>
							</div>
						</div>
					</c:if>
					<div class="write convo clearfix" style="display: none;">
						<a href="###" class="imgLink">
							<img src="${OWNUSERLOGIN.image}" alt="${OWNUSERLOGIN.lastName}"/>
						</a>
						<form action="" method="POST">
							<textarea rows="" cols="" placeholder="评论或@···"></textarea>
							<button class="button whiteButton button11 grid_comment_button" type="button">comment</button>
						</form>
					</div>
				</div>
            </c:forEach>
        </div><!-- #ColumnContainer -->
	</div><!-- #wrapper -->
	<%@ include file="/view/jsp/include/clipModel.jsp" %>
  </body>
<script type="text/javascript">
	BoardLayout.setup();
	PinEvent.initBind();
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
