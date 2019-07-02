package cn.edu.nju.o2o.dao;

import cn.edu.nju.o2o.entity.Shop;

public interface ShopDao {
    /**
     *
     * @param shopId
     * @return 返回查询的shop实体类
     */
    public Shop queryOneShop(Integer shopId);
}
