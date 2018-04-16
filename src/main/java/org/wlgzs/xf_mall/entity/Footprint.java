package org.wlgzs.xf_mall.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/16 18:39
 * @Description:
 */
@Entity
@Data
public class Footprint {
    @Id
    @GeneratedValue()
    private long footprint_id;//足迹id
    private long user_id;//用户id
    private long product_id;//商品id
    @Column(nullable = false,length = 50)
    private String product_keywords;//商品关键字
    @Column(nullable = false)
    private float product_mallPrice;//商城价格
    @Column(nullable = false,length = 200)
    private String product_picture;//商品图片
    @Column(nullable = false)
    private Date footprint_time;

}
