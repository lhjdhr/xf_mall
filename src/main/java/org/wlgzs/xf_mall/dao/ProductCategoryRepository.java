package org.wlgzs.xf_mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wlgzs.xf_mall.entity.ProductCategory;

import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/20 13:06
 * @Description:
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>,JpaSpecificationExecutor<ProductCategory> {

    ProductCategory findById(long id);
    /**
     * @author 阿杰
     * @param [category_name]
     * @description 搜索分类名
     */
    @Query(value = "SELECT o FROM ProductCategory o WHERE o.category_name like %?1%")
    List<ProductCategory> findByCategoryName(String category_name);
}
