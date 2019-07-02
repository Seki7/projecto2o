package cn.edu.nju.o2o.service;

import cn.edu.nju.o2o.entity.Order;

import java.util.List;

public interface OrderService {
    boolean createOrder(Order order);

    List<Order> getOrderByUserId(Integer userId);

    List<Order> getOrderByShopId(Integer shopId);

    Order updateOrder(Order order);
}
