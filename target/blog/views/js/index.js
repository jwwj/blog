var json = {
		"art" : [ {
			"art_id" : "8fa2e6b4-af07-4cc8-9e39-be08bfd61ea9",
			"topic" : "文章标题1",
			"cont" : "文章内容1",
			"user_name" : "作者name",
			"reading" : "阅读量",
			"like" : "点赞人数"
		}, {
			"art_id" : "987645312",
			"topic" : "文章标题2",
			"cont" : "文章内容",
			"user_name" : "作者name",
			"reading" : "阅读量",
			"like" : "点赞人数"
		}, {
			"art_id" : "123456789",
			"topic" : "文章标题3",
			"cont" : "文章内容",
			"user_name" : "作者name",
			"reading" : "阅读量",
			"like" : "点赞人数"
		} ]
	};

	
	$(function() {

		
		$.each(json.art, function(i, item) {
			$('<dir></dir>').addClass('row').addClass('topic-background')
					.addClass('b').append(
							$('<a ></a>').addClass('topic-headline').append(
									item.topic)).append('</br></br>').append(
							$('<span ></span>').addClass('topic-link').append(
									item.reading)).append(
							$('<span></span>').addClass('topic-author').append(
									item.user_name)).append(
							$('<p></p>').addClass('topic-content').append(
									item.cont)).append(
							$('<a href="/blog/article/'+item.art_id+'"></a>').addClass(
									'topic-show').append('显示全文')).appendTo(
							'#page');
		});
	
		
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

	});
	//		}