package cn.edu.nju.o2o.dao;

import cn.edu.nju.o2o.BaseTest;
import cn.edu.nju.o2o.entity.Order;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class OrderDaoTest extends BaseTest {
    @Autowired
    OrderDao orderDao;
    @Test
    @Ignore
    public void testAddOneOrder(){
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
        orderDao.addOneOrder(order);
    }

    @Test
    @Ignore
    public void testQueryOrdersByUserId(){
        List<Order> list = orderDao.queryOrdersByUserId(1);
        System.out.println(list.size());
        System.out.println(list.get(0).getProductName());
    }
    @Test
    @Ignore
    public void testQueryOrdersByShoprId(){
        List<Order> list = orderDao.queryOrdersByShopId(1);
        System.out.println(list.size());
        System.out.println(list.get(0).getProductName());
        System.out.println(list.get(0).getUserName());
    }

    @Test
    public void  testUpdateOrder(){
        Order order = new Order();
        order.setOrderId(19L);
        order.setStatus(2);
        orderDao.updateOrder(order);
    }
}
