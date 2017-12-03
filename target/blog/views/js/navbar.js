	var userinfo;
	$.ajax({
		url : '/blog/userinfo.do',
		contentType:'application/json',
		success:function(data){
			userinfo = data;
			$("#username").text(userinfo.user_name);
		}
	});
	
	
	
$(function() {
	$(".navlink .active").css("background","#eee");
	$(".navlink .active").children().attr("href","#");
	
	$("#logout").click(function() {
		$.ajax({
			url : '/blog/logout.do',
			contentType:'application/json'
		});
	});

	
});