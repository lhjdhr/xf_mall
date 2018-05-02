package org.wlgzs.xf_mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wlgzs.xf_mall.entity.UserIntegral;
import org.wlgzs.xf_mall.service.UserIntegralService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: 李晓珊
 * @Date: 2018/4/21 22:11
 * @Description:积分管理
 */
@Controller
public class IntegralController {
    @Resource
    UserIntegralService userIntegralService;
/*
//通过id查询用户积分
    @RequestMapping("/IntegralController/toChangeIntegralController")
    public String toEdit(Model model, Long id) {
        UserIntegral userIntegral=userIntegralService.findUserIntegralById(id);
        model.addAttribute("userIntegral", userIntegral);
        return "admin/adminEditUserIntegral";
    }
* */

    //遍历用户积分明细
    @RequestMapping("/IntegralController/integralList")
    public String integralList(Model model,long user_id) {
        List<UserIntegral> integralLists = userIntegralService.getUserIntegral(user_id);
        model.addAttribute("integralLists", integralLists);
        return "UserIntegralList";
    }

    //遍历用户收入积分
    @RequestMapping("/IntegralController/UserIntegralIncome")
    public String incomeList(Model model,long user_id) {
        List<UserIntegral> integralLists = userIntegralService.getUserIntegralIncome(user_id);
        model.addAttribute("integralLists", integralLists);
        return "UserIntegralList";
    }

    //遍历用户支出积分
    @RequestMapping("/IntegralController/UserIntegralExpend")
    public String expendList(Model model,long user_id) {
        List<UserIntegral> integralLists = userIntegralService.getUserIntegralExpend(user_id);
        model.addAttribute("integralLists", integralLists);
        return "UserIntegralList";
    }

    //在购买的同时添加积分明细
    @RequestMapping("/IntegralController/addUserIntegral")
    public void AddUserIntegral(long user_id,long product_id,HttpServletRequest request) {
        userIntegralService.save(user_id,request);
    }

    //删除积分记录
    @RequestMapping("/UserIntegralController/deleteUserIntegral")
    public String delete(long userIntegral_id,long user_id) {
        userIntegralService.delete(userIntegral_id);
        return "redirect:/IntegralController/integralList?user_id="+user_id;
    }
}
