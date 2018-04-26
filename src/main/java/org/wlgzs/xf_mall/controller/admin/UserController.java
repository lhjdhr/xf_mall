package org.wlgzs.xf_mall.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wlgzs.xf_mall.entity.User ;
import org.wlgzs.xf_mall.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/15 13:19
 * @Description: 后台用户增删改查控制层
 */
@Controller
@RequestMapping("AdminUserController")
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/AdminUserController/adminUserList";
    }
    /**
     * @author 阿杰
     * @param [model]
     * @return java.lang.String
     * @description 后台遍历用户
     */
    @RequestMapping("/adminUserList")
    public String list(Model model) {
        List<User> users=userService.getUserList();
        model.addAttribute("users", users);
        return "admin/adminUserList";
    }
    /**
     * @author 阿杰
     * @param [model, user_name]
     * @return java.lang.String
     * @description 搜索用户
     */
    @RequestMapping("/adminFindUser")
    public  String findUserName(Model model,String user_name){
        List<User> users = userService.findByUserName(user_name);
        model.addAttribute("users",users);
        model.addAttribute("user_name",user_name);
        return "admin/adminUserList";
    }
    /**
     * @author 阿杰
     * @return java.lang.String
     * @description 跳转至增加用户页面
     */
    @RequestMapping("/toAdminAddUser")
    public String toAdd() {
        return "admin/adminAddUser";
    }
    /**
     * @author 阿杰
     * @param [user]
     * @return java.lang.String
     * @description 后台增加用户
     */
    @RequestMapping("/adminAddUser")
    public String add(User user) {
        userService.save(user);
        return "redirect:/AdminUserController/adminUserList";
    }

    /**
     * @author 阿杰
     * @param [model, id]
     * @return java.lang.String
     * @description 跳转至修改用户页面
     */
    @RequestMapping("/toAdminEditUser")
    public String toEdit(Model model, Long id) {
        User user=userService.findUserById(id);
        model.addAttribute("user", user);
        return "admin/adminEditUser";
    }
    /**
     * @author 阿杰
     * @param [user]
     * @return java.lang.String
     * @description 后台修改用户
     */
    @RequestMapping("/adminEditUser")
    public String edit(User user) {
        userService.edit(user);
        return "redirect:/AdminUserController/adminUserList";
    }
    /**
     * @author 阿杰
     * @param [id]
     * @return java.lang.String
     * @description 后台删除用户
     */
    @RequestMapping("/adminDeleteUser")
    public String delete(Long id) {
        userService.delete(id);
        return "redirect:/AdminUserController/adminUserList";
    }
}
