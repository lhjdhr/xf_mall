package org.wlgzs.xf_mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlgzs.xf_mall.dao.OrdersRepository;
import org.wlgzs.xf_mall.entity.Orders;
import org.wlgzs.xf_mall.service.OrdersService;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/4/20 20:52
 * @Description:
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    //后台遍历订单
    @Override
    public List<Orders> getOrdersList() {
        return ordersRepository.findAll();
    }

    //后台按照id查找订单
    @Override
    public Orders findOrdersById(long id) {
        return ordersRepository.findById(id);
    }

    //按照订单号查询订单
    public List<Orders> findOrdersByOrderNumber(String order_number){
        return ordersRepository.findByOrderNumber(order_number);
    }

    //后台修改订单
    @Override
    public void edit(Orders orders) {
        ordersRepository.save(orders);
    }

    //后台删除订单
    @Override
    public void delete(long id) {
        ordersRepository.deleteById(id);
    }

    //按照用户名查询订单
    @Override
    public List<Orders> findOrdersByUserName(String user_name){
        return ordersRepository.findByUserName(user_name);
    }
}