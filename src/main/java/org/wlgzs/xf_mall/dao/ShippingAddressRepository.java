package org.wlgzs.xf_mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.wlgzs.xf_mall.entity.ShippingAddress;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/25 17:24
 * @Description:
 */
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long>,JpaSpecificationExecutor<ShippingAddress> {
}
