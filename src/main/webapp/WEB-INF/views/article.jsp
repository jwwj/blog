<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true" 
	import="com.coder.blog.model.Cmt"
	import="com.coder.blog.model.Article"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<script src="<%=basePath %>static/jquery/jquery-3.2.1.js"></script>
		<script src="<%=basePath %>static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<link href="<%=basePath %>static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" />
		<script src="<%=basePath %>js/ajaxsetup.js"></script>
		<link href="<%=basePath %>css/head.css" rel="stylesheet" />
		<link href="<%=basePath %>css/index_head.css" rel="stylesheet" />
		<link href="<%=basePath %>css/topic.css" rel="stylesheet" />
		<link href="<%=basePath %>css/article.css" rel="stylesheet" />
		<style>
		* {
			margin: 0;
			padding: 0;
		}
		
		li {
			list-style: none;
		}
		
		.j-curr {
			color: red;
		}
		
		#list, #pager {
			text-align: center;
		}
		
		#list{
			text-align: center;
		}
		
		#pager li {
			display: inline-block;
			padding: 0 5px;
			cursor: pointer;
		}
		
		#list li {
			display: inline-block;
			padding: 0 5px;
			cursor: pointer;
		}
		
		</style>
</head>
<%@ include file="navbar.jsp"%>
<body>

<%Article ac = (Article)request.getAttribute("article"); %>
<div id='art_id' style='display:none;'><%=ac.getArt_id() %></div>
	<div class="row ">
			<div class="col-md-6 col-md-offset-3 ">	
				<div class="panel panel-default " style="border: 0px; box-shadow: 0 1px 1px rgba(0,0,0,0);">
					<div class="page-header ">
  						<h1 style="text-align: center; "><%out.println(ac.getTitle()); %></h1>
					</div>
					<div class="panel-body " >
					<%out.println(ac.getCont()); %>
    				</div>
				</div>
			</div>
	<%

 //   out.println(request.getAttribute("article").toString()); 
//	out.println(ac.getUser_name());
	//out.println("ac.getAbs():" + ac.getAbs());//文章摘要
	//out.println("ac.getCont():" + ac.getCont());//文章内容
	//out.println("ac.getLike():" + ac.getLike());//点赞人数
	//out.println("ac.getReading():" + ac.getReading());//阅读量
	//out.println("ac.getTitle():" + ac.getTitle());//文章标题
	//out.println("ac.getTopic():" + ac.getTopic());//话题
	//out.println("ac.getUser_id():" + ac.getUser_id());//用户id
	
	%>
			<!--描述：评论框-->
			<div class="col-md-6 col-md-offset-3 " style=" padding-bottom: 10px; ">
				<div>
  					<img src="<%=basePath %>img/tou.jpg " alt="... " class="img-circle img_size " />
  					<input type="text" class="form-control text1"  placeholder="写下你的评论">
  					<br/>
  					<a href="#" class="btn2" role="button">取消</a>
  					<a href="#" class="btn1" role="button">评论</a>
				</div>
			</div>	
			<!-- 1级评论 -->
					<div id="list">
    <ul>	<% int i=1; int cont=0; %>
			<%for (Cmt cmt : ac.getCmtlist()) { 
					i++; cont++;	
			%>	
        <li name="li1">
			<div class="col-md-6 col-md-offset-3 ">
				<div class="user">
  					<img src="<%=basePath %>img/tou.jpg " alt="... " class="img-circle img_size "style="float:left;margin-right:20px;"/>
  					<span class="userid"><%=cmt.getUser_name() %></span>
  					<br/><br/>
  					<span class="usercont"><%=cmt.getCont() %></span>
  					<a href="javascript:void(0)" class="btn3" role="button">回复</a>
				</div>
			</div>
			<div class="reuser" id="list1">
			<ul>
			<%
				for (Cmt cmt2 : ac.getRepcmtlist()) { 
					if(cmt.getColnum()==cmt2.getColnum()) {					
			%><li name="li<%=i %>">
			<div class="col-md-5 col-md-offset-4 " style="background: #F7F8FA">			
  					<img src="<%=basePath %>img/tou.jpg " alt="... " class="img-circle img_size "style="float:left;margin-right:20px;"/>
  					<span class="userid"><%=cmt2.getUser_name() %></span>
  					<br/><br/>
  					<span class="usercont"><%=cmt2.getCont() %></span>
  					<a href='javascript:void(0)' class="rebtn" role="button">回复</a>							
			</div>
			</li>
				<%} %>
			<%} %>	
			<div class="col-md-5 col-md-offset-4 " style="background: #F7F8FA">
				<textarea class="form-control textarea" rows="2" >回复: <%out.print(cmt.getUser());%>:&nbsp;</textarea>
  				<a class="btn btn-default rebtn1" href="#" role="button">评论</a>
  				
  			</div>
  			<div class="col-md-5 col-md-offset-4 " style="background: #F7F8FA ">
  				<div id="pager<%=cont%>" style="margin-bottom: 10px;"></div>
  				</div>
  			</ul>
  			
  			</div>
  			</li>

		<%} %>
		    </ul>
