package cn.edu.nju.o2o.dto;

import cn.edu.nju.o2o.entity.Product;

import java.util.List;

public class ProductExecution {
    // 商品数量
    private int count;

    // 操作的商品(增删改时用)
    private Product product;

    // 商品列表(查询商品列表的时候使用)
    private List<Product> shopList;

    //是否有效
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getShopList() {
        return shopList;
    }

    public void setShopList(List<Product> shopList) {
        this.shopList = shopList;
    }
}
