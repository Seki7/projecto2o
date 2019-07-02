
function getUserList() {
    $.ajax({
        async : false,    //表示请求是否异步处理
        type : "get",    //请求类型
        url : "/admin/getUserList",//请求的 URL地址
        dataType : "json",//返回的数据类型
        success: function (data) {
            handleList(data.list);
            handleAdmin(data.admin);

        }

    });
}

function handleAdmin(data){
    $('#admin').text(data.adminName);

}
function handleList(data){
    var html = '';
    data.map(function(item,index) {
        var gender;
        if(item.gender == "1"){
            gender = "男";
        }else{
            gender = "女";
        }
        var status = (item.status == "1") ? "有效" : "失效";
        var validId = "valid" + item.userId;
        var invalidId = "invalid" + item.userId;
        html +=  '<div class="sui-row"> <div class="span1">' + item.userId +'</div> <div class="span1">'
            + item.userName + '</div>  <div class="span1">'+ item.age + '</div>  <div class="span1">' + gender +
            '</div> <div class="span2">' + new Date(item.createTime).toLocaleString()+ '</div> <div class="span2">' + new Date(item.lastEditTime).toLocaleString() + '</div> <div class="span1">'
        + status + '</div>'+ ' <botton id = "'+validId+'" onclick = "validate('+ item.userId+ ');" class="sui-btn btn-small btn-success">启用</botton>'
            + ' <botton id = "'+ invalidId +'" onclick = "invalidate('+ item.userId+ ');" class="sui-btn btn-small btn-danger">禁用</botton>'+'</div>';
    });
    $('#userInfo').html(html);

}

function validate(userId){
    $.ajax({
        type:"post",    // 请求类型
        url:"/admin/validateUser",    // 请求URL
        data:{userId:userId},    // 请求参数 即是  request.getParement()可以得到的字符串
        dataType:"json",    // 数据类型
        success:function(result){    // 成功返回的结果(响应)
            alert(result.msg);
            window.location.reload();
        }
    });

}

function invalidate(userId){
    $.ajax({
        type:"post",    // 请求类型
        url:"/admin/invalidateUser",    // 请求URL
        data:{userId:userId},    // 请求参数 即是  request.getParement()可以得到的字符串
        dataType:"json",    // 数据类型
        success:function(result){    // 成功返回的结果(响应)
            alert(result.msg);
            window.location.reload();
        }
    });

}

function register(){
    var userName = $('#userName').val();
    // alert(userName);
    $.ajax({
        type:"post",    // 请求类型
        url:"/admin/register",    // 请求URL
        data:{userName:userName},    // 请求参数 即是  request.getParement()可以得到的字符串
        dataType:"json",    // 数据类型
        success:function(result){    // 成功返回的结果(响应)
            alert(result.msg);
            window.location.reload();
        }
    });

}

$(document).ready(function () {
    getUserList();  //页面加载完成就执行该方法
});