package org.wlgzs.xf_mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.wlgzs.xf_mall.dao.UserManagementRepository;
import org.wlgzs.xf_mall.entity.ShippingAddress;
import org.wlgzs.xf_mall.service.ShippingAddressService;

import java.util.List;

/**
 * @author:胡亚星
 * @createTime 2018-04-27 20:28
 * @description:
 **/
@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
    @Autowired
    UserManagementRepository userManagementRepository;

    @Override
    public void addShippingAddress(ShippingAddress shippingAddress) {
        userManagementRepository.save(shippingAddress);
    }

    @Override
    public List<ShippingAddress> getShippingAddressList() {
        return userManagementRepository.findAll();
    }

    @Override
    public ShippingAddress findById(long id) {
        return userManagementRepository.findById(id);
    }

    @Override
    public void ModifyAddress(ShippingAddress shippingAddress) {
        userManagementRepository.saveAndFlush(shippingAddress);
    }

    @Override
    public void deleceAddress(long id) {
        userManagementRepository.deleteById(id);
    }
}
