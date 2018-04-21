package org.wlgzs.xf_mall.controller.admin;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wlgzs.xf_mall.entity.Orders;
import org.wlgzs.xf_mall.service.OrdersService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/4/20 20:46
 * @Description:
 */
@Controller
public class OrderController {
    @Resource
    OrdersService ordersService;

    //后台订单列表
    @RequestMapping("/OrderController/allOrders")
    public String list(Model model) {
        List<Orders> orders=ordersService.getOrdersList();
        model.addAttribute("orders", orders);
        return "admin/adminOrdersList";
    }
    //跳转至修改订单页面
    @RequestMapping("/OrdersController/toChangeOrders")
    public String toEdit(Model model, Long id) {
        Orders order=ordersService.findOrdersById(id);
        model.addAttribute("order", order);
        return "admin/adminEditOrders";
    }

    //后台通过订单号查询订单
    @RequestMapping("/OrderController/findOrder")
    public String findOrders(Model model,String order_number){
        List<Orders> orders = ordersService.findOrdersByOrderNumber(order_number);
        model.addAttribute("orders",orders);
        model.addAttribute("order_number",order_number);
        return "admin/adminOrdersList";
    }

    //后台修改订单信息
    @RequestMapping("/OrdersController/changeOrders")
    public String edit(Orders order) {
        ordersService.edit(order);
        return "redirect:/OrderController/allOrders";
    }

    //后台删除订单
    @RequestMapping("/OrderController/deleteOrder")
    public String delete(Long id){
        ordersService.delete(id);
        return "redirect:/OrderController/allOrders";
    }

    //订单详情
    @RequestMapping("/OrderController/orderDetails")
    public String orderInfo(Model model, Long id) {
        Orders order=ordersService.findOrdersById(id);
        model.addAttribute("order", order);
        return "admin/adminOrderInfo";
    }
}
