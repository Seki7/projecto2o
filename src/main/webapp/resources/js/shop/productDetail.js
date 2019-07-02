$(document).ready(function () {
    var productId = getUrlParam('productId');
    getProduct(productId);
});

var product;
var user;


function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}



function getProduct(productId) {
    $.ajax({
        async : false,    //表示请求是否异步处理
        type : "get",    //请求类型
        url : "/shop/getProduct?productId="+productId,//请求的 URL地址
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
    html += '<div class="sui-row"> <div class="span1">'+data.productId+'</div> <div class="span1">'+data.productName+'</div> <div class="span1">'+data.normalPrice+'</div> <div class="span1">'+data.number+'</div> <div class="span1">'+data.description+'</div> <div class="span1">'+data.productCategory.productCategoryName+'</div> <div class="span2">'+new Date(data.createTime).toLocaleString()+'</div>  <div class="span2">'+ new Date(data.lastEditTime).toLocaleString()+'</div> </div>';

    $('#info').html(html);

}




function plus() {
    var addNum = $('#plusNum').val();
    // if(isNaN(addNum)){
    //     alert("请输入数字");
    //     return;
    // }
    var r = /^\+?[1-9][0-9]*$/;　　//正整数正则表达式
    if(!r.test(addNum)){
        alert("请输入正整数");
        return;
    }


    var productId = product.productId;
    $.ajax({
        async : false,    //表示请求是否异步处理
        type : "get",    //请求类型
        url : "/shop/addProduct?productId="+productId+"&addNum="+addNum,//请求的 URL地址
        dataType : "json",//返回的数据类型
        success: function (data) {
            alert(data.msg);
            window.location.reload();
        }
    });
}




//data:{productId:product.productId,productName:product.productName,userId:user.userId,userName:user.userName,buyNumber:buyNumber,normalPrice:product.normalPrice},  // 请求参数 即是  request.getParement()可以得到的字符串
