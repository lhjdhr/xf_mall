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

    public User login(HttpServletRequest request,String user_name, String user_password);

    public void cancellation(HttpServletRequest request);

}
