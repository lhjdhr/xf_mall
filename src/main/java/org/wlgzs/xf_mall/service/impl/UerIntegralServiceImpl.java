package org.wlgzs.xf_mall.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlgzs.xf_mall.dao.UserIntegralRepository;
import org.wlgzs.xf_mall.entity.UserIntegral;
import org.wlgzs.xf_mall.service.UserIntegralService;

/**
 * @Auther: Administrator
 * @Date: 2018/4/23 09:02
 * @Description:
 */
@Service
public class UerIntegralServiceImpl implements UserIntegralService {
    @Autowired
    private UserIntegralRepository userIntegralRepository;

    @Override
    public UserIntegral findUserIntegralById(long id){
        return userIntegralRepository.findById(id);
    }
}
