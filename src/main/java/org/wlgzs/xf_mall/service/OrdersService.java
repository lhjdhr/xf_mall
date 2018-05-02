package org.wlgzs.xf_mall.service;

import org.wlgzs.xf_mall.entity.Orders;

import java.util.List;

/**
 * @Auther: 李晓珊
 * @Date: 2018/4/20 20:52
 * @Description: 订单管理
 */
public interface OrdersService {
    List<Orders> getOrdersList();

    void edit(Orders orders);

    List<Orders> findOrdersByOrderNumber(String order_number);

    Orders findOrdersById(long id);

    void delete(long id);

    List<Orders> findOrdersByUserName(String user_name);
}
