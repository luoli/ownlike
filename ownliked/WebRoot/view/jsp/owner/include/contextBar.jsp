<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="FixedContainer">
   <ul class="links">
	  <li>
	    <a href="ownBoard/searchBoardByOwnUser.h?userId=${ownUser.id}" <c:if test="${selBar == 'board'}">class="selected"</c:if>>
	      <strong>${ownUser.boardNum}</strong> Boards
	    </a>
	  </li>
	  <li>
	    <a href="ownClip/searchClipByCurrentUser.h?userId=${ownUser.id}" <c:if test="${selBar == 'pin'}">class="selected"</c:if>>
	      <strong>${ownUser.clipNum}</strong> Pins
	    </a>
	  </li>
	  <li>
	    <a href="ownClip/searchClipByCurrentUser.h?userId=${ownUser.id}&filter=likes" <c:if test="${selBar == 'like'}">class="selected"</c:if>>
	      <strong>${ownUser.likeNum}</strong> Likes
	    </a>
	  </li>
	  <li>
	    <a href="ownActivateHistory/getCurrentUserActive.h?userId=${ownUser.id}" <c:if test="${selBar == 'active'}">class="selected"</c:if>>
	      Activity
	    </a>
	  </li>
   </ul>
   <ul class="follow">
	  <li>
	    <a href="/fallcru6/followers/" <c:if test="${selBar == 'follow'}">class="selected"</c:if>>
	      <strong>${ownUser.followerNum}</strong> Followers
	    </a>
	  </li>
	  <li>
	    <a href="/fallcru6/following/" <c:if test="${selBar == 'following'}">class="selected"</c:if>>
	      <strong>${ownUser.followingNum}</strong> Following
	    </a>
	  </li>
    </ul>
    <div class="action">
<%--			    <a class="button button13 redButton disabled clickable unfollowuserbutton" data-text-follow="Follow All" data-text-unfollow="Unfollow All" href="javascript:void(0);">--%>
<%--			        Unfollow All--%>
<%--			    </a>--%>
		<a class="button button13 redButton followuserbutton" data-text-follow="Follow All" data-text-unfollow="Unfollow All" href="javascript:void(0);">
			  Follow All
		</a>
     </div>
