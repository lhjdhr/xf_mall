package org.wlgzs.xf_mall.service;

import org.wlgzs.xf_mall.entity.UserIntegral;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/4/23 09:04
 * @Description:
 */
public interface UserIntegralService {

    List<UserIntegral> getUserIntegral();

    UserIntegral findUserIntegralById(long id);
}
