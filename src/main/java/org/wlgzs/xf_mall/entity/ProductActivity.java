package org.wlgzs.xf_mall.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Auther: 李晓珊
 * @Date: 2018/4/15 08:36
 * @Description:
 */
@Entity
@Data
public class ProductActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long activity_id;   //活动id
    private long product_id;    //商品id
    @Column(nullable = false,length = 30)
    private String activity_name;   //活动名称
    @Column(nullable = false)
    private int activity_discount;   //活动折扣
}
