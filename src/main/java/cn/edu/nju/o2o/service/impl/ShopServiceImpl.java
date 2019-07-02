package cn.edu.nju.o2o.service.impl;

import cn.edu.nju.o2o.dao.ShopDao;
import cn.edu.nju.o2o.entity.Shop;
import cn.edu.nju.o2o.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopDao shopDao;

    @Override
    public Shop checkLogin(Integer shopId, String password) {
        Shop shop = shopDao.queryOneShop(shopId);
        if(shop != null && shop.getPassword().equals(password)){
            return shop;
        }
        return null;
    }
}
