package org.wlgzs.xf_mall.controller;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.xf_mall.entity.*;
import org.wlgzs.xf_mall.service.ProductService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/24 16:44
 * @Description: 有关商品的操作
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
    public ModelAndView toProductList(Model model, @RequestParam(value = "page",defaultValue = "0") int page,
                                      @RequestParam(value = "limit",defaultValue = "10") int limit){
        String product_keywords ="";
        Page pages =  productService.getProductListPage(product_keywords,page,limit);
        model.addAttribute("TotalPages", pages.getTotalPages());//查询的页数
        model.addAttribute("TotalElements", pages.getTotalElements());//查询的总记录数
        model.addAttribute("Number", pages.getNumber());//查询的当前第几页
        List<Product> products = pages.getContent();
        String img;
        for(int i = 0; i < products.size(); i++) {
            if (products.get(i).getProduct_picture().contains(",")){
                img = products.get(i).getProduct_picture();
                img = img.substring(0,img.indexOf(","));
                System.out.println();
                products.get(i).setProduct_picture(img);
            }
        }
        model.addAttribute("products", products);//查询的当前页的集合
        model.addAttribute("NumberOfElements", pages.getNumberOfElements());//查询的当前页的记录数
        return new ModelAndView("productList");
    }
    /**
     * @author 阿杰
     * @param [model, id]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 跳转至商品详情页面
     */
    @RequestMapping("/toProduct")
    public ModelAndView toProduct(Model model, long productId) {
        Product product = productService.findProductById(productId);
        model.addAttribute("product", product);
        return new ModelAndView("productDetails");
    }
    /**
     * @author 阿杰
     * @param [model, userId, productId, request]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 添加购物车
     */
    @RequestMapping("/addShoppingProduct")
    public  ModelAndView addShoppingProduct(long userId,long productId,HttpServletRequest request){
        productService.save(userId,productId,request);
        String url="redirect:/ProductListController/toProduct?productId="+productId;
        return new ModelAndView(url);
    }
    /**
     * @author 阿杰
     * @param [model, userId, productId, request]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 添加收藏
     */
    @RequestMapping("/addCollectionProduct")
    public  ModelAndView addCollectionProduct(long userId,long productId,HttpServletRequest request){
        productService.saveCollection(userId,productId,request);
        String url="redirect:/ProductListController/toProduct?productId="+productId;
        return new ModelAndView(url);
    }
    /**
     * @author 阿杰
     * @param [model, userId]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 跳转至购物车
     */
    @RequestMapping("/shoppingCart")
    public ModelAndView toShoppingCart(Model model,long userId){
        List<ShoppingCart> shoppingCarts = productService.findByUserIdCart(userId);
        model.addAttribute("shoppingCarts",shoppingCarts);
        return new ModelAndView("shoppingCart");
    }
    /**
     * @author 阿杰
     * @param [shoppingCart_id, userId, productId, request]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 购物车移至收藏
     */
    @RequestMapping("/moveToCollectionProduct")
    public  ModelAndView moveToCollectionProduct(long shoppingCart_id,long userId,long productId,HttpServletRequest request){
        productService.moveToCollectionProduct(shoppingCart_id,userId,productId,request);
        String url="redirect:/ProductListController/shoppingCart?userId="+userId;
        return new ModelAndView(url);
    }
    /**
     * @author 阿杰
     * @param [shoppingCart_id, userId]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 删除购物车
     */
    @RequestMapping("/deleteShoppingProduct")
    public  ModelAndView deleteShoppingProduct(long shoppingCart_id,long userId){
        productService.deleteShoppingCart(shoppingCart_id);
        String url="redirect:/ProductListController/shoppingCart?userId="+userId;
        return new ModelAndView(url);
    }
    /**
     * @author 阿杰
     * @param [model, userId, collection_id]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 跳转至用户的收藏
     */
    @RequestMapping("/collectionProduct")
    public ModelAndView toCollection(Model model,long userId){
        List<Collection> collections = productService.findByUserIdCollection(userId);
        model.addAttribute("collections",collections);
        return new ModelAndView("collection");
    }
    /**
     * @author 阿杰
     * @param [collection_id, userId]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 删除收藏
     */
    @RequestMapping("/deleteCollectionProduct")
    public  ModelAndView deleteCollectionProduct(long collection_id,long userId){
        productService.deleteCollection(collection_id);
        String url="redirect:/ProductListController/collectionProduct?userId="+userId;
        return new ModelAndView(url);
    }
}
