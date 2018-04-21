package org.wlgzs.xf_mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlgzs.xf_mall.dao.ProductCategoryRepository;
import org.wlgzs.xf_mall.dao.ProductRepository;
import org.wlgzs.xf_mall.entity.Product;
import org.wlgzs.xf_mall.entity.ProductCategory;
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

}