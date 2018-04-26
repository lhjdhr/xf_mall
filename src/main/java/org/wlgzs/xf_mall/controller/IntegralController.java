package org.wlgzs.xf_mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wlgzs.xf_mall.entity.UserIntegral;
import org.wlgzs.xf_mall.service.UserIntegralService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/4/21 22:11
 * @Description:积分管理
 */
@Controller
public class IntegralController {
    @Resource
    UserIntegralService userIntegralService;
    @RequestMapping("/IntegralController/toChangeIntegralController")
    public String toEdit(Model model, Long id) {
        UserIntegral userIntegral=userIntegralService.findUserIntegralById(id);
        model.addAttribute("userIntegral", userIntegral);
        return "admin/adminEditUserIntegral";
    }

    @RequestMapping("/AdminUserController/adminUserList")
    public String list(Model model) {
        List<UserIntegral> users=userIntegralService.getUserIntegral();
        model.addAttribute("users", users);
        return "admin/adminUserList";
    }
}
