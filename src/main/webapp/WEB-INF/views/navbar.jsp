<%@page pageEncoding="UTF-8"%><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<script src="<%=basePath %>js/navbar.js"></script>
<style type="text/css">
body {
	padding-top: 70px;
	background-color: #F7FAFC;
}
</style>
	<div class="container">
		<div class="row">
			<nav class="navbar navbar-fixed-top">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<div class="col-md-8 col-md-offset-2">
					<a class="navbar-brand" href="#" style="padding-top: 10px;"><img
						src="<%=basePath %>img/lgo.gif" /></a>
					<div class="head-center">
						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav">
								<li><a href="javascript:;" onclick="_index();" style="padding-top: 20px;">首页
								</a></li>
								<li class="active"><a href="<%=basePath %>topic"
									style="padding-top: 20px;">话题</a></li>
							</ul>
							<!--<form class="navbar-form navbar-left" style="display: none;">
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Search">
									</div>
									<button type="submit" class="btn btn-default">Submit</button>
								</form>-->
							<ul class="nav navbar-nav navbar-right">
								<li><a href="<%=basePath %>write" style="padding-top: 20px;">写文章</a>
								</li>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown" role="button" aria-haspopup="true"
									aria-expanded="false"> <img src="<%=basePath %>img/sss.png" /> <span
										id="username" style="padding-left: 5px;"></span> <span
										class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a href="<%=basePath %>people">个人主页</a></li>
										<li><a id="logout" href="/blog/logout">退出</a></li>
									</ul></li>
							</ul>
						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</div>
			</nav>
		</div>
	</div>