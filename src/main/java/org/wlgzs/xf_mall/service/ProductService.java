package org.wlgzs.xf_mall.service;

import org.wlgzs.xf_mall.entity.*;

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

    void delete(long product_id);

    Product findProductById(long product_id);

    List<Product> findByProductKeywords(String product_keywords);

    List<ProductCategory> getProductCategoryList();

    void save(ProductCategory productCategory);

    void saveOne(ProductCategory productCategory);

    void editCategory(ProductCategory productCategory);

    void deleteCategory(long id);

    ProductCategory findProductCategoryById(long id);

    List<ProductCategory> findByProductCategory(String category_name);

    void save(ShoppingCart shoppingCart);

    ShoppingCart findByUserIdAndProductId(long user_id, long product_id);

    void save(Collection collection);

    Collection findByCollectionUserIdAndProductId(long user_id, long product_id);

    void save(ShippingAddress shippingAddress);

    List<ShoppingCart> findByUserIdCart(long user_id);

    void deleteShoppingCart(long shoppingCart_id);

    List<Collection> findByUserIdCollection(long user_id);

    void deleteCollection(long collection_id);

}
