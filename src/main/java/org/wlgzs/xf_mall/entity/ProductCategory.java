package org.wlgzs.xf_mall.entity;

import lombok.Data;
import javax.persistence.*;

/**
 * @Auther: 三三~~~
 * @Date: 2018/4/15 09:32
 * @Description: 商品分类表
 */
@Entity
@Data
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryId;  //商品类别id
    @Column(nullable = false,length = 10)
    private String category_name;  //类别名称
    @Column(nullable = false,length = 10)
    private String parent_name; //父分类名字
}
