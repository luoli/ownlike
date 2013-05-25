<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML>
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
	<!--[if (gt IE 6)&(lt IE 9)]><link rel="stylesheet" href="/css/ie.css" type="text/css" media="all" /><![endif]-->
	<script type="text/javascript">window.currentUserId=${ownUser.id};</script>
  </head>
  
  <body id="profile">
  	<noscript><div id="noScript"><h1>You need to enable Javascript.</h1></div></noscript>
	<div id=header><%@include file="/view/jsp/include/head.jsp" %></div>
	<div id="ProfileHeader"><%@include file="/view/jsp/owner/include/profileHolder.jsp" %></div>
	<%@include file="/view/jsp/include/headAdd.jsp" %>
	<div id="ContextBar" class="container sticky">
		<jsp:include page="/view/jsp/owner/include/contextBar.jsp">
			<jsp:param value="viewType" name="0"/>
		</jsp:include>
	</div>
    <div id="wrapper" class="boardLayout" style="width: 1170px; opacity: 1; visibility: visible; ">
        <div id="columnContainer">
            <div id="SortableButtons">
                <h2 class="colorless">Rearrange Boards</h2>
                <h3 class="colorless">Drag around your boards to reorder them.</h3>
            </div>
            <ul class="sortable">
            	<c:forEach items="${ownBoards}" var="v">
	                <li>
					    <div class="pin pinBoard" id="${v.id}">
					        <h3 class="serif"><a href="/ownBoard/findBoardInfoAndClip.h?boId=${v.id }">${v.boardName}</a></h3>
					            <h4>
					                ${v.clipNum} pins
					            </h4>
					        <div class="board">
					            <a href="/ownBoard/findBoardInfoAndClip.h?boId=${v.id }" class="link">&nbsp;</a>
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
	<script type="text/javascript" src="/js/comm/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="/js/head/head.js"></script>
	<script type="text/javascript" src="/js/comm/ajaxfileupload.js"></script>
	<script type="text/javascript" src="/js/model.js"></script>
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
  			url = "/userFollow/userFollowing.h";
  		}else if(flag == "0"){
  			url = "/userFollow/userUnFollowing.h"
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
  </script>
  <script type="text/javascript" charset="utf-8">
    BoardLayout.setup();
    /**
    $.pageless.settings.complete = function(){
        BoardLayout.newPins();
    };
    **/
    $(document).ready(function() {
        if (50 > 0) {
            $('#LoadingPins').hide();
        }
        /**var coverSelector = new BoardCoverSelector();**/
        var fancySuccess = function(board, data) {
            var bg = $('#CropImage').find(".bg");
            var frame = $('#CropImage').find(".frame");
            var img = bg.clone().css({
                'overflow':'hidden',
                'width': frame.width(),
                'height': frame.height(),
                'z-index': 99999
            });
            var left = parseInt(bg.css('left')) - parseInt(frame.css('left'));
            var top = parseInt(bg.css('top')) - parseInt(frame.css('top'));
            var offset = frame.offset();
            img.css({
                'top': offset.top,
                'left': offset.left,
                'box-shadow': frame.css('box-shadow')
            });
            img.find('img').css({
                'position': 'absolute',
                'left': left,
                'top': top
            });
            $('body').append(img);
            bg.fadeOut(300);
            setTimeout(function() {
                $('body').removeClass('noscroll')
                var cover = board.find('.cover');
                var endOffset = cover.parents('.board').offset();
                var angle = 25;
                var bezier_params = {
                    start: {
                        x: offset.left,
                        y: offset.top,
                        angle: (offset.left > endOffset.left) ? angle : -angle
                    },
                    end: {
                        x: endOffset.left,
                        y: endOffset.top,
                        angle: (offset.left < endOffset.left) ? angle : -angle
                    }
                }
                var path = new $.path.bezier(bezier_params)
                img.animate({path: path}, 500, function() {
                    cover.empty();
                    setTimeout(function() {
                        cover.append(img);
                        img.css({'position': 'relative', 'top':'0', 'left':'0'});
                        setTimeout(function() {
                            img.css('z-index', 0);
                            bg.show();
                        }, 400);
                    }, 500);
                });
            }, 800);
        }

        $('#ColumnContainer').on('click', '.SetBoardCover', function(e) {
            var board = $(this).parents('.pinBoard');
            var boardName = board.find('h3 a').text()
            var boardURL = board.find('a').attr('href');
            coverSelector.show({
                boardName: boardName,
                boardURL: boardURL,
                delay: 0,
                success: function(data) {
                    fancySuccess(board, data);
                },
                title: "Select a cover photo and drag to position it.",
                
                buttonTitle: "Set Cover"
            });
            return false;
        });

    });

	// Create board for empty profiles
	$('.createBoardSubmit').click(function() {
        var name = $(this).siblings('.FancyContainer').children('input').val();
        var parent = $(this).parents('.createBoard');

        $.post('/board/create/', {
            'name': name
        }, function(data) {
            if (data.status == "success") {
                var board = '';
                board += '<div class="pin pinBoard" id="board' + data.id + '">';
                    board += '<h3 class="serif"><a href="' + data.url + '">' + data.name + '</a></h3>';
                    board += '<h4>0 pins</h4>';
                    board += '<div class="board">';
                        board += '<a href="' + data.url + '" class="link">&nbsp;</a>';
                        board += '<div class="holder">';
                            board += '<span class="cover empty"></span>';
                            board += '<span class="thumbs">';
                                board += '<span class="empty"></span>';
                                board += '<span class="empty"></span>';
                                board += '<span class="empty"></span>';
                                board += '<span class="empty"></span>';
                            board += '</span>';
                        board += '</div>';
                        board += '<div class="followBoard">';
                            board += '<a href="' + data.url + 'settings/" class="Button13 Button WhiteButton InlineButton">Edit</a>';
                        board += '</div>';
                    board += '</div>';
                board += '</div>';

                parent.replaceWith(board);
            }
            if (data.status == "failure") {
                parent.children('.FancyContainer').append(data.message);
            }
        });
	});
</script>
    <div id="SearchAutocompleteHolder"></div>
    <button id="ScrollToTop" class="Button WhiteButton Offscreen Indicator" type="button">
    	Scroll to Top
	</button>
</html>
