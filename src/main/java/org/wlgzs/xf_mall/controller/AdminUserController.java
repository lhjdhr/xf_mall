package org.wlgzs.xf_mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wlgzs.xf_mall.entity.User ;
import org.wlgzs.xf_mall.service.AdminUserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/15 13:19
 * @Description: 后台用户增删改查控制层
 */
@Controller
public class AdminUserController {
    @Resource
    AdminUserService userService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/adminUserList";
    }

    //后台遍历用户
    @RequestMapping("/adminUserList")
    public String list(Model model) {
        List<User> users=userService.getUserList();
        model.addAttribute("users", users);
        return "user/adminUserList";
    }

    @RequestMapping("/toAdminAddUser")
    public String toAdd() {
        return "user/adminAddUser";
    }

    //后台增加用户
    @RequestMapping("/adminAddUser")
    public String add(User user) {
        userService.save(user);
        return "redirect:/adminUserList";
    }

    @RequestMapping("/toAdminEditUser")
    public String toEdit(Model model, Long id) {
        User user=userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/adminEditUser";
    }

    //后台修改用户
    @RequestMapping("/adminEditUser")
    public String edit(User user) {
        userService.edit(user);
        return "redirect:/adminUserList";
    }

    //后台删除用户
    @RequestMapping("/adminDeleteUser")
    public String delete(Long id) {
        userService.delete(id);
        return "redirect:/adminUserList";
    }
}
