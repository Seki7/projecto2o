package cn.edu.nju.o2o.dao;

import cn.edu.nju.o2o.BaseTest;
import cn.edu.nju.o2o.entity.Shop;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.assertEquals;

public class ShopDaoTest extends BaseTest {
    @Autowired
    ShopDao shopDao;

    @Test
    @Ignore
    public void testQueryOneShop(){
        Shop shop = shopDao.queryOneShop(1);
        assertEquals("123123", shop.getPassword());
    }
}
