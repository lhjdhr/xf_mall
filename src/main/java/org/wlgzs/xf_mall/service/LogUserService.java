package org.wlgzs.xf_mall.service;

import org.wlgzs.xf_mall.entity.User;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author:胡亚星
 * @createTime 2018-04-16 21:30
 * @description:用户登陆service层
 **/
public interface LogUserService {

    User login(HttpServletRequest request,String user_name, String user_password);

    void cancellation(HttpServletRequest request);

    //验证注册用户
    boolean validationUser(HttpServletRequest request,String code);

    //发送验证码到邮箱
    void sendEmail(HttpServletRequest request,String user_mail);

    //发送验证码到邮箱
    void sendEmail1(HttpServletRequest request,String user_mail);

    //发送验证码到邮箱
    void sendEmail2(HttpServletRequest request,String user_mail);

    //判断邮箱是否已存在
    boolean selectEmail(String user_mail);

    //判断用户名是否存在
    boolean selectName(String user_name);
}
