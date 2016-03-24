/**
 * Created by Yc on 2016/3/24.
 */
$(function(){
    $('#btn-login').click(function () {
        var username = $('#userName').val(),pwd = $('#userPassword').val();
        username=username.trim();pwd=pwd.trim();
        var _t = $(this).prop('disabled',true);
        $.post('do.jsp',{act:'login',name:username,pwd:pwd}, function (text) {
            text = text.trim();
            _t.prop('disabled',false);
            if(text!=1){
                $('#alert').show().find('strong').text(text);
            }else{
                location.href = "game.jsp";
            }
        })
    })

})
