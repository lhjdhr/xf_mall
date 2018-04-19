package org.wlgzs.xf_mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlgzs.xf_mall.dao.ProductRepository;
import org.wlgzs.xf_mall.entity.Product;
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

    //遍历商品
    @Override
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(long id) {
        return productRepository.findById(id);
    }

    //添加商品
    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    //修改商品
    @Override
    public void edit(Product product) {
        productRepository.save(product);
    }

    //删除商品
    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }

    //搜索商品
    @Override
    public List<Product> findByProductKeywords(String product_keywords) {
        return productRepository.findByProductKeywords(product_keywords);
    }
}
