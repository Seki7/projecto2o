package cn.edu.nju.o2o.service.impl;

import cn.edu.nju.o2o.dao.ProductDao;
import cn.edu.nju.o2o.dto.ProductExecution;
import cn.edu.nju.o2o.entity.Product;
import cn.edu.nju.o2o.service.ProductService;
import cn.edu.nju.o2o.utils.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;
    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
        List<Product> productList = productDao.queryProductList(productCondition,rowIndex,pageSize);
        int count = productDao.queryProductCount(productCondition);
        ProductExecution productExecution = new ProductExecution();
        if(productList!=null){
            productExecution.setCount(count);
            productExecution.setShopList(productList);
            productExecution.setStatus(true);
        }else{
            productExecution.setStatus(false);

        }
        return productExecution;
    }

    @Override
    public Product queryOneProduct(Integer productId) {
        Product product = productDao.queryOneProduct(productId);
        return product;
    }

    @Override
    public Product addNum(Integer productId, Integer addNum) {
        Product product = productDao.queryOneProduct(productId);
        product.setNumber(product.getNumber()+addNum);
        product.setLastEditTime(new Date());
        productDao.updateProduct(product);
        return product;
    }

    @Override
    public Product addProduct(Product product) {
        product.setLastEditTime(new Date());
        product.setCreateTime(new Date());
        product.setStatus(1);
        product.setPriority(1);
        productDao.addProduct(product);
        return product;
    }
}
