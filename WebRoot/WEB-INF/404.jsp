<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title> 404 | 个人博客 </title>
		<meta charset="UTF-8">
		<meta name="Keywords" content="KeyWords, KeyWords"/>
		<meta name="description" content=""/>
		<meta name="author" content="Nothing"/>
		<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/title.png"/>
		<!-- css --> 
		<style type="text/css">
			*{margin:0px;padding:0px}
			body{font-size:12px;font-family:"微软雅黑";background: url(${pageContext.request.contextPath}/images/bg_1.jpg);}
			.main{width:700px;height:425px;margin:100px auto;text-align:center;color:#8B745C}
			a:visited{color:blue}
		</style>
		<!-- js -->
		<script type="text/javascript">
			var miao = 10;
			var URL;
			function load(url){
				URL = url;
				for(var i = miao; i >= 0; i--){
					//10 9 8 7 6 5 4 3 2 1 0依次传给doUpdate(num)函数更新秒
					window.setTimeout('doUpdate('+ i +')', (miao - i) * 1000);
				}
			}
	
			//更新秒
			function doUpdate(num){
				document.getElementById("time").innerHTML = num;
				if(num == 0){
					window.location = URL;
				}
			}
	
			load("${pageContext.request.contextPath}/article/qt_index.jspx");
			
		</script>
	</head>
	<body>
		<div class="main">
			<img src="${pageContext.request.contextPath}/images/404.png" width="700" height="400"/>
			<p>将在<span id="time">10</span>秒后自动转入<a href="${pageContext.request.contextPath}/article/qt_index.jspx">首页</a>，请稍后...</p>
		</div>
	</body>
</html>