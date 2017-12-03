<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true" %>

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
		<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" />
		<script src="js/ajaxsetup.js"></script>
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
				<div class="col-md-8 col-md-offset-2" style="background-color: white;">
					<div class="row">
						<div class="col-md-3" style="padding-top: 15px;"><img src="img/tou.jpg" alt="..." class="img-circle">
						</div>
						<span style="float: left; padding-top: 40px; font-weight: bold; font-size: large;">小小酥</span>
					</div>
					<br />
					<form class="form-horizontal" id="my_info" action="">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">性别</label>
							<div class="col-sm-10" style="margin-top: 5px;">
								<input type="radio" name="sex" value="男"/>男  
								<input type="radio" name="sex" value="女" />女 
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">一句话介绍</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="introduce" placeholder="填写">
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">居住地</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="city" placeholder="添加居住地">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">所在行业</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="profession" placeholder="请填写行业信息">
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">职业经历</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="career" placeholder="请填写职业经历">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">教育经历</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="edu" placeholder="请填写教育经历">
							</div>
						</div>

						<div style="text-align: center;">
							<input id="btn" class="btn btn-default" type="button" value="提交">
							<input class="btn btn-default" type="reset" value="重置">
						</div>
					</form>
				</div>
			</div>
		</div>
										
	</body>
	<script>
		$("#btn").click(function() {
			var userinfo = {"sex": $("input:radio[name=sex]:checked").val(),
					"introduce": $("input[id=introduce]").val(),
					"city": $("input[id=city]").val(),
					"profession": $("input[id=profession]").val(),
					"career": $("input[id=career]").val(),
					"edu": $("input[id=edu]").val()};
			$.ajax({
				url: "www.baidu.com",
				data: JSON.stringify(userinfo),
				dataType:"json",
				contentType:"application/json",
				type: "post",
				success: function(Data) {
					success();
				}
			});
		});
	</script>

</html>