package cn.edu.nju.o2o.dao;

import cn.edu.nju.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {
    /**
     * 列出商品类别
     * @return 商品类别列表
     */
    List<ProductCategory> queryProductCategory();

    /**
     * 增加商品类别
     * @return 影响行数 所以返回1是正常的
     */
    int addProductCategory(ProductCategory productCategory);
}
