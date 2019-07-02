package cn.edu.nju.o2o.service;

import cn.edu.nju.o2o.BaseTest;
import cn.edu.nju.o2o.dto.ProductExecution;
import cn.edu.nju.o2o.entity.Product;
import cn.edu.nju.o2o.entity.ProductCategory;
import cn.edu.nju.o2o.entity.Shop;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceTest extends BaseTest {
    @Autowired
    ProductService productService;

    @Test
    @Ignore
    public void testGetProductList(){
        Product productConditon = new Product();
        //productConditon.setProductName("咖啡");
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(2);
        productConditon.setProductCategory(productCategory);
        ProductExecution productExecution = productService.getProductList(productConditon,0,2);
        System.out.println(productExecution.getShopList().size());
        System.out.println(productExecution.getCount());
    }

    @Test
    @Ignore
    public void testAddNum(){
        productService.addNum(107,1);

    }

    @Test
    public void testAddProduct(){
        Product product = new Product();
        product.setProductName("test");
        product.setNumber(999);
        product.setNormalPrice(Double.valueOf("222"));
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(1);
        Shop shop = new Shop();
        shop.setShopId(1);
        product.setShop(shop);
        product.setProductCategory(productCategory);
        productService.addProduct(product);
    }
}
