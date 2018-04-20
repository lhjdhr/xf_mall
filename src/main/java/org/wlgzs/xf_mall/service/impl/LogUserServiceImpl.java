package org.wlgzs.xf_mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlgzs.xf_mall.dao.LogUserRepository;
import org.wlgzs.xf_mall.entity.User;

import org.wlgzs.xf_mall.service.LogUserService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author:胡亚星
 * @createTime 2018-04-16 21:36
 * @description:登陆注册用户查询方法实现
 **/
@Service
public class LogUserServiceImpl implements LogUserService {

    @Autowired
    LogUserRepository logUserRepository;

    //实现登陆的方法
    @Override
    public User login(HttpServletRequest request,String user_name, String user_password){
        HttpSession session = request.getSession(true);
        String userName = (String) session.getAttribute("name");

        if(userName != null){
            User user = logUserRepository.selectName(userName);
            return user;
        }else{
            User user = logUserRepository.Login(user_name,user_password);
            if(user != null){
                session.setMaxInactiveInterval(60 * 20);
                session.setAttribute("name", user.getUser_name());
            }
            return user;
        }
    }

    @Override
    public void cancellation(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.invalidate();
    }

}
