package org.wlgzs.xf_mall.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wlgzs.xf_mall.entity.User;
import org.wlgzs.xf_mall.service.LogUserService;
import org.wlgzs.xf_mall.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

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

    @RequestMapping("/registeredMail")
    public String register(HttpServletRequest request, Model model, User user, String code) {
        //判断用户是否合法
        if (logUserService.validationUser(request, code)) {
            userService.save(user);
            return "redirect:/toRegistered";
        } else {
            model.addAttribute("msg", "验证码错误");
            return "redirect:/toRegistered";
        }
    }

    //去注册
    @RequestMapping("/toRegistered")
    public String toRegister() {
        return "sign-up";
    }

    //去登陆
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/registered")
    public String register(HttpServletRequest request){
        Map<String, String[]> properties = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        user.setUser_role("普通用户");
        userService.save(user);
        return "redirect:/login";
    }

    /**
     * @author 胡亚星
     * @date 2018/4/19 21:20
     * @param
     * @return
     *用户实现登陆
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model, String user_name, String user_password) {
        User user = logUserService.login(request, user_name, user_password);
        System.out.println(user);
        if (user != null) {
            if (user.getUser_role().equals("管理员")) {
                model.addAttribute("user", user);
                return "adminIndex";
            } else {
                model.addAttribute("user", user);
                return "index";
            }
        } else {
            model.addAttribute("msg", "账号或密码错误");
            return "login";
        }
    }

    //用户退出
    @RequestMapping("cancellation")
    public String cancellation(HttpServletRequest request) {
        logUserService.cancellation(request);
        return "login";
    }

    /**
     * @param
     * @return
     * @author 胡亚星
     * @date 2018/4/21 19:45
     * @Description:向邮箱发送验证码
     */
    @ResponseBody
    @RequestMapping(value = "sendEmail", method = RequestMethod.POST)
    public void sendEmail(HttpServletRequest request) {
        String user_mail = request.getParameter("user_mail");
        System.out.println("in-----------------");
        logUserService.sendEmail(request, user_mail);//发送
    }

    /**
     * @param
     * @return
     * @author 胡亚星
     * @date 2018/4/22 15:36
     * @Description:验证用户验证码是否正确
     */
    @RequestMapping("contrastCode")
    public void contrastCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String usercode = request.getParameter("user_code"); //获取用户输入的验证码
        String sessioncode = (String) session.getAttribute("authCode"); //获取保存在session里面的验证码
        String result = "";
        if (usercode != null && usercode.equals(sessioncode)) { //对比两个code是否正确
            result = "1";
        } else {
            result = "0";
        }
        PrintWriter out = response.getWriter();
        out.write(result.toString()); //将数据传到前台
    }

}
