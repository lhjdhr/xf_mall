package org.wlgzs.xf_mall.service;

import org.wlgzs.xf_mall.entity.ShippingAddress;

import java.util.List;

/**
 * @author:胡亚星
 * @createTime 2018-04-27 20:27
 * @description:
 **/
public interface ShippingAddressService {
    //添加新的收货地址
    void addShippingAddress(ShippingAddress shippingAddress);
    //遍历收货地址
    List<ShippingAddress> getShippingAddressList();
    //按id查询
    ShippingAddress findById(long id);
    //修改收货地址
    void ModifyAddress(ShippingAddress shippingAddress);
    //按id删除
    void deleceAddress(long id);
}
