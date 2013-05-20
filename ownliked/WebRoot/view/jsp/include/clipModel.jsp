<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="flipScroll" class="repinMask visible" style="display: none;">
	<div id="flip" class="flipped" style="left: 683px; top: 280px; ">
		<div class="back face" style="top:-280px; left:-215px;">
			<div class="repinWrapper" style="height: 561px; width: 430px; ">
				<div id="Repin2">
					<div class="PinForm">
						<div class="PinImagePreview pin priceReveal" style="height: 300px; ">
							<div class="PinBorder"></div>
							<img class="PinImagePreviewImg" src="" style="width: 231px; height: 300px;"/>
							<div class="price visible"></div>
						</div>
						<div class="BoardListOverlay"></div>
						<div class="BoardSelector BoardPicker">
							<div class="current">
								<span class="CurrentBoard" data-id="">Create New Board</span>
								<span class="DownArrow"></span>
							</div>
							<div class="BoardList">
								<div class="wrapper">
									<ul>
										
									</ul>
									<div class="CreateBoard">
										<input type="text" placeholder="Create New Board"/>
										<button class="button whiteButton button18 noFloat" type="button">Create</button>
										<div class="CreateBoardStatus"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="InputArea">
							<ul class="form fancyForm">
								<li class="noMarginBottom val">
									<textarea class="DescriptionTextarea" rows="2"></textarea>
									<div class="tagmate-menu" style="position: absolute; display: none; "></div>
									<label>Describe your pin…</label>
									<span class="fff"></span>
								</li>
							</ul>
						</div>
						<div class="CreateBoardStatus error mainerror"></div>
						<div class="buttons">
							<button class="button button18 redButton" type="button">Clip It</button>
							<span class="CharacterCount colorless">500</span>
						</div>
					</div>
					<form action="" method="POST">
						<input type="hidden" id="boardId" name="boardId" value=""/>
						<input type="hidden" id="boardName" name="boardName" value=""/>
						<input type="hidden" id="description" name="description"/>
						<input type="hidden" id="image" name="image"/>
						<input type="hidden" id="userId" name="userId"/>
						<input type="hidden" id="userName" name="userName"/>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<%--	Reclip model	--%>
<div id="PostSuccess" style="display: none;">
	<h2></h2>
	<h2></h2>
	<h3>Also Cliped to:</h3>
	<div class="boardWrapper">
		<h3></h3>
		<h4></h4>
		<div class="followBoard">
			<a href="###" class="link"></a>
			<div class="holder">
				<span class="cover">
				</span>
				<span class="thumbs">
				</span>
			</div>
			<div class="reFollow">
			</div>
		</div>
	</div>
</div>
<%--	ReclipSuccess	--%>
<div id="zoomScroll" class="visible loaded" style="display: none;">
	<div id="zoom" class="pin" style="top: 200px; left:683px;">
		<div id="pinPinner" class="clearfix">
			<a href="###" class="button button13 redButton disabled clickable unfollowuserbutton">Unfollow</a>
			<a href="###" id="pinnerImage" class="imgLink" target="_blank">
				<img src="" alt=""/>
			</a>
			<p id="pinnerName">
				<a href="###" target="_blank">Jayne F</a>
				<span class="colorless" style="font-weight: 300;">via</span>
				<a href="###" target="_blank">Cathy Johnson</a>
			</p>
			<p id="pinnerStats" class="colorless">
				Repinned 1 day ago from 
				<a href="###" target="_blank">Master bathroom Ideas</a>
			</p>
		</div>
		<div id="pinImageHolder">
<%--			<a href="javascript:void[0]" class="pinImage imgLink">--%>
<%--				<img src="" alt="" class="pinImageImg" style="width: 300px; height:357px;"/>--%>
<%--			</a>--%>
			<a href="###" class="pinImage imgLink" rel="nofollow" target="_blank">
				<img src="" alt="" class="pinImageImg" style="width: 300px; height:357px;"/>
			</a>
			<div id="pinActionButtons">
				<a href="###" class="button button13 whiteButton contrastButton repinButton" onclick="trackGAEvent();RepinDialog2.show(''); return false;"><em></em>ReClip</a>
				<a href="###" class="button whiteButton contrastButton button13 zoomLikeButton" data-id=""><em></em>Like</a>
			</div>
		</div>
		<div id="loading">
			<img src="view/images/popular/rotating_pin.png" alt="Loading Animation"/>
		</div>
		<div id="pinButtom">
			<div id="ajaxFooter">
				<p class="description">Old bird bath next to your bathtub to hold bathing items</p>
			</div>
			<div class="pinComments">
				<div class="comment" style="background-color: rgb(255, 255, 255);">
					<a class="deleteComment floatRight tipsyHover" title="Remove Comment" data="">X</a>
					<a href="###" class="commentImage">
						<img src="" alt=""/>
					</a>
					<p class="commentMeta">
						<a href="####" class="commentName"></a><br />@Jayne F aha
					</p>
				</div>
			</div>
			<div id="pinAddComment">
				<div id="pinInputArea">
					<img src="" class="commenterImage" alt=""/>
					<div class="inputContainer">
						<textarea id="closeupComment" name="caption" placeholder="Add a comment...."></textarea>
						<div class="tagmate-menu" style="position: absolute; display: none;">
							<div class="tagmate-menu-option tagmate-menu-option-active">​
								<img src="" alt="">​
								<span>​Jayne F​</span>​
							​</div>
						​</div>
						<div id="pinAddCommentControls">
							<p class="helpText colorlight">
								Type <strong>@</strong> to recommend this pin to another Pinner
							</p>
							<button id="postComment" class="button whiteButton button13 disabled">Post Comment</button>
						</div>​
					​</div>
				</div>
			</div>
			<div class="pinInfo">
				<a href="###" class="button button13 redButton disabled clickable unfollowbutton">Unfollow</a>
				<p class="colorless">Pinned onto the board</p>
				<h3>
					<a href="###" target="_blank">For my Sweet Tooth</a>
				</h3>
				<ul id="boardThumbs">
					<li>
						<a href="###" target="_blank">
							<img src="" alt=""/>
						</a>
					</li>
				</ul>
			</div>
			<div id="zoomSource" class="pinInfo">
				<p class="colorless">
					Pinned via <a href="###" target="_blank" class="colorless">pinmarklet</a> from
				</p>
				<h3>
					<a href="###" target="_blank">passthesushi.com</a>
				</h3>
				<a href="###" class="link" target="_blank">
					<ul id="sourceThumbs">
						<li>
							<img src="" alt=""/>
						</li>
					</ul>
				</a>
			</div>
			<div id="zoomOrigin" class="pinInfo">
				<a href="###" class="button button13 redButton followuserbutton">Follow</a>
				<p class="colorless">Originally pinned by</p>
				<h3>
					<a href="###" target="_blank">Maggie</a>
				</h3>
				<p></p>
				<a href="###" target="_blank">
					<ul id="originThumbs">
						<li>
							<img src="" alt=""/>
						</li>
					</ul>
				</a>
			</div>
		</div>
	</div>
	<div style="height: 0px; margin-top: -10;"></div>
</div>
<%--	zoom clip holder	--%>
