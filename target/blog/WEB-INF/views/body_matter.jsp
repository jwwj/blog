<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"+"views/";
out.print(basePath);

%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<script src="static/jquery/jquery-3.2.1.js"></script>
		<script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" />
		<script src="js/navbar.js"></script>
		<script src="js/ajaxsetup.js"></script>
		<link href="css/head.css" rel="stylesheet" />
		<link href="css/index_head.css" rel="stylesheet" />
		<link href="css/topic.css" rel="stylesheet" />
		<script src="js/ajaxsetup.js"></script>
		<style type="text/css">
			body {
				padding-top: 70px;
			}
			
			.topic-background {
				margin: 14px 0px;
			}
			
			.img_size {
				width: 30px;
				height: 30px;
			}
			
			.user_name {
				padding-top: 40px;
				margin-left: 20px;
				font-weight: bold;
				font-size: large;
			}
			
			.reply {
				font-weight: normal;
				font-size: larger;
				float: left;
				padding-left: 52px;
			}
			
			.reply_2 {
				padding-left: 100px;
				margin-top: 20px;
				padding-top: 50px;
			}
		</style>
	</head>

	<body>
		<!--	æè¿°ï¼å¤´é¨æ ç­¾-->
		<div class="col-md-10 col-md-offset-1" ">
			<nav class="navbar navbar-fixed-top ">
					<!-- Brand and toggle get grouped for better mobile display -->
  				<div class="navbar-header ">
					<button type="button " class="navbar-toggle collapsed " data-toggle="collapse " data-target="#bs-example-navbar-collapse-1 " aria-expanded="false ">
        				<span class="sr-only ">Toggle navigation</span>
        				<span class="icon-bar "></span>
        				<span class="icon-bar "></span>
        				<span class="icon-bar "></span>
      				</button>
				</div>
		<div class="col-md-8 col-md-offset-2 ">
			<a class="navbar-brand " href="# " style="padding-top: 10px; "><img src="img/lgo.gif " /></a>
			<div class="head-center ">
				<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav navlink">
								<li ><a href="index.html" style="padding-top: 20px;">é¦é¡µ
								</a></li>
								<li ><a href="topic.html"
									style="padding-top: 20px;">è¯é¢</a></li>

							</ul>
					
							<ul class="nav navbar-nav navbar-right">
								<li><a href="article.html" style="padding-top: 20px;">
								<span class="glyphicon glyphicon-pencil" ></span>
								åæç« </a>
								</li>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown" role="button" aria-haspopup="true"
									aria-expanded="false"> <img src="img/sss.png" /> 
									<span id="username"
										style="padding-left: 5px;"></span> <span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a href="personal_homepage.html">ä¸ªäººä¸»é¡µ</a></li>

										<li><a id="logout" href="#">éåº</a></li>

									</ul></li>
							</ul>
						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</div>

			</nav>
		</div>
		<!-- 	æè¿°ï¼å¤´é¨æ ç­¾ç»æï¼ç¬¬äºæ¡æ¶å¼å§ -->
		<div class="row ">
			<div class="col-md-6 col-md-offset-3 ">	
				<div class="panel panel-default " style="border: 0px; ">
					<div class="page-header ">
  						<h1 style="text-align: center; ">Example page header</h1>
					</div>
					<div class="panel-body " >
					sadasd 
    				</div>
				</div>
			</div>
			<!--æè¿°ï¼è¯è®ºæ¡-->
				<div class="col-md-6 col-md-offset-3 " style="border-bottom:1px solid #E1E4E6;padding-bottom: 10px; ">
					<div>
  						<img src="img/tou.jpg " alt="... " class="img-circle img_size ">
						<span class="user_name ">å°å°é¥</span>
						<span class="reply ">æçè¯è®º,æçè¯è®º,æçè¯è®ºæçè¯è®ºæçè¯è®ºæçè¯è®ºæçè¯è®ºæçè¯è®º.æçè¯è®º,æçè¯è®º,æçè¯è®ºæçè¯è®ºæçè¯è®ºæçè¯è®ºæçè¯è®ºæçè¯è®º</span>
							<div class="reply_2" >
								<img src="img/tou.jpg " alt="... " class="img-circle img_size ">
								<span class="user_name ">å°å°é¥</span>
								<span class="reply "style="border-bottom:1px solid #E1E4E6;padding-bottom: 10px; ">æçè¯è®º,æçè¯è®º,æçè¯è®ºæçè¯è®ºæçè¯è®ºæçè¯è®ºæçè¯è®ºæçè¯è®º.æçè¯è®º,æçè¯è®º,æçè¯è®ºæçè¯è®ºæçè¯è®ºæçè¯è®ºæçè¯è®ºæçè¯è®º</span>
							</div>
							<a role="button" href="#" class="btn btn-primary btn1 " style="float: right; margin:20px 0px; " >æä¹è¯´ä¸å¥</a>
							<a role="button" href="#" class="btn  btn-success btn2 " style="float: right; margin:20px 20px; display: none; ">
								<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
							</a>
							<div class="reply1 " style="padding-left: 100px; margin-top: 20px; padding-top: 50px; display: none; ">
								
								<textarea class="form-control " rows="2 " style="width: 100%;resize: none; " id="cont "></textarea>
								
							</div>
					</div>
				</div>	
					
					
				
				
			
		</div>
	</body>
		
	<script>
	
	$(function(){
		$.ajax({
            url:'/blog/article/'+'8fa2e6b4-af07-4cc8-9e39-be08bfd61ea9',
            type:"get",
         //   data:'{art_id:"'+'8fa2e6b4-af07-4cc8-9e39-be08bfd61ea9'+'"}',
         //  	dataType:'json',
            contentType:"application/json ",
            success:function(data){
               console.log(date);
            },
        
        });        

	    
	});
	
	
		$(document).ready(function(){
			i=0;
			$(".btn1 ").click(function(){
				i++;
				if(i==1){
					$(".btn1").removeClass("btn-primary").addClass("btn-danger").text("");
					
					$("<span></span>").addClass("glyphicon glyphicon-remove").attr("aria-hidden","true")
					.appendTo(".btn1");
					$(".btn1 ");
				}
				else{
					$(".btn1").removeClass("btn-danger");
					$(".btn1").addClass("btn-primary");
					$(".btn1 ").text("æä¹è¯´ä¸å¥ ");
					
					i=0;
				}
				$(".btn2 ").toggle(),
				$(".reply1 ").toggle();
				
			});
		});
		
	</script>
</html>