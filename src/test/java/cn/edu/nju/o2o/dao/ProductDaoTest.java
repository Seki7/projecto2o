package cn.edu.nju.o2o.dao;

import cn.edu.nju.o2o.BaseTest;
import cn.edu.nju.o2o.entity.Product;
import cn.edu.nju.o2o.entity.ProductCategory;
import cn.edu.nju.o2o.entity.Shop;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ProductDaoTest extends BaseTest {
    @Autowired
    ProductDao productDao;
    @Test
    @Ignore
    public void testAddOneProduct(){
        Product product = new Product();
        ProductCategory productCategory = new ProductCategory();
        Shop shop = new Shop();
        for(int i = 0; i < 10; i++){
            shop.setShopId(1);
            productCategory.setProductCategoryId(4);
            product.setProductCategory(productCategory);
            product.setShop(shop);
            product.setProductName("药妆"+i);
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            product.setPriority(i);
            product.setDescription("test");
            product.setNormalPrice(200.0+i);
            product.setNumber(50+i);
            product.setStatus(1);
            productDao.addProduct(product);
        }
    }

    @Test
    @Ignore
    public void testQueryOneProduct(){
        Product product = productDao.queryOneProduct(26);
        System.out.println(product.getProductId());
        System.out.println(product.getProductName());
    }

    @Test
    @Ignore
    public  void testQueryProductList(){
        Product productCondition = new Product();
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(2);
        productCondition.setProductCategory(productCategory);
        productCondition.setProductName("咖啡");
        List<Product> productList =productDao.queryProductList(productCondition,0,5);
        int count = productDao.queryProductCount(productCondition);
        System.out.println(productList.size());
        System.out.println(count);

    }

    @Test
    public void testUpdateProduct(){
        Product product = new Product();
        product.setProductId(107);
        product.setNumber(0);
        productDao.updateProduct(product);

    }

}
