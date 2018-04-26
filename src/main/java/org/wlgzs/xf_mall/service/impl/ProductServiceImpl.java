package org.wlgzs.xf_mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlgzs.xf_mall.dao.*;
import org.wlgzs.xf_mall.entity.*;
import org.wlgzs.xf_mall.service.ProductService;

import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/18 21:21
 * @Description:
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private CollectionRepository collectionRepository;
    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    //遍历商品
    @Override
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    //添加商品
    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    //删除商品
    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }

    //通过id查找商品
    @Override
    public Product findProductById(long id) {
        return productRepository.findById(id);
    }

    //修改商品
    @Override
    public void edit(Product product) {
        productRepository.save(product);
    }

    //搜索商品
    @Override
    public List<Product> findByProductKeywords(String product_keywords) {
        return productRepository.findByProductKeywords(product_keywords);
    }

    //遍历所有分类
    @Override
    public List<ProductCategory> getProductCategoryList() {
        return productCategoryRepository.findAll();
    }

    //增加一级分类
    @Override
    public void saveOne(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }

    //增加二级分类
    @Override
    public void save(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }
    //删除分类
    @Override
    public void deleteCategory(long id) {
        productCategoryRepository.deleteById(id);
    }

    //修改分类
    @Override
    public void editCategory(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }

    //按id查找类别
    @Override
    public ProductCategory findProductCategoryById(long id) {
        return productCategoryRepository.findById(id);
    }

    //搜索分类名
    @Override
    public List<ProductCategory> findByProductCategory(String category_name) {
        return productCategoryRepository.findByCategoryName(category_name);
    }

    //添加购物车
    @Override
    public void save(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
    }

    //查找用户购物车是否存在
    @Override
    public ShoppingCart findByUserIdAndProductId(long user_id, long product_id) {
        return shoppingCartRepository.findByUserIdAndProductId(user_id,product_id);
    }

    //添加收藏
    @Override
    public void save(Collection collection) {
        collectionRepository.save(collection);
    }

    //查找用户收藏是否存在
    @Override
    public Collection findByCollectionUserIdAndProductId(long user_id, long product_id) {
        return collectionRepository.findByCollectionUserIdAndProductId(user_id,product_id);
    }

    @Override
    public void save(ShippingAddress shippingAddress) {
        shippingAddressRepository.save(shippingAddress);
    }

    //用户的购物车
    @Override
    public List<ShoppingCart> findByUserIdCart(long user_id) {
        return shoppingCartRepository.findByUserIdCart(user_id);
    }

    //删除购物车
    @Override
    public void deleteShoppingCart(long shoppingCart_id) {
        shoppingCartRepository.deleteById(shoppingCart_id);
    }

    //用户的收藏
    @Override
    public List<Collection> findByUserIdCollection(long user_id) {
        return collectionRepository.findByUserIdCollection(user_id);
    }

    //删除收藏
    @Override
    public void deleteCollection(long collection_id) {
        collectionRepository.deleteById(collection_id);
    }

}
