package org.wlgzs.xf_mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wlgzs.xf_mall.entity.Product;
import org.wlgzs.xf_mall.entity.User ;

import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/15 13:16
 * @Description: 接口
 */
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

    User findById(long id);

    /**
     * @author 阿杰
     * @param [user_name]
     * @description 搜索用户名
     */
    @Query(value = "SELECT * FROM user WHERE user_name like %:user_name%", nativeQuery = true)
    List<User> findByUserName(@Param("user_name") String user_name);

}