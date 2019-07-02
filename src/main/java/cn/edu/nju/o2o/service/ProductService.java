package cn.edu.nju.o2o.service;

import cn.edu.nju.o2o.dto.ProductExecution;
import cn.edu.nju.o2o.entity.Product;

public interface ProductService {
    /**
     * 根据productCondition返回相应列表数据
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

    /**
     *
     * @param productId
     * @return
     */
    public Product queryOneProduct(Integer productId);

    public Product addNum(Integer productId, Integer addNum);

    public Product addProduct(Product product);

}
