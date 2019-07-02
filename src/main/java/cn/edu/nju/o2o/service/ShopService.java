package cn.edu.nju.o2o.service;

import cn.edu.nju.o2o.entity.Shop;

public interface ShopService {
    Shop checkLogin(Integer shopId, String password);
}
