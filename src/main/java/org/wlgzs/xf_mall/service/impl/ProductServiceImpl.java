package org.wlgzs.xf_mall.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.wlgzs.xf_mall.dao.*;
import org.wlgzs.xf_mall.entity.*;
import org.wlgzs.xf_mall.service.ProductService;
import org.wlgzs.xf_mall.util.PageUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    //分页遍历商品  搜索商品
    @Override
    public Page<Product> getProductListPage(String product_keywords, int page, int limit) {
        Sort sort = new Sort(Sort.Direction.DESC, "productId");
        Pageable pageable = new PageRequest(page,limit, sort);
        Specification<Product> specification = new PageUtil<Product>(product_keywords).getPage("product_keywords");
        Page pages = productRepository.findAll(specification,pageable);
        return pages;
    }

    //添加商品
    @Override
    public void saveProduct(String product_details,MultipartFile[] myFileNames, HttpSession session, HttpServletRequest request) {
        String realName = "";
        String[] str = new String[myFileNames.length];
        for (int i = 0; i < myFileNames.length; i++) {
            if (!myFileNames[i].getOriginalFilename().equals("")) {
                String fileName = myFileNames[i].getOriginalFilename();
                String fileNameExtension = fileName.substring(fileName.indexOf("."), fileName.length());

                // 生成实际存储的真实文件名
                realName = UUID.randomUUID().toString() + fileNameExtension;

                // "/upload"是你自己定义的上传目录
                String realPath = session.getServletContext().getRealPath("/upload");
                File uploadFile = new File(realPath, realName);
                try {
                    myFileNames[i].transferTo(uploadFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(!myFileNames[i].getOriginalFilename().equals("")){
                str[i] = request.getContextPath() + "/upload/" + realName;
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            if(!myFileNames[i].getOriginalFilename().equals("")) {
                stringBuffer.append(str[i] + ",");
            }
        }
        String product_picture = stringBuffer.substring(0,stringBuffer.length()-1);

        Map<String, String[]> properties = request.getParameterMap();
        Product product = new Product();
        try {
            BeanUtils.populate(product, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        product.setProduct_details(product_details);
        product.setProduct_picture(product_picture);
        productRepository.save(product);
    }

    //富文本添加图片
    @Override
    public String[] uploadImg(MultipartFile myFileName, HttpSession session, HttpServletRequest request) {
        String realName = "";
        if (myFileName != null) {
            String fileName = myFileName.getOriginalFilename();
            String fileNameExtension = fileName.substring(fileName.indexOf("."), fileName.length());

            // 生成实际存储的真实文件名
            realName = UUID.randomUUID().toString() + fileNameExtension;

            // "/upload"是你自己定义的上传目录
            String realPath = session.getServletContext().getRealPath("/upload");
            File uploadFile = new File(realPath, realName);
            try {
                myFileName.transferTo(uploadFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String[] str = {request.getContextPath() + "/upload/" + realName};
        return str;
    }

    //删除商品
    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }

    //通过id查找商品
    @Override
    public Product findProductById(long productId) {
        Product product = productRepository.findById(productId);
        String img;
        if (product.getProduct_picture().contains(",")){
            img = product.getProduct_picture();
            img = img.substring(0,img.indexOf(","));
            product.setProduct_picture(img);
        }
        return product;
    }

    //修改商品
    @Override
    public void edit(Product product) {
        productRepository.save(product);
    }

    //遍历所有分类  搜索分类
    @Override
    public Page getProductCategoryList(String category_name, int page, int limit) {
        Sort sort = new Sort(Sort.Direction.DESC, "categoryId");
        Pageable pageable = new PageRequest(page,limit, sort);
        Specification<ProductCategory> specification = new PageUtil<ProductCategory>(category_name).getPage("category_name");
        Page pages = productCategoryRepository.findAll(specification,pageable);
        return pages;
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

    //添加购物车
    @Override
    public void save(long user_id,long product_id,HttpServletRequest request) {
        Product product = productRepository.findById(product_id);

        Map<String, String[]> properties = request.getParameterMap();
        ShoppingCart shoppingCart = new ShoppingCart();
        try {
            BeanUtils.populate(shoppingCart, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        ShoppingCart findShoppingCart = shoppingCartRepository.findByUserIdAndProductId(user_id,product_id);
        if(findShoppingCart != null){
            findShoppingCart.setShoppingCart_count(findShoppingCart.getShoppingCart_count()+shoppingCart.getShoppingCart_count());
            shoppingCartRepository.save(findShoppingCart);
        }
        if(findShoppingCart == null){
            shoppingCart.setProduct_id(product_id);
            shoppingCart.setProduct_picture(product.getProduct_picture());
            shoppingCart.setProduct_counterPrice(product.getProduct_counterPrice());
            shoppingCart.setProduct_keywords(product.getProduct_keywords());
            shoppingCart.setProduct_mallPrice(product.getProduct_mallPrice());
            shoppingCart.setProduct_specification(product.getProduct_specification());
            shoppingCart.setUser_id(user_id);
            shoppingCartRepository.save(shoppingCart);
        }
    }

    //查找用户购物车是否存在
    @Override
    public ShoppingCart findByUserIdAndProductId(long user_id, long product_id) {
        return shoppingCartRepository.findByUserIdAndProductId(user_id,product_id);
    }

    //添加收藏
    @Override
    public void saveCollection(long user_id,long product_id,HttpServletRequest request) {
        Product product = productRepository.findById(product_id);

        Map<String, String[]> properties = request.getParameterMap();
        Collection collection = new Collection();
        try {
            BeanUtils.populate(collection, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Collection findCollection = collectionRepository.findByCollectionUserIdAndProductId(user_id,product_id);
        if(findCollection != null){
            collectionRepository.deleteById(findCollection.getCollectionId());
            collectionRepository.save(findCollection);
        }
        if(findCollection == null){
            collection.setProduct_id(product_id);
            collection.setProduct_picture(product.getProduct_picture());
            collection.setProduct_keywords(product.getProduct_keywords());
            collection.setProduct_mallPrice(product.getProduct_mallPrice());
            collection.setUser_id(user_id);
            collectionRepository.save(collection);
        }
    }

    //查找用户收藏是否存在
    @Override
    public Collection findByCollectionUserIdAndProductId(long user_id, long product_id) {
        return collectionRepository.findByCollectionUserIdAndProductId(user_id,product_id);
    }

    //用户的购物车
    @Override
    public List<ShoppingCart> findByUserIdCart(long user_id) {
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findByUserIdCart(user_id);
        String img;
        for(int i = 0; i < shoppingCarts.size(); i++) {
            if (shoppingCarts.get(i).getProduct_picture().contains(",")){
                img = shoppingCarts.get(i).getProduct_picture();
                img = img.substring(0,img.indexOf(","));
                System.out.println(img);
                shoppingCarts.get(i).setProduct_picture(img);
            }
        }
        return shoppingCarts;
    }

    //购物车移至收藏
    @Override
    public void moveToCollectionProduct(long shoppingCartId, long user_id, long product_id, HttpServletRequest request) {
        shoppingCartRepository.deleteById(shoppingCartId);

        Product product = productRepository.findById(product_id);

        Map<String, String[]> properties = request.getParameterMap();
        Collection collection = new Collection();
        try {
            BeanUtils.populate(collection, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Collection findCollection = collectionRepository.findByCollectionUserIdAndProductId(user_id,product_id);
        if(findCollection != null){
            collectionRepository.deleteById(findCollection.getCollectionId());
            collectionRepository.save(findCollection);
        }
        if(findCollection == null){
            collection.setProduct_id(product_id);
            collection.setProduct_picture(product.getProduct_picture());
            collection.setProduct_keywords(product.getProduct_keywords());
            collection.setProduct_mallPrice(product.getProduct_mallPrice());
            collection.setUser_id(user_id);
            System.out.println();
            collectionRepository.save(collection);
        }
    }

    //删除购物车
    @Override
    public void deleteShoppingCart(long shoppingCartId) {
        shoppingCartRepository.deleteById(shoppingCartId);
    }

    //用户的收藏
    @Override
    public List<Collection> findByUserIdCollection(long user_id) {
        List<Collection> collections = collectionRepository.findByUserIdCollection(user_id);
        String img;
        for(int i = 0; i < collections.size(); i++) {
            if (collections.get(i).getProduct_picture().contains(",")){
                img = collections.get(i).getProduct_picture();
                img = img.substring(0,img.indexOf(","));
                System.out.println(" ");
                collections.get(i).setProduct_picture(img);
            }
        }
        return collections;
    }

    //删除收藏
    @Override
    public void deleteCollection(long collection_id) {
        collectionRepository.deleteById(collection_id);
    }

}