</div>
<%
	//for (Cmt cmt : ac.getCmtlist()) {
	//	out.println("cmt.getColnum():" + cmt.getColnum());
	//	out.println("cmt.getCont():" + cmt.getCont());
	//	out.println("cmt.getUser():" + cmt.getUser());
	//}
	//for (Cmt cmt : ac.getRepcmtlist()) {
	//out.println("repcmt.getColnum():" + cmt.getColnum());
	//	out.println("repcmt.getRepnum():" + cmt.getRepnum());
	//	out.println("repcmt.getCont():" + cmt.getCont());
	//	out.println("repcmt.getUser():" + cmt.getUser());
	//}
%>
		<div class="col-md-6 col-md-offset-3 " style="margin-top:100px">
			
			<nav aria-label="Page navigation" style="text-align:center;">
  <ul class="pagination" id="pager">
  </ul>
</nav>
		</div>
	</div>
</body>
	<script>
		$(document).ready(function() {
			$.ajax({
				url : '/blog/adduserhistory.do',
				data:{
					'user_id':userinfo.user_id,
					'art_id':$("#art_id").text()
				},
				type:'get',
				dataType:'json',
				contentType:'application/json',
				success:function(data){
					
				}
			});
			
					$("body").css("background-color", "#fff");
					$('.text1').click(function() {
						$('.btn2').css("display", "block");
						$('.btn1').css("display", "block");
					});
					$('.btn2').click(function() {
						$('.btn2').css("display", "none")
						$('.btn1').css("display", "none");
					});
					$(".btn3").click(
							function() {

								$(this).parents(".col-md-6").next().toggle();
								$(this).parents(".col-md-6").next().find(
										".textarea").css("display", "none");
								$(this).parents(".col-md-6").next().find(
										".rebtn1").css("display", "none");
							});
					$(".rebtn").click(function() {
						$(this).parents(".reuser").find(".textarea").toggle();
						$(this).parents(".reuser").find(".rebtn1").toggle();
					});
					$(".pagination li").click(function(){
						
						$(this).siblings('li').removeClass('active');
						$(this).siblings('li').removeClass('disabled');
						if($(this).text()==1){
							$(this).addClass('active');
							$(this).prev('li').addClass('disabled');
						}
						else if($(this).text()==5){
							$(this).addClass('active');
							$(this).next('li').addClass('disabled');
						}
						else{
							$(this).addClass('active');
						}
					});
				});
				
	</script>
	<script>
