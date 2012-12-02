<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html lang="zh" class="en js csstransforms3d">
  <head>
    <base href="<%=basePath%>">
    
    <title>Ownliked / Register</title>
    
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
    <form id="registerForm" method="POST" action="ownUser/ownUserRegister.h" class="form fancyForm registerForm" accept-charset="utf-8">
    	<ul>
    		<li>
    			<input id="id_email" type="text" name="email"/>
    			<label>Email</label>
    			<span></span>
    		</li>
    		<li>
    			<input id="id_password" type="password" name="password"/>
    			<label>Password</label>
    			<span></span>
    		</li>
    		<li>
    			<input id="id_firstName" type="text" name="firstName"/>
    			<label>First Name</label>
    			<span></span>
    		</li>
    		<li>
    			<input id="id_lastName" type="text" name="lastName"/>
    			<label>Last Name</label>
    			<span></span>
    		</li>
    	</ul>
    	<div class="non_inputs">
    		<input class="button whiteButton button18" type="submit" value="Register"/>
    		<a id="forgetPassword" class="colorless" href="view/jsp/user/login.jsp">已有账号去登录？</a>
    	</div>
    </form>
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
