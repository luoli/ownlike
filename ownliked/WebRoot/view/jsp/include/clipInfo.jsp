<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="Module Closeup canClose" style="min-height: 333px;">
	<button class="Button borderless close visible" style="right: 31px;">
		<em></em>&nbsp;
	</button>
	<div class="closeupActionBarContainer">
		<div class="PinActionBar ajax Module" style="display: block;">
			<div class="moduleMask"></div>
			<div class="repinLike">
				<button type="button" class="medium rounded ShowModalButton Button primary Module primaryOnHover btn repin">
					<em></em>
					<span class="accessibilityText">Clip it</span>
				</button>
				<button type="button" class="hasText medium rounded NavigateButton Button primary IncrementingNavigateButton ajax repinLikeNavigateButton Module <c:if test="${oc.reclipNum <= 0 }">hidden</c:if> btn">
					<span class="buttonText">${oc.reclipNum }</span>
				</button>
				<c:choose>
					<c:when test="${!empty(oc.ownLikeds) && OWNUSERLOGIN != null}">
						<c:forEach items="${oc.ownLikeds}" var="ol">
							<c:choose>
								<c:when test="${ol.userId == OWNUSERLOGIN.id }">
									<button data-text-unlike="Unlike" data-text-like="Like" data-id="${oc.id}" data-userId="${oc.userId}" type="button" class="medium rounded PinLikeButton Button hasText Module ajax btn like unlikebutton">
										<em></em>
										<span class="buttonText">Unlike</span>
									</button>
								</c:when>
								<c:otherwise>
									<button data-text-unlike="Unlike" data-text-like="Like" data-id="${oc.id}" data-userId="${oc.userId}" type="button" class="medium rounded PinLikeButton Button hasText Module ajax btn like likebutton">
										<em></em>
										<span class="buttonText">Like</span>
									</button>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<button data-text-unlike="Unlike" data-text-like="Like" data-id="${oc.id}" data-userId="${oc.userId}" type="button" class="medium rounded PinLikeButton Button hasText Module ajax btn like likebutton">
							<em></em>
							<span class="buttonText">Like</span>
						</button>
					</c:otherwise>
				</c:choose>
				<button type="button" class="medium rounded NavigateButton Button IncrementingNavigateButton ajax Module repinLikeNavigateButton like <c:if test="${oc.likeNum <= 0 }">hidden</c:if> btn">
					&nbsp;
				</button>
				<a href="${oc.image }" type="button" class="website medium rounded NavigateButton Button hasText Module ajax btn">
					<em></em>
					<span class="buttonText">Website</span>
				</a>
			</div>
			<div class="shareGear">
				<div class="ajax HoverButton Module" data-is-hovering="">
					<button type="button" class="medium rounded Button share hasText Module ajax btn">
						<em></em>
						<span class="buttonText">Share</span>
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="closeupContainer">
		<div class="ajax Module CloseupSidebar" style="">
			<div class="moduleMask"></div>
			<div class="boardPinsGridContainer">
				<div class="boardPinsGrid ajax Board Module">
					<div class="boardHeader">
						<div class="thumb hasText Module ajax boardRepTitle User">
							<a href="/nesspie/things-for-my-house/"> <span
								class="thumbImageWrapper"> <img src="${oc.image }" alt="Profile image of Nessa Pie"> </span>
								<div class="title">
									${oc.boardName }
								</div>
								<span class="fullname">${oc.userName }</span>
							</a>
						</div>
					</div>
					<div class="pinGridWrapper">
						<div class="ajax Grid Module">
							<div class="moduleMask"></div>
							<div class="trackActiveItem padItems Module ajax GridItems variableHeightLayout" style="width: 216px; height: 1496px;">
								<div class="item" tabindex="1" style="top: 0px; left: 0px; visibility: visible;">
									<div class="boardPinsGrid ajax Pin Module">
										<a href="/pin/252834966552963750/" class="boardPinsGridPin">
											<span class="hoverMask"></span>
											<img src="http://media-cache-ec3.pinimg.com/70x/ef/0c/d4/ef0cd43a6fd4702a71be634038ff579d.jpg" style="width: 70px; height: 79px;" alt="" class="image lazy loaded">
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<button type="button" class="hasText btn rounded Button ajax Module BoardFollowButton notNavigatable followBoardButton ui-FollowButton">
						<span class="buttonText">Follow Board</span>
					</button>
				</div>
			</div>
		</div>
		<div class="ajax CloseupContent Module" style="">
			<div class="moduleMask"></div>
			<div class="detailed ajax Pin Module">
				<div class="pinWrapper">
					<div class="PaddedPin ajax Module">
						<div class="pinImageSourceWrapper">
							<a href="http://www.bhg.com/rooms/home-office/accessories/affordable-home-office-decorating/#page=6">
								<div class="imageContainer" style="width: 736px;">
									<img src="http://media-cache-ec2.pinimg.com/736x/c4/9c/2b/c49c2b6ce24a111713d9e6dc94760460.jpg" style="padding: 40px 0px; width: 360px; margin: 0 auto; height: 480px;" alt="" class="image pinImage"/>
								</div>
							</a>
							<div class="sourceFlagClipper">
								<div class="sourceFlagWrapper visible">
									<a href="http://www.bhg.com/rooms/home-office/accessories/affordable-home-office-decorating/#page=6">Visit bhg.com</a>
									<button type="button" class="ShowModalButton Button pinFlag Module ajax borderless"><em></em> &nbsp;</button>
								</div>
							</div>
						</div>
					</div>
					<div class="pinnerViaPinnerAttribution">
						<div class="closeup_bottom_view ajax Board Module">
							<button type="button" class="hasText btn rounded Button ajax Module BoardFollowButton notNavigatable ui-FollowButton">
								<span class="buttonText">Follow</span>
							</button>
							<a href="/nesspie/things-for-my-house/" class="boardLink">
								<div class="shield">
									<div class="shieldImageWrapper shieldImageWrapper1">
										<img src="http://media-cache-ec3.pinimg.com/45x45/ef/0c/d4/ef0cd43a6fd4702a71be634038ff579d.jpg" class="shieldImage1" alt="">
									</div>
									<div class="shieldImageWrapper shieldImageWrapper2">
										<img src="http://media-cache-ak0.pinimg.com/45x45/e0/b7/20/e0b720185137d4571458cb7fdad70be8.jpg" class="shieldImage2" alt="">
									</div>
									<div class="shieldImageWrapper shieldImageWrapper3">
										<img src="http://media-cache-ec4.pinimg.com/45x45/a7/a8/46/a7a846c0741a6a2aa38f8d40a13b8d27.jpg" class="shieldImage3" alt="">
									</div>
									<div class="shieldImageWrapper shieldImageWrapper4">
										<img src="http://media-cache-ec2.pinimg.com/45x45/c6/e8/b8/c6e8b8281f8dfa825b195a926b4e8205.jpg" class="shieldImage4" alt="">
									</div>
								</div>
								<h3 class="boardRepTitle">
									Repinned onto
								</h3>
								<h4 class="boardRepSubtitle">
									things for my house
								</h4>
							</a>
						</div>
						<div class="medium thumb pinner hasText Module ajax User">
							<button type="button"
								class="UserFollowButton btn rounded Button hasText Module ajax notNavigatable ui-FollowButton">
								<span class="buttonText">Follow</span>
							</button>
							<a href="/cpohlner/"> <span class="thumbImageWrapper">
									<img
										src="http://media-cache-ec0.pinimg.com/avatars/cpohlner_1337115831_75.jpg"
										alt="Profile image of Corinne Pohlner"> </span>
								<div class="title">
									Repinned from
								</div> <span class="fullname">Corinne Pohlner</span> </a>
						</div>
					</div>
					<div class="pinDescription">
						<div class="pinDescriptionComment detailed">
							<a class="userThumbContainer" href="/nesspie/"> <img
									class="userThumb"
									src="http://media-cache-ec2.pinimg.com/avatars/nesspie-1364605262_75.jpg"
									alt="Nessa Pie"> </a>
							<div class="commenterNameCommentText">
								<div class="commenterWrapper">
									<a class="commentDescriptionCreator" href="/nesspie/">Nessa
										Pie</a>
									<span class="commentDescriptionTimeAgo">• 25 weeks ago </span>
								</div>
								<p class="commentDescriptionContent">
									Convert a coffee table into a seating area
								</p>
							</div>
						</div>
					</div>
					<div class="detailed ajax PinCommentList Module">
						<div class="commentsContainer Empty ">
						</div>
						<div class="pinDescriptionCommentItem pinUserCommentBox">
							<div class="pinDescriptionComment detailed">
								<a class="userThumbContainer" href="/jianglijia/"> <img
										class="userThumb"
										src="http://media-cache-ec1.pinimg.com/avatars/jianglijia_1342242838_75.jpg"
										alt="Lijia Jiang"> </a>
								<div class="commenterNameCommentText">
									<div class="commenterWrapper">
										<a class="commentDescriptionCreator" href="/jianglijia/">Lijia
											Jiang</a>
										<span class="commentDescriptionTimeAgo">• That's you! </span>
									</div>
									<form class="addCommentForm">
										<div class="ui-TextField ajax Module">
											<textarea class="content" placeholder="Add a comment..."></textarea>
										</div>
										<button type="button"
											class="hasText rounded Button primary Module large ajax addComment btn">
											<span class="buttonText">Comment</span>
										</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
