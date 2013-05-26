<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">window.userIdLogin=${OWNUSERLOGIN != null}; window.contextPath="${request.getContextPath()}";</script>
<div class="headContainer" style="width:1170px;">
	<a id="ownliked" href="/">Ownliked</a>
	<ul id="navigation">
		<li>
			<c:choose>
				<c:when test="${OWNUSERLOGIN != null}">
					<a href="#" class="nav" onclick="Modal.show('add'); return false;">Add<span class="plusIcon"></span></a>
				</c:when>
				<c:otherwise>
					<a href="###" class="nav">中文<span></span></a>
					<ul>
						<li><a href="###">中文</a></li>
						<li><a href="###">English</a></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</li>
		<li>
			<a href="###" class="nav">About<span></span></a>
			<ul>
				<li class="beforeDivider">
					<a href="###">Help</a>
				</li>
				<li class="divider">
					<a href="###">成长经历</a>
				</li>
				<li>
					<a href="###">我们的团队</a>
				</li>
				<li class="beforeDivider">
					<a href="###">博客</a>
				</li>
				<li class="divider">
					<a href="###">服务条款</a>
				</li>
				<li>
					<a href="###">保密规则</a>
				</li>
				<li>
					<a href="###">Copyright</a>
				</li>
				<li>
					<a href="###">商标</a>
				</li>
			</ul>
		</li>
		<li id="userNav">
			<c:choose>
				<c:when test="${OWNUSERLOGIN != null}">
					<a href="/ownBoard/searchBoardByOwnUser.h?userId=${OWNUSERLOGIN.id}" class="nav"><img alt="${OWNUSERLOGIN.lastName }" src="${OWNUSERLOGIN.image }"/>${OWNUSERLOGIN.lastName }<span></span></a>
					<ul>
						<li><a href="####">邀请好友</a></li>
						<li class="beforeDivider"><a href="####">查找好友</a></li>
						<li class="divider"><a href="####">板块</a></li>
						<li><a href="####">Clips</a></li>
						<li class="beforeDivider"><a href="####">Likes</a></li>
						<li class="divider"><a href="####">设置</a></li>
						<li><a href="/ownUser/ownUserLogout.h">Logout</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<a href="/view/jsp/user/login.jsp" class="nav2">Login</a>
				</c:otherwise>
			</c:choose>
		</li>
	</ul>
	<div id="search">
		<form action="/ownClip/searchKeywordClip.h?p=" method="post">
			<input type="text" id="query" name="description" placeholder="Search" autocomplete="off"/>
			<a href="" class="lg">
				<img src="/images/head/search.gif"/>
			</a>
		</form>
	</div>
</div>
<%-- <input type="hidden" id="OWNUSERLOGIN" name="OWNUSERLOGIN" value="${OWNUSERLOGIN}"/> --%>