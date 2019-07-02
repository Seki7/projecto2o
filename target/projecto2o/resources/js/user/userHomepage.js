$(document).ready(function () {
    getName();  //页面加载完成就执行该方法
});

function getName(){
    $.ajax({
        async : false,    //表示请求是否异步处理
        type : "get",    //请求类型
        url : "/user/getUser",//请求的 URL地址
        dataType : "json",//返回的数据类型
        success: function (data) {
            var name = data.user.userName;
            $('#user').text(name);
        }

    });

}