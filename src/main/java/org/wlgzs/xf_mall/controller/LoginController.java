package org.wlgzs.xf_mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wlgzs.xf_mall.entity.User;
import org.wlgzs.xf_mall.service.LogUserService;
import org.wlgzs.xf_mall.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author:胡亚星
 * @createTime 2018-04-16 21:26
 * @description:用户查询控制层
 **/

@Controller
public class LoginController {
    @Resource
    LogUserService logUserService;

    @Resource
    UserService userService;

    @RequestMapping("/registered")
    public String register(User user){
        System.out.println("123");
        userService.save(user);
        return "redirect:/login";
    }

    /*
     * @author 胡亚星
     * @date 2018/4/19 21:20
     * @param
     * @return
     *用户实现登陆
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request,Model model, String user_name, String user_password){
        User user = logUserService.login(request,user_name,user_password);
        System.out.println(user);
        if(user != null){
            if(user.getUser_role().equals("管理员")){
                model.addAttribute("user",user);
                return "adminIndex";
            }else{
                model.addAttribute("user",user);
                return "index";
            }
        }else{
            model.addAttribute("msg","账号或密码错误");
            return "login";
        }
    }

    //用户退出
    @RequestMapping("cancellation")
    public String cancellation(HttpServletRequest request){
        logUserService.cancellation(request);
        return "login";
    }


}
