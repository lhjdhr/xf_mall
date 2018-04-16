package org.wlgzs.xf_mall.service;



import org.wlgzs.xf_mall.entity.User;

import java.util.List;

public interface UserService {

    public List<User> getUserList();

    public User findUserById(long id);

    public void save(User user);

    public void edit(User user);

}
