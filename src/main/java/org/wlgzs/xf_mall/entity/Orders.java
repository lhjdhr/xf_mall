package org.wlgzs.xf_mall.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author:胡亚星
 * @createTime 2018-04-14 21:00
 * @description:订单表
 **/
@Entity
@Data
@Table(name = "xfm_order")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long order_id;//订单id

    @Column(nullable = false)
    private String product_picture;//商品图片

    @Column(nullable = false,length = 30)
    private String product_keywords;//商品关键字

    @Column(nullable = false,length = 10)
    private String product_counterPrice;//专柜价格

    @Column(nullable = false,length = 10)
    private String product_mallPrice;//商城价格

    @Column(nullable = false)
    private String product_specification;//商品规格信息

    @Column(nullable = false,length = 10)
    private String order_freight;//运费

    @Column(nullable = false)
    private Integer order_discount;//折扣

    @Column(nullable = false)
    private Integer product_isRedeemable;//是否可用积分兑换

    @Column(nullable = false,length = 50)
    private String order_number;//订单号

    @Column(nullable = false,length = 10)
    private String order_expressNumber;//订单快递号

    @Column(nullable = false,length = 10)
    private String order_status;//状态

    @Column(nullable = false,length = 10)
    private String order_receiver;//收货人

    @Column(nullable = false,length = 10)
    private String order_deliveryAddress;//发货地址

    @Column(nullable = false,length = 10)
    private String order_shippingAddress;//收货地址

    @Column(nullable = false,length = 10)
    private String order_phone;//收货地址

    @Column(nullable = false,length = 10)
    private String order_creationTime;//创建时间

    @Column(nullable = false,length = 10)
    private String order_purchaseTime;//下单时间

    @Column(nullable = false,length = 10)
    private String order_deliveryTime;//发货时间

    @Column(nullable = false,length = 10)
    private String order_dealTime;//成交时间

    @Column(nullable = false,length = 10)
    private String order_methodOfPurchase;//购买方式

    @Column(nullable = false)
    private Long product_id;//商品id

    @Column(nullable = false)
    private Long user_id;//用户id

    @Column(nullable = false)
    private Integer order_quantity;//购买数量

}
