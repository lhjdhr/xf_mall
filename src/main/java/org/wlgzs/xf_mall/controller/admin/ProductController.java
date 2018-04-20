package org.wlgzs.xf_mall.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wlgzs.xf_mall.entity.Product;
import org.wlgzs.xf_mall.entity.ProductCategory;
import org.wlgzs.xf_mall.service.ProductService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/18 20:51
 * @Description: 后台商品管理
 */
@Controller
public class ProductController {
    @Resource
    ProductService productService;
    /**
     * @author 阿杰
     * @param [model]
     * @return java.lang.String
     * @description 后台商品列表
     */
    @RequestMapping("/AdminProductController/adminProductList")
    public String list(Model model) {
        List<Product> products = productService.getProductList();
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
    /**
     * @author 阿杰
     * @param [model]
     * @return java.lang.String
     * @description 遍历所有分类
     */
    @RequestMapping("/AdminProductController/productCategoryList")
    public String category(Model model){
        List<ProductCategory> productCategories = productService.getProductCategoryList();
        model.addAttribute("productCategories",productCategories);
        return "admin/productCategorylists";
    }
    /**
     * @author 阿杰
     * @param []
     * @return java.lang.String
     * @description 跳转至添加一级分类页面
     */
    @RequestMapping("/AdminProductController/toAddProductOneCategory")
    public String toAddOneCategory(){
        return "admin/addProductOneCategory";
    }
    /**
     * @author 阿杰
     * @param [productCategory]
     * @return java.lang.String
     * @description 添加一级分类
     */
    @RequestMapping("/AdminProductController/addProductOneCategory")
    public String addOneCategory(ProductCategory productCategory){
        productService.saveOne(productCategory);
        return "redirect:/AdminProductController/productCategoryList";
    }
    /**
     * @author 阿杰
     * @param []
     * @return java.lang.String
     * @description 跳转至添加二级分类页面
     */
    @RequestMapping("/AdminProductController/toAddProductCategory")
    public String toAddCategory(){
        return "admin/addProductCategory";
    }
    /**
     * @author 阿杰
     * @param [productCategory]
     * @return java.lang.String
     * @description 添加二级分类
     */
    @RequestMapping("/AdminProductController/addProductCategory")
    public String addCategory(ProductCategory productCategory){
        productService.save(productCategory);
        return "redirect:/AdminProductController/productCategoryList";
    }
    /**
     * @author 阿杰
     * @param [id]
     * @return java.lang.String
     * @description 删除分类
     */
    @RequestMapping("/AdminProductController/deleteProductCategory")
    public String deleteCategory(Long id) {
        productService.deleteCategory(id);
        return "redirect:/AdminProductController/productCategoryList";
    }
    /**
     * @author 阿杰
     * @param [model, id]
     * @return java.lang.String
     * @description 跳转至修改分类页面
     */
    @RequestMapping("/AdminProductController/toAdminEditProductCategory")
    public String toEditCategory(Model model, Long id) {
        ProductCategory productCategory = productService.findProductCategoryById(id);
        model.addAttribute("productCategory", productCategory);
        return "admin/adminEditProductCategory";
    }
    /**
     * @author 阿杰
     * @param [productCategory]
     * @return java.lang.String
     * @description 修改分类
     */
    @RequestMapping("/AdminProductController/adminEditProductCategory")
    public String editCategory(ProductCategory productCategory) {
        productService.editCategory(productCategory);
        return "redirect:/AdminProductController/productCategoryList";
    }
    /**
     * @author 阿杰
     * @param [model, category_name]
     * @return java.lang.String
     * @description 搜索分类
     */
    @RequestMapping("/AdminProductController/findProductCategory")
    public  String findCategory(Model model,String category_name){
        List<ProductCategory> productCategories = productService.findByProductCategory(category_name);
        model.addAttribute("productCategories",productCategories);
        model.addAttribute("category_name",category_name);
        return "admin/productCategorylists";
    }

}
