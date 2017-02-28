$(function(){
	/* 日历插件 start */
	 $("#datepicker" ).datepicker({
		inline: true
	 });
	/* end 日历插件 */

	/* banner start */
	var index = 0;
	var playTime = 5000;
	var timer = null;
	var totalCount = $("#big_img_ul").find("li").length;
	var txArr = ["fadeInLeftBig", "fadeInDownBig", "fadeInUp", "flip", "rotateIn", "rotateInDownRight", "rollIn", "zoomIn"];

	//下一页功能
	$(".content .right_content .box_1 .box_1_right_banner .banner_big_img .next").on("click",function(){
		if(timer){
			clearInterval(timer);
		}
		index++;
		if(index >= totalCount){
			index=0;
		}
		banner_main(index);
	}).mouseout(function(){
		auto_play_banner();
	});

	//上一页功能
	$(".content .right_content .box_1 .box_1_right_banner .banner_big_img .prev").on("click",function(){
		if(timer)clearInterval(timer);
		index--;
		if(index < 0){
			index = totalCount-1;
		}
		banner_main(index);
	}).mouseout(function(){
		auto_play_banner();
	});

	//鼠标放在小图片上后的操作（大图片联动）
	$("#small_img_ul").find("li").mouseover(function(){
		if(timer){
			clearInterval(timer);
		}
		index = $(this).index();
		banner_main(index);			
	}).mouseout(function(){
		auto_play_banner();
	});
	
	//初始化自动轮播
	auto_play_banner();
	function auto_play_banner(){
		timer = setInterval(function(){
			if(index >= totalCount - 1){
				index = 0;
			}else{
				index++;
			}
			banner_main(index);
		},playTime);
	}
	
	//Main方法
	function banner_main(index){
		var $liObj = $("#big_img_ul").find("li").eq(index);
		var $toolbar = $("#small_img_ul").find("li").eq(index);
		//显示当前index图片，隐藏其他图片
		//alert(txArr[Math.floor(Math.random() * txArr.length)]);
		$liObj.fadeTo(800,1).attr("class","").addClass("animated " + txArr[Math.floor(Math.random() * txArr.length)]).siblings().hide();	
		//控制现图片的联动
		$toolbar.find("img").addClass("on");
		$toolbar.siblings().find("img").removeClass("on");//按钮联动
	}
	/* end banner */
	
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
});

