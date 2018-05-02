package org.wlgzs.xf_mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.wlgzs.xf_mall.entity.ShippingAddress;

/**
 * @author:胡亚星
 * @createTime 2018-04-26 21:46
 * @description:
 **/


public interface UserManagementRepository extends JpaRepository<ShippingAddress, Long>,JpaSpecificationExecutor<ShippingAddress> {
    ShippingAddress findById(long id);

    
}
