<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>解决Ajax的前进与后退</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<%--  <script type="text/javascript" src="pjax/js/jquery.pjax.js"></script>--%>
  <script type="text/javascript" src="js/jquery.pjax.js"></script>
 
  </head>
  
  <body>
    <div id="container">
    </div>
    <div id="loading">loading...</div>
    <div id="temp">
	    <a href="<%=basePath%>pjaxServlet">请求 pjaxServlet</a>
	    <a class="pjax" href="<%=basePath%>GServlet">请求 GServlet</a>
	    <form action="<%=basePath%>pjaxServlet" method="get" data-pjax="form">
	    	<input type="hidden" value="${pjax}" name="pjax" id="id_pjax"/>
	    	<input type="submit" value="submit"/>
	    </form>
    </div>
  </body> 
  <script type="text/javascript">
  	if($.support.pjax){
  		$(document).on("click", "a", function(event){
  			$.pjax.click(event, "#temp");
  		});
	  	$(document).on("submit", "form[data-pjax]", function(event){
	  		$.pjax.submit(event, "#temp");
	  	});
  	}
  
<%--  	$.pjax({--%>
<%--	   selector: ".pjax",//链接选择器--%>
<%--	   container: "#container",//要刷新的内容的容器选择器--%>
<%--	   cache: false,//是否使用缓存--%>
<%--	   storage: true,//是否使用本地存储--%>
<%--	   show: "fade",//刷新效果，可自定义function--%>
<%--	   push: true,//刷新方式，true是push，false是replace，null则什么也不做--%>
<%--	   title: "标题",//标题--%>
<%--	   titleSuffix: "- 后缀",//标题后缀--%>
<%--	   url: "${path}/pjax",//链接url--%>
<%--	   type:"post",//请求方式，GET，POST--%>
<%--	   complete : function(data){--%>
<%--		   console.log(data);--%>
<%--		   $("#temp").html(data.responseText);--%>
<%--	   },--%>
<%--	   callback: function(status){--%>
<%--            var type = status.type;--%>
<%--            switch(type){--%>
<%--                case 'success': console.log("success");break; //正常--%>
<%--                case 'cache': console.log("cache");break; //读取缓存 --%>
<%--                case 'error': console.log("error");break; //发生异常--%>
<%--                case 'hash': console.log("hash");break; //只是hash变化--%>
<%--            }--%>
<%--        },//回调函数--%>
<%--	   filter: function () { }//过滤方法，参数为href的值，可以过滤掉某些链接--%>
<%--	});--%>
<%--  	$('#container').bind('pjax.start', function(){--%>
<%--        $('#loading').show();--%>
<%--    })--%>
<%--    $('#container').bind('pjax.end', function(){--%>
<%--        $('#loading').hide();--%>
<%--    })--%>
  </script>
</html>
