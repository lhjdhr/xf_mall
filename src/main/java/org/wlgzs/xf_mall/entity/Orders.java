package org.wlgzs.xf_mall.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * @author:胡亚星
 * @createTime 2018-04-14 21:00
 * @description:订单表
 **/
@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long order_id;//订单id
    @Column(nullable = false)
    private long product_id;//商品id
    @Column(nullable = false)
    private long user_id;//用户id
    @Column(nullable = false,length = 200)
    private String product_picture;//商品图片
    @Column(nullable = false,length = 50)
    private String product_keywords;//商品关键字
    @Column(nullable = false)
    private float product_counterPrice;//专柜价格
    @Column(nullable = false)
    private float product_mallPrice;//商城价格
    @Column(nullable = false,length = 50)
    private String product_specification;//商品规格信息
    @Column(nullable = false)
    private float order_freight;//运费
    @Column(nullable = false)
    private int order_discount;//折扣
    @Column(nullable = false)
    private int product_isRedeemable;//是否可用积分兑换
    @Column(nullable = false)
    private int product_points;//需要多少积分
    @Column(nullable = false,length = 20)
    private String order_number;//订单号
    @Column(nullable = false,length = 20)
    private String order_expressNumber;//订单快递号
    @Column(nullable = false,length = 10)
    private String order_status;//订单状态
    @Column(nullable = false,length = 30)
    private String address_name;//收货人
    @Column(nullable = false,length = 50)
    private String order_deliveryAddress;//发货地址
    @Column(nullable = false,length = 50)
    private String address_shipping;//收货地址
    @Column(nullable = false,length = 11)
    private String address_phone;//收货电话
    @Column(nullable = false)
    private Date order_purchaseTime;//下单时间
    @Column(nullable = false)
    private Date order_deliveryTime;//发货时间
    @Column(nullable = false)
    private Date order_dealTime;//成交时间
    @Column(nullable = false,length = 10)
    private String order_methodOfPurchase;//购买方式
    @Column(nullable = false)
    private int order_quantity;//购买数量
}

