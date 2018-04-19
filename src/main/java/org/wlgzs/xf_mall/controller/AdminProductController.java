package org.wlgzs.xf_mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wlgzs.xf_mall.entity.Product;
import org.wlgzs.xf_mall.service.AdminProductService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/18 20:51
 * @Description: 后台商品管理
 */
@Controller
public class AdminProductController {
    @Resource
    AdminProductService productService;
    /**
     * @author 阿杰
     * @param [model]
     * @return java.lang.String
     * @description 后台商品列表
     */
    @RequestMapping("/AdminProductController/adminProductList")
    public String list(Model model) {
        List<Product> products = productService.getProductList();
        if (products==null){
            System.out.println(123);
        }
        model.addAttribute("products",products);
        return "admin/adminProductList";
    }
    /**
     * @author 阿杰
     * @param [model, product_keywords]
     * @return java.lang.String
     * @description 搜索商品
     */
    @RequestMapping("/AdminProductController/adminFindProduct")
    public  String findProduct(Model model,String product_keywords){
        List<Product> products = productService.findByProductKeywords(product_keywords);
        model.addAttribute("products",products);
        model.addAttribute("product_keywords",product_keywords);
        return "admin/adminProductList";
    }
    /**
     * @author 阿杰
     * @return java.lang.String
     * @description 跳转到添加商品
     */
    @RequestMapping("/AdminProductController/toAdminAddProduct")
    public String toAdd(){
        return "admin/adminAddProduct";
    }
    /**
     * @author 阿杰
     * @param [product]
     * @return java.lang.String
     * @description 添加商品
     */
    @RequestMapping("/AdminProductController/adminAddProduct")
    public String add(Product product){
        productService.save(product);
        return "redirect:/AdminProductController/adminProductList";
    }
    /**
     * @author 阿杰
     * @param [model, id]
     * @return java.lang.String
     * @description 跳转至修改商品页面
     */
    @RequestMapping("/AdminProductController/toAdminEditProduct")
    public String toEdit(Model model, Long id) {
        Product product=productService.findProductById(id);
        model.addAttribute("product", product);
        return "admin/adminEditProduct";
    }
    /**
     * @author 阿杰
     * @param [product]
     * @return java.lang.String
     * @description 修改商品
     */
    @RequestMapping("/AdminProductController/adminEditProduct")
    public String edit(Product product) {
        productService.edit(product);
        return "redirect:/AdminProductController/adminProductList";
    }
    /**
     * @author 阿杰
     * @param [id]
     * @return java.lang.String
     * @description 删除商品
     */
    @RequestMapping("/AdminProductController/adminDeleteProduct")
    public String delete(Long id) {
        productService.delete(id);
        return "redirect:/AdminProductController/adminProductList";
    }

}
