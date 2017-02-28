<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> <s:property value="article.article_title"/> | Nothing 个人博客 </title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="Keywords" content="KeyWords, KeyWords"/>
	<meta name="description" content=""/>
	<meta name="author" content="Nothing"/>
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/title.png"/>  
	<!-- css -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/article_content.css"/><!-- 本页样式 -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.min.css"/><!-- 文字动画 -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/cj/jquery-ui-1.11.4.custom/jquery-ui.css"/><!-- 日历插件css -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/iconfont/ux_1437980259_0028071/iconfont.css"/><!-- iconfont图标css -->
	<!-- js -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script><!-- jQuery框架 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/cj/jquery-ui-1.11.4.custom/jquery-ui.js"></script><!-- 日历插件js -->	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/left_service.js"></script><!-- 左边部分时钟&机器人js代码 -->	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/article_content.js"></script><!-- 本页js -->
	<script type="text/javascript">
		//共js文件调用
		var path = "${pageContext.request.contextPath}";
		var menuId = <s:property value="article.menu.id"/>;//菜单变色
		var articleId = <s:property value="article.id"/>;//喜欢+1
		
		//点击搜索按钮进行搜索
		$(function(){
			$(".header .nav ul li .search_png").click(function(){
				var search_input = $(".header .nav ul li .search_input").val();
				if ($.trim(search_input) != "" && $.trim(search_input) != "search...") {
					$("#searchForm").submit();
				}
			});
		});
		/** 回车搜索 start */
		function getSearchResult(){
			var search_input = $(".header .nav ul li .search_input").val();
			if ($.trim(search_input) != "" ) {
				$(".header .nav ul li .search_png").click();
			}
		}
		/** end 回车搜索 */
		
		/** 智能机器人 start */
		function getAnswer(){
			var question = $("#question").val();
			$("#left_service ._4 ._4_2 ul").append(
				"<li class='s_people animated bounceInRight'>"+
					"<span class='s_question'>"+question+"</span>"+
					"<span class='sjx'></span>"+
					"<span class='iconfont'>&#xe629;</span>"+
				"</li>"		
			);
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/funtool/ajax_tuLingRobot.jspx",
				dataType : "json",
				data : $("#robotForm").serialize(),
				success : function(json){	
					$("#question").val("");
					$("#left_service ._4 ._4_2 ul").append(
						"<li class='s_robot animated bounceInLeft'>"+
							"<span class='iconfont'>&#x3432;</span>"+
							"<span class='sjx'></span>"+
							"<span class='s_anwser'>"+json.answer.text+"</span>"+
						"</li>"
					);
					$("#left_service ._4 ._4_2").scrollTop($("#left_service ._4 ._4_2")[0].scrollHeight);
				},
				error : function(){
					alert("ERROR!");
				}
			});
		}
		/** end 智能机器人 */
	</script>
