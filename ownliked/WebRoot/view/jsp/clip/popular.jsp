<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html lang="zh" class="en js csstransforms3d">
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Ownliked / Home</title>
		<link rel="stylesheet" href="view/css/public.css" type="text/css"/>
		<link rel="stylesheet" href="view/css/head/head.css" type="text/css"/>
		<link rel="stylesheet" href="view/jsp/clip/css/indexPopular.css" type="text/css"/>
		<!--[if (gt IE 6)&(lt IE 9)]><link rel="stylesheet" href="view/css/ie.css" type="text/css" media="all" /><![endif]-->
		<script type="text/javascript">window.userIdLogin=${OWNUSERLOGIN != null};</script>
		<script type="text/javascript" src="view/js/comm/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="view/js/head/head.js"></script>
		<script type="text/javascript" src="view/js/comm/ajaxfileupload.js"></script>
		<script type="text/javascript" src="view/jsp/clip/js/model.js"></script>
	</head>
	<body id="categoriesBarPage">
	<noscript><div id="noScript"><h1>You need to enable Javascript.</h1></div></noscript>
		<div id=header><%@include file="/view/jsp/include/head.jsp" %></div>
		<div id="categoriesBar">
			<ul class="liquidContainer headContainer">
				<c:if test="${OWNUSERLOGIN != null}"><li><a href="###" class="nav selected">关注</a>&nbsp;&middot;</li></c:if>
				<li class="submenu">
					<a href="###" class="nav">分类<span></span></a>&nbsp;&middot;
					<ul id="categoriesDropdown">
						<li>
							<c:forEach items="${ownBoards}" var="v" varStatus="s">
								<span class="submenuColumn"><a href="ownClip/searchClipByCommBoardType.h?boardId=${v.id}">${v.boardName }</a></span>
							</c:forEach>
						</li>
					</ul>
				</li>
				<li>
					<a href="###" class="nav">最新</a>&nbsp;&middot;
				</li>
				<li>
					<a href="###" class="nav">流行</a>
				</li>
			</ul>
		</div>
		<c:if test="${OWNUSERLOGIN == null}">
		<div id="unauthCallout">
			<div class="nag">
				<div class="sheet1 sheet">
					<div>
						<a href="view/jsp/user/register.jsp" class="button redButton button18">注册 &raquo;</a>
						<a href="view/jsp/user/login.jsp" class="button whiteButton button18">登录</a>
					</div>
					<p>
						<strong>Pinterest is an online pinboard.</strong><br/>Organize and share things you love.
					</p>
				</div>
				<div class="sheet2 sheet"></div>
				<div class="sheet3 sheet"></div>
			</div>
			<div style="height: 80px;"></div>
		</div>
		</c:if>
		<%@include file="/view/jsp/include/headAdd.jsp" %>
		<div id="wrapper" class="boardLayout">
			<div id="columnContainer" style="margin-top: 49px; height: 7007px; ">
				<c:forEach items="${ownClips}" var="v">
					<div class="pin">
						<div class="pinHolder">
							<div class="actions">
								<a href="javascript:;" data-id="${v.id}" class="button button11 whiteButton contrastButton repin_link"><em></em>Clip</a>
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
									<a href="ownBoard/searchBoardByOwnUser.h?userId=${vc.ownUser.id}" class="imgLink"><img class="profile user_image" src="${vc.ownUser.image }" alt="${vc.ownUser.lastName }"/></a>
									<p><a href="ownBoard/searchBoardByOwnUser.h?userId=${vc.ownUser.id}">${vc.ownUser.firstName }&nbsp;&nbsp;${vc.ownUser.lastName}</a>&nbsp;${vc.commentText }</p>
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
							<a href="ownBoard/searchBoardByOwnUser.h?userId=${OWNUSERLOGIN.id}" class="imgLink">
								<img src="${OWNUSERLOGIN.image}" alt="${OWNUSERLOGIN.lastName}"/>
							</a>
							<form action="" method="POST">
								<textarea class="gridComment" rows="" cols="" placeholder="评论或@···"></textarea>
								<button class="button whiteButton button11 grid_comment_button" type="button">comment</button>
							</form>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
<%--	clip	--%>
		<%@ include file="/view/jsp/include/clipModel.jsp" %>
		<script type="text/javascript">
			BoardLayout.setup();
			PinEvent.initBind();
		</script>
	</body>
</html>