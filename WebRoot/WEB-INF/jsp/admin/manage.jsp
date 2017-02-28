<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> Nothing | 博客管理 </title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="Keywords" content="KeyWords, KeyWords"/>
	<meta name="description" content=""/>
	<meta name="author" content="Nothing" />
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/title.png"/>  
	<!-- css -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/manage.css"/><!-- 本页样式 -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.min.css"/><!-- 动画样式 -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/udeditor/themes/default/css" />
	<!-- js -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script><!-- jQuery框架 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script><!-- 异步上传框架 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage.js"></script><!-- 本页js -->
	<!-- 网页编辑器js插件 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/cj/ueditor/ueditor.config.js"></script><!-- 网页编辑器js -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/cj/ueditor/ueditor.all.js"></script><!-- 网页编辑器js -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/cj/ueditor/lang/zh-cn/zh-cn.js"></script><!-- 网页编辑器js -->
	
	<script type="text/javascript">
		//提供变量，共manage.js使用
		var login_user_role = ${session_user_blog.role};
		var login_user_id = ${session_user_blog.id};
		var path = "${pageContext.request.contextPath}";
		//初始化网页编辑器_添加文章
		var ue = UE.getEditor("editorContainer",{
			initialFrameWidth:940,
			initialFrameHeight:300,
			autoHeightEnabled: false,
			initialStyle:"p{line-height:1em; font-family:'微软雅黑'}",
		});
		//初始化网页编辑器_修改文章
		var ue = UE.getEditor("editorContainer_update",{
			initialFrameWidth:940,
			initialFrameHeight:300,
			autoHeightEnabled: false,
			initialStyle:"p{line-height:1em; font-family:'微软雅黑'}",
		});
	</script>
