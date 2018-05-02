package org.wlgzs.xf_mall.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.beanutils.BeanUtils;
import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.xf_mall.entity.Product;
import org.wlgzs.xf_mall.entity.ShippingAddress;
import org.wlgzs.xf_mall.entity.User;
import org.wlgzs.xf_mall.service.LogUserService;
import org.wlgzs.xf_mall.service.ShippingAddressService;
import org.wlgzs.xf_mall.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author:胡亚星
 * @createTime 2018-04-22 16:18
 * @description:用户信息修改控制层
 **/
@RequestMapping("UserManagementController")
@RestController
public class UserManagementController {

    @Resource
    UserService userService;

    @Resource
    LogUserService logUserService;

    @Resource
    ShippingAddressService shippingAddressService;

    /**
     * @param
     * @return
     * @author 胡亚星
     * @date 2018/4/23 21:29
     * @Description:展示用户信息
     */
    @RequestMapping("information")
    public ModelAndView displayInformation(Model model, Long id) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return new ModelAndView("information");
    }

    /**
     * @param
     * @return
     * @author 胡亚星
     * @date 2018/5/1 8:16
     * @Description:修改用户名(个人信息展示页面)
     */
    @RequestMapping("changeInformation")
    public ModelAndView ModifyName(Model model, HttpServletRequest request) {
        Map<String, String[]> properties = request.getParameterMap();
        String id = request.getParameter("user_id");
        long user_id = Long.parseLong(id);
        User user = userService.findUserById(user_id);
        try {
            BeanUtils.populate(user, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String user_name = request.getParameter("user_name");
        user.setUser_name(user_name);
        userService.edit(user);
        model.addAttribute("user", user);
        return new ModelAndView("information");
    }


    /**
     * @param
     * @return
     * @author 胡亚星
     * @date 2018/5/1 8:11
     * @Description:点击用户头像跳转到头像修改页面
     */
    @RequestMapping("toModifyAvatar")
    public ModelAndView toModifyAvatar(Model model, Long id) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return new ModelAndView("ModifyAvatar");
    }


    /**
     * @param
     * @return
     * @author 胡亚星
     * @date 2018/4/23 21:28
     * @Description:实现用户头像的修改 文件上传，替换原来的地址
     */
    @RequestMapping("/ModifyAvatar")
    public ModelAndView add(@RequestParam("file") MultipartFile myFileName, HttpSession session,
                            Model model, HttpServletRequest request) throws IOException {
        System.out.println("===================================");
        String user_id = request.getParameter("user_id");
        long id = Long.parseLong(user_id);
        String realName = "";
        String user_avatar = "";
        if (!myFileName.getOriginalFilename().equals("")) {
            String fileName = myFileName.getOriginalFilename();
            String fileNameExtension = fileName.substring(fileName.indexOf("."), fileName.length());

            // 生成实际存储的真实文件名
            realName = UUID.randomUUID().toString() + fileNameExtension;

            // "/upload"是你自己定义的上传目录
            String realPath = session.getServletContext().getRealPath("/headPortrait");
            File uploadFile = new File(realPath, realName);
            myFileName.transferTo(uploadFile);
            user_avatar = request.getContextPath() + "/headPortrait/" + realName;
        } else {
            user_avatar = request.getContextPath() + "/headPortrait/" + "morende.jpg";
        }
        System.out.println(user_avatar);
        Map<String, String[]> properties = request.getParameterMap();
        User user = userService.findUserById(id);
        try {
            BeanUtils.populate(user, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        user.setUser_avatar(user_avatar);
        userService.modifyAvatar(user_avatar, id);
        model.addAttribute("user", user);
        return new ModelAndView("information");
    }

    /**
     * @author 胡亚星
     * @date 2018/5/2 18:03
     * @param
     * @return
     *@Description:修改密码,输入正确的密码
     */
//    @RequestMapping("changePassword")
//    public ModelAndView changePassword(Model model, HttpServletRequest request){
//        String user_password = request.getParameter("user_password");
//        String password = request.getParameter("password");
//
//
//    }

    /**
     * @author 胡亚星
     * @date 2018/4/27 20:06  
     * @param
     * @return
     *@Description:发送验证码
     */
    @RequestMapping("sendChangePhone")
    public void sendChangePhone(HttpServletRequest request){
        String user_mail = request.getParameter("user_mail");
        logUserService.sendEmail(request, user_mail);//发送
    }

    /**     
     * @author 胡亚星
     * @date 2018/5/2 18:19  
     * @param   
     * @return   
     *@Description:用户电话的修改
     */
    @RequestMapping("changePhone")
    public ModelAndView changePhone(Model model, HttpServletRequest request){
        Map<String, String[]> properties = request.getParameterMap();
        String id = request.getParameter("user_id");
        long user_id = Long.parseLong(id);
        User user = userService.findUserById(user_id);
        try {
            BeanUtils.populate(user, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String user_phone = request.getParameter("user_phone");
        user.setUser_phone(user_phone);
        userService.edit(user);
        model.addAttribute("user", user);
        return new ModelAndView("information");
    }

    
    /**     
     * @author 胡亚星
     * @date 2018/5/2 17:57  
     * @param   
     * @return   
     *@Description:找回密码
     */
    //@RequestMapping("")


    /**
     * @param
     * @return
     * @author 胡亚星
     * @date 2018/4/27 20:32
     * @Description:新建收货地址
     */
    @RequestMapping("addShippingAddress")
    public ModelAndView addShippingAddress(ShippingAddress shippingAddress) {
        shippingAddressService.addShippingAddress(shippingAddress);
        return new ModelAndView("redirect:shippingAddres");
    }

    /**
     * @param
     * @return
     * @author 胡亚星
     * @date 2018/4/27 20:51
     * @Description:跳转到收货地址
     */
    @RequestMapping("shippingAddres")
    public ModelAndView listShippingAddress(Model model) {
        List<ShippingAddress> shippingAddressList = shippingAddressService.getShippingAddressList();
        model.addAttribute("shippingAddressList", shippingAddressList);
        return new ModelAndView("shippingAddres");
    }

    /**
     * @param
     * @return
     * @author 胡亚星
     * @date 2018/4/27 21:24
     * @Description:跳转到修改收货地址
     */
    @RequestMapping("toChangeShippingAddress")
    public ModelAndView toModifyAddress(Model model, long id) {
        ShippingAddress shippingAddress = shippingAddressService.findById(id);
        model.addAttribute("shippingAddress", shippingAddress);
        return new ModelAndView("ChangeShippingAddress");
    }

    /**
     * @param
     * @return
     * @author 胡亚星
     * @date 2018/4/27 21:59
     * @Description:修改收货地址
     */
    @RequestMapping("changeShippingAddress")
    public ModelAndView modifyAddress(ShippingAddress shippingAddress) {
        shippingAddressService.ModifyAddress(shippingAddress);
        return new ModelAndView("redirect:shippingAddres");
    }

    /**
     * @param
     * @return
     * @author 胡亚星
     * @date 2018/4/28 8:20
     * @Description:删除收货地址
     */
    @RequestMapping("deleteShippingAddress")
    public ModelAndView deleceAddress(Long id) {
        shippingAddressService.deleceAddress(id);
        return new ModelAndView("redirect:shippingAddres");
    }


}
