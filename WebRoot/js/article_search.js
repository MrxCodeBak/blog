$(function(){
	/* 日历插件 start */
	 $("#datepicker" ).datepicker({
		inline: true
	 });
	/* end 日历插件 */
	 
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
	if (articleSoTitle == "articleSos__NULL") {
		
		$(".content .right_content .box_2 .box_2_content .not_find").show();;
	}
});