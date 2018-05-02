package org.wlgzs.xf_mall.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wlgzs.xf_mall.entity.Orders;
import org.wlgzs.xf_mall.entity.ProductEstimate;
import org.wlgzs.xf_mall.service.OrdersService;
import org.wlgzs.xf_mall.service.ProductEstimateService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Date;

/**
 * @Auther: 李晓珊
 * @Date: 2018/4/23 13:11
 * @Description:
 */
@RequestMapping("/EstimateController")
@Controller
public class ProductEstimateController extends HttpServlet {
    @Resource
    ProductEstimateService productEstimateService;
    @Resource
    OrdersService ordersService;
    //通过订单id跳转到评论界面
    @RequestMapping("/toAddEstimate")
    public String toAdd(Model model, Long id) {
        Orders order = ordersService.findOrdersById(id);
        model.addAttribute("order", order);
        return "addEstimate";
    }

    //进行评论(添加一条评论)
    @RequestMapping("/AddEstimate")
    public String AddEstimate(HttpServletRequest request){
        Map<String,String[]> properties = request.getParameterMap();
        ProductEstimate productEstimate = new ProductEstimate();
        try {
            BeanUtils.populate(productEstimate, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        productEstimate.setEstimate_time(date);
        productEstimateService.save(productEstimate);
        return "redirect:/OrderController/findUserOrder";
    }

    @RequestMapping("/toEditEstimate")
    public String toEdit(Model model, Long id) {
        ProductEstimate productEstimate = productEstimateService.findProductEstimateById(id);
        model.addAttribute("productEstimate", productEstimate);
        return "EditEstimate";
    }


}
