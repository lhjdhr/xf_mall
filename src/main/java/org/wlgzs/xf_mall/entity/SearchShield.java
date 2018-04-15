package org.wlgzs.xf_mall.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/15 08:20
 * @Description: 搜索屏蔽
 */
@Entity
@Data
public class SearchShield {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long searchShield_id; //搜索屏蔽id
    @Column(nullable = false,length = 20)
    private String searchShield_Sensitive; //敏感词

}
