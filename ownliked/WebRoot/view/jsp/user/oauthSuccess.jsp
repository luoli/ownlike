<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
  	<div>
  		登录成功，跳转页面...
  	</div>
    <script type="text/javascript">
		window.opener.location.href = "<%=request.getContextPath()%>/ownClip/checkUserLogin.h";
		window.opener=null;
		window.open("","_self");
		this.window.opener = null;
		window.close();
    </script>
  </body>
</html>
