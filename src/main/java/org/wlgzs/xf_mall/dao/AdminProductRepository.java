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
public interface AdminProductRepository  extends JpaRepository<Product, Long>,JpaSpecificationExecutor<Product> {

    Product findById(long id);

    /**
     * @author 阿杰
     * @param [product_keywords]
     * @description 搜索商品关键字
     */
    @Query(value = "SELECT * FROM product WHERE product_keywords like %:product_keywords%", nativeQuery = true)
    List<Product> findByProductKeywords(@Param("product_keywords") String product_keywords);
}
