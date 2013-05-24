<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>Ownlikde / Login</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="view/css/public.css" type="text/css"/>
	<link rel="stylesheet" href="view/jsp/user/css/indexRegister.css" type="text/css"/>
	<script type="text/javascript" src="view/js/comm/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="view/jsp/user/js/userIndex.js"></script>
  </head>
  <body>
	<div id="SignUp" class="logo_exp">
	  <div class="education logo">
	      <p>Welcome to</p>
<%--	      <img width="251" height="61" src="">--%>
			<span>Ownliked</span>
	  </div>
	  <div class="wrapper">
	    <div class="shadow"></div>
	    <div class="content">
	      <div class="header">
	          
	            <div class="getStarted">
	              Step 1 of 2
	            </div>
	          
	          <h1>Create your account to explore Ownliked.</h1>
	      </div>
	      <div class="intermission">
	        <h2 class="text">Connect with</h2>
	      </div>
	      <ul class="buttons" id="otherLogin">
	        <li>
	          <a href="<%=request.getContextPath() %>/oauth/oauth.h?oauthType=weibo" id="weibologin" class="BigButton facebook wbdl">
	              <span class="logo"></span>
	              新浪Weibo
	          </a>
	        </li>
	        <li>
	          <a href="<%=request.getContextPath() %>/oauth/oauth.h?oauthType=qzone" id="qqlogin" class="BigButton twitter qqdl">
	              <span class="logo"></span>
	              腾讯QQ
	          </a>
	        </li>
	      </ul>
	      <h3>
	          <span>现在您不必注册本站，为了方便您的使用</span>
<%--	          <a href="/join/register/">your email address.--%>
<%--	          </a>--%>
	      </h3>
	      <div class="footer">
	          <h3 class="login">
	            <span>Already have an account? </span>
	            <a href="/login/">Log in.(OFF)</a>
	          </h3>
	          <h3 class="login">
	            <span>Are you a business? </span>
	            <a href="mailto:930387901@qq.com">930387901@qq.com</a>
	          </h3>
	      </div>
	    </div>
	  </div>
	</div>
	<script type="text/javascript">
    	$(function(){
    		Register.setup();
    		if(top != self){
				$("body").html("<h1>Unauthorized</h1>");	
    		}
    	});
    </script>
  </body>
</html>
