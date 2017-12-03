<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true" 
%>
<html>
<head>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>article</title>
		
		<script src="static/jquery/jquery-3.2.1.js"></script>
		<script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" />
		<script src="js/ajaxsetup.js"></script>
		<script src="js/navbar.js"></script>
		<link href="css/head.css" rel="stylesheet" />
		<link href="css/index_head.css" rel="stylesheet" />
		<style type="text/css">
			body {
				padding-top: 70px;
				background-color: #F7FAFC;
			}
			
			.btn-info {
				background-color: #337AB7;
			}
		</style>

	</head>

	<body>
		
		<form id="passage">
		<div class="container">
		<div class="row">
		<%@ include file="navbar.jsp"%>
			<div class="col-md-8 col-md-offset-2">
						<div class="col-md-4 col-sm-3">

							<div class="caption">
								<h3>写博客</h3>
							</div>

						</div>
						<div class="col-md-4 col-sm-3  col-md-offset-4">

							<div class="caption">
								<p class="text-right" style="margin-top: 15px;">
									<a href="#" class="btn btn-primary" role="button" id="up">发布</a>
									<a href="#" class="btn btn-default" role="button" id="delete">删除</a>
								</p>
							</div>

						</div>
					</div>
				<div class="col-md-8 col-md-offset-2">
					<div class="thumbnail" style="border: 0px;">

						<div class="caption">
							<h1>添加标题</h1>
							<div class="input-group">
								<input id="title" type="text" class="form-control" aria-describedby="basic-addon1">
							</div>
							<h3>为你的博客添加分类</h3>
							<ul class="nav nav-pills" id="topic">
									<li role="presentation" class="active">
										<a href="#">新闻</a>
									</li>
									<li role="presentation">
										<a href="#">美食</a>
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

						</div>
					</div>
				</div>
					<div class="col-md-8 col-md-offset-2">
						<div class="thumbnail" style="border: 0px;">
							<div class="caption">
								<h4>博客正文</h4>
								<div id="cont" name="cont" style="width:100%"></div>
							</div>
						</div>
					</div>
		</form>
		
		
	</body>
	<script type="text/javascript" src="static/ueditor-1.4.3.3/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="static/ueditor-1.4.3.3/ueditor.all.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript" src="static/ueditor-1.4.3.3/ueditor.parse.js"></script>
    <script type="text/javascript">
        var ue = UE.getEditor('cont',{
        	toolbars: [
                ['fullscreen', 'source', '|', 'undo', 'redo', '|',
                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
                'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
                'directionalityltr', 'directionalityrtl', 'indent', '|',
                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
                'simpleupload', 'insertimage', 'emotion', 'scrawl','insertcode', 'webapp', 'pagebreak', '|',
                'horizontal', 'date', 'time', 'spechars', 'snapscreen','wordimage','preview', 'searchreplace']
            ]
        });
    </script>
    
	<script>
		$("#topic li ").click(function() {
						$("#topic li.active ").removeClass("active ");
						$(this).addClass("active ");
			});
	</script>
	<script>
		$("#up ").click(function(){
		      //function test(){
            //var form = new FormData(document.getElementById("passage "));
         	var article = {
         			"user_id":userinfo.user_id,
         			"title":$("input[id=title]").val(),
         			"topic": $("#topic").find(".active a").text(),
         			"cont":UE.getEditor('cont').getContent(),
         			"abs":UE.getEditor('cont').getContentTxt().substring(0,10),
         			"like":"0",
         			"reading":"0"
         			};
         		console.log(article);
         		
         		
         		$.ajax({
         			url : '/blog/addarticle.do',
         			data:JSON.stringify(article),
         			type:'post',
         			dataType:'json',
         			contentType:'application/json',
         			success:function(data){
         				console.log(data.code);
         			}
         		});
         	});
         	
         	
//       	
//       	$.ajax({
//              url:"www.baidu.com ",
//              type:"post",
//              data:JSON.stringify(userpage),
//              contentType:"application/json ",
//              success:function(data){
//                 success;
//              },
//          
//          });        
          //  get();//此处为上传文件的进度条
        //}
//		     });
//		 
 
   	
	</script>
 	
	
</html>
