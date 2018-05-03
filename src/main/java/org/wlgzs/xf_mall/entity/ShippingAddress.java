package org.wlgzs.xf_mall.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/14 20:27
 * @Description: 收货地址
 */
@Entity
@Data
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long addressId; //收货id
    @Column(nullable = false,length = 30)
    private String user_name; //用户名
    @Column(nullable = false,length = 30)
    private String address_name; //收货人
    @Column(nullable = false,length = 11)
    private String address_phone; //收货电话
    @Column(nullable = false,length = 50)
    private String address_shipping; //收货地址
    @Column(nullable = false)
    private int address_isDefault; //是否默认

}
