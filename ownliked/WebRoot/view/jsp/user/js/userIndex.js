var Register = function(){
	return {
		setup : function(){
			$("#registerForm").submit(function(){
				$(".button", this).addClass("disabled");
			});
		}
	};
}();