package org.wlgzs.xf_mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.wlgzs.xf_mall.entity.User;
import org.wlgzs.xf_mall.entity.UserIntegral;

/**
 * @Auther: Administrator
 * @Date: 2018/4/23 08:42
 * @Description:
 */
public interface UserIntegralRepository extends JpaRepository <UserIntegral,Long>,JpaSpecificationExecutor<UserIntegral> {
    UserIntegral findById(long id);


}
