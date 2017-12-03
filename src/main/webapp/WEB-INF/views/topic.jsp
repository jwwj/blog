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
		<title>topic</title>
		
		<script src="static/jquery/jquery-3.2.1.js"></script>
		<script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" />
		<script src="js/navbar.js"></script>
		<script src="js/ajaxsetup.js"></script>
		<script src="js/topic.js"></script>
		<link href="css/topic.css" rel="stylesheet" />
		<link href="css/index_head.css" rel="stylesheet" />
		<link href="css/head.css" rel="stylesheet" />
		<style type="text/css">
			body {
				padding-top: 70px;
				background-color: #F7FAFC;
			}
		</style>

	</head>

	<body>

		<div class="container">

		<%@ include file="navbar.jsp"%>
			<div class="col-md-10 col-md-offset-1">
				<div class="row">
					<div class="col-md-9">
						<div class="row">
							<div class="topic-background">

								<ul class="nav nav-pills" id="topic">
									<li role="presentation" class="active">
										<a href="#">food</a>
									</li>
									<li role="presentation">
										<a href="#">lol</a>
									</li>
									<li role="presentation">
										<a href="#">军事</a>
									</li>
									<li role="presentation">
										<a href="#">自然科学</a>
									</li>
									<li role="presentation">
										<a href="#">科技</a>
									</li>
									<li role="presentation">
										<a href="#">互联网</a>
									</li>
								</ul>
								<div class="topic-ralign">
									<a href="#">热门排序</a>
									|
									<a href="#">时间排序</a>
								</div>

							</div>
						</div>
						<div id="page">
					
						</div>

					</div>
					<div id="main" class="col-md-3 ">
						<div class="right" style="height: 500px; background:white;">
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	
</html>