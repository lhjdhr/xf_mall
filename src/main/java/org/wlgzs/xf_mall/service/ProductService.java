package org.wlgzs.xf_mall.service;

import org.wlgzs.xf_mall.entity.Product;
import org.wlgzs.xf_mall.entity.ProductCategory;

import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/18 20:58
 * @Description:
 */
public interface ProductService {

    List<Product> getProductList();

    void save(Product product);

    void edit(Product product);

    void delete(long id);

    Product findProductById(long id);

    List<Product> findByProductKeywords(String product_keywords);

    List<ProductCategory> getProductCategoryList();

    void save(ProductCategory productCategory);

    void saveOne(ProductCategory productCategory);

    void editCategory(ProductCategory productCategory);

    void deleteCategory(long id);

    ProductCategory findProductCategoryById(long id);

    List<ProductCategory> findByProductCategory(String category_name);
}
