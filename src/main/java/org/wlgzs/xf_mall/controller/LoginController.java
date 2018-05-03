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
        return "sign-up1";
    }

    //去登陆
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/registered")
    public String register(Model model,HttpServletRequest request,HttpSession session){
        Map<String, String[]> properties = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String user_name = user.getUser_name();
        String user_mail = user.getUser_mail();
        //判断用户名是否已经存在
        if(logUserService.selectName(user_name)){//存在
            System.out.println("hahahahahha");
            model.addAttribute("user_mail",user_mail);
            return "sign-up2";
        }else{//不存在
            System.out.println("123456798");
            String realPath = session.getServletContext().getRealPath("/headPortrait");
            String user_avatar = realPath+"morende.jpg";
            user.setUser_avatar(user_avatar);
            user.setUser_role("普通用户");
            userService.save(user);
            return "redirect:/login";
        }
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
            HttpSession session = request.getSession();

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
    public void sendEmail(Model model,HttpServletRequest request,HttpServletResponse response) {
        String user_mail = request.getParameter("user_mail");
        System.out.println("123456789");
        if(logUserService.selectEmail(user_mail)){
            System.out.println("out---------------");
            model.addAttribute("mag","该邮箱已存在");
            String result = "该邮箱已存在";
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.write(result.toString()); //将数据传到前台
        }else{
            System.out.println("in-----------------");
            logUserService.sendEmail(request, user_mail);//发送
        }
    }

    /**
     * @param
     * @return
     * @author 胡亚星
     * @date 2018/4/22 15:36
     * @Description:验证用户验证码是否正确
     */
    @RequestMapping("contrastCode")
    public String contrastCode(Model model,HttpServletRequest request){
        String user_mail = request.getParameter("user_mail");
        HttpSession session = request.getSession();
        String usercode = request.getParameter("user_code"); //获取用户输入的验证码
        String sessioncode = (String) session.getAttribute("authCode"); //获取保存在session里面的验证码
        String sessionMail = (String) session.getAttribute("user_mail");//获取保存在session里面的邮箱
        if (usercode != null && usercode.equals(sessioncode)&&user_mail != null && user_mail.equals(sessionMail)) { //对比两个code是否正确
            model.addAttribute("user_mail",user_mail);
            System.out.println("222");
            return "sign-up2";
        } else {
            model.addAttribute("mag","请检查您的验证码或邮箱是否正确");
            System.out.println("111");
            return "sign-up1";
        }
    }

}
