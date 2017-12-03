var userinfo = JSON.parse(sessionStorage.getItem("userinfo"));
if (userinfo == null) {
	$.ajax({
		url : '/blog/userinfo.do',
		contentType : 'application/json',
		success : function(data) {
			userinfo = data;
			sessionStorage.setItem("userinfo", JSON.stringify(data));
			if (userinfo.user_name != null) {
				$("#username").text(userinfo.user_name);
			} else {
				windows.location.href = "/index";
			}
		}
	});
}else{
	$(function(){
		$("#username").text(userinfo.user_name);
	})

}
function _index(){
	sessionStorage.removeItem("offsetTop");
	sessionStorage.removeItem("articlelist");
	window.location.href="/blog/index";
}

$(function() {
	$(".navlink .active").css("background", "#eee");
	$(".navlink .active").children().attr("href", "#");

	$("#logout").click(function() {
		sessionStorage.removeItem("userinfo");
		sessionStorage.removeItem("offsetTop");
		sessionStorage.removeItem("articlelist");
		$.ajax({
			url : '/blog/logout.do',
			contentType : 'application/json'
		});
	});

});