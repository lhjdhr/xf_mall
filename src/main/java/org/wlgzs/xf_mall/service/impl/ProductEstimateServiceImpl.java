package org.wlgzs.xf_mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlgzs.xf_mall.dao.ProductEstimateRepository;
import org.wlgzs.xf_mall.entity.ProductEstimate;
import org.wlgzs.xf_mall.service.ProductEstimateService;

import javax.websocket.OnClose;

/**
 * @Auther: 李晓珊
 * @Date: 2018/4/23 13:16
 * @Description: 商品评价
 */
@Service
public class ProductEstimateServiceImpl implements ProductEstimateService {
    @Autowired
    private ProductEstimateRepository productEstimateRepository;

    @Override
    public ProductEstimate findProductEstimateById(long id){
        return productEstimateRepository.findById(id);
    }

    @Override
    public void save(ProductEstimate productEstimate){
        productEstimateRepository.save(productEstimate);
    }
}
