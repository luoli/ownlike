var Register = function(){
	return {
		setup : function(){
			$("#registerForm").submit(function(){
				$(".button", this).addClass("disabled");
			});
			$("#qqlogin, #weibologin").click(function(){
				window.open($(this).attr("href"),"binding_win","status=no,resizable=no,scrollbars=yes,personalbar=no,directories=no,location=no,toolbar=no,menubar=no,width=680,height=500,left=50,top=40");
				return !1;
			});
		}
	};
}();