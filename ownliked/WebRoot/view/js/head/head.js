$(function(){
	/*绑定头部数据显示方式*/
	if(!userIdLogin){
		$("#navigation li").eq(0).hover(
			function(){
				$(this).find("ul").show();
			},
			function(){
				$(this).find("ul").hide();
			}
		);
	}
	$("#navigation li").eq(0).next().hover(
		function(){
			$(this).find("ul").show();
		},
		function(){
			$(this).find("ul").hide();
		}
	);
	if(userIdLogin){
		$("#navigation li").eq(0).next().next().hover(
			function(){
				$(this).find("ul").show();
			},
			function(){
				$(this).find("ul").hide();
			}
		);
	}
});