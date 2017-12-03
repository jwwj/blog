
var json = {
			"art": [{
				"topic": "新闻",
				"art_id": "f2f5e926-ef21-4fc8-be48-684d95421988",
				"title": "文章标题1",
				"cont": "文章内容1",
				"user_name": "作者name",
				"reading": "阅读量",
				"like": "点赞人数"
			}, {
				"topic": "新闻",
				"art_id": "987645312",
				"title": "文章标题2",
				"cont": "文章内容",
				"user_name": "作者name",
				"reading": "阅读量",
				"like": "点赞人数"
			},
			{
				"topic": "新闻",
				"art_id": "987645312",
				"title": "文章标题2",
				"cont": "文章内容",
				"user_name": "作者name",
				"reading": "阅读量",
				"like": "点赞人数"
			},{
				"topic": "新闻",
				"art_id": "987645312",
				"title": "文章标题2",
				"cont": "文章内容",
				"user_name": "作者name",
				"reading": "阅读量",
				"like": "点赞人数"
			},{
				"topic": "新闻",
				"art_id": "123456789",
				"title": "文章标题3",
				"cont": "文章内容",
				"user_name": "作者name",
				"reading": "阅读量",
				"like": "点赞人数"
			}]
		};

		function Each_info() {
			$.each(json.art, function(i, item) {
				$('<dir></dir>').addClass('row').addClass('topic-background').addClass('b')
					.append($('<a ></a>').addClass('topic-headline').append(item.title))
					.append('</br></br>')
					.append($('<span ></span>').addClass('topic-link').append(item.reading))
					.append($('<span></span>').addClass('topic-author').append(item.user_name))
					.append($('<p style="line-height:1.7; letter-spacing:1.5px;"></p>').addClass('topic-content').append(item.cont))
					.append($('<a href="article/'+item.art_id+'"></a>').addClass('topic-show').append('显示全文'))
					.appendTo('#page');
			});
		}
		function Each_title(change) {
		var topic =  $("#topic").find(".active a").text();
			$.ajax({
				url:'/blog/topicnextpage',
				//data: JSON.stringify(topic),
				data:{
					'topic':topic,
					'change':change
				},
				dataType:"json",
				contentType:"application/json",
				type: "get",
				success: function(Data) {
					console.log(Data);
					//success();
				}
			});
		}
		$(function() {
			
			Each_title(1);
			Each_info();
			$(document).ready(function (){       
		        $(window).scroll(function(){   
		          if(window.innerHeight + $(document).scrollTop()+1 >= document.body.scrollHeight){   //下滑加载    	
		        	  Each_title(0);//topic没有换
		        	  Each_info();
		          }
		        });
			});
			$(".topic-background li").click(function() {
				$(".topic-background li.active").removeClass("active");
				$(this).addClass("active");
				$("#page").empty();
				Each_title(1);//topic已切换
				Each_info();
			});

		})
		

		$(function(){
		    var $sidebar=$(".right");
		    var offset=$sidebar.offset();
		    $(window).scroll(function(){
		        var scrollTop=$(window).scrollTop();
		        if(offset.top<scrollTop){
		            $sidebar.addClass("fixed");            
		        }else{
		            $sidebar.removeClass("fixed");
		        }
		    });
		    
		})
