package org.wlgzs.xf_mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.wlgzs.xf_mall.dao.UserManagementRepository;
import org.wlgzs.xf_mall.entity.User ;
import org.wlgzs.xf_mall.dao.UserRepository;
import org.wlgzs.xf_mall.service.UserService;
import org.wlgzs.xf_mall.util.PageUtil;

import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/15 13:42
 * @Description: 用户增删改查的实现方法
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserManagementRepository userManagementRepository;

    //后台增加用户
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    //后台按照id查找用户
    @Override
    public User findUserById(long userId) {
        return userRepository.findById(userId);
    }

    //后台修改用户
    @Override
    public void edit(User user) {
        userRepository.saveAndFlush(user);
    }

    //后台删除用户
    @Override
    public void delete(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void save(List<User> users) {
        userRepository.saveAll(users);
    }

    //后台遍历用户，后台分页搜索用户
    @Override
    public Page<User> findUserPage(String user_name, int page, int limit) {
        /*Sort sort = null;
        Pageable pageable = null;
        Page<User> page = null;
        if (user_name.equals("")) {
            sort = new Sort(Sort.Direction.DESC, "userId");
            pageable = new PageRequest(page, limit, sort);
            return userRepository.findAll(pageable);
        } else {
            sort = new Sort(Sort.Direction.ASC, "userId");
            pageable = new PageRequest(page, limit, sort);

            Specification<User> specification = new Specification<User>() {
                @Override
                public Predicate toPredicate(Root<User> root,
                                             CriteriaQuery<?> criteriaQuery,
                                             CriteriaBuilder criteriaBuilder) {
                    Predicate predicate = criteriaBuilder.like(root.get("user_name"), "%" + user_name + "%");
                    return criteriaBuilder.and(predicate);
                }
            };
            return userRepository.findAll(specification, pageable);
        }*/
        Sort sort = new Sort(Sort.Direction.DESC, "userId");
        Pageable pageable = new PageRequest(page,limit, sort);
        Specification<User> specification = new PageUtil<User>(user_name).getPage("user_name");
        Page pages = userRepository.findAll(specification,pageable);
        return pages;
    }

    @Override
    public void modifyAvatar(String user_avatar,long id) {
        userRepository.ModifyAvatar(user_avatar,id);
    }

}


