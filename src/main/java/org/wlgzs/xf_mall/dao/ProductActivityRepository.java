package org.wlgzs.xf_mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.wlgzs.xf_mall.entity.ProductActivity;

/**
 * @Auther: 阿杰
 * @Date: 2018/5/2 00:24
 * @Description:
 */
public interface ProductActivityRepository extends JpaRepository<ProductActivity, Long>,JpaSpecificationExecutor<ProductActivity> {
    ProductActivity findById(long activityId);
}
