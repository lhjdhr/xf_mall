package org.wlgzs.xf_mall.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlgzs.xf_mall.entity.User;
import org.wlgzs.xf_mall.repository.UserRepository;
import org.wlgzs.xf_mall.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {//UserService的规定

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    //修改
    @Override
    public void edit(User user) {
        userRepository.save(user);
    }

}


