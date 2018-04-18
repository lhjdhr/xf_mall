package org.wlgzs.xf_mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlgzs.xf_mall.entity.User ;
import org.wlgzs.xf_mall.dao.AdminUserRepository;
import org.wlgzs.xf_mall.service.AdminUserService;

import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/15 13:42
 * @Description: 用户增删改查的实现方法
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserRepository userRepository;

    //后台遍历用户
    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    //后台增加用户
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    //后台按照id查找用户
    @Override
    public User findUserById(long id) {
        return userRepository.findById(id);
    }

    //后台修改用户
    @Override
    public void edit(User user) {
        userRepository.saveAndFlush(user);
    }

    //后台删除用户
    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}


