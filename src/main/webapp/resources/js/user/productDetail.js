$(document).ready(function () {
    var productId = getUrlParam('productId');
    getProduct(productId);
    getUser();
});

var product;
var user;
var buyNumber;

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}



function getProduct(productId) {
    $.ajax({
        async : false,    //表示请求是否异步处理
        type : "get",    //请求类型
        url : "/user/getProduct?productId="+productId,//请求的 URL地址
        dataType : "json",//返回的数据类型
        success: function (data) {
            product = data.product;
            handleProduct(data.product);
            handleBar(data.product);
        }
    });
}

function handleBar(data) {
    $('#productName').text(data.productName);
}

function handleProduct(data) {
    var html = '';
    html += '<div class="sui-row"> <div class="span1">'+data.productId+'</div> <div class="span1">'+data.productName+'</div> <div class="span1">'+data.normalPrice+'</div> <div class="span1">'+data.number+'</div> <div class="span1">'+data.description+'</div>  <div class="span2">'+new Date(data.createTime).toLocaleString()+'</div> <div class="span1">'+data.productCategory.productCategoryName+'</div><div class="span1">'+data.shop.shopName+'</div> </div>';

    $('#info').html(html);

}

function orderCheck() {
    buyNumber = $('#buyNumber').val();
    if(isNaN(buyNumber)){
        alert("请输入数字");
        return;
    }
    var limit = product.number;
    if(buyNumber > limit || buyNumber <=0){
        alert("选购数量非法或超过数量");
        return;
    }
    var password=$('#passwordCheck').val();
    var userId = user.userId;
    $.ajax({
        type:"post",    // 请求类型
        url:"/user/checkAgain",    // 请求URL
        data:{userId:userId,password:password},    // 请求参数 即是  request.getParement()可以得到的字符串
        dataType:"json",    // 数据类型
        success:function(result){    // 成功返回的结果(响应)
            if(result.status == "true"){
                submitOrder();
            }else{
                alert("密码错误");
            }
        }
    });


}
function getUser(){
    $.ajax({
        async : false,    //表示请求是否异步处理
        type : "get",    //请求类型
        url : "/user/getUser",//请求的 URL地址
        dataType : "json",//返回的数据类型
        success: function (data) {
            user = data.user;
        }

    });

}

function submitOrder() {
    $.ajax({
        type:"post",    // 请求类型
        url:"/user/createOrder",    // 请求URL
        data:{productId:product.productId,productName:product.productName,userId:user.userId, userName:user.userName,buyNumber:buyNumber,normalPrice:product.normalPrice},  // 请求参数 即是  request.getParement()可以得到的字符串
        dataType:"json",    // 数据类型
        success:function(result){
            if(result.msg === "success"){
                window.location.href = "/user/success";
            }else{
                alert(result.msg);
            }

        }
    });

}
//设置弹出框字段
function setting() {
    $('#numCheck').text($('#buyNumber').val());
    $('#nameCheck').text(product.productName);
    $('#passwordCheck').val("").focus();


}

//data:{productID:product.productId,productName:product.productName,userId:user.userId,userName:user.userName,buyNumber:buyNumber,normalPrice:product.normalPrice},  // 请求参数 即是  request.getParement()可以得到的字符串
