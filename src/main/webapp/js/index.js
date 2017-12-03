	var json = {
			"art": [{
				"topic": "新闻",
				"art_id": "d576f726-ccb8-11e7-8055-000c29452861",
				"title": "文章标题1",
				"cont": "文章内容1",
				"user_name": "作者name",
				"reading": "阅读量",
				"like": "点赞人数"
			}, {
				"topic": "新闻",
				"art_id": "6fddfb11-6dd2-4b14-8c77-d20a13b14bbc",
				"title": "文章标题2",
				"cont": "文章内容",
				"user_name": "作者name",
				"reading": "阅读量",
				"like": "点赞人数"
			}, {
				"topic": "新闻",
				"art_id": "f2f5e926-ef21-4fc8-be48-684d95421988",
				"title": "文章标题3",
				"cont": "文章内容",
				"user_name": "作者name",
				"reading": "阅读量",
				"like": "点赞人数"
			}, {
				"topic": "新闻",
				"art_id": "f2f5e926-ef21-4fc8-be48-684d95421988",
				"title": "文章标题3",
				"cont": "文章内容",
				"user_name": "作者name",
				"reading": "阅读量",
				"like": "点赞人数"
			}, {
				"topic": "新闻",
				"art_id": "f2f5e926-ef21-4fc8-be48-684d95421988",
				"title": "文章标题4",
				"cont": "文章内容",
				"user_name": "作者name",
				"reading": "阅读量",
				"like": "点赞人数"
			}]
		};
//	$(function(){
//	$.each(json.art, function(i, item) {
//		$('<dir></dir>').addClass('row').addClass('topic-background')
//				.addClass('b').append(
//						$('<span style="color: #8590a6; font-size: 15px"></span>').append(
//								'来自话题:')).append(
//						$('<span style="color: #8590a6; font-size: 15px"></span>').append(
//								item.topic)).append('</br></br>').append(
//						$('<a ></a>').addClass('topic-headline').append(
//								item.title)).append('</br></br>').append(
//						$('<span ></span>').addClass('topic-link').append(
//								item.reading)).append(
//						$('<span></span>').addClass('topic-author').append(
//								item.user_name)).append(
//						$('<p style="line-height:1.7; letter-spacing:1.5px;"></p>').addClass('topic-content').append(
//								item.cont)).append(
//						$('<a href="article/'+item.art_id+'"></a>').addClass(
//								'topic-show').append('显示全文')).appendTo(
//						'#page');
//	});
//	})

	
	var datanull=true;
	//var articlelist = [];
	
	
	
	$(document).ready(function() {
	//	art = sessionStorage.getItem('articlelist');
	//	//console.log(art);
	//	if(art!=null){
//			success(JSON.parse(art));
	//	}else{
			NextPage();
	//	}
		//var _offset = sessionStorage.getItem("offsetTop");//获取滚动位置
	//	console.log(_offset);
	//    $(document).scrollTop(_offset);
				$(window).scroll(function() {
					 
					//        sessionStorage.setItem("offsetTop", $(window).scrollTop());//保存滚动位置
					            
							if (window.innerHeight+ $(document).scrollTop() + 1 >= document.body.scrollHeight&& datanull) { // 下滑加载
								NextPage();
							}
				});
			})		
	
    function NextPage(){
					$.ajax({
							url:'/blog/indexnextpage',
							type:'get',
							contentType:'application/json',
							success:function(data){
								 
								  success(data);
								  
							}
						});
				
	}
    function success(data){
    	$.each(data, function(i, item) {
			 
			  if(item==null){
				  datanull = false;
					$('<dir></dir>').addClass('row').addClass('topic-background')
					.addClass('b').append(
							$('<span style="color: #fefefe;"></span>').append(
									'来自话题:')).append(
							$('<span style="color: #fefefe;"></span>').append(
									)).append('</br></br>').append(
							$('<a style="color: #fefefe; "></a>').addClass('topic-headline').append(
									)).append('</br></br>').append(
							$('<span style="color: #fefefe;"></span>').addClass('topic-link').append(
									)).append(
							$('<span style="color: #fefefe;"></span>').addClass('topic-author')).append(
							$('<p style="color: #fefefe; line-height:1.7; letter-spacing:1.5px;"></p>').addClass('topic-content').append(
									)).append(
							$('<a style="color: #fefefe;"></a>').addClass(
									'topic-show').append('    ')).appendTo(
							'#page');
				  return false;
			  }else{
				//  articlelist.push(item);
				 // sessionStorage.setItem('articlelist', JSON.stringify(articlelist)); 	
  			$('<dir></dir>').addClass('row').addClass('topic-background')
				.addClass('b').append(
						$('<span style="color: #8590a6; font-size: 15px"></span>').append(
								'来自话题:')).append(
						$('<span style="color: #8590a6; font-size: 15px"></span>').append(
								item.topic)).append('</br></br>').append(
						$('<a href="/blog/article/'+item.art_id+'" target="_blank"></a>').addClass('topic-headline').append(
								item.title)).append('</br></br>').append(
						$('<span ></span>').addClass('topic-link').append(
								item.reading)).append(
						$('<span></span>').addClass('topic-author').append(
								"作者："+item.user_name)).append(
						$('<p style="line-height:1.7; letter-spacing:1.5px;"></p>').addClass('topic-content').append(
								item.abs)).append(
						$('<a href="/blog/article/'+item.art_id+'" target="_blank"></a>').addClass(
								'topic-show').append('显示全文')).appendTo(
						'#page');
			  }
	});
    }
    
		$(".topic-background li").click(function() {
			$(".topic-background li.active").removeClass("active");
			$(this).addClass("active");
			$("#page").empty();
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

			//		  	$('<dir></dir>').addClass('row').addClass('topic-background').addClass('b')
			//					.append($('<a ></a>').addClass('topic-headline').append(item.art.topic))
			//					.append('</br></br>')
			//					.append($('<span ></span>').addClass('topic-link').append(item.art.reading))
			//					.append($('<span></span>').addClass('topic-author').append(item.art.user_name))
			//					.append($('<p></p>').addClass('topic-content').append(item.art.cont))
			//					.append($('<a></a>').addClass('topic-show').append('显示全文'))
			//					.appendTo('#page')
			Each_title();
			Each_topic();
		});
		//		function success(){
		//			$('<dir></dir>').addClass("row")
		//			.append('<dir></dir>').addClass('topic-background')
		//			.append('<dir></dir>').addClass('b')
		//			.append('<a></a>').append('xxs').appendTo('#page');

	//		}