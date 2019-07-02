$(document).ready(function () {
    getProduct();  //页面加载完成就执行该方法
    var i = pageSize+pageIndex;
});


var pageSize = 20;
var pageIndex = 0;
var count = 0;
var productName = null;
var productCategoryId = 0;

function getProduct() {
    var url = "/user/getProductList?pageIndex="+pageIndex+"&pageSize="+pageSize+"&productName="+productName+"&productCategoryId="+productCategoryId;
    $.ajax({
        async : false,    //表示请求是否异步处理
        type : "get",    //请求类型
        url : url,//请求的 URL地址
        dataType : "json",//返回的数据类型
        success: function (data) {
            count = data.count;
            handleProductList(data.list);
            handlePageBar();
        }
    });
}

function handleProductList(data) {
    var html = '';
    data.map(function(item,index) {
        html +=  '<div class="sui-row"> <div class="span1">' + item.productId +'</div> <div class="span1">'
            + item.productName + '</div>  <div class="span1">'+ item.normalPrice + '</div>  <div class="span1">' + item.number +
            '</div> <botton onclick="goDetailPage('+item.productId+')" class="sui-btn btn-bordered btn-success" >查看详情</botton></div>';
    });
    $('#productInfo').html(html);

}

function search() {
    productName = $('#productName').val();
    productCategoryId = $('#productCategory').val();
    getProduct();
}

function handlePageBar(data) {
    var html = '<ul>';
    var pageNumber = count/pageSize;
    var pageNumber = count%pageSize == 0 ? pageNumber : pageNumber+1;
    for(var i = 1; i <= pageNumber; i++){
        html+= '<li><a href="#" onclick="setIndex('+i+')">'+i+'</a></li>';
    }
    html += '</ul>';
    $('#pageBar').html(html);
}

function setIndex(i) {
    pageIndex = i;
    getProduct();
}

function goDetailPage(i) {
    window.location.href= "/user/productDetail?"+"productId="+i;

}