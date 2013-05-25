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
		<link rel="stylesheet" href="/css/public.css" type="text/css"/>
		<link rel="stylesheet" href="/css/head/head.css" type="text/css"/>
		<link rel="stylesheet" href="/css/new_myboard.css" type="text/css">
		<link rel="stylesheet" href="/css/flip.css" type="text/css">
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
								<ul class="searchScope">
							        <li>
							    		<a href="/search/pins/?q=le" class="active">Pins</a>,
							        </li>
							        <li>
							    		<a href="/search/boards/?q=le">Boards</a>,
							        </li>
							        <li>
							    		<a href="/search/people/?q=le">Pinners</a>
							        </li>
								</ul>
								<h3 class="headerText">
							        Search results for <span class="query">"le"</span>
							    </h3>
							</div>
						</div>
						<div id="wrapper" class="hasFooter ajax Grid Module">
							<div class="moduleMask"></div>
							<div id="columnContainer" class="padItems Module centeredWithinWrapper ajax GridItems variableHeightLayout boardLayout" style="height: 4439px;">
							<c:forEach items="${ownBoard.clipList}" var="v">
								<div class="item pin" visibility: visible;">
									<div class="ajax summary Pin Module hasRichPins">
										<div class="pinWrapper">
											<div class="pinHolder">
												<div class="actions">
													<a href="javascript:;" data-id="${v.id}" data-userId="${ownBoard.ownUser.id}" class="button button11 whiteButton contrastButton repin_link"><em></em>Clip</a>
													<c:choose>
														<c:when test="${!empty(v.ownLikeds) && OWNUSERLOGIN != null}">
															<c:forEach items="${v.ownLikeds}" var="ol">
																<c:choose>
																	<c:when test="${ol.userId == OWNUSERLOGIN.id }">
																		<a href="javascript:;" data-id="${v.id}" data-userId="${ownBoard.ownUser.id}" class="button whiteButton contrastButton button11 disabled unlikebutton">UnLike</a>
																	</c:when>
																	<c:otherwise>
																		<a href="javascript:;" data-id="${v.id}" data-userId="${ownBoard.ownUser.id}" class="button whiteButton contrastButton button11 likebutton"><em></em>Like</a>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														</c:when>
														<c:otherwise>
															<a href="javascript:;" data-id="${v.id}" data-userId="${ownBoard.ownUser.id}" class="button whiteButton contrastButton button11 likebutton"><em></em>Like</a>
														</c:otherwise>
													</c:choose>
<%-- 													<a href="javascript:;" data-id="${v.id}" data-userId="${v.userId}" class="button button11 whiteButton contrastButton comment"><em></em>Comment</a> --%>
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
												<p class="pinDescription">${v.description }</p>
												<div class="pinSocialMeta">
													<c:if test="${v.reclipNum != 0 }">
						                            <a class="socialItem" href="#">${v.reclipNum } repins</a>
						                            </c:if>
						                            <c:if test="${v.likeNum != 0 }">
						                            <a class="socialItem likes" href="/ownClip/searchClipByCurrentUser.h?userId=${ownBoard.ownUser.id}&filter=likes">${v.likeNum } likes</a>
						                            </c:if>
						                            <c:if test="${v.commentNum != 0 }">
						                            <a class="socialItem comments">${v.commentNum }</a>
						                            </c:if>
						                    	</div>
											</div>
											<div class="pinUserAttribution">
												<a href="/maureenl/" class="attributionItem firstAttribution">
													<img src="http://media-cache-ec1.pinimg.com/avatars/maureenl-61_30.jpg" style="" alt="" class="image attributionImg">
													<span class="attributionTitle">Pinned by</span>
													<span class="attributionName">Maureen LaMountain</span>
												</a> <a href="/maureenl/every-girl-s-crazy-about-a-sharp-dressed-man/" class="attributionItem lastAttribution">
													<img src="http://media-cache-is0.pinimg.com/upload/121808433610512878_board_thumbnail_2013-05-14-22-57-36_26229_60.jpg" style="" alt="" class="image attributionImg">
													<span class="attributionTitle">onto</span>
													<span class="attributionName">Every girl's crazy about a sharp dressed man</span>
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
		</script>
	</body>
</html>
