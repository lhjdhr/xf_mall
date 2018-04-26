package org.wlgzs.xf_mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.wlgzs.xf_mall.entity.Collection;
import org.wlgzs.xf_mall.entity.ShoppingCart;

import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/25 10:55
 * @Description:
 */
public interface CollectionRepository extends JpaRepository<Collection, Long>,JpaSpecificationExecutor<Collection> {

    @Query(value = "SELECT o FROM Collection o WHERE o.user_id=?1")
    List<Collection> findByUserIdCollection(long user_id);

    @Query(value = "SELECT o FROM Collection o WHERE o.user_id=?1 AND o.product_id=?2")
    Collection findByCollectionUserIdAndProductId(long user_id, long product_id);
}
