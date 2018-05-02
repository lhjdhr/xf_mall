package org.wlgzs.xf_mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
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

    /**     
     * @author 胡亚星
     * @date 2018/5/1 10:10  
     * @param   
     * @return   
     *@Description:修改用户头像路径
     */
    @Query("UPDATE User u SET u.user_avatar=?1 WHERE u.user_id=?2")
    @Modifying
    @Transactional
    void ModifyAvatar(String user_avatar,long id);
}