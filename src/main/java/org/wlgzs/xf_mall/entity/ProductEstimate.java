package org.wlgzs.xf_mall.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Auther: 李晓珊
 * @Date: 2018/4/15 08:38
 * @Description: 评价
 */
@Entity
@Data
public class ProductEstimate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long estimate_id;  //评价id
    private long product_id;   //商品id
    private long user_id;  //用户id
    @Column(nullable = false,length = 30)
    private String user_name;  //用户名
    @Column(nullable = false)
    private int estimate_isNameless;  //是否匿名
    @Column(nullable = false,length = 50)
    private String product_specification;  //商品规格信息
    private Date estimate_time;   //评价时间
    private int estimate_grade;  //评价等级
    @Column(nullable = false,length = 200)
    private String estimate_content;   //评价内容
    @Column(nullable = false,length = 200)
    private String estimate_img;   //买家秀
    private long order_id;
}

