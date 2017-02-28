$(function(){
	/* 日历插件 start */
	 $("#datepicker" ).datepicker({
		inline: true
	 });
	/* end 日历插件 */

	/* 表情展开收缩 start */
	$(".content .right_content .box_2 .box_2_content_2 .left .hover").click(function(){
		var css = $(".content .right_content .box_2 .box_2_content_2 .left .face").css("display");
		if(css == "none"){
			$(".content .right_content .box_2 .box_2_content_2 .left .hover").attr("title","收起表情");
			$(".content .right_content .box_2 .box_2_content_2 .left .face").addClass("animated bounceInLeft").show();
		}else{
			$(".content .right_content .box_2 .box_2_content_2 .left .hover").attr("title","展开表情");
			$(".content .right_content .box_2 .box_2_content_2 .left .face").hide();
		}
	});
	/* end 表情展开收缩 */

	/* 评论框获取焦点展开登录框 start */
	$(".content .right_content .box_2 .box_2_content_1 .textarea_box").focus(function(){
		$(".content .right_content .box_2 .box_2_content_3").slideDown();
	});
	/* end 评论框获取焦点展开登录框 */
	
	/** 点击表情 将表情放到textareat框中去 start */
	$(".content .right_content .box_2 .box_2_content_2 .left .face .face_ul > li").click(function(){
		var nowindex = $('.face_ul>li').index(this)+1;
		if (nowindex < 10) {
			nowindex = "0"+nowindex;
		}
		$(".content .right_content .box_2 .box_2_content_1 .textarea_box").append(
				"<img src='"+path+"/images/face/"+nowindex+".gif' width='18' height='18'/>"
		);
	});
	/** end 点击表情 将表情放到textareat框中去 */

	/* 发表评论js校验 start */
	$(".content .right_content .box_2 .box_2_content_2 .all_right .right").click(function(){
		$(".content .right_content .box_2 .box_2_content_2 .left .face").hide();
		$(".content .right_content .box_2 .box_2_content_3").slideDown();
		//内容赋值
		$("#add_remark_content").val($(".content .right_content .box_2 .box_2_content_1 .textarea_box").html());
		var pl_content = document.getElementById("textareabox_content_jy").innerHTML;//对div校验值只能用js不能用jQuery
		//昵称赋值
		$("#add_remark_username").val($("#pl_nickname").val());
		var pl_nickname = $("#add_remark_username").val();
		//邮箱赋值
		$("#add_remark_usermail").val($("#pl_mail").val());
		var pl_mail = $("#add_remark_usermail").val();
		
		if(pl_nickname == ""){
			$(".content .right_content .box_2 .box_2_content_2 .all_right .tip").text("昵称！昵称！").addClass("animated shake").show().delay(2000).fadeOut();
		}else if(pl_mail == ""){
			$(".content .right_content .box_2 .box_2_content_2 .all_right .tip").text("邮箱！邮箱！").addClass("animated shake").show().delay(2000).fadeOut();
		}else if(/\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/.test(pl_mail) == false){
			$(".content .right_content .box_2 .box_2_content_2 .all_right .tip").text("邮箱格式！！！").addClass("animated shake").show().delay(2000).fadeOut();
		}else if (pl_content == "" || pl_content == null) {
			$(".content .right_content .box_2 .box_2_content_2 .all_right .tip").text("内容！内容！").addClass("animated shake").show().delay(2000).fadeOut();
		}else{
			$(".content .right_content .box_2 .box_2_content_2 .all_right .load_tip").show();
			$(".content .right_content .box_2 .box_2_content_2 .all_right .tip").text("正在提交...").removeClass("animated shake").show();
			$.ajax({
				type : "post",
				url : path+"/article/qt_ajax_add_remark_info.jspx",
				dataType : "json",
				data : $("#addRemarkForm").serialize(),
				success : function(json){	
					$(".content .right_content .box_2 .really_remark .wc_ul").append(
						"<li class='wc_li animated fadeInUp'>"+
							"<img src='"+path+"/images/ykpl.png' width='40' height='40' title='游客' class='yk_img'/>"+
							"<span class='sjx'></span>"+
							"<div class='remark_content'>"+
								"<span class='_1'>"+json.newRemark.remark_content+"</span>"+
								"<span class='_2'>"+
									"<ul>"+
										"<li title='评论人昵称'>"+
											"<span class='iconfont'>&#xe629;</span>" +
											"<span>  "+pl_nickname+"</span>"+
										"</li>"+
										"<li title='评论时间'>"+
											"<span class='iconfont'>&#x3435;</span>" +
											"<span>  "+json.newRemark.remark_timejson+" ("+json.newRemark.remark_timeaxis+")</span>"+
										"</li>"+										
									"</ul>"+
								"</span>"+
							"</div>"+
						"</li>"
					);
					//情空内容
					$(".content .right_content .box_2 .box_2_content_1 .textarea_box").html("");
					$(".content .right_content .box_2 .box_2_content_2 .all_right .load_tip").hide();
					$(".content .right_content .box_2 .box_2_content_2 .all_right .ok_tip").show().delay(2000).fadeOut();
					$(".content .right_content .box_2 .box_2_content_2 .all_right .tip").text("评论成功！").removeClass("animated shake").show().delay(2000).fadeOut();
				},
				error : function(){
					alert("ERROR");
				}
			});
		}
	});
	/* end 发表评论js校验 */

	/* 百度分享代码位置摆放 start */
	$(".content .right_content .box_1 .box_1_content_4 ._1 .right").click(function(){
		var css = $(".content .right_content .box_1 .box_1_content_4 .bdsharebuttonbox").css("display");	
		if(css == "none"){
			$(".content .right_content .box_1 .box_1_content_4 .bdsharebuttonbox").addClass("animated bounceInLeft").show();	
			$(".content .right_content .box_1 .box_1_content_4 ._1").css("margin-top",5);
		}else{
			$(".content .right_content .box_1 .box_1_content_4 .bdsharebuttonbox").hide();	
			$(".content .right_content .box_1 .box_1_content_4 ._1").css("margin-top",83);
		}
	});	
	/* end 百度分享代码位置摆放 */
	
	 /** 为更多分类加背景颜色 start */
	var menuSize = $(".content .left_content .box_3 .more_menu li").length;
	var bgColors = ["#15a287", "#5cb85c", "#d9534f", "#567e95", "#b433ff", "#00a67c", "#b37333", "#f60", "#5cb85c"];
	for (var i = 0; i < menuSize; i++) {
		$(".content .left_content .box_3 .more_menu li").eq(i).css("background-color", bgColors[Math.floor(Math.random() * bgColors.length)]);
	}
	/** end 为更多分类加背景颜色 */
	
	/** 近期文章导航那一块格式化一下 start */
	var jqwzSize = $(".content .left_content .box_2 .box_2_content ul li").length;
	for (var j = 0; j < jqwzSize; j++) {
		$(".content .left_content .box_2 .box_2_content ul li").eq(j).find("a").text($(".content .left_content .box_2 .box_2_content ul li").eq(j).find("a").text().substring(0,15)+"...");
	}
	/** end 近期文章导航那一块格式化一下 */
	
	/** 菜单栏变色 start */
	$(".header .nav ul").find("li").eq(menuId).css({"background":"#E5B91C","border-radius":"40px 0px"});
	/** end 菜单栏变色 */
	
	/** 这篇文章时第一篇，则隐藏上一篇导航 */
	if ($(".content .right_content .box_1 .box_1_content_5 .left a").text() == "") {
		$(".content .right_content .box_1 .box_1_content_5 .left").hide();
	}
	/** 这篇文章时第一篇，则隐藏下一篇导航 */
	if ($(".content .right_content .box_1 .box_1_content_5 .right a").text() == "") {
		$(".content .right_content .box_1 .box_1_content_5 .right").hide();
	}
	
	/** 点击喜欢+1 start */
	$(".content .right_content .box_1 .box_1_content_4 ._1 .left").click(function(){
		$.ajax({
			type : "post",
			url : path+"/article/qt_ajax_add_like_info.jspx?articleId="+articleId,
			dataType : "json",
			success : function(json){
				$(".content .right_content .box_1 .box_1_content_4 ._1 .left .like").removeClass("animated zoomInUp").text(json.likeNum).addClass("animated zoomInUp");
			},
			error : function(){
				alert("ERROR！");
			}
		});
	});
	/** end 点击喜欢+1 */
});