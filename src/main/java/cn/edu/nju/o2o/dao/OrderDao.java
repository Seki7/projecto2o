package cn.edu.nju.o2o.dao;

import cn.edu.nju.o2o.entity.Order;

import java.util.List;

public interface OrderDao {

    /**
     * 根据userId来检索订单列表
     * @param userId
     * @return
     */
    List<Order> queryOrdersByUserId(Integer userId);

    /**
     *
     * @param order
     * @return 影响的行数 1为正常
     */
    int addOneOrder(Order order);


    /**
     *
     * @return
     */
    List<Order> queryOrdersByShopId(Integer shopId);

    int updateOrder(Order order);

    Order queryOneOrder(Long orderId);
}
