$(document).ready(function () {
    getOrder();  //页面加载完成就执行该方法
});

function getOrder() {
    $.ajax({
        async : false,    //表示请求是否异步处理
        type : "get",    //请求类型
        url : "/user/getOrder",//请求的 URL地址
        dataType : "json",//返回的数据类型
        success: function (data) {
            handleList(data.list);
        }

    });
}
function handleList(data) {
    var html = '';
    data.map(function(item,index) {
        var status;
        if(item.status == "1"){
            status = "已发货";
        }else if(item.status == "0"){
            status = "待审核";
        }else{
            status = "被拒绝";
        }
        html +=  '<div class="sui-row"> <div class="span1">' + item.orderId +'</div> <div class="span1">'
            + item.productName + '</div>  <div class="span1">'+ item.shopName + '</div>  <div class="span1">' + item.normalPrice +
            '</div> <div class="span1">' + item.productNumber + '</div> <div class="span1">' + item.totalPrice + '</div> <div class="span2">'
            + new Date(item.createTime).toLocaleString() + '</div> <div class="span1">'+ status+'</div> </div>'
    });
    $('#currOrder').html(html);

}