package cn.edu.nju.o2o.dao;

import cn.edu.nju.o2o.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    /**
     * 根据id查询一个商品
     * @param productId
     * @return 返回这个商品 如不存在返回null
     */
    Product queryOneProduct(Integer productId);

    /**
     * 插入一个商品
     * @param product
     * @return 返回影响的行数
     */
    int addProduct(Product product);


    /**
     * 分页查询商品，可输入的条件有 商品名（模糊查询） 商品类别
     * @param productCondition 查询条件
     * @param rowIndex 第几行开始查询
     * @param pageSize 查询条数
     * @return
     */
    List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex,
                                   @Param("pageSize") int pageSize);

    /**
     * 返回queryProductList的总数
     * @param productCondition
     * @return
     */
    int queryProductCount(@Param("productCondition") Product productCondition);


    int updateProduct(Product product);
}
