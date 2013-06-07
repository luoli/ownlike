$(function(){
	$("#categoriesBar ul li").eq(userIdLogin ? 1 : 0).hover(
		function(){
			$(this).find("ul").show();
		},
		function(){
			$(this).find("ul").hide();
		}
	);
	$(".pin").hover(
		function(){
			$(".actions", this).show();
		},
		function(){
			$(".actions", this).hide();
		}
	);
	var url = contextPath+"/ownBoard/searchSessionBoard.h";
	$.ajax({
		url : url,
		data : {},
		type : "POST",
		dataType : "json"
	}).done(function(data){
		window.myBoards = data.obList;
	}).fail(function(e){console.log(e);});
});
var WelcomeBoard = function(){
	return {
		init : function(){
			var t = this;
			$(".item .pinImageWrapper").on("click", function(){
				var url = $(this).attr("href");
				var params = {};
				$.ajax({
					url : url,
					data : params,
					dataType : "html",
					type : "POST"
				}).done(function(data){
					document.body.style.overflow = 'hidden';
					$(".appendedContainer").html(data);
				}).fail(function(e){console.log(e);});
				return !1;
			});
			t.commentBtnDis();
			$(document).on("click", ".close", function(){
				document.body.style.overflow = 'scroll';
				$(".appendedContainer").html("");
			});
			$(document).on("click", ".PinActionBar .repin_link", function(){
				var cur = this;
				var url = contextPath+"/ownBoard/searchBoardModule.h";
				params = {};
				$.ajax({
					url : url,
					data : params,
					dataType : "html",
					type : "POST"
				}).done(function(data){
					$(".Module .modalModule").html(data).parents(".Module").show();
					$(".boardPickerOuter .currentBoard").attr("data-id", $(cur).attr("data-boardId"));
					$(".boardPickerOuter .currentBoardName").html($(cur).attr("data-boardName"));
					$("#pinFormDescription").val($(".modalContainer .pinDescription .commentDescriptionContent").html());
					$(".boardPickerOuter").on("click", function(){
						$(".boardPickerInnerWrapper").addClass("visible");
						$(".BoardPicker").append('<div class="boardPickerMask"></div>');
						$(".BoardPicker .boardPickerMask").on("click", function(){
							console.log("BoardPicker click");
							t.closeBoardPicker();
						});
					});
					$(".closeModal").on("click", function(){
						t.closePinForm();
					});
					var pinForm = $(".PinForm");
					pinForm.on("click", function(e){
						console.log("click");
						e.stopPropagation();
						return !1;
					});
					$("#id_PinCreate").on("click", function(e){
						console.log(e);
						if(e.currentTarget.id == "id_PinCreate"){
							t.closePinForm();
						}
					});
					$(".boardPickerItem", pinForm).on("click", function(){
						var boardName = $(this).text();
						var boardId = $(this).attr("data-id");
						$(".boardPickerOuter .currentBoardName", pinForm).html(boardName);
						$(".boardPickerOuter .currentBoard", pinForm).attr("data-id", boardId);
						t.closeBoardPicker();
					});
					$(".createBoardButton", pinForm).on("click", function(){
						var vn = $(".createBoardNameContainer input.createBoardName", pinForm).val();
						console.log(vn);
						if(vn == null || vn == ""){
							
							return;
						}
						var u = this;
						$(u).addClass("disabled");
						var data = vn;
						$.ajax({
							url : contextPath+"/ownBoard/insertOwnBoardByUser.h",
							type : "POST",
							data : {"boardName":data},
							dataType : "json",
							success : function(result){
								if(result.status == "seccess"){
									$(".boardPickerOuter .currentBoardName", pinForm).html(vn);
									$(".boardPickerOuter .currentBoard", pinForm).attr("data-id", result.id);
									t.closeBoardPicker();
								}
							},
							error : function(e){}
						});
					});
					$(".formFooter .cancelButton", pinForm).on("click", function(){
						console.log("cancel");
						t.closePinForm();
					});
				}).fail(function(e){console.log(e);});
			});
		},
		closePinForm : function(){
			$(".PinCreate").remove();
			$(".Module#id_PinCreate").hide();
			$(".boardPickerInnerWrapper").removeClass("visible");
			$(".BoardPicker .boardPickerMask").remove();
		},
		closeBoardPicker : function(){
			$(".boardPickerInnerWrapper").removeClass("visible");
			$(".BoardPicker .boardPickerMask").remove();
		},
		commentBtnDis : function(){
			var t = this;
			$(document).on("focus", ".addCommentForm textarea", function(){
				var btn = $(this).parents("form").find("button");
				btn.show();
				btn.off("click").on("click", function(){
					btn.hide();
					var curTex = $(".addCommentForm textarea");
					var te = $(".addCommentForm");
					if(curTex.val() == ""){
						return;
					}
					t.submitComment(curTex, $("#id_clipId").val(), $("#id_byUserId").val());
				});
			});
		},
		submitComment : function(tex, clipId, byUserId){
			var te = $(".pinUserCommentBox");
			$.ajax({
				url : contextPath+"/ownComment/insertComment.h",
				type : "POST",
				data : {"commentText" : tex.val(), "clipId" : clipId, "byUserId" : byUserId},
				dataType : "json",
				success : function(result){
					if(result.status == "error"){
						console.log("error");
					}else if(result.status == "success"){
						var str = '<div class="pinDescriptionComment detailed">' +
										'<a class="userThumbContainer" href="ownBoard/searchBoardByOwnUser.h?userId='+result.id+'" class="imgLink"><img class="userThumb" src="'+result.image+'" alt="'+result.name+'"/></a>'+
										'<div class="commenterNameCommentText">'+
											'<div class="commenterWrapper">'+
												'<a class="commentDescriptionCreator" href="ownBoard/searchBoardByOwnUser.h?userId='+result.id+'">'+result.name+'</a>'+
											'</div>'+
											'<p class="commentDescriptionContent">'+tex.val()+'</p>'+
										'</div>' +
									'</div>';
						$(te).before(str);
						tex.val("");
					}
				},
				error : function(e){}
			});
		}
	};
}();
var ZoomHolder = function(){
	return {
		openHolder : function(t){
			var z = this;
			z.eventListener();
			$("#zoomScroll").show();
			
		},
		eventListener : function(){
			$("#zoomScroll").bind("click", function(e){
				if(e.target.id == "zoomScroll"){
					$(this).hide();
				}
			});
		}
	};
}();
/**关闭第一层Add模板*/
var Modal = function(){
	return {
		show : function(m){
			$("#" + m).show();
		},
		close : function(m){
			$("#" + m).hide();
		}
	};
}();
/**打开第二(一)层模板  关闭第一(二)层模板*/
var AddDialog = function(){
	return {
		close : function(m, s){
			$("#" + m).hide();
			var t = $("#" + s);
			t.show();
			if(s == "uploadPin"){
				UploadClip.initBind(t);
			}
			if(s == "createBoard"){
				CreateBoardModel.initBind(t);
				CreateBoardModel.submit(t);
			}
		},
		childClose : function(m, s){
			$("#" + m).show();
			$("#" + s).hide();
		}
	}
}();
/**head创建版块*/
var CreateBoardModel = function(){
	return {
		initBind : function(t){
			PinEvent.pinForm(t);
//			PinEvent.showDiv(t);
		},
		submit : function(t){
			var f = $(".form", t);
			$(".submit a", f).click(function(){
				var vn = $("#boardName", f).val();
				var cn = $(".CurrentBoard", f).attr("data-id");
				if(vn == null || vn == ""){
					$(".error", f).html("Please enter a board name");
					return;
				}
				var u = this;
				$(u).addClass("disabled");
				var data = vn;
				$.ajax({
					url : contextPath+"/ownBoard/insertOwnBoardByUser.h",
					type : "POST",
					data : {"boardName":data, "parentId":cn},
					dataType : "json",
					success : function(result){
						if(result.status == "seccess"){
							console.log("seccess");
							//window.location.href="";
						}
					},
					error : function(e){$(".error", f).html("Aha sorry!!!");}
				});
			});
		}
	};
}();
/**上传clip模板*/
var UploadClip = function(){
	return {
		initBind : function(t){
			var u = this;
			var ub = $(".pinBottom", t);
			$("input[type='file']", t).bind("change", function(){
				u.upalod();
				ub.show();
				u.submit();
			});
			PinEvent.pinForm(ub);
			PinEvent.showDiv(ub);
		},
		upalod : function(){
			var u = this;
			$.ajaxFileUpload({
				url : contextPath+"/ownClip/uploadImg.h",
				secureuri : false,
				fileElementId : "upimg",
				dataType : "JSON",
				success : function(data){
					data = eval("("+data+")");
					var path = data.path;
					$("#upImage").val(path);
					$(".pinBottom .images >ul img").attr("src", path);
				},
				error : function(e){console.log(e)}
			});
		},
		submit : function(){
			var t = $("#uploadPin");
			var pt = $(".pinTop", t);
			var pb = $(".pinBottom", t);
			$(".PinForm >.buttons >button", pb).bind("click", function(){
				if(!CheckSession.disable(this)){return false;}
				if(!KeyEvent.checkedText(pb,$("textarea", pb))){return false;}
				var CheBId = $(".BoardSelector >.current >.CurrentBoard", pb).attr("data-id");
				if(null == CheBId || CheBId == ""){return false;}
				$("#description", pt).val($("textarea", pb).val());
				$("#boardName", pt).val($(".BoardSelector >.current >.CurrentBoard", pb).html());
				$("#boardId", pt).val(CheBId);
				$("form", pt).submit();
			});
		}
	}
}();
/**检查用户是否登录*/
var CheckSession = function(){
	return {
		check : function(t, o){
			if(window.userIdLogin){
				o(t);
			}else{
				window.location.href=contextPath+"/view/jsp/user/register.jsp";
			}
		},
		disable : function(t){
			if($(t).hasClass("disabled")){
				return false;
			}
			return true;
		}
	}
}();
/**监听textarea键盘输入事件*/
var KeyEvent = {
	initBind : function(f){
		var t = this;
		/**验证textarea输入多少字符*/
		var p = ".PinForm>.InputArea textarea";
		var pf = $(p, f).bind("focus keyup", function(){
			t.checkedText(f, this);
		});
		pf.focus().select();
	},
	checkedText : function(f, t){
		var b = ".PinForm>.buttons button";
		var numl = 500 - $(t).val().length;
		$(".PinForm >.buttons >.CharacterCount", f).html(numl);
		if(numl >= 500 || numl < 0){
			$(b, f).addClass("disabled");
			$(t).siblings("label").show();
			return false;
		}else{
			$(t).siblings("label").hide();
			$(b, f).removeClass("disabled");
			return true;
		}
	}
}
/**clip操作住方法*/
var PinEvent = function(){
	function reLayout(c){
		var e = $(c).height();
		window.setTimeout(function(){
			e !== $(c).height() && BoardLayout.allPins();
		},1);
	}
	return {
		initBind : function(){
			var p = this;
			$(".repin_link").bind("click", function(){
				CheckSession.check(this, p.openRePin);
			});
			$("#columnContainer").on("click", ".likebutton", function(){CheckSession.check(this, p.likeRePin);});
			$("#columnContainer").on("click", ".unlikebutton", function(){CheckSession.check(this, p.unLike);});
			$(".button.comment").bind("click", function(){
				CheckSession.check(this, p.commentRePin);
			});
			$("#search .lg").on("click", function(){p.searchKeyword();console.log("click");return !1;});
			$("#search input").on("keydown", function(e){
				if(e.keyCode == 13){
					p.searchKeyword();
					return !1;
				}
			});
		  	/**执行方法*/
		  	function userFollow(t, flag){
		  		var url;
		  		if(flag == "1"){
		  			url = contextPath+"/userFollow/userFollowing.h";
		  		}else if(flag == "0"){
		  			url = contextPath+"/userFollow/userUnFollowing.h"
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
			/**添加关注事件*/
		  	$(document).on("click", ".followbutton", function(){
		  		console.log("click");
		  		var t = $(this);
		  		userFollow(t, "1");
		  		t.html(t.attr("data-text-unfollow"));
		  		t.removeClass("followbutton").addClass("unfollowbutton disabled clickable");
		  	});
		  	/**解除关注*/
		  	$(document).on("click", ".unfollowbutton", function(){
		  		console.log("ca click");
		  		var t = $(this);
		  		userFollow($(this), "0");
		  		t.html(t.attr("data-text-follow"));
		  		t.removeClass("unfollowbutton disabled clickable").addClass("followbutton");
		  	});
		},
		showDiv : function(f){
			console.log(myBoards);
			/**组装转载clip显示图层*/
			var result = myBoards;
			if(myBoards && typeof(myBoards) != "object"){
				result = myBoards = eval("("+myBoards+")");
			}
			if(result.length <= 0){
				return false;
			}
			var currentBoard = result[0].boardName;
			var boardList = "";
			for(var i = 0; i < result.length; i++){
				boardList += '<li>'+
								'<span data-id="'+result[i].id+'">'+result[i].boardName+'</span>'+
							'</li>';
			}
			$(".BoardSelector .CurrentBoard", f).html(currentBoard).attr("data-id", result[0].id);
			$(".BoardSelector >.BoardList ul", f).html(boardList);
		},
		eventListener : function(f){
			/**监听转载图层关闭事件*/
			$("body").unbind("click");
			$("body").bind("click", function(e){
				if($(f)){
					if(e.target.id == "flipScroll"){
						$(f).hide();
						$(".BoardSelector >.BoardList").hide();
						$(".BoardListOverlay", f).hide();
						$("body").unbind("click");
					}else{
						e.stopPropagation();
					}
				}
			});
		},
		openRePin : function(t){	/**打开转载clip显示图层，及相应事件绑定主方法*/
			var b = this;
			var did = $(t).attr("data-id");
			var f = $("#flipScroll");
			f.show();
			$(".PinForm >.buttons >button", f).removeClass("disabled");
			$("#boardId", f).val($(t).parents(".pin").find(".convo").find("a").eq(2).attr("data-id"));
			$("#boardName", f).val($(t).parents(".pin").find(".convo").find("a").eq(2).html());
			$("#description", f).val($(t).parents(".pin").find(".description").html());
			$("#image", f).val($(t).parents(".pinHolder").find("img").attr("src"));
			$("#userId", f).val($(t).parents(".pin").find(".convo").find("a").eq(1).attr("data-id"));
			$("#userName", f).val($(t).parents(".pin").find(".convo").find("a").eq(1).html());
			$.getJSON(contextPath+"/ownClip/findClipByReClip.h", {"id" : did}, function(e){
				PinEvent.showDiv(f);
				var img = e["ownClip"].image;
				var description = e["ownClip"].description;
				$(".PinForm >.PinImagePreview >img", f).attr("src", img);
				$(".PinForm >.InputArea textarea", f).val(description);
				PinEvent.eventListener(f);
				PinEvent.pinForm(f);
				PinEvent.rePin(f, t);
			});
		},
		pinForm : function(f){
			KeyEvent.initBind(f);
			var bo = $(".BoardListOverlay", f);
			bo.bind("click", function(e){
				$(".BoardSelector >.BoardList" ,f).hide();
				$(this).hide();
			});
			$(".BoardSelector" ,f).bind("click", function(e){
				$(this).find(".BoardList").show();
				bo.show();
			});
			/**选择board*/
			$(".BoardSelector >.BoardList", f).delegate("li", "click", function(){
				setTimeout(function(){$(".BoardSelector >.BoardList" ,f).hide();bo.hide();},0);
				var boardId = $(this).find("span").attr("data-id");
				var boardName = $(this).find("span").html();
				$(".BoardSelector >.current >.CurrentBoard", f).html(boardName).attr("data-id", boardId);
			});
			/**创建板块*/
			$(".CreateBoard :input[type='button']", f).bind("click", function(){
				var t = this;
				$(t).addClass("disabled");
				var data = $(this).siblings("input").val();
				$.ajax({
					url : contextPath+"/ownBoard/insertOwnBoardByUser.h",
					type : "POST",
					data : {"boardName":data},
					success : function(result){
						console.log("result: "+result);
						if(result.status == "seccess"){
							myBoards.push({"boardName":"'"+result.boardName+"'", "id":result.id});
							console.log(myBoards);
							$(".current>.CurrentBoard", f).html(result.boardName).attr("data-id", result.id);
							var bl = $(".current", f).siblings(".BoardList");
							bl.hide();
							bo.hide();
							bl.find("ul").append("<li><span data-id='"+result.id+"'>"+result.boardName+"</span></li>");
							$(t).removeClass("disabled");
						}
					},
					error : function(e){}
				});
			});
		},
		rePin : function(f, t){
			/**转夹clip*/
			$(".PinForm >.buttons >button", f).bind("click", function(){
				if($(this).hasClass("disabled")){
					return;
				}
				$(this).addClass("disabled");
				var boardId = $("#flipScroll .PinForm >.BoardSelector .CurrentBoard").attr("data-id");
				var boardName = $("#flipScroll .PinForm >.BoardSelector .CurrentBoard").html();
				var description = $("#flipScroll .PinForm>.InputArea textarea").val();
				var image = $("#flipScroll #image").val();
				var reUserId = $("#flipScroll #userId").val();
				var data = {"boardId":boardId, "boardName":boardName, "description":description, "image":image, "previousId":$(t).attr("data-id") };
				$.ajax({
					url : contextPath+"/ownClip/reClip.h",
					type : "POST",
					data : data,
					dataType : "json",
					success : function(result){
						if(result.status == "success"){
							var rp = $("#Repin2");
							var pinForm = rp.html();
							var curb = $(".CurrentBoard", rp);
							var posts = $("#PostSuccess");
							posts.find("h2").eq(0).html('Recliped to <a href="'+curb.attr("data-id")+'">'+curb.html()+'</a>');
							posts.find("h2").eq(1).html('Shared with your followers. <a href="">See it now</a>');
							console.log("result: " + result);
							$(".boardWrapper", posts).find("h3").eq(0).html('<a href="'+contextPath+'/ownBoard/findBoardInfoAndClip.h?boId='+result.paramOwnClip.boardId+'">'+result.paramOwnClip.boardName+'</a>');
							$(".boardWrapper", posts).find("h4").eq(0).html('<a href='+contextPath+'"/ownBoard/searchBoardByOwnUser.h?userId='+result.paramOwnClip.userId+'"><img src="'+result.paramOwnClip.image+'" style="width: 15px; height:15px;"/></a><a href="'+contextPath+'/ownBoard/searchBoardByOwnUser.h?userId='+result.paramOwnClip.userId+'">'+result.paramOwnClip.userName+'</a>');
							for(var i=0; i<result.ownClips.length; i++){
								var v = result.ownClips[i];
								if(i == 0){
									$(".cover", posts).html('<img src="'+v.image+'"/>');
								}else{
									$(".thumbs", posts).append('<img src="'+v.image+'"/>');
								}
							}
							if(result.isFollow > 0){
								$(".reFollow", posts).html('<a class="button button13 whiteButton disabled clickable unfollowbutton InlineButton" data-text-follow="Follow" data-text-unfollow="Unfollow" href="javascript:void(0);">Unfollow</a>');
							}else{
								$(".reFollow", posts).html('<a class="button button13 whiteButton followbutton InlineButton" data-text-follow="Follow" data-text-unfollow="Unfollow" href="javascript:void(0);">Follow</a>');
							}
							$("#Repin2").html(posts.html());
							setTimeout(function(){
								$("#flipScroll").hide();
								rp.html(pinForm);
							}, 3000);
							if(result){
								var ts = $(t).parents(".pin");
								if($(".repinsCount", ts).html()){
									var re = $(".repinsCount >em", ts);
									var num = re.html();
									var v = 1 + parseInt(num);
									re.html(v);
								}else{
									reLayout(ts);
									var reText = '<span class="repinsCount"><em>'+1+'</em>&nbsp;&nbsp;转夹</span>';
									ts.find(".stats").append(reText);
								}
							}
						}else{
							$("#flipScroll").hide();
						}
					},
					error : function(e){}
				});
			});
		},
		likeRePin : function(t){	/**like clip 操作方法*/
			console.log("likeRePin data-userId: "+$(t).attr("data-userId"));
			$.ajax({
				url : contextPath+"/ownClip/likeClip.h",
				type : "POST",
				data : {"id":$(t).attr("data-id"),"userId":$(t).attr("data-userId"), "like" : 1},
				success : function(result){
					if(result > 0){
						var ts = $(t).parents(".pin");
						var stats = ts.children(".stats");
						reLayout(stats);
						if($(".likesCount", ts).html()){
							var like = $(".likesCount >em", ts);
							var num = like.html();
							var v = parseInt(num) + 1;
							like.html(v);
						}else{
							var likeText = $('<span class="likesCount"><em>'+1+'</em>&nbsp;&nbsp;喜欢</span>');
							stats.prepend(likeText);
						}
						$(t).removeClass("likebutton")
						.addClass("disabled unlikebutton").html("Unlike");
					}
				},
				error : function(e){console.log(e);}
			});
		},
		unLike : function(t){console.log("data-userId: "+$(t).attr("data-userId"));
			$.ajax({
				url : contextPath+"/ownClip/likeClip.h",
				type : "POST",
				data : {"id":$(t).attr("data-id"), "userId":$(t).attr("data-userId"), "like" : -1},
				success : function(result){
					if(result > 0){
						var ts = $(t).parents(".pin");
						var stats = ts.children(".stats");
						reLayout(stats);
						var like = $(".likesCount >em", ts);
						var num = like.html();
						var v = parseInt(num) - 1;
						like.html(v);
						$(t).removeClass("disabled unlikebutton")
						.addClass("likebutton").html("<em></em>Like");
					}
				},
				error : function(e){}
			});
		},
		commentRePin : function(t){
			var ts = $(t).parents(".pin");
			$(t).toggleClass("disabled");
			reLayout(ts);
			var te = $(".write", ts).toggle();
			var tex = $("textarea", te);
			tex.focus();
			$(".grid_comment_button", ts).off("click").on("click", function(){
				var text = tex.val();
				$.ajax({
					url : contextPath+"/ownComment/insertComment.h",
					type : "POST",
					data : {"commentText" : text, "clipId" : $(t).attr("data-id"), "byUserId" : $(t).attr("data-userId")},
					success : function(result){
						tex.val("");
						result = eval("("+result+")");
						if(result.status == "success"){
							var str = '<div class="comments colormuted">' +
											'<div class="comment convo clearfix">'+
												'<a href="ownBoard/searchBoardByOwnUser.h?userId='+result.id+'" class="imgLink"><img class="profile user_image" src="'+result.image+'" alt="'+result.name+'"/></a>'+
												'<p><a href="ownBoard/searchBoardByOwnUser.h?userId='+result.id+'">'+result.name+'</a>&nbsp;'+text+'</p>'+
											'</div>' +
										'</div>';
							var cc = $(".commentsCount", ts);
							if(cc.html()){
								var count = $("em", cc).html();
								$("em", cc).html(parseInt(count) + 1);
							}else{
								var stats = ts.children(".stats");
								var commentHtml = '<span class="commentsCount"><em>1</em>&nbsp;&nbsp;评论</span>';
								stats.append(commentHtml);
							}
							reLayout(ts);
							te.before(str);
						}
					},
					error : function(e){}
				});
			});
		},
		searchKeyword : function(){
			//$("#search form").submit();
			console.log($("#search input").val());
			var url = $("#search form").attr("action")+encodeURIComponent($("#search input").val());
			console.log(url);
			window.location.href=url;
		}
	};
}();
/**------------------------------------------------------------------------------------------*/
/**判断用户登录终端*/
var useLazyLoad = !window.navigator.userAgent.match(/ipad.*OS 4_/gi);
/**pinterest copy方法，处理clip 页面排版*/
(function () {
    var c = this,
        e = c._,
        g = {},
        f = Array.prototype,
        d = Object.prototype,
        h = f.slice,
        j = f.unshift,
        k = d.toString,
        l = d.hasOwnProperty,
        r = f.forEach,
        u = f.map,
        o = f.reduce,
        m = f.reduceRight,
        q = f.filter,
        v = f.every,
        w = f.some,
        B = f.indexOf,
        D = f.lastIndexOf;
    d = Array.isArray;
    var I = Object.keys,
        p = Function.prototype.bind,
        n = function (s) {
            return new G(s)
        };
    if (typeof exports !== "undefined") {
        if (typeof module !== "undefined" && module.exports) exports = module.exports = n;
        exports._ = n
    } else c._ = n;
    n.VERSION = "1.3.1";
    var z = n.each = n.forEach = function (s, x, y) {
            if (s != null) if (r && s.forEach === r) s.forEach(x, y);
            else if (s.length === +s.length) for (var C = 0, J = s.length; C < J; C++) {
                if (C in s && x.call(y, s[C], C, s) === g) return
            } else for (C in s) if (n.has(s, C)) if (x.call(y, s[C], C, s) === g) return
        };
    n.map = n.collect = function (s, x, y) {
        var C = [];
        if (s == null) return C;
        if (u && s.map === u) return s.map(x, y);
        z(s, function (J, L, M) {
            C[C.length] = x.call(y, J, L, M)
        });
        if (s.length === +s.length) C.length = s.length;
        return C
    };
    n.reduce = n.foldl = n.inject = function (s, x, y, C) {
        var J = arguments.length > 2;
        if (s == null) s = [];
        if (o && s.reduce === o) {
            if (C) x = n.bind(x, C);
            return J ? s.reduce(x, y) : s.reduce(x)
        }
        z(s, function (L, M, O) {
            if (J) y = x.call(C, y, L, M, O);
            else {
                y = L;
                J = true
            }
        });
        if (!J) throw new TypeError("Reduce of empty array with no initial value");
        return y
    };
    n.reduceRight = n.foldr = function (s, x, y, C) {
        var J = arguments.length > 2;
        if (s == null) s = [];
        if (m && s.reduceRight === m) {
            if (C) x = n.bind(x, C);
            return J ? s.reduceRight(x, y) : s.reduceRight(x)
        }
        var L = n.toArray(s).reverse();
        if (C && !J) x = n.bind(x, C);
        return J ? n.reduce(L, x, y, C) : n.reduce(L, x)
    };
    n.find = n.detect = function (s, x, y) {
        var C;
        A(s, function (J, L, M) {
            if (x.call(y, J, L, M)) {
                C = J;
                return true
            }
        });
        return C
    };
    n.filter = n.select = function (s, x, y) {
        var C = [];
        if (s == null) return C;
        if (q && s.filter === q) return s.filter(x, y);
        z(s, function (J, L, M) {
            if (x.call(y, J, L, M)) C[C.length] = J
        });
        return C
    };
    n.reject = function (s, x, y) {
        var C = [];
        if (s == null) return C;
        z(s, function (J, L, M) {
            x.call(y, J, L, M) || (C[C.length] = J)
        });
        return C
    };
    n.every = n.all = function (s, x, y) {
        var C = true;
        if (s == null) return C;
        if (v && s.every === v) return s.every(x, y);
        z(s, function (J, L, M) {
            if (!(C = C && x.call(y, J, L, M))) return g
        });
        return C
    };
    var A = n.some = n.any = function (s, x, y) {
            x || (x = n.identity);
            var C = false;
            if (s == null) return C;
            if (w && s.some === w) return s.some(x, y);
            z(s, function (J, L, M) {
                if (C || (C = x.call(y, J, L, M))) return g
            });
            return !!C
        };
    n.include = n.contains = function (s, x) {
        var y = false;
        if (s == null) return y;
        if (B && s.indexOf === B) return s.indexOf(x) != -1;
        return y = A(s, function (C) {
            return C === x
        })
    };
    n.invoke = function (s, x) {
        var y = h.call(arguments, 2);
        return n.map(s, function (C) {
            return (n.isFunction(x) ? x || C : C[x]).apply(C, y)
        })
    };
    n.pluck = function (s, x) {
        return n.map(s, function (y) {
            return y[x]
        })
    };
    n.max = function (s, x, y) {
        if (!x && n.isArray(s)) return Math.max.apply(Math, s);
        if (!x && n.isEmpty(s)) return -Infinity;
        var C = {
            computed: -Infinity
        };
        z(s, function (J, L, M) {
            L = x ? x.call(y, J, L, M) : J;
            L >= C.computed && (C = {
                value: J,
                computed: L
            })
        });
        return C.value
    };
    n.min = function (s, x, y) {
        if (!x && n.isArray(s)) return Math.min.apply(Math, s);
        if (!x && n.isEmpty(s)) return Infinity;
        var C = {
            computed: Infinity
        };
        z(s, function (J, L, M) {
            L = x ? x.call(y, J, L, M) : J;
            L < C.computed && (C = {
                value: J,
                computed: L
            })
        });
        return C.value
    };
    n.shuffle = function (s) {
        var x = [],
            y;
        z(s, function (C, J) {
            if (J == 0) x[0] = C;
            else {
                y = Math.floor(Math.random() * (J + 1));
                x[J] = x[y];
                x[y] = C
            }
        });
        return x
    };
    n.sortBy = function (s, x, y) {
        return n.pluck(n.map(s, function (C, J, L) {
            return {
                value: C,
                criteria: x.call(y, C, J, L)
            }
        }).sort(function (C, J) {
            C = C.criteria;
            J = J.criteria;
            return C < J ? -1 : C > J ? 1 : 0
        }), "value")
    };
    n.groupBy = function (s, x) {
        var y = {},
            C = n.isFunction(x) ? x : function (J) {
                return J[x]
            };
        z(s, function (J, L) {
            L = C(J, L);
            (y[L] || (y[L] = [])).push(J)
        });
        return y
    };
    n.sortedIndex = function (s, x, y) {
        y || (y = n.identity);
        for (var C = 0, J = s.length; C < J;) {
            var L = C + J >> 1;
            y(s[L]) < y(x) ? (C = L + 1) : (J = L)
        }
        return C
    };
    n.toArray = function (s) {
        if (!s) return [];
        if (s.toArray) return s.toArray();
        if (n.isArray(s)) return h.call(s);
        if (n.isArguments(s)) return h.call(s);
        return n.values(s)
    };
    n.size = function (s) {
        return n.toArray(s).length
    };
    n.first = n.head = function (s, x, y) {
        return x != null && !y ? h.call(s, 0, x) : s[0]
    };
    n.initial = function (s, x, y) {
        return h.call(s, 0, s.length - (x == null || y ? 1 : x))
    };
    n.last = function (s, x, y) {
        return x != null && !y ? h.call(s, Math.max(s.length - x, 0)) : s[s.length - 1]
    };
    n.rest = n.tail = function (s, x, y) {
        return h.call(s, x == null || y ? 1 : x)
    };
    n.compact = function (s) {
        return n.filter(s, function (x) {
            return !!x
        })
    };
    n.flatten = function (s, x) {
        return n.reduce(s, function (y, C) {
            if (n.isArray(C)) return y.concat(x ? C : n.flatten(C));
            y[y.length] = C;
            return y
        }, [])
    };
    n.without = function (s) {
        return n.difference(s, h.call(arguments, 1))
    };
    n.uniq = n.unique = function (s, x, y) {
        y = y ? n.map(s, y) : s;
        var C = [];
        n.reduce(y, function (J, L, M) {
            if (0 == M || (x === true ? n.last(J) != L : !n.include(J, L))) {
                J[J.length] = L;
                C[C.length] = s[M]
            }
            return J
        }, []);
        return C
    };
    n.union = function () {
        return n.uniq(n.flatten(arguments, true))
    };
    n.intersection = n.intersect = function (s) {
        var x = h.call(arguments, 1);
        return n.filter(n.uniq(s), function (y) {
            return n.every(x, function (C) {
                return n.indexOf(C, y) >= 0
            })
        })
    };
    n.difference = function (s) {
        var x = n.flatten(h.call(arguments, 1));
        return n.filter(s, function (y) {
            return !n.include(x, y)
        })
    };
    n.zip = function () {
        for (var s = h.call(arguments), x = n.max(n.pluck(s, "length")), y = new Array(x), C = 0; C < x; C++) y[C] = n.pluck(s, "" + C);
        return y
    };
    n.indexOf = function (s, x, y) {
        if (s == null) return -1;
        var C;
        if (y) {
            y = n.sortedIndex(s, x);
            return s[y] === x ? y : -1
        }
        if (B && s.indexOf === B) return s.indexOf(x);
        y = 0;
        for (C = s.length; y < C; y++) if (y in s && s[y] === x) return y;
        return -1
    };
    n.lastIndexOf = function (s, x) {
        if (s == null) return -1;
        if (D && s.lastIndexOf === D) return s.lastIndexOf(x);
        for (var y = s.length; y--;) if (y in s && s[y] === x) return y;
        return -1
    };
    n.range = function (s, x, y) {
        if (arguments.length <= 1) {
            x = s || 0;
            s = 0
        }
        y = arguments[2] || 1;
        for (var C = Math.max(Math.ceil((x - s) / y), 0), J = 0, L = new Array(C); J < C;) {
            L[J++] = s;
            s += y
        }
        return L
    };
    var E = function () {};
    n.bind = function (s, x) {
        var y, C;
        if (s.bind === p && p) return p.apply(s, h.call(arguments, 1));
        if (!n.isFunction(s)) throw new TypeError;
        C = h.call(arguments, 2);
        return y = function () {
            if (!(this instanceof y)) return s.apply(x, C.concat(h.call(arguments)));
            E.prototype = s.prototype;
            var J = new E,
                L = s.apply(J, C.concat(h.call(arguments)));
            if (Object(L) === L) return L;
            return J
        }
    };
    n.bindAll = function (s) {
        var x = h.call(arguments, 1);
        if (x.length == 0) x = n.functions(s);
        z(x, function (y) {
            s[y] = n.bind(s[y], s)
        });
        return s
    };
    n.memoize = function (s, x) {
        var y = {};
        x || (x = n.identity);
        return function () {
            var C = x.apply(this, arguments);
            return n.has(y, C) ? y[C] : (y[C] = s.apply(this, arguments))
        }
    };
    n.delay = function (s, x) {
        var y = h.call(arguments, 2);
        return setTimeout(function () {
            return s.apply(s, y)
        }, x)
    };
    n.defer = function (s) {
        return n.delay.apply(n, [s, 1].concat(h.call(arguments, 1)))
    };
    n.throttle = function (s, x) {
        var y, C, J, L, M, O = n.debounce(function () {
            M = L = false
        }, x);
        return function () {
            y = this;
            C = arguments;
            var S = function () {
                    J = null;
                    M && s.apply(y, C);
                    O()
                };
            J || (J = setTimeout(S, x));
            if (L) M = true;
            else s.apply(y, C);
            O();
            L = true
        }
    };
    n.debounce = function (s, x) {
        var y;
        return function () {
            var C = this,
                J = arguments;
            clearTimeout(y);
            y = setTimeout(function () {
                y = null;
                s.apply(C, J)
            }, x)
        }
    };
    n.once = function (s) {
        var x = false,
            y;
        return function () {
            if (x) return y;
            x = true;
            return y = s.apply(this, arguments)
        }
    };
    n.wrap = function (s, x) {
        return function () {
            var y = [s].concat(h.call(arguments, 0));
            return x.apply(this, y)
        }
    };
    n.compose = function () {
        var s = arguments;
        return function () {
            for (var x = arguments, y = s.length - 1; y >= 0; y--) x = [s[y].apply(this, x)];
            return x[0]
        }
    };
    n.after = function (s, x) {
        if (s <= 0) return x();
        return function () {
            if (--s < 1) return x.apply(this, arguments)
        }
    };
    n.keys = I ||
    function (s) {
        if (s !== Object(s)) throw new TypeError("Invalid object");
        var x = [];
        for (var y in s) if (n.has(s, y)) x[x.length] = y;
        return x
    };
    n.values = function (s) {
        return n.map(s, n.identity)
    };
    n.functions = n.methods = function (s) {
        var x = [];
        for (var y in s) n.isFunction(s[y]) && x.push(y);
        return x.sort()
    };
    n.extend = function (s) {
        z(h.call(arguments, 1), function (x) {
            for (var y in x) s[y] = x[y]
        });
        return s
    };
    n.defaults = function (s) {
        z(h.call(arguments, 1), function (x) {
            for (var y in x) if (s[y] == null) s[y] = x[y]
        });
        return s
    };
    n.clone = function (s) {
        if (!n.isObject(s)) return s;
        return n.isArray(s) ? s.slice() : n.extend({}, s)
    };
    n.tap = function (s, x) {
        x(s);
        return s
    };
    n.isEqual = function (s, x) {
        return b(s, x, [])
    };
    n.isEmpty = function (s) {
        if (n.isArray(s) || n.isString(s)) return s.length === 0;
        for (var x in s) if (n.has(s, x)) return false;
        return true
    };
    n.isElement = function (s) {
        return !!(s && s.nodeType == 1)
    };
    n.isArray = d ||
    function (s) {
        return k.call(s) == "[object Array]"
    };
    n.isObject = function (s) {
        return s === Object(s)
    };
    n.isArguments = function (s) {
        return k.call(s) == "[object Arguments]"
    };
    if (!n.isArguments(arguments)) n.isArguments = function (s) {
        return !!(s && n.has(s, "callee"))
    };
    n.isFunction = function (s) {
        return k.call(s) == "[object Function]"
    };
    n.isString = function (s) {
        return k.call(s) == "[object String]"
    };
    n.isNumber = function (s) {
        return k.call(s) == "[object Number]"
    };
    n.isNaN = function (s) {
        return s !== s
    };
    n.isBoolean = function (s) {
        return s === true || s === false || k.call(s) == "[object Boolean]"
    };
    n.isDate = function (s) {
        return k.call(s) == "[object Date]"
    };
    n.isRegExp = function (s) {
        return k.call(s) == "[object RegExp]"
    };
    n.isNull = function (s) {
        return s === null
    };
    n.isUndefined = function (s) {
        return s === void 0
    };
    n.has = function (s, x) {
        return l.call(s, x)
    };
    n.noConflict = function () {
        c._ = e;
        return this
    };
    n.identity = function (s) {
        return s
    };
    n.times = function (s, x, y) {
        for (var C = 0; C < s; C++) x.call(y, C)
    };
    n.escape = function (s) {
        return ("" + s).replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#x27;").replace(/\//g, "&#x2F;")
    };
    n.mixin = function (s) {
        z(n.functions(s), function (x) {
            N(x, n[x] = s[x])
        })
    };
    var F = 0;
    n.uniqueId = function (s) {
        var x = F++;
        return s ? s + x : x
    };
    n.templateSettings = {
        evaluate: /<%([\s\S]+?)%>/g,
        interpolate: /<%=([\s\S]+?)%>/g,
        escape: /<%-([\s\S]+?)%>/g
    };
    var Q = /.^/,
        H = function (s) {
            return s.replace(/\\\\/g, "\\").replace(/\\'/g, "'")
        };
    n.template = function (s, x) {
        var y = n.templateSettings;
        s = "var __p=[],print=function(){__p.push.apply(__p,arguments);};with(obj||{}){__p.push('" + s.replace(/\\/g, "\\\\").replace(/'/g, "\\'").replace(y.escape || Q, function (J, L) {
            return "',_.escape(" + H(L) + "),'"
        }).replace(y.interpolate || Q, function (J, L) {
            return "'," + H(L) + ",'"
        }).replace(y.evaluate || Q, function (J, L) {
            return "');" + H(L).replace(/[\r\n\t]/g, " ") + ";__p.push('"
        }).replace(/\r/g, "\\r").replace(/\n/g, "\\n").replace(/\t/g, "\\t") + "');}return __p.join('');";
        var C = new Function("obj", "_", s);
        if (x) return C(x, n);
        return function (J) {
            return C.call(this, J, n)
        }
    };
    n.chain = function (s) {
        return n(s).chain()
    };
    var G = function (s) {
            this._wrapped = s
        };
    n.prototype = G.prototype;
    var K = function (s, x) {
            return x ? n(s).chain() : s
        },
        N = function (s, x) {
            G.prototype[s] = function () {
                var y = h.call(arguments);
                j.call(y, this._wrapped);
                return K(x.apply(n, y), this._chain)
            }
        };
    n.mixin(n);
    z(["pop", "push", "reverse", "shift", "sort", "splice", "unshift"], function (s) {
        var x = f[s];
        G.prototype[s] = function () {
            var y = this._wrapped;
            x.apply(y, arguments);
            var C = y.length;
            if ((s == "shift" || s == "splice") && C === 0) delete y[0];
            return K(y, this._chain)
        }
    });
    z(["concat", "join", "slice"], function (s) {
        var x = f[s];
        G.prototype[s] = function () {
            return K(x.apply(this._wrapped, arguments), this._chain)
        }
    });
    G.prototype.chain = function () {
        this._chain = true;
        return this
    };
    G.prototype.value = function () {
        return this._wrapped
    }
}).call(this);
var BoardLayout = function () {
    return {
        setup: function (b) {
            if (!this.setupComplete) {
                this.setupFlow();
                $(function () {
                    if (window.userIsAuthenticated) {
                        Like.gridListeners();
                        Follow.listeners();
                        Comment.gridComment();
                        RepinDialog2.setup()
                    }
                });
                this.center = !! b;
                this.setupComplete = true
            }
        },
        setupFlow: function (b) {
            if (!this.flowSetupComplete) {
                BoardLayout.allPins();
                b || $(window).resize(_.throttle(function () {
                    BoardLayout.allPins()
                }, 200));
                this.flowSetupComplete = true
            }
        },
        pinsContainer: ".boardLayout",
        pinArray: [],
        orderedPins: [],
        mappedPins: {},
        nextPin: function (b) {
            b = this.orderedPins.indexOf(b) + 1;
            if (b >= this.orderedPins.length) return 0;
            return this.orderedPins[b]
        },
        previousPin: function (b) {
            b = this.orderedPins.indexOf(b) - 1;
            if (b >= this.orderedPins.length) return 0;
            return this.orderedPins[b]
        },
        columnCount: 4,
        columns: 0,
        columnWidthInner: 192,
        columnMargin: 15,
        columnPadding: 30,
        columnContainerWidth: 0,
        allPins: function () {
            var b = $(this.pinsContainer + " .pin"),
                c = this.getContentArea();
            this.columnWidthOuter = this.columnWidthInner + this.columnMargin + this.columnPadding;
            if($(".padItems").length > 0){
            	this.columnWidthOuter += 15;
            }
            this.columns = Math.max(this.columnCount, parseInt(c / this.columnWidthOuter));
            $("body").addClass("columns-"+this.columns-1);
            if (b.length < this.columns) this.columns = Math.max(this.columnCount, b.length);
            c = this.columnWidthOuter * this.columns - this.columnMargin;
            var e = document.getElementById("wrapper");
            if (e) e.style.width = c + "px";
            $(".liquidContainer").css("width", c + "px");
            $(".BoardInfoBar").css("width", c + "px");
            for (c = 0; c < this.columns; c++) this.pinArray[c] = 0;
            document.getElementById("SortableButtons") ? this.showPins() : this.flowPins(b, true);
            if ($("#columnContainer .pin").length === 0 && window.location.pathname === "/") {
                $("#columnContainer").addClass("empty");
                /*setTimeout(function () {
                    window.location.reload()
                }, 5E3)*/
            }
        },
        newPins: function () {
            var b = window.jQuery ? ":last" : ":last-of-type",
                c = $(this.pinsContainer + b + " .pin");
            c = c.length > 0 ? c : $(this.pinsContainer + b + " .pin");
            this.flowPins(c)
        },
        flowPins: function (b, c) {
            if (c) {
                this.mappedPins = {};
                this.orderedPins = []
            }
            if (this.pinArray.length > this.columns) this.pinArray = this.pinArray.slice(0, this.columns);
            for (i = 0; i < b.length; i++) this.positionPin(b[i]);
            this.updateContainerHeight();
            this.showPins();
            window.useLazyLoad && LazyLoad.invalidate()
        },
        positionPin: function (b) {
            var c = $(b).attr("data-id");
            if (c && this.mappedPins[c]) $(b).remove();
            else {
                var e = _.indexOf(this.pinArray, Math.min.apply(Math, this.pinArray)),
                    g = this.shortestColumnTop = this.pinArray[e];
                b.style.top = g + "px";
                b.style.left = e * this.columnWidthOuter + "px";
                b.setAttribute("data-col", e);
                this.pinArray[e] = g + b.offsetHeight + this.columnMargin;
                this.mappedPins[c] = this.orderedPins.length;
                this.orderedPins.push(c)
            }
        },
        showPins: function () {
            $.browser.msie && parseInt($.browser.version) == 7 || $(this.pinsContainer).css("opacity", 1);
            var b = $(this.pinsContainer);
            setTimeout(function () {
                b.css({
                    visibility: "visible"
                })
            }, 200)
        },
        imageLoaded: function () {
            $(this).removeClass("lazy")
        },
        getContentArea: function () {
            return this.contentArea || document.documentElement.clientWidth
        },
        updateContainerHeight: function () {
            $("#columnContainer").height(Math.max.apply(Math, this.pinArray))
        }
    }
}();
var LazyLoad = new(function () {
    var b = this,
        c = 0,
        e = 0,
        g = 100,
        f = $(window);
    b.images = {};
    b.invalidate = function () {
        $("img.lazy").each(function (u, o) {
            u = $(o);
            b.images[u.attr("data-id")] = u;
            h(u) && j(u)
        })
    };
    b.check = function () {
        var u, o = false;
        return function () {
            if (!o) {
                o = true;
                clearTimeout(u);
                u = setTimeout(function () {
                    o = false;
                    d()
                }, 200)
            }
        }
    }();
    var d = function () {
            var u = 0,
                o = 0;
            for (var m in b.images) {
                var q = b.images[m];
                u++;
                if (h(q)) {
                    j(q);
                    o++
                }
            }
        };
    b.stop = function () {
        f.unbind("scroll", k);
        f.unbind("resize", l)
    };
    var h = function (u) {
            return u.offset().top <= g
        },
        j = function (u) {
            if (u.hasClass("lazy")) {
                var o = u.attr("data-src"),
                    m = u.attr("data-id");
                u.load(function () {
                    if (u[0]) u[0].style.opacity = "1";
                    delete b.images[m]
                });
                u.attr("src", o);
                u.removeClass("lazy");
                if (u[0]) u[0].style.opacity = "0"
            }
        },
        k = function () {
            c = $(window).scrollTop();
            r();
            b.check()
        },
        l = function () {
            e = $(window).height();
            r();
            b.check()
        },
        r = function () {
            g = c + e + 600
        };
    if (useLazyLoad) {
        f.ready(function () {
            k();
            l()
        });
        f.scroll(k);
        f.resize(l)
    }
});
