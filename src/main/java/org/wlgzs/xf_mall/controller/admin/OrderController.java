package org.wlgzs.xf_mall.controller.admin;

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
@RequestMapping("AdminOrdersController")
public class OrderController {
    @Resource
    OrdersService ordersService;

    //后台订单列表
    @RequestMapping("/allProductOrdersLists")
    public String list(Model model) {
        List<Orders> orders=ordersService.getOrdersList();
        model.addAttribute("orders", orders);
        return "admin/adminOrdersList";
    }

    //跳转至修改订单页面
    @RequestMapping("/toChangeProductOrders")
    public String toEdit(Model model, Long id) {
        Orders order=ordersService.findOrdersById(id);
        model.addAttribute("order", order);
        return "admin/adminEditOrders";
    }

    //后台通过订单号查询订单
    @RequestMapping("/changeProductOrders")
    public String findOrders(Model model,String order_number){
        List<Orders> orders = ordersService.findOrdersByOrderNumber(order_number);
        model.addAttribute("orders",orders);
        model.addAttribute("order_number",order_number);
        return "admin/adminOrdersList";
    }

    //后台修改订单信息
    @RequestMapping("/changeProductOrders")
    public String edit(Orders order) {
        ordersService.edit(order);
        return "redirect:/OrderController/allOrders";
    }

    //后台删除订单
    @RequestMapping("/deleteOrder")
    public String delete(Long id){
        ordersService.delete(id);
        return "redirect:/OrderController/allOrders";
    }

    //订单详情
    @RequestMapping("/orderDetails")
    public String orderInfo(Model model, Long id) {
        Orders order=ordersService.findOrdersById(id);
        model.addAttribute("order", order);
        return "admin/adminOrderInfo";
    }
/*

    //下单(添加订单)页面
    @RequestMapping("/OrdersController/toAddOrder")
    public String toAdd() {
        return "admin/adminAddOrder";
    }
*/

   /* //下单时自动添加订单
    @RequestMapping("/OrdersController/AddOrder")
    public String add(Orders order) {
        ordersService.save(order);
        return "redirect:/OrdersController/OrderInfo";
    }*/

    //通过用户名查询订单

    //后台通过订单号查询订单
    @RequestMapping("/userFindOrder")
    public String userFindOrders(Model model,String order_number){
        List<Orders> orders = ordersService.findOrdersByOrderNumber(order_number);
        model.addAttribute("orders",orders);
        model.addAttribute("order_number",order_number);
        return "/userOrdersList";
    }

    @RequestMapping("/findUserOrder")
    public String UserOrders(Model model,String user_name){
        List<Orders> orders = ordersService.findOrdersByUserName(user_name);
        model.addAttribute("orders",orders);
        model.addAttribute("user_name",user_name);
        return "/userOrdersList";
    }
}