</div>
    <script type="text/javascript">
    $(document).ready(function() {
        var bar = $('#ContextBar');
<%--        var offset = bar.offset();--%>
        var shim = $('<div/>').insertAfter(bar).css({height:bar.height(), display:'none', width:10});
        var offset = bar.offset().top;
        var fixed = false,
            noDescription = $('.noDescription');

        $(window).scroll(function() {
            var threshold = $(this).scrollTop() >= offset;
            if (threshold && !fixed) {
                shim.show();
                bar.addClass('fixed');
                fixed = true;
            } else if (!threshold && fixed) {
                shim.hide();
                bar.removeClass('fixed');
                fixed = false;
            };
        });
		/**
        $('#rearrangeButton').tipsy({
            title: 'tooltip',
            gravity: 'n',
            fade: true,
            html: true
        });

        $('#RearrangeCancel').tipsy({
            title: 'tooltip',
            gravity: 'n',
            fade: true,
            html: true
        });
		**/
        noDescription.click(function() {
            var form = $('#editDescription'),
                textarea = $('textarea', form),
                button = $('.Button', form);

            trackGAEvent('about_field', 'expanded', 'profile');
            noDescription.hide();
            form.show();

            collapseEditWebsite();
            collapseEditLocation();

            // Character count
            CharacterCount.truncateData("#editDescription textarea", 200);
            CharacterCount.setup('#editDescription textarea', '#editDescription .CharacterCount', '#editDescription .editDescription', 200);
            textarea.focus();

            button.click(function() {
                if (!button.hasClass('disabled')) {
                    var about = textarea.val();
                    trackGAEvent('about_field', 'clicked', 'profile');
                    $.post('/settings/about/',
                        { about : about },
                        function(data) {
                            if (data.status == 'ok') {
                                trackGAEvent('about_field', 'success', 'profile');
                                form.replaceWith('<p class="colormuted">' + about + '</p>');
                            }
                        }
                    );
                }
            });
        });

        // Connect a Facebook account to a user account
        $(".addFacebook").click(function() {
            Facebook.startFacebookConnect(''); // reloads window
            (function(error) {
                trackGAEvent('fb_btn', 'clicked', 'profile');
                $.post("/facebook/connect/", function(data) {
                    if (data == "success") {
                        trackGAEvent('fb_btn', 'success', 'profile');
                        // Don't do anything, auto-refreshes.
                    } else {
                        error();
                    }
                }).error(error);
            })(function() {
                alert("Oops! Something went wrong disconnecting your Facebook account. Please try again.");
            });
        });

        $(".addTwitter").click(function() {
            Twitter.startTwitterConnect(''); // reloads window
            (function(error) {
                trackGAEvent('twitter_btn', 'clicked', 'profile');
                $.post("/twitter/connect/", function(data) {
                    if (data == "success") {
                        trackGAEvent('twitter_btn', 'success', 'profile');
                        // Show Twitter icon
                    } else {
                        error();
                    }
                }).error(error);
            })(function() {
                alert("Oops! Something went wrong disconnecting your Twitter account. Please try again.");
            });
        });

        $(".addIcons").on('click', '.addWebsite', function() {
            collapseEditDescription();
            collapseEditLocation();

            var form = $('<div class="Form" id="editWebsite"></div>');
            var icon = $('<img class="inputIcon" src="http://passets-ec.pinterest.com/images/Input-Website.png">');
            var input = $('<input class="SplitInput" id="websiteInput" type="text">');
            var button = $('<button class="Button11 Button RedButton SplitButton editWebsite" type="button"><img src="http://passets-ec.pinterest.com/images/Rearrange-Confirm.png"></button>');

            form.append(icon).append(input).append(button);
            $(this).replaceWith(form);
            input.focus();

            trackGAEvent('website_btn', 'expanded', 'profile');

            var submit = function() {
                if (!button.hasClass('disabled')) {
                    var website = $('#editWebsite input').val();
                    $.post('/settings/website/',
                        { website : website },
                        function(data) {
                            trackGAEvent('website_btn', 'clicked', 'profile');
                            if (data.status == "ok") {
                                trackGAEvent('website_btn', 'success', 'profile');

                                if ($('#ProfileLocation').length != 0) {
                                    $('#ProfileLocation').before('<li><a href="' + website + '" class="icon website" target="_blank"></a></li>');

                                } else {
                                    $('.addIcons').parent().before('<li><a href="' + website + '" class="icon website" target="_blank"></a></li>');
                                }

                                $('.addIcons').addClass('Existing');
                                $('#editWebsite').remove();
                            } else {
                                error();
                            }
                        }
                    );
                }
            }

            input.keypress(function(e) {
                if (e.which == 13) {
                    submit();
                }
            });

            button.click(function() {
                submit();
            });
        });

        $(".addIcons").on('click', '.addLocation', function() {
            collapseEditDescription();
            collapseEditWebsite();

            var form = $('<div class="Form" id="editLocation"></div>');
            var icon = $('<img class="inputIcon" src="http://passets-lt.pinterest.com/images/Input-Location.png">');
            var input = $('<input class="SplitInput" id="locationInput" type="text">');
            var button = $('<button class="Button11 Button RedButton SplitButton editLocation" type="button"><img src="http://passets-ec.pinterest.com/images/Rearrange-Confirm.png"></button>');

            form.append(icon).append(input).append(button);
            $(this).replaceWith(form);
            input.focus();

            trackGAEvent('location_btn', 'expanded', 'profile');
            var submit = function() {
                if (!button.hasClass('disabled')) {
                    var location = $('#editLocation input').val();
                    $.post('/settings/location/',
                        { location : location },
                        function(data) {
                            trackGAEvent('location_btn', 'clicked', 'profile');
                            if (data.status == "ok") {
                                trackGAEvent('location_btn', 'success', 'profile');

                                var icon = '<li id="ProfileLocation"><span class="icon location"></span>' + location + '</li>';

                                $('.addIcons').parent().before(icon);
                                $('.addIcons').addClass('Existing');
                                $('#editLocation').remove();
                            } else {
                                // Error
                            }
                        }
                    );
                }
            }

            input.keypress(function(e) {
                if (e.which == 13) {
                    submit();
                }
            });

            button.click(function() {
                submit();
            });
        });

        var collapseEditDescription = function() {
            var form = $('#editDescription');
            if (form.length) {
                form.hide();
                noDescription.show();
            }
        }

        var collapseEditWebsite = function() {
            if ($('#editWebsite').length != 0) {
                var btn = '';
                btn += '<button class="Button Button11 WhiteButton addWebsite" type="button">';
                btn += '<img src="http://passets-ec.pinterest.com/images/LinkNag-Website.png"></button>';

                $('#editWebsite').replaceWith(btn);
            }
        }

        var collapseEditLocation = function() {
            if ($('#editLocation').length != 0) {
                var btn = '';
                btn += '<button class="Button Button11 WhiteButton addLocation" type="button">';
                btn += '<img src="http://passets-ec.pinterest.com/images/LinkNag-Location.png"></button>';

                $('#editLocation').replaceWith(btn);
            }
        }

        // New sortable
        var sorting = false;
        var sortButton = $('#RearrangeButton');
        var sortCancel = $("#RearrangeCancel");
        var sortHelper = $("#SortableButtons").find('h2, h3').css({opacity:0})

        sortButton.click(function() {
            trackGAEvent('rearrange_boards', 'clicked', 'profile');
            if (sorting) {
                trackGAEvent('rearrange_boards', 'success', 'profile');
                sorting = false;
                BoardSort.save();
                setTimeout(function() { sortHelper.animate({opacity:0}) }, 500);
                sortButton.attr('tooltip', '<strong>Rearrange Boards</strong>');
                sortButton.removeClass("RedButton").addClass("WhiteButton");
                sortCancel.addClass('hidden');
            } else {
                sorting = true;
                BoardSort.start();
                setTimeout(function() { sortHelper.animate({opacity:1}) }, 300);
                sortButton.attr('tooltip', '<strong>Save Arrangement</strong>');
                sortButton.addClass("RedButton").removeClass("WhiteButton");
                sortCancel.removeClass('hidden');
            }
            return false;
        });

        sortCancel.click(function() {
            trackGAEvent('rearrange_boards', 'cancelled', 'profile');
            BoardSort.cancel();
            return false;
        });

    });
    </script>