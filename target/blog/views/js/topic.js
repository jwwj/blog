
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

