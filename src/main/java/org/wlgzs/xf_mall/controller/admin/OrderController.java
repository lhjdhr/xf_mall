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
public class OrderController {
    @Resource
    OrdersService ordersService;


    /*@RequestMapping("/")
    public String index() {
        return "redirect:/adminOrdersList";
    }*/

    //后台订单列表
    @RequestMapping("/AdminOrdersController/allProductOrdersLists")
    public String list(Model model) {
        List<Orders> orders=ordersService.getOrdersList();
        model.addAttribute("orders", orders);
        return "admin/adminOrdersList";
    }
    //跳转至修改订单页面
    @RequestMapping("/AdminOrdersController/toChangeProductOrders")
    public String toEdit(Model model, Long id) {
        Orders order=ordersService.findOrdersById(id);
        model.addAttribute("order", order);
        return "admin/adminEditOrders";
    }

    @RequestMapping("/AdminOrdersController/adminFindOrders")
    public String findOrders(Model model,String order_number){
        List<Orders> orders = ordersService.findOrdersByOrderNumber(order_number);
        if(orders == null){
            System.out.println("asdad");
        }
        model.addAttribute("orders",orders);
        model.addAttribute("order_number",order_number);
        return "admin/adminOrdersList";
    }

    //后台修改订单信息
    @RequestMapping("/AdminOrdersController/changeProductOrders")
    public String edit(Orders order) {
        ordersService.edit(order);
        return "redirect:/AdminOrdersController/allProductOrdersLists";
    }

    @RequestMapping("/AdminOrdersController/adminDeleteOrders")
    public String delete(Long id){
        ordersService.delete(id);
        return "redirect:/AdminOrdersController/allProductOrdersLists";
    }
}