</head>
<body>
	<div class="container">
		<div class="left_content">
			<div class="left_content_1 animated bounceInDown">
				<div class="_info">
					<span class="_text">Blog Manage System</span>
				</div>
				<img src="${pageContext.request.contextPath}/images/login_logo.png" width="100" height="100" class="logo"/>
			</div>
			<div class="left_content_2 animated bounceInUp">
				<div class="_info">
					<span class="_text">Menu</span>
				</div>
				<div class="left_content_2_menu">
					<ul>
						<a href="#" id="menu_a_1"><li>首页</li></a>
						<a href="#" id="menu_a_2"><li>用户管理</li></a>
						<a href="#" id="menu_a_3"><li>文章管理</li></a>
						<a href="#" id="menu_a_4"><li>评论管理</li></a>
						<a href="#" id="menu_a_5"><li>菜单管理</li></a>
						<a href="#" id="menu_a_6"><li>链接管理</li></a>
						<a href="#" id="menu_a_7"><li>注销登陆</li></a>
					</ul>
				</div>
			</div>
		</div>
		<div class="right_content animated bounceInRight">
			<div class="right_content_1">
				<div class="_info">
					<span class="_text">Content</span>
					<span class="_dlr">Welcome! <span class="role_name"></span>： <font color="red">${session_user_blog.nickname}</font></span>
				</div>
			</div>	
			<!-- 正式内容_1 start -->
			<div id="zs_content_1">
				<div class="location">
					<i class="icon"></i>
					<span class="info">您当前所在位置：后台中心&gt;&gt;首页</span>
				</div>
				<div class="welcome">
				</div>
				<div class="all_mess">
					<ul>
						<li>
							<i class="icon_1"></i>
							<span class="count" id="count_1">${count["userCount"]}</span>
							<span class="what">所有用户数</span>
						</li>
						<li style="background-color:#28b779">
							<i class="icon_2"></i>
							<span class="count" id="count_2">${count["articleCount"]}</span>
							<span class="what">所有文章数</span>
						</li>
						<li style="background-color:#ffb848">
							<i class="icon_3"></i>
							<span class="count" id="count_3">${count["remarkCount"]}</span>
							<span class="what">所有评论数</span>
						</li>
						<li style="background-color:#cc6a6a">
							<i class="icon_4"></i>
							<span class="count">6</span>
							<span class="what">所有图片数</span>
						</li>
						<li style="background-color:#2255a4">
							<i class="icon_5"></i>
							<span class="count" id="count_5">${count["menuCount"]}</span>
							<span class="what">所有菜单数</span>
						</li>
						<li style="background-color:#da542e">
							<i class="icon_6"></i>
							<span class="count" id="count_6">${count["linkCount"]}</span>
							<span class="what">所有链接数</span>
						</li>
					</ul>
				</div>				
			</div>
			<!-- end 正式内容_1 -->

			<!-- ############################################################################## -->
	
			<!-- 正式内容_2 用户管理 start -->
			<!-- 展示所有用户 -->
			<div id="zs_content_2">
				<div class="location">
					<i class="icon"></i>
					<span class="info">您当前所在位置：后台中心&gt;&gt;用户管理</span>
					<span class="add_user" title="添加用户"></span>
				</div>
				<div class="all_users">
					<table class="table">
						<tr class="th">
							<th>ID</th>
							<th>账号</th>
							<th>昵称</th>
							<th>姓名</th>
							<th>QQ</th>
							<th>邮箱</th>
							<th>电话</th>
							<th>网址</th>
							<th>学校</th>
							<th>专业</th>
							<th>工作</th>
							<th colspan="2">操作</th>
						</tr>
					</table>
					<div class="page">
						<a href="#" class="homepge">首页</a>
						<a href="#" class="prev">上一页</a>
						<a href="#" class="next">下一页</a>
						<a href="#" class="lastpage">尾页</a>
						当前<font color="red" class="nowpage"></font>/<font class="allpages"></font>页 共<font color="red" class="allcount">30</font>条记录
					</div>
				</div>
			</div>
			<!-- 修改用户资料 -->
			<div id="zs_content_2_update">
				<div class="location">
					<i class="icon"></i>
					<span class="info">您当前所在位置：后台中心&gt;&gt;用户管理&gt;&gt;修改用户&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="syj">上一级</a></span> 
				</div>
				<div class="update">
					<fieldset class="fieldset">
						<legend>用户资料修改</legend>
						<form id="updateUser">
							<label style="display:none">ID</label><input type="hidden" id="user_update_id" name="user.id"/>
							<label>账号</label><input type="text" class="pt_input" readonly="readonly" id="user_update_username"/>
							<label>昵称</label><input type="text" class="pt_input" autocomplete="off" id="user_update_nickname" name="user.nickname" maxlength="10"/>
							<label>姓名</label><input type="text" class="pt_input" autocomplete="off" id="user_update_realyname" name="user.reallyname" maxlength="4"/>
							<label>企鹅</label><input type="text" class="pt_input" autocomplete="off" id="user_update_qq" name="user.qq" maxlength="10"/>
							<label>邮箱</label><input type="text" class="pt_input" autocomplete="off" id="user_update_email" name="user.email"/>
							<label>电话</label><input type="text" class="pt_input" autocomplete="off" id="user_update_tel" name="user.tel" maxlength="11"/>
							<label>网址</label><input type="text" class="pt_input" autocomplete="off" id="user_update_website" name="user.website"/>
							<label>学校</label><input type="text" class="pt_input" autocomplete="off" id="user_update_school" name="user.school" maxlength="10"/>
							<label>专业</label><input type="text" class="pt_input" autocomplete="off" id="user_update_marjor" name="user.marjor" maxlength="10"/>
							<label>工作</label><input type="text" class="pt_input" autocomplete="off" id="user_update_job" name="user.job" maxlength="20"/>
							<input type="button" value="确 定" class="btn" id="updateUserBtn"/>
							<input type="button" value="重 置" class="btn" id="user_rest_btn"/>
						</form>
					</fieldset>
				</div>
				<div class="tip_box">
					<div class="sx">
					</div>
					<div class="info">
						<img src="${pageContext.request.contextPath}/images/ok.png" width="20" height="20" class="ok"/>
						<img src="${pageContext.request.contextPath}/images/no.jpg" width="20" height="20" class="no"/>
						<span></span>
					</div>
				</div>
			</div>
			<!-- 添加用户 start -->
			<div id="zs_content_2_add">
				<div class="location">
					<i class="icon"></i>
					<span class="info">您当前所在位置：后台中心&gt;&gt;用户管理&gt;&gt;添加用户&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="syj">上一级</a></span> 
				</div>
				<div class="add">
					<fieldset class="fieldset">
						<legend>添加用户</legend>
						<form id="addUserForm">
							<label>账号</label><input type="text" class="pt_input" autocomplete="off" id="user_add_username" name="user.username" maxlength="10"/>
							<label>昵称</label><input type="text" class="pt_input" autocomplete="off" id="user_add_nickname" name="user.nickname" maxlength="10"/>
							<label>密码</label><input type="text" class="pt_input" autocomplete="off" id="user_add_password" name="user.password" maxlength="20"/>
							<label>企鹅</label><input type="text" class="pt_input" autocomplete="off" id="user_add_qq" name="user.qq" maxlength="10"/>
							<label>邮箱</label><input type="text" class="pt_input" autocomplete="off" id="user_add_email" name="user.email"/>
							<label>电话</label><input type="text" class="pt_input" autocomplete="off" id="user_add_tel" name="user.tel" maxlength="11"/>
							<label>网址</label><input type="text" class="pt_input" autocomplete="off" id="user_add_website" name="user.website"/>
							<label>学校</label><input type="text" class="pt_input" autocomplete="off" id="user_add_school" name="user.school" maxlength="10"/>
							<label>专业</label><input type="text" class="pt_input" autocomplete="off" id="user_add_marjor" name="user.marjor" maxlength="10"/>
							<label>工作</label><input type="text" class="pt_input" autocomplete="off" id="user_add_job" name="user.job" maxlength="20"/>
							<input type="button" value="确 定" class="btn" id="addUserBtn"/>
							<input type="reset" value="重 置" class="btn"/>
						</form>
					</fieldset>
				</div>
				<div class="tip_box">
					<div class="sx">
					</div>
					<div class="info">
						<img src="${pageContext.request.contextPath}/images/jg.png" width="20" height="20" class="jg"/>
						<img src="${pageContext.request.contextPath}/images/ok.png" width="20" height="20" class="ok"/>
						<img src="${pageContext.request.contextPath}/images/no.jpg" width="20" height="20" class="no"/>
						<span></span>
					</div>
				</div>
			</div>
			<!-- end 添加用户 -->
			<!-- end 正式内容_2 用户管理 -->
			
			<!-- ############################################################################## -->
			<!-- 正式内容_3 文章管理 start -->
			<!-- 分页展示所有文章数据 start -->
			<div id="zs_content_3">
				<div class="location">
					<i class="icon"></i>
					<span class="info">您当前所在位置：后台中心&gt;&gt;文章管理</span>
					<span class="add_article" title="添加文章"></span>
					<span class="delete_article" title="批量删除文章"></span>
				</div>
				<div class="all_articles">
					<table class="table">
						<tr class="th">
							<th><i class="checkall"></i></th>
							<th>ID</th>
							<th>标题</th>
							<th>内容</th>
							<th>发表时间</th>
							<th>喜欢数量</th>
							<th>创建人</th>
							<th>分类</th>
							<th colspan="3">操作</th>
						</tr>
					</table>
					<div class="page">
						<a href="#" class="homepge">首页</a>
						<a href="#" class="prev">上一页</a>
						<a href="#" class="next">下一页</a>
						<a href="#" class="lastpage">尾页</a>
						当前<font color="red" class="nowpage"></font>/<font class="allpages"></font>页 共<font color="red" class="allcount">30</font>条记录
					</div>
				</div>
			</div>
			<!-- end 分页展示所有文章数据 -->
			<!-- 添加文章 start -->
			<div id="zs_content_3_add">
				<div class="location">
					<i class="icon"></i>
					<span class="info">您当前所在位置：后台中心&gt;&gt;文章管理&gt;&gt;添加文章&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="syj">上一级</a></span> 
				</div>
				<div class="add">
					<fieldset class="fieldset">
						<legend>添加文章</legend>
						<form id="addArticleForm" enctype="multipart/form-data" method="post">
							<!-- 隐式传递 -->
							<input type="hidden" value="${session_user_blog.id}" name="article.user.id"/>
							<input type="hidden" id="article_add_faceimg_name" name="article.article_faceimg"/>
							<textarea style="display:none" id="article_add_content_html" name="article.article_content"></textarea>
						
							<label>标题</label>
							<input type="text" autocomplete="off" id="article_add_title" name="article.article_title"/><br/>
							<label>分类</label>
							<select class="fl" id="article_add_menuId" name="article.menu.id">
							</select><br/>
							<div class="file">
								<label>封面</label>
								<input type="file" style="display:none" class="hiddenFile" id="article_add_faceimg" name="article_faceimg"/>
								<div class="fileBtn">点我选择封面图片</div>
								<div class="upImgBtn">上 传</div>
							</div><br/>
							<textarea class="textarea" id="editorContainer">
							在这里编辑文章内容！
							</textarea>
							<input type="button" value="保 存" id="addArticleBtn"/>
							<img src="${pageContext.request.contextPath}/images/load.jpg" width="20" height="20" class="load"/>
							<img src="${pageContext.request.contextPath}/images/ok.png" width="20" height="20" class="ok"/>
							<span class="add_article_tip">标题未输入</span>
						</form>
					</fieldset>
				</div>
				<div class="upImgPre">
					<img src="${pageContext.request.contextPath}/images/load.jpg" width="25" height="25" class="load_img"/>
					<img src="#" width="200" height="120" class="pre_img"/>
				</div>
				<div class="upImgTextTip">
					<img src="${pageContext.request.contextPath}/images/no.jpg" width="20" height="20" class="no"/>
					<span class="text">啦啦啦</span>
				</div>
			</div>
			<!-- end 添加文章 -->
			<!-- 修改文章 start -->
			<div id="zs_content_3_update">
				<div class="location">
					<i class="icon"></i>
					<span class="info">您当前所在位置：后台中心&gt;&gt;文章管理&gt;&gt;修改文章&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="syj">上一级</a></span> 
				</div>
				<div class="update">
					<fieldset class="fieldset">
						<legend>修改文章</legend>
						<form id="updateArticleForm" enctype="multipart/form-data">
							<!-- 隐式传递 -->
							<input type="hidden" id="article_update_id" name="article.id"/>
							<input type="hidden" value="${session_user_blog.id}" name="article.user.id"/>
							<input type="hidden" id="article_update_faceimg_name" name="article.article_faceimg"/>
							<textarea style="display:none" id="article_update_content_html" name="article.article_content"></textarea>
						
							<label>标题</label>
							<input type="text" autocomplete="off" id="article_update_title" name="article.article_title"/><br/>
							<label>分类</label>
							<select class="fl" id="article_update_menuId" name="article.menu.id">
							</select><br/>
							<div class="file">
								<label>封面</label>
								<input type="file" style="display:none" class="hiddenFile" id="article_update_faceimg" name="article_faceimg"/>
								<div class="fileBtn">点我选择封面图片</div>
								<div class="upImgBtn">上 传</div>
							</div><br/>
							<textarea class="textarea" id="editorContainer_update">
							在这里编辑文章内容！
							</textarea>
							<input type="button" value="修 改" id="updateArticleBtn"/>
							<img src="${pageContext.request.contextPath}/images/load.jpg" width="20" height="20" class="load"/>
							<img src="${pageContext.request.contextPath}/images/ok.png" width="20" height="20" class="ok"/>
							<span class="update_article_tip">标题未输入</span>
						</form>
					</fieldset>
				</div>
				<div class="upImgPre">
					<img src="${pageContext.request.contextPath}/images/load.jpg" width="25" height="25" class="load_img"/>
					<img src="#" width="200" height="120" class="pre_img"/>
				</div>
				<!-- 封面上传失败提示信息 -->
				<div class="upImgTextTip">
					<img src="${pageContext.request.contextPath}/images/no.jpg" width="20" height="20" class="tip_img"/>
					<span class="text">封面异步上传失败！</span>
				</div>
			</div>
			<!-- end 修改文章 -->
			<!-- end 正式内容_3 文章管理 -->

			<!-- ############################################################################## -->
			<!-- 正式内容_4 评论管理 start -->
			<div id="zs_content_4">
				<div class="location">
					<i class="icon"></i>
					<span class="info">您当前所在位置：后台中心&gt;&gt;评论管理</span>
					<span class="delete_remark" title="批量删除评论"></span>
				</div>
				<div class="all_remarks">
					<table class="table">
						<tr class="th">
							<th><i class="checkall"></i></th>
							<th>ID</th>
							<th>文章</th>
							<th>评论内容</th>
							<th>评论人</th>
							<th>评论人邮箱</th>
							<th>评论时间</th>
							<th>操作</th>
						</tr>
					</table>
					<div class="page">
						<a href="#" class="homepge">首页</a>
						<a href="#" class="prev">上一页</a>
						<a href="#" class="next">下一页</a>
						<a href="#" class="lastpage">尾页</a>
						当前<font color="red" class="nowpage"></font>/<font class="allpages"></font>页 共<font color="red" class="allcount">30</font>条记录
					</div>
				</div>
			</div>
			<!-- end 正式内容_4 评论管理 -->

			<!-- ############################################################################## -->
			<!-- 正式内容_5 菜单管理 start -->
			<div id="zs_content_5">
				<div class="location">
					<i class="icon"></i>
					<span class="info">您当前所在位置：后台中心&gt;&gt;菜单管理</span>
					<span class="add_menu" title="添加菜单"></span>
				</div>
				<div class="all_menus">
					<table class="table">
						<tr class="th">
							<th>ID</th>
							<th>菜单名</th>
							<th>所含文章数</th>
						</tr>
					</table>
					<div class="page">
						<a href="#" class="homepge">首页</a>
						<a href="#" class="prev">上一页</a>
						<a href="#" class="next">下一页</a>
						<a href="#" class="lastpage">尾页</a>
						当前<font color="red" class="nowpage"></font>/<font class="allpages"></font>页 共<font color="red" class="allcount">30</font>条记录
					</div>
				</div>
			</div>
			<!-- end 正式内容_5 菜单管理 -->
			
			<!-- ############################################################################## -->
			<!-- 正式内容_6 链接管理 start -->
			<div id="zs_content_6">
				<div class="location">
					<i class="icon"></i>
					<span class="info">您当前所在位置：后台中心&gt;&gt;链接管理</span>
					<span class="add_link" title="添加菜单"></span>
				</div>
				<div class="all_links">
					<table class="table">
						<tr class="th">
							<th>ID</th>
							<th>链接</th>
							<th>URL</th>
							<th>操作</th>
						</tr>
					</table>
					<div class="page">
						<a href="#" class="homepge">首页</a>
						<a href="#" class="prev">上一页</a>
						<a href="#" class="next">下一页</a>
						<a href="#" class="lastpage">尾页</a>
						当前<font color="red" class="nowpage"></font>/<font class="allpages"></font>页 共<font color="red" class="allcount">30</font>条记录
					</div>
				</div>
			</div>
			<!-- end 正式内容_6 链接管理 -->
			
			<!-- ############################################################################## -->
			<!-- 正式内容_7 注销登陆 start -->
			<div id="zs_content_7">
				<img src="${pageContext.request.contextPath}/images/loginout_logo.png" width="100" height="100" class="loginout_logo"/>
				<img src="${pageContext.request.contextPath}/images/loginout_load.gif" width="200" height="200" class="loginout_load"/>
				<span class="tip">稍等，正在安全注销。。。</span>
			</div>
			<!-- end 正式内容_7 注销登陆 -->
			
			<!-- ############################################################################## -->
			<!-- 正式内容_8 加载出错 start -->
			<div id="zs_content_8">
				<img src="${pageContext.request.contextPath}/images/yaom.png" alt=""/>
			</div>
			<!-- end 正式内容_8 注销登陆 -->
		</div>
		<div class="footer animated bounceInLeft">
			后台界面 V1.0 By Nothing<br/>
			&copy; 2015.08
		</div>
	</div>	
	
	<!-- 阴影start -->
	<div class="yy">
	</div>
	<!-- end 阴影 -->
	<!-- 删除用户提示框 start -->
	<div class="delete_tip_user">
		<img src="${pageContext.request.contextPath}/images/jg.png" width="30" height="30"/>
		<div class="right">
			<span>您确定要删除用户<font color="red" class="mention">adminadmin</font>吗？</span>
			<div class="btn">
				<div class="cancel_btn">取 消</div>
				<div class="ok_btn">确 定</div>
			</div>
		</div>
	</div>
	<!-- end 删除用户提示框 -->
	<!-- 批量删除文章提示框 start -->
	<div class="delete_tip_article_pl">
		<img src="${pageContext.request.contextPath}/images/jg.png" width="30" height="30"/>
		<div class="right">
			<span>您确定要删除ID为<font color="red" class="mention">adminadmin</font>的文章吗？</span>
			<div class="btn">
				<div class="cancel_btn">取 消</div>
				<div class="ok_btn">确 定</div>
			</div>
		</div>
	</div>
	<!-- end 批量删除文章提示框 -->
	<!-- 单个删除文章提示框 start -->
	<div class="delete_tip_article_dg">
		<img src="${pageContext.request.contextPath}/images/jg.png" width="30" height="30"/>
		<div class="right">
			<span>您确定要删除ID为<font color="red" class="mention">adminadmin</font>的文章吗？</span>
			<div class="btn">
				<div class="cancel_btn">取 消</div>
				<div class="ok_btn">确 定</div>
			</div>
		</div>
	</div>
	<!-- end 单个删除文章提示框 -->
	<!-- 预览文章 strat -->
	<div class="previewArticle">
		<div class="close" title="关闭预览">x</div>
		<div class="title"></div>
		<div class="content"></div>
	</div>
	<!-- end 预览文章 -->
	<!-- 信息提示框 start -->
	<div class="mess_tip">
		<img src="${pageContext.request.contextPath}/images/no.jpg" width="25" height="25"/>
		<div class="right">
			<span>温馨提示：</span>
			<div class="btn">
				<div class="ok_btn">确 定</div>
			</div>
		</div>
	</div>
	<!-- end 信息提示框 -->
	<!-- 批量删除评论提示框 start -->
	<div class="delete_tip_remark_pl">
		<img src="${pageContext.request.contextPath}/images/jg.png" width="30" height="30"/>
		<div class="right">
			<span>您确定要删除ID为<font color="red" class="mention">adminadmin</font>的评论吗？</span>
			<div class="btn">
				<div class="cancel_btn">取 消</div>
				<div class="ok_btn">确 定</div>
			</div>
		</div>
	</div>
	<!-- end 批量删除评论提示框 -->
	<!-- 单个删除评论提示框 start -->
	<div class="delete_tip_remark_dg">
		<img src="${pageContext.request.contextPath}/images/jg.png" width="30" height="30"/>
		<div class="right">
			<span>您确定要删除ID为<font color="red" class="mention">adminadmin</font>的评论吗？</span>
			<div class="btn">
				<div class="cancel_btn">取 消</div>
				<div class="ok_btn">确 定</div>
			</div>
		</div>
	</div>
	<!-- end 单个删除评论提示框 -->
	<!-- 添加Menu start -->
	<div class="addMenu animated bounceIn">
		<form id="addMenu">
			<input type="text" class="menu_name" placeholder="请输入菜单名" id="menu_name" name="menu.menu_name"/>
			<input type="text" style="display:none"/>
			<input type="button" value="添 加" class="addBtn"/>
			<input type="button" value="取 消" class="cancelBtn"/>
			<img src="${pageContext.request.contextPath}/images/ok.png" alt="" width="25" height="25"/>
			<span>添加成功！</span>
		</form>
	</div>
	<!-- end 添加Menu -->
	<!-- 添加Link start -->
	<div class="addLink animated bounceIn">
		<form id="addLink">
			<input type="text" placeholder="请输入链接目标" id="link_name" name="link.link_name" maxlength="6"/>
			<input type="text" placeholder="请输入链接URL" id="link_url" name="link.link_url" maxlength="40"/>
			<input type="button" value="添 加" class="addBtn"/>
			<input type="button" value="取 消" class="cancelBtn"/>
			<img src="${pageContext.request.contextPath}/images/ok.png" alt="" width="25" height="25"/>
			<span>添加成功！</span>
		</form>
	</div>
	<!-- end 添加Link -->
</body>
</html>
