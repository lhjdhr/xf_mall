package org.wlgzs.xf_mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.wlgzs.xf_mall.entity.ShoppingCart;

import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/25 10:53
 * @Description:
 */
public interface ShoppingCartRepository  extends JpaRepository<ShoppingCart, Long>,JpaSpecificationExecutor<ShoppingCart> {

    @Query(value = "SELECT o FROM ShoppingCart o WHERE o.user_id=?1")
    List<ShoppingCart> findByUserIdCart(long user_id);

    @Query(value = "SELECT o FROM ShoppingCart o WHERE o.user_id=?1 AND o.product_id=?2")
    ShoppingCart findByUserIdAndProductId(long user_id,long product_id);
}
