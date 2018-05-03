package org.wlgzs.xf_mall.service;

import org.wlgzs.xf_mall.entity.UserIntegral;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: 李晓珊
 * @Date: 2018/4/23 09:04
 * @Description:  用户积分管理
 */
public interface UserIntegralService {

    List<UserIntegral> getUserIntegral(long user_id);

    List<UserIntegral> getUserIntegralIncome(long user_id);

    List<UserIntegral> getUserIntegralExpend(long user_id);

    UserIntegral findUserIntegralById(long id);

    void delete(Long userIntegralId);

    void save(long user_id,HttpServletRequest request);
}
