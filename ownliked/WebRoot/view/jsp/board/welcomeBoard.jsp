<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Ownliked / Welcome</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div>
    	<h1>Welcome to</h1>
    	<a href="/ownliked" title="Ownliked" rel="Ownliked">Ownliked</a>
    </div>
    <p>Setp 1 of 2</p>
    <h1>点击一些你喜欢的事情：</h1>
    <div>
    	<div><img src="view/images/welcomeBoard/13088655136986278_OIeO7hct_b.jpg"/></div>
    </div>
    <div>
    	<span></span>
    	<span></span>
    	<span></span>
    	<span></span>
    	<span></span>
    </div>
  </body>
</html>
