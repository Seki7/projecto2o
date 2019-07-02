package cn.edu.nju.o2o.service;

import cn.edu.nju.o2o.BaseTest;
import cn.edu.nju.o2o.entity.Order;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class OrderServiceTest extends BaseTest {
    @Autowired
    OrderService orderService;

    @Test
    @Ignore
    public void testCreateOrder(){
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setLastEditTime(new Date());
        order.setUserId(12);
        order.setUserName("小明");
        order.setProductId(4);
        order.setProductName("上岛咖啡");
        order.setShopId(1);
        order.setShopName("唯一超市");
        order.setNormalPrice(90.0);
        order.setProductNumber(2);
        order.setDiscount(0.9);
        order.setPromotionPrice(81.0);
        order.setTotalPrice(162.0);
        order.setStatus(1);
        orderService.createOrder(order);
    }

    @Test
    public void testUpdateOrdr(){
        Order order = new Order();
        order.setOrderId(28L);
        order.setStatus(2);
        orderService.updateOrder(order);

    }
}
