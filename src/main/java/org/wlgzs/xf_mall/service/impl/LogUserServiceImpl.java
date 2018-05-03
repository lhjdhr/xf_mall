package org.wlgzs.xf_mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.wlgzs.xf_mall.dao.LogUserRepository;
import org.wlgzs.xf_mall.entity.User;

import org.wlgzs.xf_mall.service.LogUserService;
import org.wlgzs.xf_mall.util.RandonNumberUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author:胡亚星
 * @createTime 2018-04-16 21:36
 * @description:登陆注册用户查询方法实现
 **/
@Service
public class LogUserServiceImpl implements LogUserService {

    @Autowired
    private LogUserRepository logUserRepository;

   @Autowired
    private JavaMailSender mailSender;

    //实现登陆的方法
    @Override
    public User login(HttpServletRequest request,String user_name, String user_password){
        HttpSession session = request.getSession(true);
            User user = logUserRepository.Login(user_name,user_password);
            if(user != null){
                session.setMaxInactiveInterval(60 * 20);
                session.setAttribute("name", user.getUser_name());//之后用过滤器实现
            }
            return user;
    }

    @Override
    public void cancellation(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.invalidate();
    }

    //发送邮箱
    @Override
    public void sendEmail(HttpServletRequest request,String user_mail){
        HttpSession session = request.getSession(true);
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        RandonNumberUtils randonNumberUtils = new RandonNumberUtils();
        String authCode = randonNumberUtils.getRandonString(6);
        System.out.println(authCode);
        session.setMaxInactiveInterval(60 * 2);
        session.setAttribute("authCode", authCode);
        session.setAttribute("user_mail", user_mail);
        //发送者
        mainMessage.setFrom("huystar@126.com");
        //接收者
        mainMessage.setTo(user_mail);
        //发送的标题
        mainMessage.setSubject("注册新飞商城");
        //发送的内容
        mainMessage.setText("验证码："+authCode+"您正在注册新飞商城，请输入您的验证码继续完成注册");
        System.out.println(mainMessage+"////////////////////////////////////////////////////////////");
        System.out.println(mailSender+"-------------------------------------------");
        mailSender.send(mainMessage);
        System.out.println(mailSender);
        System.out.println("ok");
    }

    //发送邮箱
    @Override
    public void sendEmail1(HttpServletRequest request,String user_mail){
        HttpSession session = request.getSession(true);
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        RandonNumberUtils randonNumberUtils = new RandonNumberUtils();
        String authCode = randonNumberUtils.getRandonString(6);
        System.out.println(authCode);
        session.setMaxInactiveInterval(60 * 2);
        session.setAttribute("authCode", authCode);
        session.setAttribute("user_mail", user_mail);
        //发送者
        mainMessage.setFrom("huystar@126.com");
        //接收者
        mainMessage.setTo(user_mail);
        //发送的标题
        mainMessage.setSubject("修改手机号");
        //发送的内容
        mainMessage.setText("验证码："+authCode+" ， 您正在修改您的手机号，请继续");
        System.out.println(mainMessage+"////////////////////////////////////////////////////////////");
        System.out.println(mailSender+"-------------------------------------------");
        mailSender.send(mainMessage);
        System.out.println(mailSender);
        System.out.println("ok");
    }

    //发送邮箱
    @Override
    public void sendEmail2(HttpServletRequest request,String user_mail){
        HttpSession session = request.getSession(true);
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        RandonNumberUtils randonNumberUtils = new RandonNumberUtils();
        String authCode = randonNumberUtils.getRandonString(6);
        System.out.println(authCode);
        session.setMaxInactiveInterval(60 * 2);
        session.setAttribute("authCode", authCode);
        session.setAttribute("user_mail", user_mail);
        //发送者
        mainMessage.setFrom("huystar@126.com");
        //接收者
        mainMessage.setTo(user_mail);
        //发送的标题
        mainMessage.setSubject("找回密码");
        //发送的内容
        mainMessage.setText("验证码："+authCode+"您正在找回您的密码，输入验证码以继续");
        System.out.println(mainMessage+"////////////////////////////////////////////////////////////");
        System.out.println(mailSender+"-------------------------------------------");
        mailSender.send(mainMessage);
        System.out.println(mailSender);
        System.out.println("ok");
    }

    //验证注册用户
    @Override
    public boolean validationUser(HttpServletRequest request,String code){
        HttpSession session = request.getSession(true);
        String Code = (String) session.getAttribute("code");
        if(Code.equals(code)){
            return true;
        }else{
            return false;
        }
    }
    //判断邮箱是否已存在
    @Override
    public boolean selectEmail(String user_mail){
        User user = logUserRepository.selectEmail(user_mail);
        if(user != null){
            return true;
        }else{
            return false;
        }
    }

    //判断用户名是否已存在
    @Override
    public boolean selectName(String user_name) {
        User user = logUserRepository.selectName(user_name);
        if(user != null){
            return true;
        }else{
            return false;
        }
    }
}
