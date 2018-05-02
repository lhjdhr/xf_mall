package org.wlgzs.xf_mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wlgzs.xf_mall.entity.Product;

import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/18 20:59
 * @Description:
 */
public interface ProductRepository extends JpaRepository<Product, Long>,JpaSpecificationExecutor<Product> {

    Product findById(long productId);

    /**
     * @author 阿杰
     * @param [product_keywords]
     * @description 搜索商品关键字
     */
    @Query(value = "SELECT o FROM Product o WHERE o.product_keywords like %?1%")
    List<Product> findByProductKeywords(String product_keywords);
}
