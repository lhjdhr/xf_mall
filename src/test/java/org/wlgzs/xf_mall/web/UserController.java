package org.wlgzs.xf_mall.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wlgzs.xf_mall.entity.User;
import org.wlgzs.xf_mall.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {

    /*@Resource
    UserService userService;


    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }


    @RequestMapping("/add")
    public String add(User user) {
        userService.save(user);
        return "redirect:/list";
    }*/


    //删除
    /*@RequestMapping("/delete")
    public String delete(Long id) {
        userService.delete(id);
        return "redirect:/list";
    }*/
}
