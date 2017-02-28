<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> Nothing | 博客管理登陆 </title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="Keywords" content="KeyWords, KeyWords"/>
	<meta name="description" content=""/>
	<meta name="author" content="Nothing" />
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/title.png"/>  
	<!-- css -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"/><!-- 本页样式 -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.min.css"/><!-- 动画样式 -->
	<!-- js -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script><!-- jQuery框架 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script><!-- 本页js -->
	<script type="text/javascript">
		//提供变量，共manage.js使用
		var path = "${pageContext.request.contextPath}";
	</script>
</head>
<body>
	<div class="login animated bounceInDown">
		<div class="one">
			<img src="${pageContext.request.contextPath}/images/login_logo.png" width="100" height="100" class="login_logo"/>
			<img src="${pageContext.request.contextPath}/images/login_text.png" width="310" height="100"/>
		</div>
		<div class="two">
			<form id="loginForm">			
				<label>账号</label><input type="text" class="username" autocomplete="off" placeholder="请输入账号" id="username" name="username"/><br/>
				<label>密码</label><input type="text" onfocus="this.type='password'" class="password" autocomplete="off" placeholder="请输入密码" id="password" name="password"/><br/>
				<input type="button" value="登&nbsp;&nbsp;陆" class="btn" id="loginBtn"/>
			</form>
			<div class="tip">
				<span class="_1">账号！</span>
				<span class="_2">密码！</span>
				<span class="_3">Error！</span>
			</div>
		</div>
	</div>
	<div class="footer animated bounceInUp">
			登陆界面 V1.0 By Nothing<br/>
			&copy; 2015.08
	</div>
</body>
</html>
