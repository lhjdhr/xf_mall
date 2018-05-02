package org.wlgzs.xf_mall.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import org.wlgzs.xf_mall.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/18 20:58
 * @Description:
 */
public interface ProductService {

    Page<Product> getProductListPage(String product_keywords, int page, int limit);

    void saveProduct(String product_details,MultipartFile[] myFileNames, HttpSession session, HttpServletRequest request);

    String[] uploadImg(MultipartFile myFileName, HttpSession session, HttpServletRequest request);

    void edit(Product product);

    void delete(long product_id);

    Product findProductById(long productId);

    Page getProductCategoryList(String category_name, int page, int limit);

    void save(ProductCategory productCategory);

    void saveOne(ProductCategory productCategory);

    void editCategory(ProductCategory productCategory);

    void deleteCategory(long id);

    ProductCategory findProductCategoryById(long id);

    void save(long user_id,long product_id,HttpServletRequest request);

    ShoppingCart findByUserIdAndProductId(long user_id, long product_id);

    void saveCollection(long user_id,long product_id,HttpServletRequest request);

    Collection findByCollectionUserIdAndProductId(long user_id, long product_id);

    List<ShoppingCart> findByUserIdCart(long user_id);

    void moveToCollectionProduct(long shoppingCart_id,long user_id,long product_id,HttpServletRequest request);

    void deleteShoppingCart(long shoppingCart_id);

    List<Collection> findByUserIdCollection(long user_id);

    void deleteCollection(long collection_id);

}
