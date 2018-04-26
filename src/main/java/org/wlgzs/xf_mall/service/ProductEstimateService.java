package org.wlgzs.xf_mall.service;

import org.wlgzs.xf_mall.entity.ProductEstimate;

/**
 * @Auther: Administrator
 * @Date: 2018/4/23 13:18
 * @Description:
 */
public interface ProductEstimateService {
    ProductEstimate findProductEstimateById(long id);

    void save(ProductEstimate productEstimate);
}
