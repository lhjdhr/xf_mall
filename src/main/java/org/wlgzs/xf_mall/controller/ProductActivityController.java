package org.wlgzs.xf_mall.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.xf_mall.entity.ProductActivity;
import org.wlgzs.xf_mall.service.ProductActivityService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/5/2 00:26
 * @Description: 活动商品
 */
@RequestMapping("ActivityController")
@RestController
public class ProductActivityController {
    @Resource
    ProductActivityService productActivityService;
    /**
     * @author 阿杰
     * @param []
     * @return org.springframework.web.servlet.ModelAndView
     * @description 跳转至活动页面
     */
    @RequestMapping("/adminActivityProducts")
    public ModelAndView activityProductList(Model model){
        List<ProductActivity> activities = productActivityService.activityProductList();
        model.addAttribute("activities",activities);
        return new ModelAndView("productActivityList");
    }
}
