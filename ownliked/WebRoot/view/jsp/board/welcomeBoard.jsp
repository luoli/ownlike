<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<!--[if IE 7 ]><html lang="en" class="ie7 ielt9 ielt10 en"><![endif]-->
<!--[if IE 8 ]><html lang="en" class="ie8 ielt9 ielt10 en"><![endif]-->
<!--[if IE 9 ]><html lang="en" class="ie9 ielt10 en"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="en">
<!--<![endif]-->
	<head>
		<title>Ownliked / Welcome</title>
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
		<link rel="stylesheet" href="/css/new_myboard.css" type="text/css">
		
		<script type="text/javascript">window.userIdLogin=${OWNUSERLOGIN != null};</script>
	</head>

	<body>
		<div id=header><%@include file="/view/jsp/include/head.jsp" %></div>
		<%@include file="/view/jsp/include/headAdd.jsp" %>
		<div class="App full Module">
			<div class="appContent">
				<div class="mainContainer">
					<div class="Nags Module">
						<!--[if lt IE 8]>
						<div class="NagBase Module centeredWithinWrapper" id="NagBase-15">
						    <div class="message">Wups, your browser's a little out of date! <a href="http://windows.microsoft.com/en-US/internet-explorer/download-ie">Update it now</a> or <a href="http://whatbrowser.org/">try another browser</a> for a faster, smoother Pinterest.</div>
						    <button type="button" class="Button nagClose Module borderless" id="Button-16">
						    &nbsp;</button>
						</div>
						<![endif]-->
					</div>
					<div class="BoardPage ajax Module fadeIn" style="min-height: 400px;">
						<div class="moduleMask"></div>
						<div class="BoardInvite ajax Module">
						</div>
						<div class="ajax BoardHeader Module centeredWithinWrapper">
							<h1>
								${ownBoard.boardName }
							</h1>
							<p class="description">
							</p>
							<div class="ajax centeredWithinWrapper InfoBarBase Module BoardInfoBar">
								<div class="metaLeft">
									<div class="thumb hasText Module ajax User small">
										<a href="ownBoard/searchBoardByOwnUser.h?userId=${ownBoard.ownUser.id }"> <span class="thumbImageWrapper">
											<img src="${ownBoard.ownUser.image }" alt="Profile image of Nessa Pie"> </span>
											<span class="fullname">${ownBoard.ownUser.firstName }${ownBoard.ownUser.lastName }</span>
										</a>
									</div>
								</div>
								<ul class="counts">
									<li>
										<a href="/nesspie/things-for-my-house/pins/" class="active">${ownBoard.clipNum } Pins </a>
									</li>
									<li>
										<a href="/nesspie/things-for-my-house/followers/">127 Followers</a>
									</li>
								</ul>
								<button type="button" class="hasText btn rounded Button boardFollowUnfollowButton ajax Module primary BoardFollowButton notNavigatable ui-FollowButton">
									<span class="buttonText">Follow Board</span>
								</button>
							</div>
						</div>
						<div id="wrapper" class="hasFooter ajax Grid Module">
							<div class="moduleMask"></div>
							<div id="columnContainer" class="padItems Module centeredWithinWrapper ajax GridItems variableHeightLayout boardLayout" style="height: 4439px;">
							<c:forEach items="${ownBoard.clipList}" var="v">
								<div class="item pin" style="visibility: visible;">
									<div class="ajax Pin Module summary">
										<div class="pinWrapper">
											<div class="pinHolder">
												<div class="repinLikeWrapper">
													<button type="button" class="rounded ShowModalButton Button repinSmall pinIt primary Module ajax primaryOnHover btn">
														<em></em>
														<span class="accessibilityText">Pin it</span>
													</button>
													<button data-text-unlike="Unlike" data-text-like="Like" type="button" class="rounded PinLikeButton Button hasText Module likeSmall ajax btn">
														<em></em>
														<span class="buttonText">Like</span>
													</button>
												</div>
												<a href="/pin/252834966552963750/" class="pinImageWrapper " style="background: #c9b4a3;">
													<h4 class="pinDomain">
														prettyhandygirl.com
													</h4>
													<div class="fadeContainer loaded">
														<div class="hoverMask"></div>
														<img src="${v.image }" class="image pinImg fullBleed lazy loaded">
													</div>
												</a>
											</div>
											<div class="pinMeta">
												<p class="pinDescription">
													${v.description }
												</p>
											</div>
											<div class="pinUserAttribution">
												<a href="${v.link }" class="attributionItem firstAttribution lastAttribution ">
													<span class="attributionTitle">Pinned from</span> <span class="attributionName">${v.viaUrl }</span>
												</a>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
							</div>
							<div class="ajax GridFooter Module centeredWithinWrapper showGridLoading" style="display: block;">
								<div class="gridError" style="display: none;">
									<button class="Button btn rounded large">
										<span>Whoops! Something went wrong.</span> Try again.
									</button>
								</div>
								<div class="gridLoadingWrapper">
									<hr>
									<div class="gridLoading">
										<span class="gridFooterLogoIcon"></span>
										<span class="gridFooterSpinner"></span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="appendedContainer">
				</div>
			</div>
		</div>
		<script type="text/javascript" src="/js/comm/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="/js/head/head.js"></script>
		<script type="text/javascript" src="/js/comm/ajaxfileupload.js"></script>
		<script type="text/javascript" src="/js/model.js"></script>
		<script>
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
	</body>
</html>
