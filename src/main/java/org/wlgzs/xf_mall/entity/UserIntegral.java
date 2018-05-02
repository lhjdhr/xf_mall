package org.wlgzs.xf_mall.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Auther: 三三~~~
 * @Date: 2018/4/15 08:43
 * @Description:用户积分表
 * id  用户id  积分变化（正负）  商品关键字  图片
 */
@Data
@Entity
public class UserIntegral {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userIntegral_id;   //积分id
    @Column(nullable = false)
    private long user_id;   //用户id
    @Column(nullable = false)
    private int  userIntegral_vary;   //积分变化(正负)
    @Column(nullable = false,length = 50)
    private String product_keyword; //商品关键字
    @Column(nullable = false,length = 100)
    private String product_picture; //商品图片

}
