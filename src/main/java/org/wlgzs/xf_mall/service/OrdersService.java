package org.wlgzs.xf_mall.service;

import org.wlgzs.xf_mall.entity.Orders;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/4/20 20:52
 * @Description:
 */
public interface OrdersService {
    List<Orders> getOrdersList();

    void edit(Orders orders);

    List<Orders> findOrdersByOrderNumber(String order_number);

    Orders findOrdersById(long id);

    void delete(long id);
}
