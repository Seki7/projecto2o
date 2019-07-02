package cn.edu.nju.o2o.dao;

import cn.edu.nju.o2o.BaseTest;
import cn.edu.nju.o2o.entity.Product;
import cn.edu.nju.o2o.entity.ProductCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static junit.framework.TestCase.assertEquals;

import java.util.Date;
import java.util.List;

public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    ProductCategoryDao productCategoryDao;

    @Test
    @Ignore
    public void testQueryProductCategory(){
        List<ProductCategory> list = productCategoryDao.queryProductCategory();
        assertEquals(0,(int)list.get(0).getPriority());
    }

    @Test
    @Ignore
    public void testAddProductCategory(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName("食品");
        productCategory.setCreateTime(new Date());
        productCategory.setLastEditTime(new Date());
        int i = productCategoryDao.addProductCategory(productCategory);
        assertEquals(1,i);
    }
}
