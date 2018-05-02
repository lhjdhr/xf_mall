package org.wlgzs.xf_mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.wlgzs.xf_mall.entity.User ;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/15 13:16
 * @Description: 接口
 */
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User>  {
    User findById(long userId);

}