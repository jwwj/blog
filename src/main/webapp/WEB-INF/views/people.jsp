<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		
		<script src="static/jquery/jquery-3.2.1.js"></script>
		<script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet"/>
		<script src="js/ajaxsetup.js"></script>
		<link href="css/head.css" rel="stylesheet" />
		<link href="css/index_head.css" rel="stylesheet" />
		<link href="css/topic.css" rel="stylesheet" />
		<style type="text/css">
			body {
				padding-top: 70px;
				background-color:#F7FAFC ;
			}
			.topic-background{
				margin: 14px 0px;
			}
		</style>
	</head>
	<body>
		<div class="container">
			<%@ include file="navbar.jsp"%>
		
    						<div style="margin-top: 20px;">
								<img src="img/tou.jpg" alt="..." class="img-circle">
								<span style=" padding-top: 40px; font-weight: bold; font-size: large; margin-left: 20px;">小小酥</span>											
       							<a class="btn btn-primary " href="/blog/edit" role="button" style="float: right; margin-right: 80px; margin-top: 20px;">编辑个人资料</a>
    							<br /><br />
    						</div>
  				
  					<!--
                      	作者：offline
                      	时间：2017-11-10
                      	描述：第二模块
                    -->
  					<div class="row" style="margin-top: 20px;">
  						<div class="col-md-8">
  						<ul class="nav nav-tabs">
  							<li role="presentation" class="active"><a href="#">动态</a></li>
  							<li role="presentation"><a href="#">文章</a></li>
  						</ul>
  						<div id="page">
  							
  						</div>
  						</div>
  						<!--
                          	作者：offline
                          	时间：2017-11-10
                          	描述：第三模块
                       -->
  						
  						<div class="col-xs-6 col-sm-3">
  							<ul class="nav nav-tabs">
								<li role="presentation" class="active"><a href="#">关注了</a></li>	
								<li role="presentation"><a href="#">关注者</a></li>
							</ul>
							
							<ul class="list-group">
  									<li class="list-group-item"><a href="#">关注的话题</a></li>
									<li class="list-group-item"><a href="#">关注的专栏</a></li>
									<li class="list-group-item"><a href="#">关注的问题</a></li>
 									<li class="list-group-item"><a href="#">关注的收藏夹</a></li>
 							</ul>
  						</div>
  						
					</div>
  				</div>
			
		</div>
	</body>
	<script>
	$(function(){
		$.ajax({
			url:'/blog/userhistroy',
			type:'get',
			contentType:'application/json',
			success:function(data){
				 console.log(data);
				  success(data);
				  
			}
		});
		
		$.ajax({
			url:'/blog/userarticle',
			type:'get',
			contentType:'application/json',
			success:function(data){
				 console.log(data);
				  success(data);
				  
			}
		});

	});
	
		var i=0;
		$(".nav-tabs li").click(function(){
						$(".nav-tabs li.active").removeClass("active");
						$(this).addClass("active");
					$("#page").empty();
		
			for(var j = 0; j < 20; j++) {
				
				$('<dir></dir>').addClass('row').addClass('topic-background').addClass('b')
					//.append($('<div></div>').addClass('topic-background'))
					//.append($('<div></div>').addClass('b'))
					.append($('<a></a>').addClass('topic-headline').append('标题' + i))
					.append('</br></br>')
					.append($('<span></span>').addClass('topic-link').append('浏览量' + i))
					.append($('<span></span>').addClass('topic-author').append('作者' + i))
					.append($('<p></p>').addClass('topic-content').append('正文' + i))
					.append($('<a></a>').addClass('topic-show').append('显示全文'))
					.appendTo('#page');
			}
			//			$.ajax({
			//				url:127.0.0.1:8080,
			//				data:{
			//					dir:'food',
			//					page:1
			//					uid:'xxs'
			//				},
			//				success:function(Data){
			//					success();
			//				}
			//			})
			i++;
		})
		//		function success(){
		//			$('<dir></dir>').addClass("row")
		//			.append('<dir></dir>').addClass('topic-background')
		//			.append('<dir></dir>').addClass('b')
		//			.append('<a></a>').append('xxs').appendTo('#page');
		//		}
	</script>
</html>
