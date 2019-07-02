
function getUserList() {
    $.ajax({
        async : false,    //表示请求是否异步处理
        type : "get",    //请求类型
        url : "/shop/getOrder",//请求的 URL地址
        dataType : "json",//返回的数据类型
        success: function (data) {
            handleList(data.list);
            //handleAdmin(data.admin);

        }

    });
}

// function handleAdmin(data){
//     $('#admin').text(data.adminName);
//
// }
// <div class="span1">订单ID</div>
//     <div class="span1">用户ID</div>
//     <div class="span1">用户名</div>
//     <div class="span1">商品ID</div>
//     <div class="span1">商品名称</div>
//     <div class="span1">单价</div>
//     <div class="span1">数量</div>
//     <div class="span1">总价</div>//xiedaozheli
//     <div class="span2">创建时间</div>//TODO
//     <div class="span2">最后修改时间</div>
//     <div class="span1">状态</div>
function handleList(data){
    var html = '';
    data.map(function(item,index) {
        var status;
        html +=  '<div class="sui-row"> <div class="span1">' + item.orderId +'</div> <div class="span1">'
            + item.userId + '</div>  <div class="span1">'+ item.userName + '</div>  <div class="span1">' + item.productId +
            '</div> <div class="span1">' + item.productName + '</div> <div class="span1">' + item.normalPrice + '</div> <div class="span1">'
            + item.productNumber + '</div> <div class="span1">'+ item.totalPrice + '</div> <div class="span2">'+ new Date(item.createTime).toLocaleString()
            + '</div>';
        if(item.status == "0"){
            status = "未处理";
            html += '<div class="span1">'+ status + '</div> <botton  onclick = "validate('+ item.orderId + ');" class="sui-btn btn-small btn-success">发货</botton>'
                       + ' <botton onclick = "invalidate('+ item.orderId+ ');" class="sui-btn btn-small btn-danger">拒绝</botton>'+'</div>'
        }else if(item.status == '1'){
            status = "已发货";
            html += '<div class="span1">'+ status +'</div></div>';
        }else {
            status = "已拒绝";
            html += '<div class="span1">'+ status +'</div></div>';
        }

    });//+ '</div> <div class="span2">'+ new Date(item.lastEditTime).toLocaleString()
    $('#userInfo').html(html);
    //<div class="span1">'+ status + '</div> <botton  onclick = "validate('+ item.userId+ ');" class="sui-btn btn-small btn-success">发货</botton>'
    //             + ' <botton onclick = "invalidate('+ item.userId+ ');" class="sui-btn btn-small btn-danger">拒绝</botton>'+'</div>'

}

function validate(orderId){
    var r=confirm("确定发货吗？")
    if (r==true)
    {
        $.ajax({
            type:"post",    // 请求类型
            url:"/shop/validateOrder",    // 请求URL
            data:{orderId:orderId},    // 请求参数 即是  request.getParement()可以得到的字符串
            dataType:"json",    // 数据类型
            success:function(result){    // 成功返回的结果(响应)
                alert(result.msg);
                window.location.reload();
            }
        });
    }


}

function invalidate(orderId){
    var r=confirm("确定拒绝吗？")
    if (r==true){
        $.ajax({
            type:"post",    // 请求类型
            url:"/shop/invalidateOrder",    // 请求URL
            data:{orderId:orderId},    // 请求参数 即是  request.getParement()可以得到的字符串
            dataType:"json",    // 数据类型
            success:function(result){    // 成功返回的结果(响应)
                alert(result.msg);
                window.location.reload();
            }
        });
    }

}


$(document).ready(function () {
    getUserList();  //页面加载完成就执行该方法
});