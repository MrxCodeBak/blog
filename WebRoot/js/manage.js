$(function(){
	/** 点击左边menu菜单 start */
	$("#menu_a_1").click(function(){
		$("#zs_content_1").hide();
		//异步查询
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/homepage/ajax_load_menu_sy.jspx",
			dataType : "json",
			success : function(json){
				//更改数量
				$("#count_1").text(json.count['userCount']);
				$("#count_2").text(json.count['articleCount']);
				$("#count_3").text(json.count['userCount']);
				$("#count_5").text(json.count['menuCount']);
				$("#count_6").text(json.count['linkCount']);
				
				$("#zs_content_2").hide();
				$("#zs_content_3").hide();
				$("#zs_content_4").hide();
				$("#zs_content_5").hide();
				$("#zs_content_6").hide();
				$("#zs_content_7").hide();
				$("#zs_content_8").hide();
				/*** 额外关闭 ***/
				$("#zs_content_2_update").hide();//修改用户
				$("#zs_content_2_add").hide();//添加用户
				$("#zs_content_3_add").hide();//添加文章
				$("#zs_content_3_update").hide();//修改文章
				$("#zs_content_1").addClass("animated bounceInRight").show();//首页
			},
			error : function(){
				$("#zs_content_1").hide();
				$("#zs_content_2").hide();
				$("#zs_content_3").hide();
				$("#zs_content_4").hide();
				$("#zs_content_5").hide();
				$("#zs_content_6").hide();
				$("#zs_content_7").hide();
				/*** 额外关闭 ***/
				$("#zs_content_2_update").hide();//修改用户
				$("#zs_content_2_add").hide();//添加用户
				$("#zs_content_3_add").hide();//添加文章
				$("#zs_content_3_update").hide();//修改文章
				$("#zs_content_8").addClass("animated bounceInRight").show();//加载出错
			}
		});		
	});

	//登陆人角色
	if (login_user_role == 1) {
		$(".container .right_content .right_content_1 ._info .role_name").text("超级管理员");
	}else{
		$(".container .right_content .right_content_1 ._info .role_name").text("普通管理员");
	}
	
	var pageIndex = 1; //分页所需要全局变量：当前页码
	var totalSize = 0; //分页所需要全局变量：总页数
	/***********************************************用户管理模块********************************************************/
	//预备函数
	function menu_a_2(page_index){
		//异步查询
		$.ajax({
			type : "post",
			url : path+"/user/ajax_load_menu_yh.jspx?pageModel.pageIndex="+page_index,
			dataType : "json",
			success : function(json){
				//数据显示
				$("#zs_content_2 .all_users .table .td_cz").remove();
				for (var i = 0; i < json.users.length; i++) {
					$("#zs_content_2 .all_users .table").append(
					
						"<tr class='td_cz animated fadeInRight'>" +
							"<td class='updateId deleteId'>" + json.users[i].id + "</td>" +
							"<td class='deleteUsername'>" + json.users[i].username + "</td>" +
							"<td>" + json.users[i].nickname + "</td>" +
							"<td>" + json.users[i].reallyname + "</td>" +
							"<td>" + json.users[i].qq + "</td>" +
							"<td>" + json.users[i].email + "</td>" +
							"<td>" + json.users[i].tel + "</td>" +
							"<td>" + json.users[i].website + "</td>" +
							"<td>" + json.users[i].school + "</td>" +
							"<td>" + json.users[i].marjor + "</td>" +
							"<td>" + json.users[i].job + "</td>" +
							"<td><a href='#' class='update' title='修改用户'></a></td>" +
							"<td><a href='#' class='delete' title='删除用户'></a></td>" +
							"<td style='display:none' class='role'>"+json.users[i].role+"</td>" +
						"</tr>"
					
					);
				}
				$("#zs_content_2 .all_users .page .nowpage").text(json.pageModel.pageIndex);
				$("#zs_content_2 .all_users .page .allpages").text(json.pageModel.totalSize);
				$("#zs_content_2 .all_users .page .allcount").text(json.pageModel.recordCount);
				//alert(json.pageModel.pageIndex);
				pageIndex = json.pageModel.pageIndex;
				totalSize = json.pageModel.totalSize;
				$("#zs_content_1").hide();
				$("#zs_content_3").hide();
				$("#zs_content_4").hide();
				$("#zs_content_5").hide();
				$("#zs_content_6").hide();
				$("#zs_content_7").hide();
				$("#zs_content_8").hide();
				/*** 额外关闭 ***/
				$("#zs_content_2_update").hide();//修改用户
				$("#zs_content_2_add").hide();//添加用户
				$("#zs_content_3_add").hide();//添加文章
				$("#zs_content_3_update").hide();//修改文章
				$("#zs_content_2").addClass("animated bounceInRight").show();//用户管理
			},
			error : function(){
				$("#zs_content_1").hide();
				$("#zs_content_2").hide();
				$("#zs_content_3").hide();
				$("#zs_content_4").hide();
				$("#zs_content_5").hide();
				$("#zs_content_6").hide();
				$("#zs_content_7").hide();
				/*** 额外关闭 ***/
				$("#zs_content_2_update").hide();//修改用户
				$("#zs_content_2_add").hide();//添加用户
				$("#zs_content_3_add").hide();//添加文章
				$("#zs_content_3_update").hide();//修改文章
				$("#zs_content_8").addClass("animated bounceInRight").show();//加载出错
			}
		});		
	}
	//点击左边菜单【用户管理】
	$("#menu_a_2").click(function(){
		$("#zs_content_2").hide();
		menu_a_2(1);
	});
	//首页
	$("#zs_content_2 .all_users .page .homepge").click(function(){
		menu_a_2(1);
	});
	//上一页
	$("#zs_content_2 .all_users .page .prev").click(function(){
		menu_a_2(pageIndex - 1);
	});
	//下一页
	$("#zs_content_2 .all_users .page .next").click(function(){
		if (pageIndex >= totalSize) {
			menu_a_2(totalSize);
		}else{
			menu_a_2(pageIndex + 1);
		}
	});
	//尾页
	$("#zs_content_2 .all_users .page .lastpage").click(function(){
		menu_a_2(totalSize);
	});
	/** ---点击【修改】图标--- */
	//步骤1 根据userId异步查询该用户信息
	function updateUser_ajaxLoadUserInfoById(id){
		$.ajax({
			type : "post",
			url : path+"/user/ajax_load_update_user_info.jspx?userId="+id,
			dataType : "json",
			success : function(json){
				$("#user_update_id").val(json.user.id);
				$("#user_update_username").addClass("animated bounceInRight").val(json.user.username);
				$("#user_update_nickname").addClass("animated bounceInRight").val(json.user.nickname);
				$("#user_update_realyname").addClass("animated bounceInRight").val(json.user.reallyname);
				$("#user_update_qq").addClass("animated bounceInRight").val(json.user.qq);
				$("#user_update_email").addClass("animated bounceInRight").val(json.user.email);
				$("#user_update_tel").addClass("animated bounceInRight").val(json.user.tel);
				$("#user_update_website").addClass("animated bounceInRight").val(json.user.website);
				$("#user_update_school").addClass("animated bounceInRight").val(json.user.school);
				$("#user_update_marjor").addClass("animated bounceInRight").val(json.user.marjor);
				$("#user_update_job").addClass("animated bounceInRight").val(json.user.job);
			},
			error : function(){
				$("#zs_content_1").hide();
				$("#zs_content_2").hide();
				$("#zs_content_3").hide();
				$("#zs_content_4").hide();
				$("#zs_content_5").hide();
				$("#zs_content_6").hide();
				$("#zs_content_7").hide();
				/*** 额外关闭 ***/
				$("#zs_content_2_update").hide();//修改用户
				$("#zs_content_2_add").hide();//添加用户
				$("#zs_content_3_add").hide();//添加文章
				$("#zs_content_3_update").hide();//修改文章
				$("#zs_content_8").addClass("animated bounceInRight").show();//加载出错
			}
		});
	}
	//步骤2 在所有用户界面点击修改图标
	$(document).on("click", "#zs_content_2 .all_users .table .update", function () {
		if (login_user_role != 1) {
			if (login_user_role == $(this).parent().parent().find(".updateId").text()) {
				$("#zs_content_2").hide();
				$("#zs_content_2_update").addClass("animated bounceInRight").show();
				//获取点击的用户id
				var updateId = $(this).parent().parent().find(".updateId").text();
				updateUser_ajaxLoadUserInfoById(updateId);
			}else{
				$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
				$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
				$(".mess_tip .right span").text("普通管理员没有权限修改其他用户的资料噢~");
			}
		}else{
			$("#zs_content_2").hide();
			$("#zs_content_2_update").addClass("animated bounceInRight").show();
			//获取点击的用户id
			var updateId = $(this).parent().parent().find(".updateId").text();
			updateUser_ajaxLoadUserInfoById(updateId);
		}
		
		
		
	});
	//步骤3 点击【重置】按钮
	$("#user_rest_btn").click(function(){
		updateUser_ajaxLoadUserInfoById($("#user_update_id").val());
	});
	//步骤4 点击返回上一级
	$("#zs_content_2_update .location .info .syj").click(function(){
		$("#zs_content_2_update").hide();
		menu_a_2(pageIndex);
	});
	//步骤5 点击【确定】按钮
	$("#updateUserBtn").click(function(){
		$("#zs_content_2_update .tip_box .sx").addClass("animated bounceInDown").show().delay(2000).fadeOut();
		$("#zs_content_2_update .tip_box .info").addClass("animated fadeInUp").show().delay(2000).fadeOut();
		$.ajax({
			type : "post",
			url : path+"/user/ajax_update_user_info.jspx",
			dataType : "json",
			data : $("#updateUser").serialize(),
			success : function(json){
				$("#zs_content_2_update .tip_box .info .ok").show();
				$("#zs_content_2_update .tip_box .info .no").hide();
				$("#zs_content_2_update .tip_box .info span").text("修改成功！");
			},
			error : function(){
				$("#zs_content_2_update .tip_box .info .ok").hide();
				$("#zs_content_2_update .tip_box .info .no").show();
				$("#zs_content_2_update .tip_box .info span").text("修改失败！");
			}
		});
	});
	/** --------在所有用户界面点击【删除】图标------------ */
	//步骤1 弹出提示框进行提示
	var deleteUserId = 0;
	$(document).on("click", "#zs_content_2 .all_users .table .delete", function () {
		deleteUserId = $(this).parent().parent().find(".deleteId").text();
		if (login_user_role == 1) {
			var clickRole = $("#zs_content_2 .all_users .table .role").text();
			if (clickRole == 1) {
				$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
				$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
				$(".mess_tip .right span").text("超级管理员账号不能被删除噢~");
			}else{
				$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
				$(".delete_tip_user").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
				//删除用户提示姓名
				var deleteUsername = $(this).parent().parent().find(".deleteUsername").text();
				$(".delete_tip_user .right .mention").text(deleteUsername);
				$(".delete_tip_user img").css("margin-top",($(".delete_tip_user").height()-60)/2);
			}
		}else{
			$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
			$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
			$(".mess_tip .right span").text("普通管理员没有权限删除用户噢~");
		}
	});
	//步骤2 用户点击取消删除
	$(".delete_tip_user .btn .cancel_btn").click(function(){
		$(".delete_tip_user").hide();
		$(".yy").hide();
	});
	//步骤2 用户点击确定删除
	$(".delete_tip_user .btn .ok_btn").click(function(){
		$(".delete_tip_user").hide();
		$(".yy").hide();
		$.ajax({
			type : "post",
			url : path+"/user/ajax_delete_user_info.jspx?userId="+deleteUserId,
			dataType : "json",
			success : function(json){
				if (json.flag == 1) {
					$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
					$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
					$(".mess_tip .right span").text("超级管理员账号不能被删除噢~");
				}else{
					if ($("#zs_content_2 .all_users .table .td_cz").length == 1) {
						menu_a_2(pageIndex-1);
					}else{
						menu_a_2(pageIndex);
					}
				}
			},
			error : function(){
				$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
				$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
				$(".mess_tip .right span").text("服务器错误，删除失败！");
			}
		});
	});
	/** --------在所有用户界面点击【添加用户】图标------------ */
	//步骤1 把添加用户页面弄出来
	$("#zs_content_2 .location .add_user").click(function(){
		if (login_user_role != 1) {
			$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
			$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
			$(".mess_tip .right span").text("普通管理员不能添加用户噢~");
		}else{
			$("#zs_content_2").hide();
			$("#zs_content_2_add").addClass("animated bounceInRight").show();//添加用户页面
		}
	});
	//步骤2 当点击【确定添加用户】按钮时
	$("#addUserBtn").click(function(){
		var user_add_username = $("#user_add_username").val();
		var user_add_nickname = $("#user_add_nickname").val();
		var user_add_password = $("#user_add_password").val();
		var user_add_qq = $("#user_add_qq").val();
		var user_add_email = $("#user_add_email").val();
		var user_add_tel = $("#user_add_tel").val();
		var user_add_website = $("#user_add_website").val();
		var user_add_school = $("#user_add_school").val();
		var user_add_marjor = $("#user_add_marjor").val();
		var user_add_job = $("#user_add_job").val();
		if (user_add_username == "") {
			$("#zs_content_2_add .tip_box .sx").addClass("animated bounceInDown").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info").addClass("animated fadeInUp").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info .jg").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info span").text("账号还未填写！");
			$("#user_add_username").focus();
		}else if (user_add_nickname == "") {
			$("#zs_content_2_add .tip_box .sx").addClass("animated bounceInDown").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info").addClass("animated fadeInUp").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info .jg").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info span").text("昵称还未填写！");
			$("#user_add_nickname").focus();
		}else if(user_add_password == ""){
			$("#zs_content_2_add .tip_box .sx").addClass("animated bounceInDown").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info").addClass("animated fadeInUp").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info .jg").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info span").text("密码还未填写！");
			$("#user_add_password").focus();
		}else if (user_add_qq == "") {
			$("#zs_content_2_add .tip_box .sx").addClass("animated bounceInDown").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info").addClass("animated fadeInUp").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info .jg").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info span").text("QQ还未填写！");
			$("#user_add_qq").focus();
		}else if (user_add_email == "") {
			$("#zs_content_2_add .tip_box .sx").addClass("animated bounceInDown").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info").addClass("animated fadeInUp").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info .jg").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info span").text("邮箱还未填写！");
			$("#user_add_email").focus();
		}else if (user_add_tel == "") {
			$("#zs_content_2_add .tip_box .sx").addClass("animated bounceInDown").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info").addClass("animated fadeInUp").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info .jg").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info span").text("电话还未填写！");
			$("#user_add_tel").focus();
		}else if (user_add_website == "") {
			$("#zs_content_2_add .tip_box .sx").addClass("animated bounceInDown").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info").addClass("animated fadeInUp").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info .jg").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info span").text("网址还未填写！");
			$("#user_add_website").focus();
		}else if (user_add_school == "") {
			$("#zs_content_2_add .tip_box .sx").addClass("animated bounceInDown").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info").addClass("animated fadeInUp").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info .jg").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info span").text("学校还未填写！");
			$("#user_add_school").focus();
		}else if (user_add_marjor == "") {
			$("#zs_content_2_add .tip_box .sx").addClass("animated bounceInDown").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info").addClass("animated fadeInUp").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info .jg").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info span").text("专业还未填写！");
			$("#user_add_marjor").focus();
		}else if (user_add_job == "") {
			$("#zs_content_2_add .tip_box .sx").addClass("animated bounceInDown").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info").addClass("animated fadeInUp").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info .jg").show().delay(2000).fadeOut();
			$("#zs_content_2_add .tip_box .info span").text("工作还未填写！");
			$("#user_add_job").focus();
		}else{
			$.ajax({
				type : "post",
				url : path+"/user/ajax_add_user_info.jspx",
				dataType : "json",
				data : $("#addUserForm").serialize(),
				success : function(json){
					if (json.flag != true) {
						$("#zs_content_2_add .tip_box .sx").addClass("animated bounceInDown").show().delay(2000).fadeOut();
						$("#zs_content_2_add .tip_box .info").addClass("animated fadeInUp").show().delay(2000).fadeOut();
						$("#zs_content_2_add .tip_box .info .ok").show().delay(2000).fadeOut();
						$("#zs_content_2_add .tip_box .info span").text("添加成功！");
						
						$("#user_add_username").val("");
						$("#user_add_nickname").val("");
						$("#user_add_password").val("");
						$("#user_add_qq").val("");
						$("#user_add_email").val("");
						$("#user_add_tel").val("");
						$("#user_add_website").val("");
						$("#user_add_school").val("");
						$("#user_add_marjor").val("");
						$("#user_add_job").val("");
					}else{
						$("#zs_content_2_add .tip_box .sx").addClass("animated bounceInDown").show().delay(2000).fadeOut();
						$("#zs_content_2_add .tip_box .info").addClass("animated fadeInUp").show().delay(2000).fadeOut();
						$("#zs_content_2_add .tip_box .info .jg").show().delay(2000).fadeOut();
						$("#zs_content_2_add .tip_box .info span").text("账号已经存在！");

					}
				},
				error : function(){
					$("#zs_content_2_add .tip_box .sx").addClass("animated bounceInDown").show().delay(2000).fadeOut();
					$("#zs_content_2_add .tip_box .info").addClass("animated fadeInUp").show().delay(2000).fadeOut();
					$("#zs_content_2_add .tip_box .info .no").show().delay(2000).fadeOut();
					$("#zs_content_2_add .tip_box .info span").text("添加失败！");
				}
			});
		}
	});
	//步骤3 验证用户是否已经存在
	$("#user_add_username").blur(function(){
		var username = $("#user_add_username").val();
		$.ajax({
			type : "post",
			url : path+"/user/ajax_jy_username_info.jspx?username="+username,
			dataType : "json",
			success : function(json){
				if (json.flag == true) {
					$("#zs_content_2_add .tip_box .sx").addClass("animated bounceInDown").show().delay(2000).fadeOut();
					$("#zs_content_2_add .tip_box .info").addClass("animated fadeInUp").show().delay(2000).fadeOut();
					$("#zs_content_2_add .tip_box .info .jg").show().delay(2000).fadeOut();
					$("#zs_content_2_add .tip_box .info span").text("账号已经存在！");
				}
			},
			error : function(){
				$("#zs_content_2_add .tip_box .sx").addClass("animated bounceInDown").show().delay(2000).fadeOut();
				$("#zs_content_2_add .tip_box .info").addClass("animated fadeInUp").show().delay(2000).fadeOut();
				$("#zs_content_2_add .tip_box .info .no").show().delay(2000).fadeOut();
				$("#zs_content_2_add .tip_box .info span").text("异步校验账号Error！");
			}
		});
	});
	//步骤4 在添加用户页面点击返回【上一级】
	$("#zs_content_2_add .location .info").click(function(){
		menu_a_2(pageIndex);
	});
	
	//【通用】点击【提示信息框】中的确定
	$(".mess_tip .btn .ok_btn").click(function(){
		$(".mess_tip").hide();
		$(".yy").hide();
	});
	
	/**********************************************文章管理模块********************************************************/
	/** -------------分页查询所有文章------------------- */
	//步骤1 分页查询所有文章公共方法
	var td_cz_article_length = 0;//table所有的数据行数量
	var arr_td_cz_variable = new Array();//定义一个数组，为table的每个数据行都添加一个自己的变量，供复选框使用
	var strs_all_checked_articleIds = "";//定义一个字符串，装点击【单个复选框】按钮的加起来的最后的所有文章ids
	var strs_all_checkall_articleIds = "";//定义一个字符串，装点击【总复选框】按钮的所有文章ids
	var check_article_num = 0;//全局变量：总共选择好了的复选框数量
	var plDelete_article_flag = false;//全局变量，让批量删除按钮变色之后才点击效果才可以生效
	var click_checkboxAll_flag = false;//全局变量，点击【总复选框】flag
	function ajaxLoadAllArticleByPage(page_index){
		$.ajax({
			type : "post",
			url : path+"/article/ajax_load_menu_wz.jspx?pageModel.pageIndex="+page_index,
			dataType : "json",
			success : function(json){
				$("#zs_content_3 .all_articles .table .td_cz").remove();
				//每次进这里时 先清空之前所存在【总复选框】中的值
				strs_all_checkall_articleIds = "";
				for (var i = 0; i < json.articles.length; i++) {
					$("#zs_content_3 .all_articles .table").append(
						"<tr class='td_cz animated fadeInRight'>" +
							"<td><i class='check'></i></td>" +
							"<td class='articleId'>"+json.articles[i].id+"</td>" +
							"<td class='article_title'>"+json.articles[i].article_title+"</td>"+
							"<td class='article_content'>"+json.articles[i].article_content+"</td>"+
							"<td>"+json.articles[i].article_timeaxis+"</td>"+
							"<td>"+json.articles[i].article_like+"</td>"+
							"<td>"+json.articles[i].user.nickname+"</td>"+
							"<td style='display:none' class='article_userId'>"+json.articles[i].user.id+"</td>"+
							"<td>"+json.articles[i].menu.menu_name+"</td>"+
							"<td style='display:none' class='article_menuId'>"+json.articles[i].menu.id+"</td>"+
							"<td><a href='#' class='preview' title='查看文章'></a></td>"+
							"<td><a href='#' class='update' title='修改文章'></a></td>"+
							"<td><a href='#' class='delete' title='删除文章'></a></td>"+
						"</tr>"
					
					);
					//点击【总复选框】时，把所有id装进去
					strs_all_checkall_articleIds = strs_all_checkall_articleIds + json.articles[i].id +",";
				}
				//修改分页数据
				$("#zs_content_3 .all_articles .page .nowpage").text(json.pageModel.pageIndex);
				$("#zs_content_3 .all_articles .page .allpages").text(json.pageModel.totalSize);
				$("#zs_content_3 .all_articles .page .allcount").text(json.pageModel.recordCount);
				//提供给局部变量
				pageIndex = json.pageModel.pageIndex;
				totalSize = json.pageModel.totalSize;
				//每次刷新后，为table数据行的每个变量赋初值
				td_cz_article_length = json.articles.length;
				for (var i = 0; i < td_cz_article_length; i++) {
					arr_td_cz_variable[i] = false;
				}
				//每次刷新后，所点击的复选框总数赋初值
				check_article_num = 0;
				$("#zs_content_3 .all_articles .table .th .checkall").css("background","#fff");
				//每次刷新后，为评论删除图标变色赋初值
				plDelete_article_flag = false;
				$("#zs_content_3 .location .delete_article").css({"background-color":"#eee","cursor":"auto"});
				//每次刷新后，清空已经被选取得文章id字符串
				strs_all_checked_articleIds = "";
				//每次刷新后，总复选框的值都变成false
				click_checkboxAll_flag = false;
				
				//需要隐藏打开的部分
				$("#zs_content_1").hide();
				$("#zs_content_2").hide();
				$("#zs_content_4").hide();
				$("#zs_content_5").hide();
				$("#zs_content_6").hide();
				$("#zs_content_7").hide();
				/*** 额外关闭 ***/
				$("#zs_content_2_update").hide();//修改用户
				$("#zs_content_2_add").hide();//添加用户
				$("#zs_content_3_add").hide();//添加文章
				$("#zs_content_3_update").hide();//修改文章
				$("#zs_content_3").addClass("animated bounceInRight").show();//文章管理
			},
			error : function(){
				$("#zs_content_1").hide();
				$("#zs_content_2").hide();
				$("#zs_content_3").hide();
				$("#zs_content_4").hide();
				$("#zs_content_5").hide();
				$("#zs_content_6").hide();
				$("#zs_content_7").hide();
				/*** 额外关闭 ***/
				$("#zs_content_2_update").hide();//修改用户
				$("#zs_content_2_add").hide();//添加用户
				$("#zs_content_3_add").hide();//添加文章
				$("#zs_content_3_update").hide();//修改文章
				$("#zs_content_8").addClass("animated bounceInRight").show();//加载出错
			}
		});
	}
	// 步骤2 点击左边【文章管理】菜单
	$("#menu_a_3").click(function(){
		$("#zs_content_3").hide();
		ajaxLoadAllArticleByPage(1);
	});
	//首页
	$("#zs_content_3 .all_articles .page .homepge").click(function(){
		ajaxLoadAllArticleByPage(1);
	});
	//上一页
	$("#zs_content_3 .all_articles .page .prev").click(function(){
		ajaxLoadAllArticleByPage(pageIndex - 1);
	});
	//下一页
	$("#zs_content_3 .all_articles .page .next").click(function(){
		if (pageIndex >= totalSize) {
			ajaxLoadAllArticleByPage(totalSize);
		}else{
			ajaxLoadAllArticleByPage(pageIndex + 1);
		}
	});
	//尾页
	$("#zs_content_3 .all_articles .page .lastpage").click(function(){
		ajaxLoadAllArticleByPage(totalSize);
	});
	/** ------------批量删除文章---------------- */
	$(document).on("click", "#zs_content_3 .all_articles .table td .check", function () {
		var articleId = $(this).parent().parent().find(".articleId").text();
		var click_tr_index = $(this).parent().parent().index();
		for (var j = 0; j < td_cz_article_length; j++) {
			if ((click_tr_index - 1) == j) {
				if (arr_td_cz_variable[j] == false) {
					$(this).parent().parent().find(".check").css("background","url('"+path+"/images/icon.png') -341px -54px no-repeat");
					arr_td_cz_variable[j] = true;
					check_article_num ++;//所选取的文章ids
					strs_all_checked_articleIds = strs_all_checked_articleIds+articleId+",";
				}else{
					$(this).parent().parent().find(".check").css("background","#fff");
					arr_td_cz_variable[j] = false;
					check_article_num --;
					//调取函数截取
					strs_all_checked_articleIds = checkboxSubsrting(strs_all_checked_articleIds, articleId);
					
				}
			}
		}
		//如果子复选框都选中了，那么总复选框也应该选中
		if (check_article_num == td_cz_article_length) {
			$("#zs_content_3 .all_articles .table .th .checkall").css("background","url('"+path+"/images/icon.png') -341px -54px no-repeat");
			click_checkboxAll_flag = true;
		}else{
			$("#zs_content_3 .all_articles .table .th .checkall").css("background","#fff");
			click_checkboxAll_flag = false;
		}
		//情况1：点击【子复选框】时如果有选中复选框，则批量删除按钮变色
		if (check_article_num > 0) {
			$("#zs_content_3 .location .delete_article").css({"background-color":"#cc6a6a","cursor":"pointer"});
			plDelete_article_flag = true;
		}else{
			$("#zs_content_3 .location .delete_article").css({"background-color":"#eee","cursor":"auto"});
			plDelete_article_flag = false;
		}
	});
	//点击【总复选框】按钮
	$("#zs_content_3 .all_articles .table .th .checkall").click(function(){
		if (click_checkboxAll_flag == false) {
			$("#zs_content_3 .all_articles .table .th .checkall").css("background","url('"+path+"/images/icon.png') -341px -54px no-repeat");
			//让子复选框也全部选中
			$("#zs_content_3 .all_articles .table td .check").css("background","url('"+path+"/images/icon.png') -341px -54px no-repeat");
			for (var i = 0; i < td_cz_article_length; i++) {
				arr_td_cz_variable[i] = true;
				//更改strs_all_checked_articleIds的值
				//alert(strs_all_checked_articleIds);
				strs_all_checked_articleIds = strs_all_checkall_articleIds;
			}
			//在上总数变一下
			check_article_num = td_cz_article_length;
			click_checkboxAll_flag = true;
		}else{
			$("#zs_content_3 .all_articles .table .th .checkall").css("background","#fff");
			//让子复选框全部不选中
			$("#zs_content_3 .all_articles .table td .check").css("background","#fff");
			for (var i = 0; i < td_cz_article_length; i++) {
				arr_td_cz_variable[i] = false;
			}
			//在上总数变一下
			check_article_num = 0;
			click_checkboxAll_flag = false;
			//更改strs_all_checked_articleIds的值
			strs_all_checked_articleIds = "";
		}
		//情况2：点击【总复选框】时，如果有选中复选框，则批量删除按钮变色
		if (check_article_num > 0) {
			$("#zs_content_3 .location .delete_article").css({"background-color":"#cc6a6a","cursor":"pointer"});
			plDelete_article_flag = true;
		}else{
			$("#zs_content_3 .location .delete_article").css({"background-color":"#eee","cursor":"auto"});
			plDelete_article_flag = false;
		}
	});
	//点击【批量删除按钮】，弹出确认对话框
	$("#zs_content_3 .location .delete_article").click(function(){
		if (plDelete_article_flag == true) {
			if (login_user_role == 1){
				$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
				$(".delete_tip_article_pl").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
				$(".delete_tip_article_pl .right .mention").text(strs_all_checked_articleIds.substring(0, strs_all_checked_articleIds.length-1));
				$(".delete_tip_article_pl img").css("margin-top",($(".delete_tip_article_pl").height()-60)/2);
			}else{
				$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
				$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
				$(".mess_tip .right span").text("普通管理员没有权限删除文章噢~");
			}
		}
	});
	//在弹出确认对话框中选择【取消按钮】- 批量删除
	$(".delete_tip_article_pl .btn .cancel_btn").click(function(){
		$(".delete_tip_article_pl").hide();
		$(".yy").hide();
	});
	//在弹出确认对话框中选择【确定按钮】- 批量删除
	$(".delete_tip_article_pl .btn .ok_btn").click(function(){
		$(".delete_tip_article_pl").hide();
		$(".yy").hide();
		$.ajax({
			type : "post",
			url : path+"/article/ajax_deletepl_article_info.jspx?articleIds_Str="+strs_all_checked_articleIds,
			dataType : "json",
			success : function(json){	
				//这里要减去1 因为最后会剩下,
				if (strs_all_checked_articleIds.split(",").length-1 == td_cz_article_length) {
					ajaxLoadAllArticleByPage(pageIndex - 1);
				}else{
					ajaxLoadAllArticleByPage(pageIndex);
				}
			},
			error : function(){
				$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
				$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
				$(".mess_tip .right span").text("服务器错误，删除失败！");
			}
		});
	});
	//点击单行的【删除】按钮 弹出删除单个文章对话框
	var deleteArticleId = 0;
	$(document).on("click", "#zs_content_3 .all_articles .table .delete", function () {
		deleteArticleId = $(this).parent().parent().find(".articleId").text();
		if (login_user_role == 1) {
			$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
			$(".delete_tip_article_dg").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
			$(".delete_tip_article_dg .right .mention").text(deleteArticleId);
			$(".delete_tip_article_dg img").css("margin-top",($(".delete_tip_article_pl").height()-60)/2);
		}else{
			$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
			$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
			$(".mess_tip .right span").text("普通管理员没有权限删除文章噢~");
		}
	});
	//在弹出删除单个文章对话框点击【取消】按钮
	$(".delete_tip_article_dg .btn .cancel_btn").click(function(){
		$(".delete_tip_article_dg").hide();
		$(".yy").hide();
	});
	//在弹出删除单个文章对话框点击【确定】按钮
	$(".delete_tip_article_dg .btn .ok_btn").click(function(){
		$(".delete_tip_article_dg").hide();
		$(".yy").hide();
		$.ajax({
			type : "post",
			url : path+"/article/ajax_deletepl_article_info.jspx?articleIds_Str="+deleteArticleId,
			dataType : "json",
			success : function(json){	
				if ($("#zs_content_3 .all_articles .table .td_cz").length == 1) {
					ajaxLoadAllArticleByPage(pageIndex - 1);
				}else{
					ajaxLoadAllArticleByPage(pageIndex);
				}
			},
			error : function(){
				$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
				$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
				$(".mess_tip .right span").text("服务器错误，删除失败！");
			}
		});
	});
	/** -------------点击预览按钮-------------- */
	//打开预览框
	$(document).on("click", "#zs_content_3 .all_articles .table .preview", function () {
		$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
		$(".previewArticle").slideDown(2000);
		var previewArticleId = $(this).parent().parent().find(".articleId").text();
		$.ajax({
			type : "post",
			url : path+"/article/ajax_load_article_info.jspx?articleId="+previewArticleId,
			dataType : "json",
			success : function(json){
				$(".previewArticle .content").scrollTop(0);//每次打开时候滚动条都处于最上方
				$(".previewArticle .title").html(json.article.article_title);
				$(".previewArticle .content").html(json.article.article_content);
			},
			error : function(){
			
			}
		});
	});
	//点击【关闭预览】按钮，关闭预览框
	$(".previewArticle .close").click(function(){
		$(".yy").fadeOut(2000);
		$(".previewArticle").slideUp(2000);
	});
	/** -------------------------添加文章---------------------------------- */
	//把添加文章页面显示出来，并且把所有的menu分类查询出来
	$("#zs_content_3 .location .add_article").click(function(){
		$.ajax({
			type : "post",
			url : path+"/article/ajax_load_article_menuids.jspx",
			dataType : "json",
			success : function(json){
				$("#zs_content_3").hide();
				$("#zs_content_3_add").addClass("animated bounceInRight").show();//添加文章
				for (var i = 0; i < json.menus.length; i++) {
					
					$("#article_add_menuId").append(
							"<option value='"+json.menus[i].id+"'>"+json.menus[i].menu_name+"</option>"
					);
				}
			},
			error : function(){
				$("#zs_content_1").hide();
				$("#zs_content_2").hide();
				$("#zs_content_3").hide();
				$("#zs_content_4").hide();
				$("#zs_content_5").hide();
				$("#zs_content_6").hide();
				$("#zs_content_7").hide();
				/*** 额外关闭 ***/
				$("#zs_content_2_update").hide();//修改用户
				$("#zs_content_2_add").hide();//添加用户
				$("#zs_content_3_add").hide();//添加文章
				$("#zs_content_3_update").hide();//修改文章
				$("#zs_content_8").addClass("animated bounceInRight").show();//加载出错
			}
		});
		
	});
	//在添加文章页面点击【上一级】按钮
	$("#zs_content_3_add .location .info .syj").click(function(){
		ajaxLoadAllArticleByPage(pageIndex);
	});
	//点击【点我选择封面图片】按钮
	$("#zs_content_3_add .add .fieldset .file .fileBtn").click(function(){
		$("#zs_content_3_add .add .fieldset .file .hiddenFile").click();
	});
	//点击【上传】按钮，异步上传封面
	var newFileName = "";
	$("#zs_content_3_add .add .fieldset .file .upImgBtn").click(function(){
		//newFileName = "";
		var article_add_faceimg = $("#article_add_faceimg").val();
		if (article_add_faceimg == "") {
			if (newFileName != "") {
				$("#zs_content_3_add .upImgPre .pre_img").hide();
				$("#zs_content_3_add .upImgTextTip .text").text("图片还未选择噢~");
				$("#zs_content_3_add .upImgTextTip").show().addClass("animated swing").delay(2000).fadeOut();
				$("#zs_content_3_add .upImgPre .pre_img").attr("src", path+"/images/AllUploadImgs/ArticleFaceImgs/"+newFileName).delay(3000).slideDown("slow");
			}else{
				$("#zs_content_3_add .upImgPre .pre_img").hide();
				$("#zs_content_3_add .upImgTextTip .text").text("图片还未选择噢~");
				$("#zs_content_3_add .upImgTextTip").show().addClass("animated swing").delay(2000).fadeOut();
			}
			
		}else{
			$("#zs_content_3_add .upImgPre .pre_img").hide();
			$("#zs_content_3_add .upImgPre .load_img").show();
			$.ajaxFileUpload({
		        url: path+"/article/ajax_upload_articlefaceimg.jspx", 
		        type: 'post',
		        secureuri: false, //一般设置为false
		        fileElementId: 'article_add_faceimg', // 上传文件的id、name属性名
		        dataType: 'json', //返回值类型，一般设置为json、application/json
		        success: function(json){
		        	newFileName = json.newFileName;
		        	$("#zs_content_3_add .upImgPre .load_img").hide();
		        	$("#zs_content_3_add .upImgPre .pre_img").attr("src", path+"/images/AllUploadImgs/ArticleFaceImgs/"+json.newFileName).slideDown("slow");
		         },
		        error: function(){ 
		        	$("#zs_content_3_add .upImgTextTip .text").text("异步上传封面出错！");
					$("#zs_content_3_add .upImgTextTip").show().addClass("animated swing").delay(2000).fadeOut();
		        }
		     });
		}
	});
	//点击【保存】按钮 上传文章
	$("#zs_content_3_add .add .fieldset #addArticleBtn").click(function(){
		var article_add_title = $("#article_add_title").val();
		var article_add_content_html = UE.getEditor("editorContainer").getContent();//带html
		//赋值 【html文本】
		$("#article_add_content_html").html(article_add_content_html);
		//赋值 【封面名称】
		$("#article_add_faceimg_name").val(newFileName);
		
		if (article_add_title == "") {
			$("#zs_content_3_add .add .fieldset .add_article_tip").addClass("animated shake").text("标题未输入！").show().delay(2000).fadeOut();
		}else if (newFileName == "") {
			$("#zs_content_3_add .add .fieldset .add_article_tip").addClass("animated shake").text("封面未上传！").show().delay(2000).fadeOut();
		}else if (article_add_content_html == "") {
			$("#zs_content_3_add .add .fieldset .add_article_tip").addClass("animated shake").text("内容未输入！").show().delay(2000).fadeOut();
		}else{
			$("#zs_content_3_add .add .fieldset .load").show();
			$("#zs_content_3_add .add .fieldset .add_article_tip").removeClass("animated shake").text("正在保存，请稍后...").show();
			$.ajax({
				type : "post",
				url : path+"/article/ajax_add_article_info.jspx",
				dataType : "json",
				data : $("#addArticleForm").serialize(),
				success : function(json){
					$("#zs_content_3_add .add .fieldset .load").hide();
					$("#zs_content_3_add .add .fieldset .ok").show().delay(2000).fadeOut();
					$("#zs_content_3_add .add .fieldset .add_article_tip").removeClass("animated shake").text("保存成功！").delay(2000).fadeOut();
					
					//清空已填数据
					$("#article_add_title").val("");//标题
					UE.getEditor("editorContainer").setContent("在这里编辑文章内容！");//网页编辑框
					newFileName = "";
					$("#zs_content_3_add .upImgPre .pre_img").hide();
				},
				error : function(){
					//清空已填数据
					newFileName = "";
					$("#zs_content_3_add .upImgPre .pre_img").hide();
					
					$("#zs_content_1").hide();
					$("#zs_content_2").hide();
					$("#zs_content_3").hide();
					$("#zs_content_4").hide();
					$("#zs_content_5").hide();
					$("#zs_content_6").hide();
					$("#zs_content_7").hide();
					//*** 额外关闭 ***//
					$("#zs_content_2_update").hide();//修改用户
					$("#zs_content_2_add").hide();//添加用户
					$("#zs_content_3_add").hide();//添加文章
					$("#zs_content_3_update").hide();//修改文章
					$("#zs_content_8").addClass("animated bounceInRight").show();//加载出错
				}
			});
		}
	});
	/** ---------------------修改文章------------------------ */
	// 步骤1 点击【修改】按钮 先做判断
	var flMenuId = 0;
	$(document).on("click", "#zs_content_3 .all_articles .table .update", function () {
		var updateId = $(this).parent().parent().find(".articleId").text();
		flMenuId = $(this).parent().parent().find(".article_menuId").text();
		if (login_user_role != 1) {
			if (login_user_id == $(this).parent().parent().find(".article_userId").text()) {
				$("#zs_content_3").hide();
				$("#zs_content_3_update").addClass("animated bounceInRight").show();
				//执行函数
				updateArticle_ajaxLoadArticleInfoById(updateId);
			}else{
				$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
				$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
				$(".mess_tip .right span").text("普通管理员没有权限修改其他用户发表的文章噢~");
			}
		}else{
			$("#zs_content_3").hide();
			$("#zs_content_3_update").addClass("animated bounceInRight").show();
			//执行函数
			updateArticle_ajaxLoadArticleInfoById(updateId);
		}
	});
	//步骤2 异步加载要修改文章的相关数据 -- >>和预览文章复用一个URL请求
	var oldArticleFaceImg = "";
	function updateArticle_ajaxLoadArticleInfoById(id){
		$("#zs_content_3_update .upImgPre .pre_img").hide();
		//请求1 基本数据
		$.ajax({
			type : "post",
			url : path+"/article/ajax_load_article_info.jspx?articleId="+id,
			dataType : "json",
			success : function(json){
				$("#article_update_title").val(json.article.article_title);
				UE.getEditor("editorContainer_update").setContent(json.article.article_content);
				//封面信息提示
				$("#zs_content_3_update .upImgPre").show();
				$("#zs_content_3_update .upImgPre .load_img").hide();
				$("#zs_content_3_update .upImgPre .pre_img").attr("src", path+"/images/AllUploadImgs/ArticleFaceImgs/"+json.article.article_faceimg).delay(2000).slideDown("slow");
				//赋值
				oldArticleFaceImg = json.article.article_faceimg;
				//隐式articlId 修改要用
				$("#article_update_id").val(json.article.id);
			},
			error : function(){
				$("#zs_content_1").hide();
				$("#zs_content_2").hide();
				$("#zs_content_3").hide();
				$("#zs_content_4").hide();
				$("#zs_content_5").hide();
				$("#zs_content_6").hide();
				$("#zs_content_7").hide();
				/*** 额外关闭 ***/
				$("#zs_content_2_update").hide();//修改用户
				$("#zs_content_2_add").hide();//添加用户
				$("#zs_content_3_add").hide();//添加文章
				$("#zs_content_3_update").hide();//修改文章
				$("#zs_content_8").addClass("animated bounceInRight").show();//加载出错
			}
		});
		//请求2 菜单分类数据
		$.ajax({
			type : "post",
			url : path+"/article/ajax_load_article_menuids.jspx",
			dataType : "json",
			success : function(json){
				for (var i = 0; i < json.menus.length; i++) {
					$("#article_update_menuId").append(
							"<option value='"+json.menus[i].id+"'>"+json.menus[i].menu_name+"</option>"
					);
				}
				//让该分类选中
				$("#article_update_menuId option:eq("+(parseInt(flMenuId)-1)+")").attr("selected", "selected");
			},
			error : function(){
				$("#zs_content_1").hide();
				$("#zs_content_2").hide();
				$("#zs_content_3").hide();
				$("#zs_content_4").hide();
				$("#zs_content_5").hide();
				$("#zs_content_6").hide();
				$("#zs_content_7").hide();
				/*** 额外关闭 ***/
				$("#zs_content_2_update").hide();//修改用户
				$("#zs_content_2_add").hide();//添加用户
				$("#zs_content_3_add").hide();//添加文章
				$("#zs_content_3_update").hide();//修改文章
				$("#zs_content_8").addClass("animated bounceInRight").show();//加载出错
			}
		});
		
	}
	//在添加文章页面点击【上一级】按钮
	$("#zs_content_3_update .location .info .syj").click(function(){
		ajaxLoadAllArticleByPage(pageIndex);
	});
	
	//修改文章页面点击【点我选择封面图片】按钮
	$("#zs_content_3_update .update .fieldset .file .fileBtn").click(function(){
		$("#zs_content_3_update .update .fieldset .file .hiddenFile").click();
	});
	
	//在修改文章页面点击【上传】按钮
	$("#zs_content_3_update .update .fieldset .file .upImgBtn").click(function(){
		var article_update_faceimg = $("#article_update_faceimg").val();
		if (article_update_faceimg == "") {
			if (newFileName != "") {
				$("#zs_content_3_update .upImgPre .pre_img").hide();
				$("#zs_content_3_update .upImgTextTip .text").text("图片还未选择噢~");
				$("#zs_content_3_update .upImgTextTip").show().addClass("animated swing").delay(2000).fadeOut();
				$("#zs_content_3_update .upImgPre .pre_img").attr("src", path+"/images/AllUploadImgs/ArticleFaceImgs/"+newFileName).delay(3000).slideDown("slow");
			}else{
				$("#zs_content_3_update .upImgPre .pre_img").hide();
				$("#zs_content_3_update .upImgTextTip .text").text("图片还未选择噢~");
				$("#zs_content_3_update .upImgTextTip").show().addClass("animated swing").delay(2000).fadeOut();
				$("#zs_content_3_update .upImgPre .pre_img").attr("src", path+"/images/AllUploadImgs/ArticleFaceImgs/"+oldArticleFaceImg).delay(3000).slideDown("slow");
			}
		}else{
			$("#zs_content_3_update .upImgPre .pre_img").hide();
			$("#zs_content_3_update .upImgPre .load_img").show();
			$.ajaxFileUpload({
		        url: path+"/article/ajax_upload_articlefaceimg.jspx", 
		        type: 'post',
		        secureuri: false, //一般设置为false
		        fileElementId: 'article_update_faceimg', // 上传文件的id、name属性名
		        dataType: 'json', //返回值类型，一般设置为json、application/json
		        success: function(json){
		        	newFileName = json.newFileName;
		        	$("#zs_content_3_update .upImgPre .load_img").hide();
		        	$("#zs_content_3_update .upImgPre .pre_img").attr("src", path+"/images/AllUploadImgs/ArticleFaceImgs/"+json.newFileName).slideDown("slow");
		         },
		        error: function(){ 
		        	$("#zs_content_3_update .upImgTextTip .text").text("异步上传封面出错！");
					$("#zs_content_3_update .upImgTextTip").show().addClass("animated swing").delay(2000).fadeOut();
		        }
		     });
			
		}
		
	});
	//在修改文章页面点击【修改】按钮
	$("#zs_content_3_update .update .fieldset #updateArticleBtn").click(function(){
		var article_update_title = $("#article_update_title").val();
		//赋值 【html文本】
		var article_update_content_html = UE.getEditor("editorContainer_update").getContent();//带html
		$("#article_update_content_html").html(article_update_content_html);
		//赋值 【封面名称】
		$("#article_update_faceimg_name").val(newFileName);
		
		//js校验
		if (article_update_title == "") {
			$("#zs_content_3_update .update .fieldset .update_article_tip").addClass("animated shake").text("标题不能修改成空！").show().delay(2000).fadeOut();
		}else if (article_update_content_html == "") {
			$("#zs_content_3_add .update .fieldset .update_article_tip").addClass("animated shake").text("内容不能修改成空！").show().delay(2000).fadeOut();
		}else{
			$("#zs_content_3_update .update .fieldset .load").show();
			$("#zs_content_3_update .update .fieldset .update_article_tip").removeClass("animated shake").text("正在修改，请稍后...").show();
			$.ajax({
				type : "post",
				url : path+"/article/ajax_update_article_info.jspx",
				dataType : "json",
				data : $("#updateArticleForm").serialize(),
				success : function(json){
					$("#zs_content_3_update .update .fieldset .load").hide();
					$("#zs_content_3_update .update .fieldset .ok").show().delay(2000).fadeOut();
					$("#zs_content_3_update .update .fieldset .update_article_tip").removeClass("animated shake").text("修改成功！").delay(2000).fadeOut();
					
					//清空已填数据
					newFileName = "";
				},
				error : function(){
					//清空已填数据
					newFileName = "";
					
					$("#zs_content_1").hide();
					$("#zs_content_2").hide();
					$("#zs_content_3").hide();
					$("#zs_content_4").hide();
					$("#zs_content_5").hide();
					$("#zs_content_6").hide();
					$("#zs_content_7").hide();
					//*** 额外关闭 ***//
					$("#zs_content_2_update").hide();//修改用户
					$("#zs_content_2_add").hide();//添加用户
					$("#zs_content_3_add").hide();//添加文章
					$("#zs_content_3_update").hide();//修改文章
					$("#zs_content_8").addClass("animated bounceInRight").show();//加载出错
				}
			});
		}
	});
	
	/**********************************************评论管理模块********************************************************/
	var td_cz_remark_length = 0;//table所有的数据行数量
	var plDelete_remark_flag = false;//全局变量，让批量删除按钮变色之后才点击效果才可以生效
	var strs_all_checked_remarkIds = "";//定义一个字符串，装点击【单个复选框】按钮的加起来的最后的所有文章ids
	var strs_all_checkall_remarkIds = "";//定义一个字符串，装点击【总复选框】按钮的所有文章ids
	//异步查询方法
	function ajaxLoadAllRemarkByPage(page_index){
		$.ajax({
			type : "post",
			url : path+"/remark/ajax_load_menu_remark.jspx?pageModel.pageIndex="+page_index,
			dataType : "json",
			success : function(json){		
				$("#zs_content_4 .all_remarks .table .td_cz").remove();
				//每次进这里时 先清空之前所存在【总复选框】中的值
				strs_all_checkall_remarkIds = "";
				for (var i = 0; i < json.remarks.length; i++) {
					$("#zs_content_4 .all_remarks .table").append(
						"<tr class='td_cz animated fadeInRight'>"+
							"<td><i class='check'></i></td>"+
							"<td class='remarkId'>"+json.remarks[i].id+"</td>"+
							"<td>"+json.remarks[i].article.article_title+"</td>"+
							"<td class='remark_contnet'>"+json.remarks[i].remark_content+"</td>"+
							"<td>"+json.remarks[i].remark_username+"</td>"+
							"<td>"+json.remarks[i].remark_useremail+"</td>"+
							"<td>"+json.remarks[i].remark_timejson+"&nbsp;&nbsp;"+json.remarks[i].remark_timeaxis+"</td>"+
							"<td><a href='#' class='delete' title='删除评论'></a></td>"+
						"</tr>"
					);
					//点击【总复选框】时，把所有id装进去
					strs_all_checkall_remarkIds = strs_all_checkall_remarkIds + json.remarks[i].id +",";
				}
				//修改分页数据
				$("#zs_content_4 .all_remarks .page .nowpage").text(json.pageModel.pageIndex);
				$("#zs_content_4 .all_remarks .page .allpages").text(json.pageModel.totalSize);
				$("#zs_content_4 .all_remarks .page .allcount").text(json.pageModel.recordCount);
				//提供给局部变量
				pageIndex = json.pageModel.pageIndex;
				totalSize = json.pageModel.totalSize;
				//每次刷新后，为table数据行的每个变量赋初值
				td_cz_remark_length = json.remarks.length;
				for (var i = 0; i < td_cz_remark_length; i++) {
					arr_td_cz_variable[i] = false;//arr_td_cz_variable[]数组已经在上面删除文章那里定义，不必重复定义
				}
				//每次刷新后，所点击的复选框总数赋初值
				check_remark_num = 0;//【文章模块定义有】
				$("#zs_content_4 .all_remarks .table .th .checkall").css("background","#fff");
				//每次刷新后，总复选框的值都变成false
				click_checkboxAll_flag = false;
				//每次刷新后，清空已经被选取得文章id字符串
				strs_all_checked_remarkIds = "";
				//每次刷新后，为评论删除图标变色赋初值
				plDelete_remark_flag = false;
				$("#zs_content_4 .location .delete_remark").css({"background-color":"#eee","cursor":"auto"});
				
				$("#zs_content_1").hide();
				$("#zs_content_2").hide();
				$("#zs_content_3").hide();
				$("#zs_content_5").hide();
				$("#zs_content_6").hide();
				$("#zs_content_7").hide();
				/*** 额外关闭 ***/
				$("#zs_content_2_update").hide();//修改用户
				$("#zs_content_2_add").hide();//添加用户
				$("#zs_content_3_add").hide();//添加文章
				$("#zs_content_3_update").hide();//修改文章
				$("#zs_content_4").addClass("animated bounceInRight").show();//图片管理
			},
			error : function(){
				$("#zs_content_1").hide();
				$("#zs_content_2").hide();
				$("#zs_content_3").hide();
				$("#zs_content_5").hide();
				$("#zs_content_6").hide();
				$("#zs_content_7").hide();
				/*** 额外关闭 ***/
				$("#zs_content_2_update").hide();//修改用户
				$("#zs_content_2_add").hide();//添加用户
				$("#zs_content_3_add").hide();//添加文章
				$("#zs_content_3_update").hide();//修改文章
				$("#zs_content_4").addClass("animated bounceInRight").show();//图片管理
			}
		});
	}
	
	//点击左边【评论管理】菜单
	$("#menu_a_4").click(function(){
		$("#zs_content_4").hide();
		ajaxLoadAllRemarkByPage(1);
	});
	//首页
	$("#zs_content_4 .all_remarks .page .homepge").click(function(){
		ajaxLoadAllRemarkByPage(1);
	});
	//上一页
	$("#zs_content_4 .all_remarks .page .prev").click(function(){
		ajaxLoadAllRemarkByPage(pageIndex - 1);
	});
	//下一页
	$("#zs_content_4 .all_remarks .page .next").click(function(){
		if (pageIndex >= totalSize) {
			ajaxLoadAllRemarkByPage(totalSize);
		}else{
			ajaxLoadAllRemarkByPage(pageIndex + 1);
		}
	});
	//尾页
	$("#zs_content_4 .all_remarks .page .lastpage").click(function(){
		ajaxLoadAllRemarkByPage(totalSize);
	});
	/** -------------------批量删除评论--------------------- */
	$(document).on("click", "#zs_content_4 .all_remarks .table td .check", function () {
		var remarkId = $(this).parent().parent().find(".remarkId").text();
		var click_tr_index = $(this).parent().parent().index();
		for (var i = 0; i < td_cz_remark_length; i++) {
			if ((click_tr_index-1) == i) {
				if (arr_td_cz_variable[i] == false) {
					$(this).parent().parent().find(".check").css("background","url('"+path+"/images/icon.png') -341px -54px no-repeat");
					arr_td_cz_variable[i] = true;
					check_remark_num ++;
					strs_all_checked_remarkIds = strs_all_checked_remarkIds+remarkId+",";
				}else{
					$(this).parent().parent().find(".check").css("background","#fff");
					arr_td_cz_variable[i] = false;
					check_remark_num --;
					strs_all_checked_remarkIds = checkboxSubsrting(strs_all_checked_remarkIds, remarkId);
				}
			}
		}
		//如果子复选框都选中了，那么总复选框也应该选中
		if (check_remark_num == td_cz_remark_length) {
			$("#zs_content_4 .all_remarks .table .th .checkall").css("background","url('"+path+"/images/icon.png') -341px -54px no-repeat");
			click_checkboxAll_flag = true;
		}else{
			$("#zs_content_4 .all_remarks .table .th .checkall").css("background","#fff");
			click_checkboxAll_flag = false;
		}
		//情况1：点击【子复选框】时如果有选中复选框，则批量删除按钮变色
		if (check_remark_num > 0) {
			$("#zs_content_4 .location .delete_remark").css({"background-color":"#cc6a6a","cursor":"pointer"});
			plDelete_remark_flag = true;
		}else{
			$("#zs_content_4 .location .delete_remark").css({"background-color":"#eee","cursor":"auto"});
			plDelete_remark_flag = false;
		}
		
	});
	//点击总复选框
	$("#zs_content_4 .all_remarks .table .th .checkall").click(function(){
		if (click_checkboxAll_flag == false) {
			$("#zs_content_4 .all_remarks .table .th .checkall").css("background","url('"+path+"/images/icon.png') -341px -54px no-repeat");
			//让子复选框也全部选中
			$("#zs_content_4 .all_remarks .table td .check").css("background","url('"+path+"/images/icon.png') -341px -54px no-repeat");
			for (var i = 0; i < td_cz_remark_length; i++) {
				arr_td_cz_variable[i] = true;
				strs_all_checked_remarkIds = strs_all_checkall_remarkIds;
			}
			check_remark_num = td_cz_remark_length;
			click_checkboxAll_flag = true;
		}else{
			$("#zs_content_4 .all_remarks .table .th .checkall").css("background","#fff");
			//让子复选框全部不选中
			$("#zs_content_4 .all_remarks .table td .check").css("background","#fff");
			for (var i = 0; i < td_cz_remark_length; i++) {
				arr_td_cz_variable[i] = false;
			}
			//在上总数变一下
			check_remark_num = 0;
			click_checkboxAll_flag = false;
			strs_all_checked_remarkIds = "";
		}
		//情况2：点击【总复选框】时，如果有选中复选框，则批量删除按钮变色
		if (check_remark_num > 0) {
			$("#zs_content_4 .location .delete_remark").css({"background-color":"#cc6a6a","cursor":"pointer"});
			plDelete_remark_flag = true;
		}else{
			$("#zs_content_4 .location .delete_remark").css({"background-color":"#eee","cursor":"auto"});
			plDelete_remark_flag = false;
		}
	});
	
	//点击批量删除按钮，弹出【确认删除】对话框
	$("#zs_content_4 .location .delete_remark").click(function(){
		if (plDelete_remark_flag == true) {
			if (login_user_role == 1){
				$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
				$(".delete_tip_remark_pl").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
				$(".delete_tip_remark_pl .right .mention").text(strs_all_checked_remarkIds.substring(0, strs_all_checked_remarkIds.length-1));
				$(".delete_tip_remark_pl img").css("margin-top",($(".delete_tip_article_pl").height()-60)/2);
			}else{
				$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
				$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
				$(".mess_tip .right span").text("普通管理员没有权限删除评论噢~");
			}
		}
	});
	//在弹出确认对话框中选择【取消按钮】- 批量删除
	$(".delete_tip_remark_pl .btn .cancel_btn").click(function(){
		$(".delete_tip_remark_pl").hide();
		$(".yy").hide();
	});
	//在弹出确认对话框中选择【确定按钮】- 批量删除
	$(".delete_tip_remark_pl .btn .ok_btn").click(function(){
		$(".delete_tip_remark_pl").hide();
		$(".yy").hide();
		$.ajax({
			type : "post",
			url : path+"/remark/ajax_deletepl_remark_info.jspx?remarkIds_Str="+strs_all_checked_remarkIds,
			dataType : "json",
			success : function(json){		
				//这里要减去1 因为最后会剩下,
				if (strs_all_checked_remarkIds.split(",").length-1 == td_cz_remark_length) {
					ajaxLoadAllRemarkByPage(pageIndex - 1);
				}else{
					ajaxLoadAllRemarkByPage(pageIndex);
				}
			},
			error : function(){
				$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
				$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
				$(".mess_tip .right span").text("服务器错误，删除失败！");
			}
		});
	});
	//点击单行的【删除】按钮 弹出删除单个评论对话框
	var deleteReamrkId = 0;
	$(document).on("click", "#zs_content_4 .all_remarks .table .delete", function () {
		deleteReamrkId = $(this).parent().parent().find(".remarkId").text();
		if (login_user_role == 1) {
			$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
			$(".delete_tip_remark_dg").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
			$(".delete_tip_remark_dg .right .mention").text(deleteReamrkId);
			$(".delete_tip_remark_dg img").css("margin-top",($(".delete_tip_article_pl").height()-60)/2);
		}else{
			$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
			$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
			$(".mess_tip .right span").text("普通管理员没有权限删除评论噢~");
		}
	});
	//在弹出删除单个评论对话框点击【取消】按钮
	$(".delete_tip_remark_dg .btn .cancel_btn").click(function(){
		$(".delete_tip_remark_dg").hide();
		$(".yy").hide();
	});
	//在弹出删除单个评论对话框点击【确定】按钮
	$(".delete_tip_remark_dg .btn .ok_btn").click(function(){
		$(".delete_tip_remark_dg").hide();
		$(".yy").hide();
		$.ajax({
			type : "post",
			url : path+"/remark/ajax_deletepl_remark_info.jspx?remarkIds_Str="+deleteReamrkId,
			dataType : "json",
			success : function(json){	
				if ($("#zs_content_4 .all_remarks .table .td_cz").length == 1) {
					ajaxLoadAllRemarkByPage(pageIndex - 1);
				}else{
					ajaxLoadAllRemarkByPage(pageIndex);
				}
			},
			error : function(){
				$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
				$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
				$(".mess_tip .right span").text("服务器错误，删除失败！");
			}
		});
	});
	/**********************************************菜单管理模块********************************************************/
	//异步加载菜单数据
	var jsonMenus = new Array();
	function ajaxLoadAllMEnuByPage(page_index){
		$.ajax({
			type : "post",
			url : path+"/menu/ajax_load_menu_cd.jspx?pageModel.pageIndex="+page_index,
			dataType : "json",
			success : function(json){
				$("#zs_content_5 .all_menus .table .td_cz").remove();
				for (var i = 0; i < json.menus.length; i++) {
					$("#zs_content_5 .all_menus .table").append(
						"<tr class='td_cz animated fadeInRight'>"+
							"<td class='menuId'>"+json.menus[i].id+"</td>"+
							"<td title='单击进行修改'><input type='text' value='"+json.menus[i].menu_name+"'/></td>"+
							"<td>"+json.articleNum[i]+"</td>"+
						"</tr>"
					);
					jsonMenus[i] = json.menus[i].menu_name;
				}
				//修改分页数据
				$("#zs_content_5 .all_menus .page .nowpage").text(json.pageModel.pageIndex);
				$("#zs_content_5 .all_menus .page .allpages").text(json.pageModel.totalSize);
				$("#zs_content_5 .all_menus .page .allcount").text(json.pageModel.recordCount);
				//提供给全局
				pageIndex = json.pageModel.pageIndex;
				totalSize = json.pageModel.totalSize;
				
				$("#zs_content_1").hide();
				$("#zs_content_2").hide();
				$("#zs_content_3").hide();
				$("#zs_content_4").hide();
				$("#zs_content_6").hide();
				$("#zs_content_7").hide();
				/*** 额外关闭 ***/
				$("#zs_content_2_update").hide();//修改用户
				$("#zs_content_2_add").hide();//添加用户
				$("#zs_content_3_add").hide();//添加文章
				$("#zs_content_3_update").hide();//修改文章
				$("#zs_content_5").addClass("animated bounceInRight").show();//菜单管理
			},
			error : function(){
				$("#zs_content_1").hide();
				$("#zs_content_2").hide();
				$("#zs_content_3").hide();
				$("#zs_content_4").hide();
				$("#zs_content_6").hide();
				$("#zs_content_7").hide();
				/*** 额外关闭 ***/
				$("#zs_content_2_update").hide();//修改用户
				$("#zs_content_2_add").hide();//添加用户
				$("#zs_content_3_add").hide();//添加文章
				$("#zs_content_3_update").hide();//修改文章
				$("#zs_content_5").addClass("animated bounceInRight").show();//菜单管理
			}
		});
	}
	//点击左边菜单【菜单管理】按钮
	$("#menu_a_5").click(function(){
		$("#zs_content_5").hide();
		ajaxLoadAllMEnuByPage(1);
	});
	//首页
	$("#zs_content_5 .all_menus .page .homepge").click(function(){
		ajaxLoadAllMEnuByPage(1);
	});
	//上一页
	$("#zs_content_5 .all_menus .page .prev").click(function(){
		ajaxLoadAllMEnuByPage(pageIndex - 1);
	});
	//下一页
	$("#zs_content_5 .all_menus .page .next").click(function(){
		if (pageIndex >= totalSize) {
			ajaxLoadAllMEnuByPage(totalSize);
		}else{
			ajaxLoadAllMEnuByPage(pageIndex + 1);
		}
	});
	//尾页
	$("#zs_content_5 .all_menus .page .lastpage").click(function(){
		ajaxLoadAllMEnuByPage(totalSize);
	});
	//失去焦点就修改
	$(document).on("blur", "#zs_content_5 .all_menus .table .td_cz input", function () {
		var click_tr_index = $(this).parent().parent().index();
		var inputValue = $(this).parent().parent().find("input").val();
		var menuId = $(this).parent().parent().find(".menuId").text();
//		alert($.trim(inputValue) + "---" +jsonMenus[click_tr_index-1]);
		if ($.trim(inputValue) != jsonMenus[click_tr_index-1] && $.trim(inputValue) != "") {
			$.ajax({
				type : "post",
				url : path+"/menu/ajax_update_menuname_info.jspx?newNemuName="+$.trim(inputValue)+"&menuId="+menuId,
				dataType : "json",
				success : function(json){
					$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
					$(".mess_tip img").attr("src", path+"/images/ok.png");
					$(".mess_tip").css("right",$(document).width()/2-190).addClass("animated bounceInDown").show();
					$(".mess_tip .right span").text("修改成功！！");
					ajaxLoadAllMEnuByPage(pageIndex);
				},
				error : function(){
					$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
					$(".mess_tip img").attr("src", path+"/images/no.jpg");
					$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
					$(".mess_tip .right span").text("服务器错误，修改失败！");
				}
			});
		}
	});
	//点击添加按钮，弹出阴影和添加菜单输入框
	$("#zs_content_5 .location .add_menu").click(function(){
		$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
		$(".addMenu").show();
	});
	//点击添加按钮
	$(".addMenu .addBtn").click(function(){
		var menu_name = $(".addMenu .menu_name").val();
		if (menu_name == "") {
			$(".addMenu img").attr("src", path+"/images/jg.png").addClass("animated shake").show().delay(2000).fadeOut();
			$(".addMenu span").text("菜单名未输入！").addClass("animated shake").show().delay(2000).fadeOut();
		}else{
			$.ajax({
				type : "post",
				url : path+"/menu/ajax_add_menu_info.jspx",
				dataType : "json",
				data : $("#addMenu").serialize(),
				success : function(json){		
					$(".yy").hide();
					$(".addMenu").hide();
					ajaxLoadAllMEnuByPage(totalSize);
				},
				error : function(){
					$(".addMenu img").attr("src", path+"/images/no.jpg").addClass("animated shake").show().delay(2000).fadeOut();
					$(".addMenu span").text("添加失败！").addClass("animated shake").show().delay(2000).fadeOut();
				}
			});
		}
	});
	//点击取消按钮
	$(".addMenu .cancelBtn").click(function(){
		$(".yy").hide();
		$(".addMenu").hide();
	});
	/**********************************************链接管理模块********************************************************/
	//异步加载链接数据
	var linkNames = new Array();
	var linkUrls = new Array();
	function ajaxLoadAllLinkByPage(page_index){
		$.ajax({
			type : "post",
			url : path+"/link/ajax_load_link_info.jspx?pageModel.pageIndex="+page_index,
			dataType : "json",
			success : function(json){
				$("#zs_content_6 .all_links .table .td_cz").remove();
				for (var i = 0; i < json.links.length; i++) {
					$("#zs_content_6 .all_links .table").append(
						"<tr class='td_cz animated fadeInRight'>"+
							"<td class='linkId'>"+json.links[i].id+"</td>"+
							"<td title='单击进行修改链接名'><input type='text' value='"+json.links[i].link_name+"' id='updateLinkNameInput' maxlength='6'/></td>"+
							"<td title='单击进行修改链接URL'><input type='text' value='"+json.links[i].link_url+"' id='updateLinkUrlInput' maxlength='40'/></td>"+
							"<td><a href='#' class='delete' title='删除链接'></a></td>"+
						"</tr>"
					);
					linkNames[i] = json.links[i].link_name;
					linkUrls[i] = json.links[i].link_url;
				}
				
				//修改分页数据
				$("#zs_content_6 .all_links .page .nowpage").text(json.pageModel.pageIndex);
				$("#zs_content_6 .all_links .page .allpages").text(json.pageModel.totalSize);
				$("#zs_content_6 .all_links .page .allcount").text(json.pageModel.recordCount);
				//提供给全局
				pageIndex = json.pageModel.pageIndex;
				totalSize = json.pageModel.totalSize;
				
				$("#zs_content_1").hide();
				$("#zs_content_2").hide();
				$("#zs_content_3").hide();
				$("#zs_content_4").hide();
				$("#zs_content_5").hide();
				$("#zs_content_7").hide();
				/*** 额外关闭 ***/
				$("#zs_content_2_update").hide();//修改用户
				$("#zs_content_2_add").hide();//添加用户
				$("#zs_content_3_add").hide();//添加文章
				$("#zs_content_3_update").hide();//修改文章
				$("#zs_content_6").addClass("animated bounceInRight").show();//链接管理
			},
			error : function(){
				$("#zs_content_1").hide();
				$("#zs_content_2").hide();
				$("#zs_content_3").hide();
				$("#zs_content_4").hide();
				$("#zs_content_5").hide();
				$("#zs_content_7").hide();
				/*** 额外关闭 ***/
				$("#zs_content_2_update").hide();//修改用户
				$("#zs_content_2_add").hide();//添加用户
				$("#zs_content_3_add").hide();//添加文章
				$("#zs_content_3_update").hide();//修改文章
				$("#zs_content_6").addClass("animated bounceInRight").show();//链接管理
			}
		});
	}
	$("#menu_a_6").click(function(){
		$("#zs_content_6").hide();
		ajaxLoadAllLinkByPage(1);
	});
	//首页
	$("#zs_content_6 .all_links .page .homepge").click(function(){
		ajaxLoadAllLinkByPage(1);
	});
	//上一页
	$("#zs_content_6 .all_links .page .prev").click(function(){
		ajaxLoadAllLinkByPage(pageIndex - 1);
	});
	//下一页
	$("#zs_content_6 .all_links .page .next").click(function(){
		if (pageIndex >= totalSize) {
			ajaxLoadAllLinkByPage(totalSize);
		}else{
			ajaxLoadAllLinkByPage(pageIndex + 1);
		}
	});
	//尾页
	$("#zs_content_6 .all_links .page .lastpage").click(function(){
		ajaxLoadAllLinkByPage(totalSize);
	});
	//链接名称失去焦点就修改
	$(document).on("blur", "#updateLinkNameInput", function () {
		var click_tr_index = $(this).parent().parent().index();
		var link_name = $(this).parent().parent().find("#updateLinkNameInput").val();
		var linkId = $(this).parent().parent().find(".linkId").text();		
//		alert(link_name+"--"+linkNames[click_tr_index-1]);
		if ($.trim(link_name) != linkNames[click_tr_index-1] && $.trim(link_name) != "") {
			$.ajax({
				type : "post",
				url : path+"/link/ajax_update_linkname_info.jspx?link_name="+$.trim(link_name)+"&linkId="+linkId,
				dataType : "json",
				success : function(json){
					$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
					$(".mess_tip img").attr("src", path+"/images/ok.png");
					$(".mess_tip").css("right",$(document).width()/2-190).addClass("animated bounceInDown").show();
					$(".mess_tip .right span").text("修改成功！！");
					ajaxLoadAllLinkByPage(pageIndex);
				},
				error : function(){
					$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
					$(".mess_tip img").attr("src", path+"/images/no.jpg");
					$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
					$(".mess_tip .right span").text("服务器错误，修改失败！");
				}
			});
		}
	});
	//链接URL失去焦点就修改
	$(document).on("blur", "#updateLinkUrlInput", function () {
		var click_tr_index = $(this).parent().parent().index();
		var link_url = $(this).parent().parent().find("#updateLinkUrlInput").val();
		var linkId = $(this).parent().parent().find(".linkId").text();		
//		alert(link_url+"--"+linkNames[click_tr_index-1]);
		if ($.trim(link_url) != linkUrls[click_tr_index-1] && $.trim(link_url) != "") {
			$.ajax({
				type : "post",
				url : path+"/link/ajax_update_linkurl_info.jspx?link_url="+$.trim(link_url)+"&linkId="+linkId,
				dataType : "json",
				success : function(json){
					$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
					$(".mess_tip img").attr("src", path+"/images/ok.png");
					$(".mess_tip").css("right",$(document).width()/2-190).addClass("animated bounceInDown").show();
					$(".mess_tip .right span").text("修改成功！！");
					ajaxLoadAllLinkByPage(pageIndex);
				},
				error : function(){
					$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
					$(".mess_tip img").attr("src", path+"/images/no.jpg");
					$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
					$(".mess_tip .right span").text("服务器错误，修改失败！");
				}
			});
		}
	});
	//点击添加按钮
	$("#zs_content_6 .location .add_link").click(function(){
		$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
		$(".addLink").show();
	});
	//点击确定按钮
	$(".addLink .addBtn").click(function(){
		var link_name = $("#link_name").val();
		var link_url = $("#link_url").val();
		if ($.trim(link_name) == "") {
			$(".addLink img").attr("src", path+"/images/jg.png").addClass("animated shake").show().delay(2000).fadeOut();
			$(".addLink span").text("链接目标未输入！").addClass("animated shake").show().delay(2000).fadeOut();
		}else if ($.trim(link_url) == "") {
			$(".addLink img").attr("src", path+"/images/jg.png").addClass("animated shake").show().delay(2000).fadeOut();
			$(".addLink span").text("链接URL未输入！").addClass("animated shake").show().delay(2000).fadeOut();
		}else{
			$.ajax({
				type : "post",
				url : path+"/link/ajax_add_link_info.jspx",
				dataType : "json",
				data : $("#addLink").serialize(),
				success : function(json){		
					$(".yy").hide();
					$(".addLink").hide();
					ajaxLoadAllLinkByPage(totalSize);
				},
				error : function(){
					$(".addLink img").attr("src", path+"/images/no.jpg").addClass("animated shake").show().delay(2000).fadeOut();
					$(".addLink span").text("添加失败！").addClass("animated shake").show().delay(2000).fadeOut();
				}
			});
		}
		
		
	});
	//点击取消按钮
	$(".addLink .cancelBtn").click(function(){
		$(".yy").hide();
		$(".addLink").hide();
	});
	//点击每行的删除按钮
	$(document).on("click", "#zs_content_6 .all_links .table .td_cz .delete", function () {
		var linkId = $(this).parent().parent().find(".linkId").text();
		$.ajax({
			type : "post",
			url : path+"/link/ajax_delete_link_info.jspx?linkId="+linkId,
			dataType : "json",
			success : function(json){		
				ajaxLoadAllLinkByPage(pageIndex);
			},
			error : function(){
				$(".yy").css({"height":$(document).height(),"width":$(document).width()}).show();
				$(".mess_tip").css("right",$(document).width()/2-220).addClass("animated bounceInDown").show();
				$(".mess_tip .right span").text("服务器错误，修改失败！");
			}
		});
		
	});
	/**********************************************注销登陆模块********************************************************/
	$("#menu_a_7").click(function(){
		window.location = path+"/loginout.jspx";
		$("#zs_content_1").hide();
		$("#zs_content_2").hide();
		$("#zs_content_3").hide();
		$("#zs_content_4").hide();
		$("#zs_content_5").hide();
		$("#zs_content_6").hide();
		/*** 额外关闭 ***/
		$("#zs_content_2_update").hide();//修改用户
		$("#zs_content_2_add").hide();//添加用户
		$("#zs_content_3_add").hide();//添加文章
		$("#zs_content_3_update").hide();//修改文章
		$("#zs_content_7").addClass("animated bounceInRight").show();//注销登陆
	});	
	/** end 点击左边menu菜单 */
	
	/**********************************************自定义函数********************************************************/
	/**
	 * 
	 *  复选框去除函数 
	 *  srt_1:总字符串
	 *  srt_2:要删除的字符串
	 *  
	 */
	var checkboxSubsrting = function(str_1, str_2){
		var arr_old = str_1.split(",");
		str_new = "";
		for (var i = 0; i < arr_old.length-1; i++) {
			if (arr_old[i] != str_2) {
				str_new = str_new + arr_old[i] +","; 
			}
		}
		return str_new;
	};
});