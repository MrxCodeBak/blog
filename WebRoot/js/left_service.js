$(function(){
	/** 位置摆放 start */
	var llq_width = $(window).width(); 
	//原本位置
	if(llq_width >= 1349){
		$("#left_service").css({"position":"fixed","top":0,"right":llq_width / 2 + 490});
	}else{
		$("#left_service").css({"position":"fixed","top":0,"left":0});
	}	
	//变化后位置
	$(window).resize(function() { 
		var llq_width = $(window).width(); 
		if(llq_width >= 1349){
			$("#left_service").css({"position":"fixed","top":0,"right":llq_width / 2 + 490});
		}else{
			$("#left_service").css({"position":"fixed","top":0,"left":0});
		}
	});
	/** end 位置摆放 */

	/** 点击时钟小圆图片 start */
	$("#left_service ._2 .img_click").click(function(){
		$("#left_service ._2 .img_click").hide();
		$("#left_service ._4").hide();
		$("#left_service ._3").addClass("animated bounceInUp").show();
		$("#left_service ._2 .img_robot").show();
		$("#left_service ._1").animate({"height":300});
	});
	/** end 点击时钟小圆图片*/

	/** 点击机器人小圆图片 start */
	$("#left_service ._2 .img_robot").click(function(){
		$("#left_service ._2 .img_robot").hide();
		$("#left_service ._3").hide();
		$("#left_service ._2 .img_click").show();
		$("#left_service ._4").addClass("animated bounceInUp").show();
		$("#left_service ._1").animate({"height":130});
		//清楚目前已有的li
		$("#left_service ._4 ._4_2 ul li").remove();
		//添加机器人导航信息
		$("#left_service ._4 ._4_2 ul").append(
				"<li class='s_robot animated bounceInLeft'>"+
					"<span class='iconfont'>&#x3432;</span>"+
					"<span class='sjx'></span>"+
					"<span class='s_anwser'>" +
					"你可能想问：<br/>" +
					"1 本站博主？<br/>" +
					"2 建站日期？<br/>" +
					"3 后台技术？<br/>" +
					"4 博主信息？<br/>" +
					"5 联系方式？" +
					"</span>"+
				"</li>"
			);
	});
	/** end 点击机器人小圆图片*/

	/** 时钟代码 start */
	// inner variables
	var canvas, ctx;
	var clockRadius = 75;
	var clockImage;
	 
	// draw functions :
	function clear() { // clear canvas function
		ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
	}
	 
	function drawScene() { // main drawScene function
		clear(); // clear canvas
	 
		// get current time
		var date = new Date();
		var hours = date.getHours();
		var minutes = date.getMinutes();
		var seconds = date.getSeconds();
		hours = hours > 12 ? hours - 12 : hours;
		var hour = hours + minutes / 60;
		var minute = minutes + seconds / 60;
	 
		// save current context
		ctx.save();
	 
		// draw clock image (as background)
		ctx.drawImage(clockImage, 0, 0, 150, 150);
	 
		ctx.translate(canvas.width / 2, canvas.height / 2);
		ctx.beginPath();
	 
		// draw numbers
		ctx.font = '12px Arial';
		ctx.fillStyle = '#000';
		ctx.textAlign = 'center';
		ctx.textBaseline = 'middle';
		for (var n = 1; n <= 12; n++) {
			var theta = (n - 3) * (Math.PI * 2) / 12;
			var x = clockRadius * 0.7 * Math.cos(theta);
			var y = clockRadius * 0.7 * Math.sin(theta);
			ctx.fillText(n, x, y);
		}
	 
		// draw hour
		ctx.save();
		var theta = (hour - 3) * 2 * Math.PI / 12;
		ctx.rotate(theta);
		ctx.beginPath();
		ctx.moveTo(-15, -5);
		ctx.lineTo(-15, 5);
		ctx.lineTo(clockRadius * 0.5, 1);
		ctx.lineTo(clockRadius * 0.5, -1);
		ctx.fill();
		ctx.restore();
	 
		// draw minute
		ctx.save();
		var theta = (minute - 15) * 2 * Math.PI / 60;
		ctx.rotate(theta);
		ctx.beginPath();
		ctx.moveTo(-15, -4);
		ctx.lineTo(-15, 4);
		ctx.lineTo(clockRadius * 0.8, 1);
		ctx.lineTo(clockRadius * 0.8, -1);
		ctx.fill();
		ctx.restore();
	 
		// draw second
		ctx.save();
		var theta = (seconds - 15) * 2 * Math.PI / 60;
		ctx.rotate(theta);
		ctx.beginPath();
		ctx.moveTo(-15, -3);
		ctx.lineTo(-15, 3);
		ctx.lineTo(clockRadius * 0.9, 1);
		ctx.lineTo(clockRadius * 0.9, -1);
		ctx.fillStyle = '#0f0';
		ctx.fill();
		ctx.restore();
	 
		ctx.restore();
	}
	 
	// initialization
	$(function(){
		canvas = document.getElementById('canvas');
		ctx = canvas.getContext('2d');
	 
		// var width = canvas.width;
		// var height = canvas.height;
	 
	clockImage = new Image();
	clockImage.src = path+'/images/sz.png';
	 
		setInterval(drawScene, 1000); // loop drawScene
	});
	/** end 时钟代码 */
});