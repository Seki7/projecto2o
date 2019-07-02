package cn.edu.nju.o2o.service;
import cn.edu.nju.o2o.BaseTest;
import cn.edu.nju.o2o.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static junit.framework.TestCase.assertEquals;

public class ShopServiceTest extends BaseTest {
    @Autowired
    ShopService shopService;

    @Test
    public void testCheckLogin(){
        Shop shop = shopService.checkLogin(0,"123123");
        assertEquals("超市", shop.getShopName());
    }
}
