<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
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
    <h1 id="login_logo"><a href="/ownliked">Ownliked</a></h1>
    <div class="otherLogin">
    	<a href="oauth/oauth.h?oauthType=weibo" class="wbdl" id="weibologin">weibologin</a>
		<a href="oauth/oauth.h?oauthType=qzone" class="qqdl" id="qqlogin">qqlogin</a>
    </div>
<%--   	<c:if test="${!empty(msg)}"><p class="error">${msg }</p></c:if>--%>
<%--    <form id="registerForm" method="POST" action="ownUser/ownUserLogin.h" class="form fancyForm registerForm" accept-charset="utf-8">--%>
<%--    	<ul>--%>
<%--    		<li>--%>
<%--    			<input type="text" name="email"/>--%>
<%--    			<label>Email</label>--%>
<%--    			<span></span>--%>
<%--    		</li>--%>
<%--    		<li>--%>
<%--    			<input type="password" name="password"/>--%>
<%--    			<label>Password</label>--%>
<%--    			<span></span>--%>
<%--    		</li>--%>
<%--    	</ul>--%>
<%--    	<div class="non_inputs">--%>
<%--    		<input class="button whiteButton button18" type="submit" value="Login"/>--%>
<%--    		<a id="forgetPassword" class="colorless" href="###">忘记了你的Password？</a>--%>
<%--    	</div>--%>
<%--    </form>--%>
<%--    <form id="resetForm" class="form fancyForm registerForm" accept-charset="utf-8">--%>
<%--    	<ul>--%>
<%--    		<li>--%>
<%--    			<input type="text" name="email"/>--%>
<%--		    	<label>Email Address</label>--%>
<%--		    	<span></span>--%>
<%--    		</li>--%>
<%--    	</ul>--%>
<%--    	<div class="not_inputs">--%>
<%--    		<input class="button whiteButton button18" type="submit" value="Reset"/>--%>
<%--    		<a href="###" id="backToLogin" class="colorless">Back to Login?</a>--%>
<%--    	</div>--%>
<%--    </form>--%>
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
