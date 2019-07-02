function myLogin() {

    var url = "";
    if($('#character').val() == 1){
        url = "/user/checkLogin"
    }else if($('#character').val() == 2){
        url = "/shop/checkLogin";
    }else if($('#character').val() == 3){
        url = "/admin/checkLogin";
    }
    alert("what");
    var id = $('#id').val();
    var password = $('#password').val();

    $.ajax({
        type:"post",    // 请求类型
        url: url,    // 请求URL
        data:{id:id,password:password},    // 请求参数 即是  request.getParement()可以得到的字符串
        dataType:"json",    // 数据类型
        success:function(result){
            alert(("success"))// 成功返回的结果(响应)
        }
    });

}