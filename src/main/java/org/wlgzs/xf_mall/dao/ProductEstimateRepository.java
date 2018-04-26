package org.wlgzs.xf_mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.wlgzs.xf_mall.entity.ProductEstimate;

/**
 * @Auther: Administrator
 * @Date: 2018/4/23 13:14
 * @Description:
 */
public interface ProductEstimateRepository extends JpaRepository<ProductEstimate,Long>,JpaSpecificationExecutor<ProductEstimate> {
    ProductEstimate findById(long id);
}
