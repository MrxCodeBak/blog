$(function(){
	/** 根据浏览器变化大小来设置登陆框的位置 start */
	var llq_height = $(window).height();
	$(".login").css("margin-top",(llq_height - 300) / 2);
	$(window).resize(function() { 
		var llq_height = $(window).height();
		if(llq_height > 300){
			$(".login").css("margin-top",(llq_height - 300) / 2);
		}else{
			$(".login").css("margin-top",0);
		}
	});
	/** end 根据浏览器变化大小来设置登陆框的位置 */

	/** 表单校验 start */
	$("#loginBtn").click(function(){
		var username = $("#username").val();
		var password = $("#password").val();
		if(username == ""){
			$(".login .two .tip ._1").addClass("animated shake").show().delay(2000).fadeOut();
		}else if(password == ""){
			$(".login .two .tip ._2").addClass("animated shake").show().delay(2000).fadeOut();
		}else{
			$(".login .two .btn").val("登 陆 中...");
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/login.jspx",
				dataType : "json",
				data : $("#loginForm").serialize(),
				success : function(json){
					if (json.flag == true) {
						window.location = path+"/homepage/manage_page.jspx";
					}else{
						$(".login .two .tip ._3").text("登陆失败！").addClass("animated shake").show().delay(2000).fadeOut();
						$(".login .two .btn").val("登  陆");
					}
				},
				error : function(){
					$(".login .two .tip ._3").addClass("animated shake").show().delay(2000).fadeOut();
					$(".login .two .btn").val("登  陆");
				}
			});
		}
	});
	/** end 表单校验 */
	
	/** 在密码input框中回车登陆 start */
	var password = document.getElementById("password");
	password.onkeypress = function(e){
		if (e.keyCode == 13) {
			$("#loginBtn").click();
		}
	};
	/** end 在密码input框中回车登陆 */
});