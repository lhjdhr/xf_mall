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

    void delete(long productId);

    Product findProductById(long productId);

    Page getProductCategoryList(String category_name, int page, int limit);

    void save(ProductCategory productCategory);

    void saveOne(ProductCategory productCategory);

    void editCategory(ProductCategory productCategory);

    void deleteCategory(long categoryId);

    ProductCategory findProductCategoryById(long categoryId);

    void save(long userId,long productId,HttpServletRequest request);

    ShoppingCart findByUserIdAndProductId(long userId, long productId);

    void saveCollection(long userId,long productId,HttpServletRequest request);

    Collection findByCollectionUserIdAndProductId(long userId, long productId);

    List<ShoppingCart> findByUserIdCart(long userId);

    void moveToCollectionProduct(long shoppingCarId,long userId,long productId,HttpServletRequest request);

    void deleteShoppingCart(long shoppingCartId);

    List<Collection> findByUserIdCollection(long userId);

    void deleteCollection(long collectionId);

}
