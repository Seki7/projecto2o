$(document).ready(function () {
    getUser();  //页面加载完成就执行该方法
});

var Info;

function getUser() {
    $.ajax({
        async : false,    //表示请求是否异步处理
        type : "get",    //请求类型
        url : "/user/getUser",//请求的 URL地址
        dataType : "json",//返回的数据类型
        success: function (data) {
            Info = data;
            handleName(data.user);
            handleInfo(data.user);
            handleDefault(data.user);

        }

    });
}
function handleName(data){
    var name = data.userName;
    $('#user').text(name);
}
function handleInfo(data){
    var html = '';
    var gender = data.gender == 1 ? "男" : "女";
    html += '<div class="sui-row"> <div class="span1">' + data.userId +'</div> <div class="span1">' + data.userName + '</div> <div class="span1">'
    + data.age + '</div> <div class="span1">' + gender + '</div> </div>';
    $('#info').html(html);
}
function handleDefault(data){
    $('#changeName').val(data.userName);
    if(data.age == null){
        $('#changeAge').val(0);
    }else{
        $('#changeAge').val(data.age);
    }
}

function changeInfo(){
    var changedName = $('#changeName').val();
    var changedGender = $('#changeGender').val();
    var changedAge = $('#changeAge').val();

    $.ajax({
        type:"post",    // 请求类型
        url:"/user/updateInfo",    // 请求URL
        data:{userName:changedName,age:changedAge,gender:changedGender},    // 请求参数 即是  request.getParement()可以得到的字符串
        dataType:"json",    // 数据类型
        success:function(result){    // 成功返回的结果(响应)
            alert(result.msg);
            window.location.reload();
        }
    });

}

function changePw(){
    var orignPw = $('#orignPw').val();
    var changedPw1 = $('#changePw1').val();
    var changedPw2 = $('#changePw2').val();
    if(changedPw1 != changedPw2){
        alert("两次密码不一致");
        return;
    }
    $.ajax({
        type:"post",    // 请求类型
        url:"/user/updatePassword",    // 请求URL
        data:{before:orignPw,after:changedPw1},    // 请求参数 即是  request.getParement()可以得到的字符串
        dataType:"json",    // 数据类型
        success:function(result){    // 成功返回的结果(响应)
            alert(result.msg);
            window.location.reload();
        }
    });
}