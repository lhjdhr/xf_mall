package org.wlgzs.xf_mall.service;

import org.wlgzs.xf_mall.entity.Product;

import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/18 20:58
 * @Description:
 */
public interface ProductService {

    List<Product> getProductList();

    Product findProductById(long id);

    void save(Product product);

    void edit(Product product);

    void delete(long id);

    List<Product> findByProductKeywords(String product_keywords);
}
