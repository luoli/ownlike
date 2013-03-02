<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
  <script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
  <script type="text/javascript">
	window.onload=function(){
	  xiuxiu.embedSWF("altContent",3,"900px","500px");
	  /*第1个参数是加载编辑器div容器，第2个参数是编辑器类型，第3个参数是div容器宽，第4个参数是div容器高*/
<%--	  xiuxiu.setUploadURL("http://web.upload.meitu.com/image_upload.php");//修改为上传接收图片程序地址--%>
	  xiuxiu.setUploadURL("http://localhost:8080/Test/meitu");//修改为上传接收图片程序地址
	  xiuxiu.onInit = function ()
	  {
	    xiuxiu.loadPhoto("http://open.web.meitu.com/sources/images/1.jpg");//修改为要处理的图片url
	  }
	  xiuxiu.onUploadResponse = function (data)
	  {
	    //alert("上传响应" + data); 可以开启调试
	  }
	  xiuxiu.setUploadType(2);
	}
  </script>
  </head>
  
  <body>
  	<div id="altContent">
  	
  	</div>
  </body>
</html>