var pager = function(){
    //公共函数
    function T$(id){
        return document.getElementById(id);
    }
    function T$$(tag){
        return document.getElementsByName(tag);
    }
    function T$$$(root,tag){
        return (root || document).getElementsByTagName(tag);
    }
    function $extend(object1,object2){
        for(var p in object2){
            object1[p] = object2[p];
        }
        return object1;
    }
    function $each(arr, callback, thisp) {
        if (arr.forEach){
            arr.forEach(callback, thisp);
        }else{
            for(var i = 0, len = arr.length; i < len; i++){
                callback.call(thisp, arr[i], i, arr);
            }
        }
    }
    
    //默认参数配置
    var defaultOptions = {
        elemShowCount:5,     //每页显示条数，默认为5条
        pageShowCount:4,     //显示的页码数目，默认显示4个页码
        showFirstPageBtnAndLastPageBtn:true,     //是否显示首页，尾页，默认显示
        showPrevBtnAndNextBtn:true,    //是否显示上一页，下一页，默认显示
        showPageNumTips:true,      //是否显示【1/7页】页面提示，默认显示
        showPageNum:true      //是否显示分页的数字，默认显示
    };
    
    //主类构造函数 参数说明：bid为元素容器div的ID,pid为分页容器div的ID,options为重写的默认配置参数
    var showPage = function(bid,pid,options,li_name){   
        var self = this;             
        if(!(self instanceof showPage)){
            return new showPage(bid,pid,options);
        }
        self.container = T$(bid);    //元素容器div
        self.pagerBox = T$(pid);    //翻页容器div
        if(!self.container){
            return;                     
        }
        self.options = $extend(defaultOptions,options||{});
        self.elem = T$$(li_name);     //需要分页的元素
       
        self.elemTotalCount = self.elem.length || 0;    //分页元素的总个数
        self.pageTotalCount = Math.ceil(self.elemTotalCount/self.options.elemShowCount) || 0;   //总页数
    };
    
    showPage.prototype = {
        constructor:showPage,
        //显示当页的元素内容，参数currPageNum为当前页码，从0开始
        showChangeElemCont: function(currPageNum){                     
            var self = this;     //this为showPage对象
            var nextPageCount = (currPageNum+1)*self.options.elemShowCount < self.elemTotalCount ? (currPageNum+1)*self.options.elemShowCount : self.elemTotalCount;     //判断为最后一页时，最后一页应该显示的数据条数
            for(var i=0;i<self.elemTotalCount;i++){
                self.elem[i].style.display = 'none';
            }
            for(var i=currPageNum*self.options.elemShowCount,l=nextPageCount;i<l;i++){
                self.elem[i].style.display = 'block';
            }
        },
        //显示当页的页码内容，参数currPageNum为当前页码，从0开始
        showChangePageCont: function(currPageNum){                      
            var self = this;      //this为showPage对象
            var firstPageHtml = prevPageHtml = pageLinkHtml = nextPageHtml = lastPageHtml = pageTips = '';     //firstPageHtml:首页   prevPageHtml:上一页  pageLinkHtml:页码表   nextPageHtml:下一页  lastPageHtml:尾页   pageTips:页码提示
            var startPage,endPage;        //startPage:页码列表中的第一页   endPage:页码列表中的最后一页
            var pageShowMidCount = Math.ceil(self.options.pageShowCount/2);      
            if(self.pageTotalCount <= self.options.pageShowCount){           //总页码数小于等于默认要显示的页码数时，直接在当前页面显示全部的页码
                startPage = 0;
                endPage = self.pageTotalCount-1;
            }else{
                if(self.options.pageShowCount%2 == 0){
                    if((currPageNum + 1 - pageShowMidCount) <= 0){       //首页
                        startPage = 0;
                        endPage = self.options.pageShowCount-1;
                    }else if((currPageNum + 1 + pageShowMidCount) >= self.pageTotalCount){            //尾页
                        startPage = self.pageTotalCount-1 - self.options.pageShowCount + 1;
                        endPage = self.pageTotalCount-1;
                    }else{
                        startPage = currPageNum - pageShowMidCount + 1;
                        endPage = currPageNum + pageShowMidCount;
                    }    
                }else{
                    if((currPageNum + 1 - pageShowMidCount) <= 0){       //首页
                        startPage = 0;
                        endPage = self.options.pageShowCount-1;
                    }else if((currPageNum + 1 + pageShowMidCount - 1) >= self.pageTotalCount){            //尾页
                        startPage = self.pageTotalCount-1 - self.options.pageShowCount + 1;
                        endPage = self.pageTotalCount-1;
                    }else{
                        startPage = currPageNum - pageShowMidCount + 1;
                        endPage = currPageNum + pageShowMidCount - 1;
                    }    
                }    
            }
            
            //显示首页尾页
            if(self.options.showFirstPageBtnAndLastPageBtn == true){

              	firstPageHtml='<li><a href="javascript:void(0)" aria-label="Previous"><span aria-hidden="true">首页</span></a></li>';
                lastPageHtml='<li><a href="javascript:void(0)" aria-label="Previous"><span aria-hidden="true">尾页</span></a></li>';    
            }
            //显示上一页下一页
            //if(self.options.showPrevBtnAndNextBtn == true){
             //   if(currPageNum != 0) prevPageHtml = '<li>上一页</li>';       //首页不显示上一页
             //   if(currPageNum != self.pageTotalCount-1) nextPageHtml = '<li>下一页</li>';      //尾页不显示最后一页
            //}
            //显示页码数字链接
            for(var i=startPage,l=endPage;i<=l;i++){
                if(currPageNum==i){
                    pageLinkHtml += '<li class="active"><span>'+(i+1)+'<span class="sr-only">(current)</span></span></li>';
                }else{
                    pageLinkHtml += '<li ><a href="javascript:void(0)">'+(i+1)+'<span class="sr-only">(current)</span></a></li>';
                }
            }
            //显示页码提示信息
          //  if(self.options.showPageNumTips == true){
         //       pageTips = '<p style="color:#337AB7;">' +(currPageNum+1) + '/' + self.pageTotalCount + '</p>';
          //  }
            //拼出页码元素的整体内容
            self.pagerBox.innerHTML = firstPageHtml + prevPageHtml + pageLinkHtml + nextPageHtml + lastPageHtml + pageTips;    

            var elems = T$$$(self.pagerBox,'li');
            $each(elems, function(o, i) {
                o.onclick = function() {
                    if(o.innerText == '首页'){
                        self.showChangeElemCont(0);
                        self.showChangePageCont(0);
                    }else if(o.innerText == '尾页'){
                        self.showChangeElemCont(self.pageTotalCount-1);
                        self.showChangePageCont(self.pageTotalCount-1);
                    }else if(o.innerText == '上一页'){
                        self.showChangeElemCont(currPageNum-1);
                        self.showChangePageCont(currPageNum-1);
                    }else if(o.innerText == '下一页'){
                        self.showChangeElemCont(currPageNum+1);
                        self.showChangePageCont(currPageNum+1);
                    }else{
                        index = parseInt(o.innerText) - 1;
                        self.showChangeElemCont(index);
                        self.showChangePageCont(index);
                    }        
                }
            });
        }
    };
    
    return{
        onShowPage:function(bid,pid,options,li_name){
            //调用主类
            var st = new showPage(bid,pid,options,li_name);
            st.showChangeElemCont(0);
            st.showChangePageCont(0);
        }
    }
}();
console.log('pager'+1);
pager.onShowPage('list','pager',{elemShowCount:2,pageShowCount:3},"li1");
for(var i=1;i<=document.getElementsByName("li1").length || 0;i++){
pager.onShowPage('list1','pager'+i,{elemShowCount:2,pageShowCount:3},"li"+(i+1));
}


</script>
</html>