</head>
<body>
	<!-- container start -->
	<div class="container">
		<!-- header start -->
		<div class="header">
			<embed src="${pageContext.request.contextPath}/images/bg_2.swf" width="960" wmode="transparent"/>
			<img src="${pageContext.request.contextPath}/images/text_1.png" class="text_1_png animated zoomIn"/>
			<div class="nav">
				<ul>
					<li><a href="${pageContext.request.contextPath}/article/qt_index.jspx">首页</a></li>
					<s:iterator value="menus_five">
						<li class="li_hover"><a href="${pageContext.request.contextPath}/article/qt_getArticlesByMenuId.jspx?menuId=<s:property value="id"/>"><s:property value="menu_name"/></a></li>
					</s:iterator>
					<li class="last_child">
						<form action="${pageContext.request.contextPath}/article/qt_getSearchArticles.jspx" method="post" id="searchForm">
							<input type="text" value="search..." class="search_input" onblur="if(this.value=='')this.value='search...';" onfocus="if(this.value=='search...')this.value='';" name="searchContent" autocomplete="off" onkeypress="if (event.keyCode == 13 && this.value != ''){ getSearchResult();}"/>
							<input type="text" style="display:none;"/>
							<div class="search_png"></div>
						</form>
					</li>
				</ul>
			</div>
		</div>
		<!-- end start -->
		<!-- content start -->
		<div class="content">
			<!-- start left_content -->
			<div class="left_content">
				<div class="box_1">
					<div class="box_1_top">
						<p class="head_info">博主资料</p>
					</div>
					<img src="${pageContext.request.contextPath}/images/me.jpg" width="180" height="180" class="photo"/>
					<div class="box_1_content">
						<p>博主：<s:property value="user.reallyname"/></p>
						<p>行业：IT</p>
						<p>网名：<s:property value="user.nickname"/></p>
						<p>QQ：761328790</p>
						<p>E-mail：<s:property value="user.email"/></p>
						<p>Tel：<s:property value="user.tel"/></p>
						<p>个人主页：<s:property value="user.website"/></p>
						<p>毕业院校：<s:property value="user.school"/></p>
						<p>专业：<s:property value="user.marjor"/></p>
						<p>职业：<s:property value="user.job"/></p>
					</div>
				</div>
				<div class="box_2">
					<div class="box_2_top">
						<p class="head_info">近期文章</p>
					</div>
					<div class="box_2_content">
						<ul>
							<s:iterator value="articles_five">
								<li><a href="${pageContext.request.contextPath}/article/qt_getArticleContentByArticleId.jspx?articleId=<s:property value="id"/>"><s:property value="article_title"/></a></li>
							</s:iterator>
						</ul>
					</div>
				</div>
				<div class="box_3">
					<div class="box_3_top">
						<p class="head_info">更多分类</p>
					</div>
					<div class="box_3_content">
						<ul class="more_menu">
							<s:iterator value="menus">
								<li style="background-color:#15a287;"><a href="${pageContext.request.contextPath}/article/qt_getArticlesByMenuId.jspx?menuId=<s:property value="id"/>"><s:property value="menu_name"/></a></li>
							</s:iterator>
						</ul>
					</div>
				</div>
				<div class="box_4">
					<div class="box_4_top">
						<p class="head_info">当前日历</p>
					</div>
					<div class="box_4_content" id="datepicker">
					</div>					
				</div>
				<div class="box_5">
					<div class="box_5_top">
						<p class="head_info">友情链接</p>
					</div>
					<div class="box_5_content">
						<ul>
							<s:iterator value="links">
								<li><a href="<s:property value="link_url"/>"><s:property value="link_name"/></a></li>
							</s:iterator>
						</ul>
					</div>
				</div>
			</div>
			<!-- end left_content -->
			<!-- start right_content -->
			<div class="right_content">
				<div class="box_1">
					<div class="box_1_top">
						<p class="head_info">文章摘要</p>
					</div>
					<div class="box_1_content_1">
						<div class="_1">
							<s:property value="article.article_title"/>
						</div>
						<div class="_2">
							<ul>
								<li>
									<span class="iconfont">&#xe621;</span>
									<span><s:property value="article.menu.menu_name"/></span>
								</li>
								<li>
									<span class="iconfont">&#xe629;</span>
									<span><s:property value="article.user.nickname"/></span>
								</li>
								<li>
									<span class="iconfont">&#x3435;</span>
									<span><s:property value="article.article_timeaxis"/></span>
								</li>
								<li>
									<span class="iconfont">&#xe60d;</span>
									<span>(<s:property value="article.article_remarknum"/>)</span>
								</li>
								<li>
									<span class="iconfont">&#xe62a;</span>
									<span>(<s:property value="article.article_like"/>)</span>
								</li>
							</ul>
						</div>
					</div>
					<div class="box_1_content_2">
						<s:property value="article.article_content" escapeHtml="false"/>
					</div>
					<div class="box_1_content_3">
						转载时请注明：Nothing | 个人博客 <span class="iconfont">&#xe606;</span><s:property value="article.article_title"/>
					</div>


					


					<div class="box_1_content_4">
						<div class="bdsharebuttonbox">
							<a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
							<a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
							<a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
							<a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
							<a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
							<a href="#" class="bds_more" data-cmd="more"></a>
						</div>

						<div class="_1">
							<div class="left">
								<span class="iconfont">&#xe62a;</span>
								喜欢(<span class="like"><s:property value="article.article_like"/></span>)
							</div>
							<div class="right">
								<span class="iconfont">&#xf000d;</span>
								<span>分享</span>
							</div>
							<div class="or">
								or
							</div>
						</div>
					</div>
					<div class="box_1_content_5">
						<div class="left">
							<span class="iconfont">&#xe608;</span>
							<a href="${pageContext.request.contextPath}/article/qt_getArticleContentByArticleId.jspx?articleId=<s:property value="article_prev.id"/>"><s:property value="article_prev.article_title"/></a>
						</div>
						<div class="right">							
							<a href="${pageContext.request.contextPath}/article/qt_getArticleContentByArticleId.jspx?articleId=<s:property value="article_next.id"/>"><s:property value="article_next.article_title"/></a>
							<span class="iconfont">&#xe606;</span>
						</div>
					</div>
				</div>
				<div class="box_2">
					<div class="box_2_top">
						<p class="head_info">文章评论</p>
					</div>
					<!-- 评论列表 start -->
					<div class="really_remark">
						<ul class="wc_ul">
							<s:iterator value="remarks">
								<li class="wc_li">
									<img src="${pageContext.request.contextPath}/images/ykpl.png" width="40" height="40" title="游客" class="yk_img"/>
									<span class="sjx"></span>
									<div class="remark_content">
										<span class="_1">
											<s:property value="remark_content" escapeHtml="false"/>
										</span>
										<span class="_2">
											<ul>
												<li title="评论人昵称">
													<span class="iconfont">&#xe629;</span>
													<span><s:property value="remark_username"/></span>
												</li>
												<li title="评论时间">
													<span class="iconfont">&#x3435;</span>
													<span><s:date name="remark_time" format="yyyy/mm/dd HH:mm:ss"/> (<s:property value="remark_timeaxis"/>)</span>
												</li>										
											</ul>
										</span>
									</div>
								</li>
							</s:iterator>
						</ul>
					</div>
					<!-- end 评论列表 -->
					<div class="box_2_content_1">
						<div class="textarea_box" contentEditable="true" id="textareabox_content_jy"></div>
						<!-- 隐式form -->
						<form id="addRemarkForm">
							<input type="hidden" id="add_remark_content" name="remark.remark_content"/>
							<input type="hidden" id="add_remark_username" name="remark.remark_username"/>
							<input type="hidden" id="add_remark_usermail" name="remark.remark_useremail"/>
							<input type="hidden"  name="remark.article.id" value="<s:property value="article.id"/>"/>
						</form>
					</div>
					<div class="box_2_content_2">
						<div class="left">
							<div class="hover" title="展开表情">
								<span class="iconfont">&#xe604;</span>
								<span class="text">表情</span>
							</div>
							<span class="face">
								<ul class="face_ul">
									<li><img src="${pageContext.request.contextPath}/images/face/01.gif" width="18" height="18"/></li>
									<li><img src="${pageContext.request.contextPath}/images/face/02.gif" width="18" height="18"/></li>
									<li><img src="${pageContext.request.contextPath}/images/face/03.gif" width="18" height="18"/></li>
									<li><img src="${pageContext.request.contextPath}/images/face/04.gif" width="18" height="18"/></li>
									<li><img src="${pageContext.request.contextPath}/images/face/05.gif" width="18" height="18"/></li>
									<li><img src="${pageContext.request.contextPath}/images/face/06.gif" width="18" height="18"/></li>
									<li><img src="${pageContext.request.contextPath}/images/face/07.gif" width="18" height="18"/></li>
									<li><img src="${pageContext.request.contextPath}/images/face/08.gif" width="18" height="18"/></li>
									<li><img src="${pageContext.request.contextPath}/images/face/09.gif" width="18" height="18"/></li>
									<li><img src="${pageContext.request.contextPath}/images/face/10.gif" width="18" height="18"/></li>
									<li><img src="${pageContext.request.contextPath}/images/face/11.gif" width="18" height="18"/></li>
									<li><img src="${pageContext.request.contextPath}/images/face/12.gif" width="18" height="18"/></li>
									<li><img src="${pageContext.request.contextPath}/images/face/13.gif" width="18" height="18"/></li>
									<li><img src="${pageContext.request.contextPath}/images/face/14.gif" width="18" height="18"/></li>
									<li><img src="${pageContext.request.contextPath}/images/face/15.gif" width="18" height="18"/></li>
									<li><img src="${pageContext.request.contextPath}/images/face/16.gif" width="18" height="18"/></li>
									<li><img src="${pageContext.request.contextPath}/images/face/17.gif" width="18" height="18"/></li>
									<li><img src="${pageContext.request.contextPath}/images/face/18.gif" width="18" height="18"/></li>
									<li><img src="${pageContext.request.contextPath}/images/face/19.gif" width="18" height="18"/></li>
								</ul>
							</span>
						</div>
						<div class="all_right">
							<img src="${pageContext.request.contextPath}/images/load.jpg" width="20" height="20" class="load_tip"/>
							<img src="${pageContext.request.contextPath}/images/ok.png" width="20" height="20" class="ok_tip"/>
							<span class="tip"></span>
							<div class="right">
								<span class="iconfont">&#xe767;</span>
								<span>发表评论</span>
							</div>
						</div>
					</div>
					<div class="box_2_content_3">
						<div class="left">
							嘿，您需要填写昵称和邮箱！
						</div>
						<div class="right">
							<label>昵称(必填)</label>
							<input type="text" id="pl_nickname" maxlength="10"/>
							</br>
							<label>邮箱(必填)</label>
							<input type="text" id="pl_mail" maxlength="20"/>
						</div>
					</div>
				</div>
			</div>
			<!-- end right_content -->
		</div>
		<!-- end content -->
		<!-- footer start -->
		<div class="footer">
			<a href="http://user.qzone.qq.com/761328790">Nothing</a>&nbsp;<span class="sx"></span>
			<a href="javascript:void(0)" onclick="alert('本系统暂未开放，敬请期待……')">联系作者</a>&nbsp;<span class="sx"></span>
			<a href="javascript:void(0)" onclick="alert('本系统暂未开放，敬请期待……')">反馈意见</a>&nbsp;<span class="sx"></span>
			<a href="javascript:void(0)" onclick="alert('本系统暂未开放，敬请期待……')">欢迎纠错</a>&nbsp;<span class="sx"></span>
			<a href="${pageContext.request.contextPath}/login_page.jspx">博客管理</a><br>
			<span>&copy;2015-08-08 For </span><a href="http://user.qzone.qq.com/1138544296">那次心动</a>
		</div>
		<!-- end footer -->
	</div>
	<!-- end container -->		
	<!-- 分享漂浮代码(仅仅这点js代码即可) start -->
	<script type="text/javascript" >
		var jiathis_config={
			summary:"",
			showClose:false,
			shortUrl:true,
			hideMore:false
		}
	</script>
	<script type="text/javascript" src="http://v3.jiathis.com/code_mini/jiathis_r.js?btn=r2.gif&move=1" charset="utf-8"></script>
	<!-- 分享漂浮代码 start -->
	<!-- 左边部分时钟代码 start -->
	<div id="left_service">
		<div class="_1 animated bounceInDown">		
		</div>	
		<div class="_2 animated bounceInDown">
			<img src="${pageContext.request.contextPath}/images/sz.jpg" width="30" height="30" class="img_click" title="点我查看时间"/>
			<img src="${pageContext.request.contextPath}/images/jqr.jpg" width="30" height="30" class="img_robot" title="智能机器服务"/>
		</div>
		<div class="_3">
			<canvas id="canvas" width="150" height="150"></canvas>
		</div>
		<div class="_4">
			<div class="_4_1">
				<span class="iconfont">&#x3432;</span><span>机器人服务</span>
			</div>
			<div class="_4_2">							
				<ul>
					<li class="s_robot">
						<span class="iconfont">&#x3432;</span>
						<span class="sjx"></span>
						<span class="s_anwser">你好~你好~你好~你好~你好~你好~你好~你好~</span>
					</li>
					<li class="s_people">
						<span class="s_question">请问请问请问请问请问请问请问请问请问请问请问请问？</span>
						<span class="sjx"></span>
						<span class="iconfont">&#xe629;</span>
					</li>
					<li class="s_robot">
						<span class="iconfont">&#x3432;</span>
						<span class="sjx"></span>
						<span class="s_anwser">你好</span>
					</li>
					<li class="s_people">
						<span class="s_question">请问？</span>
						<span class="sjx"></span>
						<span class="iconfont">&#xe629;</span>
					</li>
					<li class="s_robot">
						<span class="iconfont">&#x3432;</span>
						<span class="sjx"></span>
						<span class="s_anwser">你好~你好~你好~你好~你好~你好~你好~你好~</span>
					</li>
					<li class="s_people">
						<span class="s_question">请问请问请问请问请问请问请问请问请问请问请问请问？</span>
						<span class="sjx"></span>
						<span class="iconfont">&#xe629;</span>
					</li>
					<li class="s_robot">
						<span class="iconfont">&#x3432;</span>
						<span class="sjx"></span>
						<span class="s_anwser">你好</span>
					</li>
					<li class="s_people">
						<span class="s_question">请问？</span>
						<span class="sjx"></span>
						<span class="iconfont">&#xe629;</span>
					</li>
					<li class="s_robot">
						<span class="iconfont">&#x3432;</span>
						<span class="sjx"></span>
						<span class="s_anwser">你好~你好~你好~你好~你好~你好~你好~你好~</span>
					</li>
					<li class="s_people">
						<span class="s_question">请问请问请问请问请问请问请问请问请问请问请问请问？</span>
						<span class="sjx"></span>
						<span class="iconfont">&#xe629;</span>
					</li>
					<li class="s_robot">
						<span class="iconfont">&#x3432;</span>
						<span class="sjx"></span>
						<span class="s_anwser">你好</span>
					</li>
					<li class="s_people">
						<span class="s_question">请问？</span>
						<span class="sjx"></span>
						<span class="iconfont">&#xe629;</span>
					</li>
				</ul>
			</div>
			<form id="robotForm">
				<input type="text" onblur="if(this.value=='')this.value='Enter FAQ';" onfocus="if(this.value=='Enter FAQ')this.value='';" value="Enter FAQ" id="question" onkeypress="if (event.keyCode == 13 && this.value != ''){ getAnswer();}" name="question"/>
				<input type="text" style="display:none"/>
			</form>
		</div>
	</div>
	<!-- end 左边部分时钟代码 -->
	<!-- 百度分享 start -->
	<script>
		window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"24"},"share":{},"image":{"viewList":["qzone","tsina","tqq","weixin","renren"],"viewText":"分享到：","viewSize":"24"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","weixin","renren"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
	</script>
	<!-- end 百度分享 -->

</body>
</html>
