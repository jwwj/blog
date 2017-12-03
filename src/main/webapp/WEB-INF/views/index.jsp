<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<script src="static/jquery/jquery-3.2.1.js"></script>
<script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script src="js/ajaxsetup.js"></script>
<script src="js/index.js"></script>

<link href="css/index_head.css" rel="stylesheet" />
<link href="css/head.css" rel="stylesheet" />
<style type="text/css">

</style>
<link href="css/topic.css" rel="stylesheet" />

<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<% response.setHeader("Pragma","No-cache"); response.setHeader("Cache-Control","No-cache"); response.setDateHeader("Expires", -1); response.setHeader("Cache-Control", "No-store"); %>
</head>

<body>
<%@ include file="navbar.jsp"%>
		<div class="container">
			<div class="col-md-10 col-md-offset-1">
				<div class="row">
					<div class="col-md-9">
					<div id="page"></div>
					</div>
					<div id="main" class="col-md-3 ">
						<div class="right" style="height: 500px; background: white;">
						</div>
					</div>
				</div>
			</div>
		</div>
 </body>
</html>