package org.wlgzs.xf_mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wlgzs.xf_mall.entity.Orders;

import java.util.List;


/**
 * @Auther: Administrator
 * @Date: 2018/4/20 20:04
 * @Description:
 */
public interface OrdersRepository extends JpaRepository<Orders, Long>,JpaSpecificationExecutor<Orders> {
   Orders findById(long id);

 /*  @Query(value = "SELECT * FROM orders WHERE product_keywords like %:product_keywords%", nativeQuery = true)
    List<Orders> findByProductKeywords(@Param("product_keywords") String product_keywords);

    @Query(value = "SELECT * FROM orders WHERE user_name like %:user_name%", nativeQuery = true)
    List<Orders> findByUserName(@Param("user_name") String user_name);
*/
    @Query(value = "SELECT * FROM orders WHERE order_number like %:order_number%", nativeQuery = true)
    List<Orders> findByOrderNumber(@Param("order_number") String order_number);
}
