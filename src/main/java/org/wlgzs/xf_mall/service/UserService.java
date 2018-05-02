package org.wlgzs.xf_mall.service;

import org.wlgzs.xf_mall.entity.User ;

import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/15 13:28
 * @Description: 后台增删改查service层
 */
public interface UserService {

    List<User> getUserList();

    User findUserById(long id);

    void save(User user);

    void edit(User user);

    void delete(long id);

    List<User> findByUserName(String user_name);

    //修改用户头像路径
    void modifyAvatar(String user_avatar,long id);

}
