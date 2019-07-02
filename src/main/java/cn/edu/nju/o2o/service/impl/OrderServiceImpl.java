package cn.edu.nju.o2o.service.impl;

import cn.edu.nju.o2o.dao.OrderDao;
import cn.edu.nju.o2o.entity.Order;
import cn.edu.nju.o2o.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Override
    public boolean createOrder(Order order) {
        order.setStatus(0);
        order.setCreateTime(new Date());
        order.setLastEditTime(new Date());
        order.setShopName("唯一超市");
        order.setShopId(1);
        int i = orderDao.addOneOrder(order);
        if(i == 1) return true;
        return false;
    }

    @Override
    public List<Order> getOrderByUserId(Integer userId) {
        List<Order> list = orderDao.queryOrdersByUserId(userId);
        return list;
    }

    @Override
    public List<Order> getOrderByShopId(Integer shopId) {
        List<Order> list = orderDao.queryOrdersByShopId(shopId);
        return list;
    }

    @Override
    public Order updateOrder(Order order) {
        order.setLastEditTime(new Date());
        orderDao.updateOrder(order);
        return orderDao.queryOneOrder(order.getOrderId());
    }


}
