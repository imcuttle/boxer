/**
 * Created by Yc on 2016/3/24.
 */
$(function(){
    $('#btn-register').click(function () {
        var username = $('#userName').val(),pwd = $('#userPassword').val(),pwd2=$('#userPassword2').val();
        username=username.trim();pwd=pwd.trim();pwd2 = pwd2.trim();
        var _t = $(this).prop('disabled',true);
        $.post('do.jsp',{act:'register',name:username,pwd:pwd,pwd2:pwd2}, function (text) {
            text = text.trim();
            _t.prop('disabled',false);
            $('#alert').show().find('strong').text(text);
        })
    })
})