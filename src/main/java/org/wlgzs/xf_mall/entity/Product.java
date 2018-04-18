package org.wlgzs.xf_mall.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author:胡亚星
 * @createTime 2018-04-14 16:43
 * @description:商品表
 **/
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long product_id;//商品id
    @Column(nullable = false)
    private int product_isShelf;//是否上架
    @Column(nullable = false,length = 50)
    private String product_keywords;//商品关键字
    @Column(nullable = false)
    private float product_counterPrice;//专柜价格
    @Column(nullable = false)
    private float product_mallPrice;//商城价格
    @Column(nullable = false,length = 50)
    private String product_specification;//商品规格信息
    @Column(nullable = false,length = 200)
    private String product_picture;//商品图片
    @Column(nullable = false,length = 100)
    private String product_details;//商品详情
    @Column(nullable = false,length = 100)
    private String product_serviceType;//服务类型
    @Column(nullable = false,length = 10)
    private String product_category;//商品类别
    @Column(nullable = false,length = 20)
    private String product_activity;//活动状态
    @Column(nullable = false)
    private int product_isTop;//是否热门
    @Column(nullable = false)
    private int product_isRedeemable;//是否可用积分兑换
    @Column(nullable = false)
    private int product_points;//需要多少积分
    @Column(nullable = false)
    private int product_Inventory;//库存
}

