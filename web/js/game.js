/**
 * Created by Yc on 2016/3/24.
 */
$.fn.makeBoxes = function (r,c) {
    //var item = "";
    //var cnt= 0;
    $(this).empty();
    for(var i = 0;i<r;i++){
        for(var j = 0;j<c;j++){
            $(this).append("<div i='"+i+"' j='"+j+"' class='boxer-item boxer-click'>"+"</div>");
        }
        $(this).append("<div style='clear: left;'></div>")
    }
    $('.boxer-item').click(function () {
        $(this).expandAdjacent();
    })
    boxer.step = 0;
    $('#step').text(0);
    middleBoxer();
}

$.boxer = function (i,j) {
    return $("[i="+i+"]").filter("[j="+j+"]");
};



$.fn.expandAdjacent = function () {
    var go = function (i,j) {
        if (i < 0 || i >= boxer.level || j < 0 || j >= boxer.level) return;
        $.boxer(i, j).toggleClass('boxer-active boxer-click');
    }
    var i = parseInt($(this).attr('i')),j = parseInt($(this).attr('j'));
    if(!$(this).hasClass('boxer-click')) return;
    go(i,j);go(i-1,j);go(i,j-1);go(i+1,j);go(i,j+1);
    $('#step').text(++boxer.step);
    if(isSuccess()){
        $.post("do.jsp",{act:'uploadpoint',lev:boxer.level,step:boxer.step},function(d){
            d = JSON.parse(d);
            $('#best').html('<span> Level: '+ d.point +', Step: '+ d.step+', Time: '+ d.date+'</span>')
        });
        boxer.container.makeBoxes(++boxer.level,boxer.level);
    }
};

function isSuccess() {
    for(var i = 0; i<boxer.level; i++){
        for(var j = 0; j<boxer.level; j++) {
            if(!$.boxer(i,j).hasClass('boxer-active'))
                return false;
        }
    }
    return true;
}
boxer={},boxer.level = 1;
function middleBoxer(){
    var boxContainerWidth = boxer.container.width();
    var windowWidth = $('.container-fluid').width();
    boxer.container.css("margin-left",(windowWidth-boxContainerWidth)>>1);
}
$(function () {
    boxer.container = $('#boxer-container');
    var boxContainer = boxer.container;
    boxContainer.makeBoxes(boxer.level,boxer.level);
    $(window).on('resize',middleBoxer).resize();
    
    $('#btn-say').click(function () {
        var content = $('#input-say').val().trim();
        if(content=='') return;
        $.post('do.jsp',{act:'say',content:content}, function (d) {
            d = JSON.parse(d);
            var html = '<div><h5 style="display:inline-block;margin: 0;margin-left: 2px;" class="text-primary">'+ d.username+'</h5>'
                        +'<time style="vertical-align:middle;margin-left:20px; ">'+ d.time+'</time>'
                        +'<blockquote>'+ d.content+'</blockquote>'
                        +'</div>'
            $('#say-container').prepend(html);
            $('#input-say').val('');
        })
    });
    $('#btn-exit').click(function () {
        if(confirm('exit? sure?'))
            $.post('do.jsp',{act:'exit'}, function () {
                location.href='login.jsp';
            })
    })
});
