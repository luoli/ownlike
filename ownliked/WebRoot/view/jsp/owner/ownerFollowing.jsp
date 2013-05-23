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
	<script type="text/javascript">window.userIdLogin=${OWNUSERLOGIN!=null};window.currentUserId=${ownUser.id};</script>
	<script type="text/javascript" src="view/js/comm/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="view/js/head/head.js"></script>
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
            <ul class="sortable">
            	<c:forEach items="${ownUesrFollows}" var="v">
	                <li>
					    <div class="pin pinBoard" id="${v.id}">
					        <h3 class="serif"><a href="">${v.boardName}</a></h3>
					            <h4>
					                ${v.clipNum} pins
					            </h4>
					        <div class="board">
					            <a href="" class="link">&nbsp;</a>
					            <div class="holder">
					            	<c:choose>
					            		<c:when test="${fn:length(v.clipList) <= 0}">
					            			<span class="cover empty">
						                    </span>
						                    <span class="thumbs">
						                    	<span class="empty"></span>
						                    	<span class="empty"></span>
						                    	<span class="empty"></span>
						                    	<span class="empty"></span>
						                    </span>
					            		</c:when>
					            		<c:otherwise>
						                    <span class="cover">
						                        <img src="${v.clipList[0].image}" style="opacity:0" onload="this.style.opacity=1" onerror="this.src = this.src.replace('_222.jpg', '_b.jpg'); this.onerror = null; return false;">
						                    </span>
						                    <span class="thumbs">
					            			<c:forEach begin="0" end="3" varStatus="vs">
							            		<c:choose>
						            				<c:when test="${fn:length(v.clipList) < (vs.index + 2)}">
						            					<span class="empty"></span>
						            				</c:when>
							            			<c:otherwise>
								                            <img src="${v.clipList[vs.index + 1].image}" alt="Photo of a pin" style="opacity:0" onload="this.style.opacity=1"/>
								            		</c:otherwise>
							            		</c:choose>
							            	</c:forEach>
						                    </span>
					            		</c:otherwise>
					            	</c:choose>
					            </div>
					            <div class="followBoard">
					            	<c:choose>
					            		<c:when test="${v.a_userId == OWNUSERLOGIN.id && v.boardId == v.id}">
										    <a class="button button13 whiteButton disabled clickable unfollowbutton InlineButton" data-text-follow="Follow" data-text-unfollow="Unfollow" href="javascript:void(0);">
										        Unfollow
										    </a>
					            		</c:when>
					            		<c:otherwise>
										    <a class="button button13 whiteButton followbutton InlineButton" data-text-follow="Follow" data-text-unfollow="Unfollow" href="javascript:void(0);">
										        Follow
										    </a>
					            		</c:otherwise>
					            	</c:choose>
					            </div>
					        </div>
					    </div>
	                </li>
            	</c:forEach>
            </ul>
        </div><!-- #ColumnContainer -->
	</div><!-- #wrapper -->
  </body>
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
    <div id="SearchAutocompleteHolder"></div>
    <button id="ScrollToTop" class="Button WhiteButton Offscreen Indicator" type="button">
    	Scroll to Top
	</button>
</html>
