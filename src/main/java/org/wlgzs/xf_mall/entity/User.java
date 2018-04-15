package org.wlgzs.xf_mall.entity;

import javax.persistence.*;

/**
 * @author:胡亚星
 * @createTime 2018-04-14 17:47
 * @description:用户表
 **/
@Entity
@Table(name = "xfm_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;//用户id

    @Column(nullable = false)
    private String user_avatar;//用户头像

    @Column(nullable = false,length = 30)
    private String user_name;//用户名

    @Column(nullable = false,length = 30)
    private String user_nickname;//昵称

    @Column(nullable = false,length = 10)
    private String user_role;//角色

    @Column(nullable = false,length = 11)
    private String user_phone;//电话

    @Column(nullable = false,length = 30)
    private String user_password;//密码

    @Column(nullable = false,length = 20)
    private String user_mail;//邮箱

    @Column(nullable = false,length = 50)
    private String user_favorite;//收藏商品

    @Column(nullable = false,length = 50)
    private String user_footPrint;//足迹

}
