function addProduct(){
    var productName = $('#productName').val();
    var normalPrice = $('#normalPrice').val();
    var number = $('#number').val();
    var productCategoryId = $('#productCategory').val();
    var description = $('#description').val();
    var r = /^\+?[1-9][0-9]*$/;　　//正整数
    if(productName == null || normalPrice == null || number == null || productCategoryId == 0 || description == null){
        alert("任意一项不能为空");
        return;
    }
    if(!r.test(number) || isNaN(number)){
        alert("数量非法");
        return;
    }
    if( normalPrice <= 0 || isNaN(normalPrice)){
        alert("价格非法");
        return;
    }


    $.ajax({
        type:"post",    // 请求类型
        url:"/shop/addProduct",    // 请求URL
        data:{productName:productName, normalPrice:normalPrice, number:number, productCategoryId:productCategoryId,description:description},    // 请求参数 即是  request.getParement()可以得到的字符串
        dataType:"json",    // 数据类型
        success:function(result){    // 成功返回的结果(响应)
            alert(result.msg);
            window.location.reload();
        }
    });

}