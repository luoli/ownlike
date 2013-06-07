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
				<button type="button" data-id="${oc.id}" data-boardName="${oc.boardName }" data-boardId="${oc.boardId }" data-userId="${oc.userId}" class="medium rounded ShowModalButton Button primary Module primaryOnHover btn repin repin_link">
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
				<a target="_blank" href="${oc.image }" type="button" class="website medium rounded NavigateButton Button hasText Module ajax btn">
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
		<div class="ajax CloseupContent Module" style="">
			<div class="moduleMask"></div>
			<div class="detailed ajax Pin Module">
				<div class="pinWrapper">
					<div class="PaddedPin ajax Module">
						<div class="pinImageSourceWrapper">
							<a href="${oc.link }" target="_blank">
								<div class="imageContainer">
									<img src="${oc.image }" alt="" class="image pinImage"/>
								</div>
							</a>
							<div class="sourceFlagClipper">
								<div class="sourceFlagWrapper visible">
									<a href="${oc.link }">${oc.viaUrl }</a>
									<button type="button" class="ShowModalButton Button pinFlag Module ajax borderless"><em></em> &nbsp;</button>
								</div>
							</div>
						</div>
					</div>
					<div class="pinDescription">
						<div class="pinDescriptionComment detailed">
							<a class="userThumbContainer" href="/ownBoard/searchBoardByOwnUser.h?userId=${oc.userId }">
								<img class="userThumb" src="${oc.userImage }" alt="${oc.userName }">
							</a>
							<div class="commenterNameCommentText">
								<div class="commenterWrapper">
									<a class="commentDescriptionCreator" href="/ownBoard/searchBoardByOwnUser.h?userId=${oc.userId }">
										${oc.userName }
									</a>
								</div>
								<p class="commentDescriptionContent">
									${oc.description }
								</p>
							</div>
						</div>
					</div>
					<div class="detailed ajax PinCommentList Module">
						<div class="commentsContainer Empty ">
						</div>
						<div class="pinDescriptionCommentItem pinUserCommentBox">
							<div class="pinDescriptionComment detailed">
								<a class="userThumbContainer" href="/ownBoard/searchBoardByOwnUser.h?userId=${OWNUSERLOGIN.id }">
									<img class="userThumb" src="${OWNUSERLOGIN.image }" alt="${OWNUSERLOGIN.lastName }">
								</a>
								<div class="commenterNameCommentText">
									<div class="commenterWrapper">
										<a class="commentDescriptionCreator" href="/ownBoard/searchBoardByOwnUser.h?userId=${OWNUSERLOGIN.id }">${OWNUSERLOGIN.lastName }</a>
										<span class="commentDescriptionTimeAgo">• That's you! </span>
									</div>
									<form class="addCommentForm">
										<div class="ui-TextField ajax Module">
											<textarea class="content" placeholder="Add a comment..."></textarea>
										</div>
										<button type="button" class="hasText rounded Button primary Module large ajax addComment btn">
											<span class="buttonText">Comment</span>
										</button>
										<input type="hidden" id="id_byUserId" value="${oc.userId }"/>
										<input type="hidden" id="id_clipId" value="${oc.id }"/>
									</form>
								</div>
							</div>
							<c:forEach items="${oc.ownCommentList }" var="v">
								<div class="pinDescriptionComment detailed">
								    <a class="userThumbContainer" href="/ownBoard/searchBoardByOwnUser.h?userId=${v.userId }">
								        <img class="userThumb" src="${v.ownUser.image }" alt="${v.ownUser.lastName }">
								    </a>
								    <div class="commenterNameCommentText">
								        <div class="commenterWrapper">
								        	<a class="commentDescriptionCreator" href="/ownBoard/searchBoardByOwnUser.h?userId=${v.userId }">${v.ownUser.lastName }</a>
								        </div>
								        <p class="commentDescriptionContent">${v.commentText }</p>
								    </div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
