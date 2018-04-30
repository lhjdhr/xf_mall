package org.wlgzs.xf_mall.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.xf_mall.entity.*;
import org.wlgzs.xf_mall.service.ProductService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/24 16:44
 * @Description:
 */
@RequestMapping("ProductListController")
@RestController
public class ProductListController {
    @Resource
    ProductService productService;
    /**
     * @author 阿杰
     * @param [model]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 跳转至商品列表页面
     */
    @RequestMapping("/toProductList")
    public ModelAndView toProductList(Model model){
        List<Product> products = productService.getProductList();
        String img;
        for(int i = 0; i < products.size(); i++) {
            if (products.get(i).getProduct_picture().contains(",")){
                img = products.get(i).getProduct_picture();
                img = img.substring(0,img.indexOf(","));
                products.get(i).setProduct_picture(img);
            }
        }
        model.addAttribute("products",products);
        return new ModelAndView("productList");
    }
    /**
     * @author 阿杰
     * @param [model, id]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 跳转至商品详情页面
     */
    @RequestMapping("/toProduct")
    public ModelAndView toProduct(Model model, long product_id) {
        Product product = productService.findProductById(product_id);
        String img;
        if (product.getProduct_picture().contains(",")){
            img = product.getProduct_picture();
            img = img.substring(0,img.indexOf(","));
            product.setProduct_picture(img);
        }
        model.addAttribute("product", product);
        return new ModelAndView("productDetails");
    }
    /**
     * @author 阿杰
     * @param [model, user_id, product_id, request]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 添加购物车
     */
    @RequestMapping("/addShoppingProduct")
    public  ModelAndView addShoppingProduct(long user_id,long product_id,HttpServletRequest request){
        Product product = productService.findProductById(product_id);

        Map<String, String[]> properties = request.getParameterMap();
        ShoppingCart shoppingCart = new ShoppingCart();
        try {
            BeanUtils.populate(shoppingCart, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        ShoppingCart findShoppingCart = productService.findByUserIdAndProductId(user_id,product_id);
        if(findShoppingCart != null){
            findShoppingCart.setShoppingCart_count(findShoppingCart.getShoppingCart_count()+shoppingCart.getShoppingCart_count());
            productService.save(findShoppingCart);
        }
        if(findShoppingCart == null){
            shoppingCart.setProduct_id(product_id);
            shoppingCart.setProduct_picture(product.getProduct_picture());
            shoppingCart.setProduct_counterPrice(product.getProduct_counterPrice());
            shoppingCart.setProduct_keywords(product.getProduct_keywords());
            shoppingCart.setProduct_mallPrice(product.getProduct_mallPrice());
            shoppingCart.setProduct_specification(product.getProduct_specification());
            shoppingCart.setUser_id(user_id);
            productService.save(shoppingCart);
        }
        String url="redirect:/ProductListController/toProduct?product_id="+product_id;
        return new ModelAndView(url);
    }
    /**
     * @author 阿杰
     * @param [model, user_id, product_id, request]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 添加收藏
     */
    @RequestMapping("/addCollectionProduct")
    public  ModelAndView addCollectionProduct(long user_id,long product_id,HttpServletRequest request){
        Product product = productService.findProductById(product_id);

        Map<String, String[]> properties = request.getParameterMap();
        Collection collection = new Collection();
        try {
            BeanUtils.populate(collection, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Collection findCollection = productService.findByCollectionUserIdAndProductId(user_id,product_id);
        if(findCollection != null){
            productService.deleteCollection(findCollection.getCollection_id());
            productService.save(findCollection);
        }
        if(findCollection == null){
            System.out.println();
            collection.setProduct_id(product_id);
            collection.setProduct_picture(product.getProduct_picture());
            collection.setProduct_keywords(product.getProduct_keywords());
            collection.setProduct_mallPrice(product.getProduct_mallPrice());
            collection.setUser_id(user_id);
            productService.save(collection);
        }
        String url="redirect:/ProductListController/toProduct?product_id="+product_id;
        return new ModelAndView(url);
    }
    /**
     * @author 阿杰
     * @param [model, user_id]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 跳转至购物车
     */
    @RequestMapping("/shoppingCart")
    public ModelAndView toShoppingCart(Model model,long user_id){
        List<ShoppingCart> shoppingCarts = productService.findByUserIdCart(user_id);
        String img;
        for(int i = 0; i < shoppingCarts.size(); i++) {
            if (shoppingCarts.get(i).getProduct_picture().contains(",")){
                img = shoppingCarts.get(i).getProduct_picture();
                img = img.substring(0,img.indexOf(","));
                System.out.println();
                shoppingCarts.get(i).setProduct_picture(img);
            }
        }
        model.addAttribute("shoppingCarts",shoppingCarts);
        return new ModelAndView("shoppingCart");
    }
    /**
     * @author 阿杰
     * @param [shoppingCart_id, user_id, product_id, request]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 购物车移至收藏
     */
    @RequestMapping("/moveToCollectionProduct")
    public  ModelAndView moveToCollectionProduct(long shoppingCart_id,long user_id,long product_id,HttpServletRequest request){
        productService.deleteShoppingCart(shoppingCart_id);

        Product product = productService.findProductById(product_id);

        Map<String, String[]> properties = request.getParameterMap();
        Collection collection = new Collection();
        try {
            BeanUtils.populate(collection, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Collection findCollection = productService.findByCollectionUserIdAndProductId(user_id,product_id);
        if(findCollection != null){
            productService.deleteCollection(findCollection.getCollection_id());
            productService.save(findCollection);
        }
        if(findCollection == null){
            collection.setProduct_id(product_id);
            collection.setProduct_picture(product.getProduct_picture());
            collection.setProduct_keywords(product.getProduct_keywords());
            collection.setProduct_mallPrice(product.getProduct_mallPrice());
            collection.setUser_id(user_id);
            productService.save(collection);
        }
        String url="redirect:/ProductListController/shoppingCart?user_id="+user_id;
        return new ModelAndView(url);
    }
    /**
     * @author 阿杰
     * @param [shoppingCart_id, user_id]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 删除购物车
     */
    @RequestMapping("/deleteShoppingProduct")
    public  ModelAndView deleteShoppingProduct(long shoppingCart_id,long user_id){
        productService.deleteShoppingCart(shoppingCart_id);
        String url="redirect:/ProductListController/shoppingCart?user_id="+user_id;
        return new ModelAndView(url);
    }
    /**
     * @author 阿杰
     * @param [model, user_id, collection_id]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 跳转至用户的收藏
     */
    @RequestMapping("/collectionProduct")
    public ModelAndView toCollection(Model model,long user_id){
        List<Collection> collections = productService.findByUserIdCollection(user_id);
        String img;
        for(int i = 0; i < collections.size(); i++) {
            if (collections.get(i).getProduct_picture().contains(",")){
                img = collections.get(i).getProduct_picture();
                img = img.substring(0,img.indexOf(","));
                System.out.println(" ");
                collections.get(i).setProduct_picture(img);
            }
        }
        model.addAttribute("collections",collections);
        return new ModelAndView("collection");
    }
    /**
     * @author 阿杰
     * @param [collection_id, user_id]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 删除收藏
     */
    @RequestMapping("/deleteCollectionProduct")
    public  ModelAndView deleteCollectionProduct(long collection_id,long user_id){
        productService.deleteCollection(collection_id);
        String url="redirect:/ProductListController/collectionProduct?user_id="+user_id;
        return new ModelAndView(url);
    }
    /*@RequestMapping("/toOrder")
    public ModelAndView toOrder(Model model){
        return new ModelAndView("order");
    }
    @RequestMapping("/address")
    public ModelAndView add(ShippingAddress shippingAddress,HttpServletRequest request){

        productService.save(shippingAddress);
        return new ModelAndView("redirect:/AdminProductController/productCategoryList");
    }*/
}
