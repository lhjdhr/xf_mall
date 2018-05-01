package org.wlgzs.xf_mall.controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wlgzs.xf_mall.entity.User ;
import org.wlgzs.xf_mall.service.UserService;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import javax.sound.midi.Soundbank;
import java.util.ArrayList;
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
    public String list(Model model, @RequestParam(value = "page",defaultValue = "0") int page,
                       @RequestParam(value = "limit",defaultValue = "10") int limit) {
        String user_name="";
        Page pages = userService.findUserPage(user_name,page,limit);
        model.addAttribute("TotalPages", pages.getTotalPages());//查询的页数
        model.addAttribute("TotalElements", pages.getTotalElements());//查询的总记录数
        model.addAttribute("Number", pages.getNumber());//查询的当前第几页
        model.addAttribute("users", pages.getContent());//查询的当前页的集合
        model.addAttribute("NumberOfElements", pages.getNumberOfElements());//查询的当前页的记录数
        return "admin/adminUserList";
    }
    /*
    批量添加
    @RequestMapping("/addUsers")
    public void addUsers(){
        List<User> users = new ArrayList<User>();
        User user = null;
        for (int i = 0; i < 100; i++) {
            user = new User();
            user.setUser_phone("test"+i);
            user.setUser_password("test"+(100-i));
            user.setUser_mail("test"+i);
            user.setUser_avatar("test"+i);
            user.setUser_role("test"+i);
            user.setUser_name("test"+i);
            users.add(user);
        }
        userService.save(users);
    }*/
    /**
     * @author 阿杰
     * @param [model, user_name]
     * @return java.lang.String
     * @description 搜索用户
     */
    @RequestMapping("/adminFindUser")
    public  String findUserName(Model model,String user_name,@RequestParam(value = "page",defaultValue = "0") int page,
                                @RequestParam(value = "limit",defaultValue = "10") int limit){
        Page pages = userService.findUserPage(user_name,page,limit);
        model.addAttribute("TotalPages", pages.getTotalPages());//查询的页数
        model.addAttribute("TotalElements", pages.getTotalElements());//查询的总记录数
        model.addAttribute("Number", pages.getNumber());//查询的当前第几页
        model.addAttribute("users", pages.getContent());//查询的当前页的集合
        model.addAttribute("NumberOfElements", pages.getNumberOfElements());//查询的当前页的记录数
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
    public String toEdit(Model model, Long userId) {
        User user = userService.findUserById(userId);
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
    public String delete(Long userId) {
        userService.delete(userId);
        return "redirect:/AdminUserController/adminUserList";
    }
}
