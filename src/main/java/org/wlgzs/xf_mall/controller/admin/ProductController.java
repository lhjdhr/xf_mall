package org.wlgzs.xf_mall.controller.admin;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.xf_mall.entity.Product;
import org.wlgzs.xf_mall.entity.ProductCategory;
import org.wlgzs.xf_mall.entity.Result;
import org.wlgzs.xf_mall.service.ProductService;
import org.wlgzs.xf_mall.util.ResultUtil;

import javax.annotation.Resource;
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
 * @Date: 2018/4/18 20:51
 * @Description: 后台商品管理
 */
@RequestMapping("AdminProductController")
@RestController
public class ProductController {
    @Resource
    ProductService productService;
    /**
     * @author 阿杰
     * @param [model]
     * @return java.lang.String
     * @description 后台商品列表
     */
    @RequestMapping(value = "/adminProductList")
    public ModelAndView list(Model model) {
        List<Product> products = productService.getProductList();
        model.addAttribute("products",products);
        return new ModelAndView("admin/adminProductList");
    }
    /**
     * @author 阿杰
     * @param [model, product_keywords]
     * @return java.lang.String
     * @description 搜索商品
     */
    @RequestMapping("/adminFindProduct")
    public  ModelAndView findProduct(Model model,String product_keywords){
        List<Product> products = productService.findByProductKeywords(product_keywords);
        model.addAttribute("products",products);
        model.addAttribute("product_keywords",product_keywords);
        return new ModelAndView("admin/adminProductList");
    }
    /**
     * @author 阿杰
     * @return java.lang.String
     * @description 跳转到添加商品
     */
    @RequestMapping(value = "/toAdminAddProduct")
    public ModelAndView toAdd(){
        return new ModelAndView("admin/adminAddProduct");
    }
    /**
     * @author 阿杰
     * @param [myFileName, session, request]
     * @return org.wlgzs.xf_mall.entity.Result
     * @description 富文本图片上传
     */
    @RequestMapping("/upload")
    public Result uploadImg(MultipartFile myFileName, HttpSession session, HttpServletRequest request) throws IllegalStateException, IOException {
        String realName = "";
        if (myFileName != null) {
            String fileName = myFileName.getOriginalFilename();
            String fileNameExtension = fileName.substring(fileName.indexOf("."), fileName.length());

            // 生成实际存储的真实文件名
            realName = UUID.randomUUID().toString() + fileNameExtension;

            // "/upload"是你自己定义的上传目录
            String realPath = session.getServletContext().getRealPath("/upload");
            File uploadFile = new File(realPath, realName);
            myFileName.transferTo(uploadFile);
        }
        String [] str = {request.getContextPath() + "/upload/" + realName};
        return ResultUtil.success(str);
    }
    /**
     * @author 阿杰
     * @param [product]
     * @return java.lang.String
     * @description 添加商品
     */
    @RequestMapping("/adminAddProduct")
    public ModelAndView add(String product_details, @RequestParam("file") MultipartFile[] myFileNames, HttpSession session,
                            Model model, HttpServletRequest request) throws IOException {
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
                myFileNames[i].transferTo(uploadFile);
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
        productService.save(product);
        model.addAttribute("product_details",product_details);
        return new ModelAndView("redirect:/AdminProductController/adminProductList");
    }
    /**
     * @author 阿杰
     * @param [model, id]
     * @return java.lang.String
     * @description 跳转至修改商品页面
     */
    @RequestMapping("/toAdminEditProduct")
    public ModelAndView toEdit(Model model, long product_id) {
        Product product=productService.findProductById(product_id);
        model.addAttribute("product", product);
        return new ModelAndView("admin/adminEditProduct");
    }
    /**
     * @author 阿杰
     * @param [product]
     * @return java.lang.String
     * @description 修改商品
     */
    @RequestMapping("/adminEditProduct")
    public ModelAndView edit(Product product) {
        productService.edit(product);
        return new ModelAndView("redirect:/AdminProductController/adminProductList");
    }
    /**
     * @author 阿杰
     * @param [id]
     * @return java.lang.String
     * @description 删除商品
     */
    @RequestMapping("/adminDeleteProduct")
    public ModelAndView delete(long product_id) {
        productService.delete(product_id);
        return new ModelAndView("redirect:/AdminProductController/adminProductList");
    }
    /**
     * @author 阿杰
     * @param [model]
     * @return java.lang.String
     * @description 遍历所有分类
     */
    @RequestMapping("/productCategoryList")
    public ModelAndView category(Model model){
        List<ProductCategory> productCategories = productService.getProductCategoryList();
        model.addAttribute("productCategories",productCategories);
        return new ModelAndView("admin/productCategorylists");
    }
    /**
     * @author 阿杰
     * @param []
     * @return java.lang.String
     * @description 跳转至添加一级分类页面
     */
    @RequestMapping("/toAddProductOneCategory")
    public ModelAndView toAddOneCategory(){
        return new ModelAndView("admin/addProductOneCategory");
    }
    /**
     * @author 阿杰
     * @param [productCategory]
     * @return java.lang.String
     * @description 添加一级分类
     */
    @RequestMapping("/addProductOneCategory")
    public ModelAndView addOneCategory(ProductCategory productCategory){
        productService.saveOne(productCategory);
        return new ModelAndView("redirect:/AdminProductController/productCategoryList");
    }
    /**
     * @author 阿杰
     * @param []
     * @return java.lang.String
     * @description 跳转至添加二级分类页面
     */
    @RequestMapping("/toAddProductCategory")
    public ModelAndView toAddCategory(){
        return new ModelAndView("admin/addProductCategory");
    }
    /**
     * @author 阿杰
     * @param [productCategory]
     * @return java.lang.String
     * @description 添加二级分类
     */
    @RequestMapping("/addProductCategory")
    public ModelAndView addCategory(ProductCategory productCategory){
        productService.save(productCategory);
        return new ModelAndView("redirect:/AdminProductController/productCategoryList");
    }
    /**
     * @author 阿杰
     * @param [id]
     * @return java.lang.String
     * @description 删除分类
     */
    @RequestMapping("/deleteProductCategory")
    public ModelAndView deleteCategory(Long id) {
        productService.deleteCategory(id);
        return new ModelAndView("redirect:/AdminProductController/productCategoryList");
    }
    /**
     * @author 阿杰
     * @param [model, id]
     * @return java.lang.String
     * @description 跳转至修改分类页面
     */
    @RequestMapping("/toAdminEditProductCategory")
    public ModelAndView toEditCategory(Model model, Long id) {
        ProductCategory productCategory = productService.findProductCategoryById(id);
        model.addAttribute("productCategory", productCategory);
        return new ModelAndView("admin/adminEditProductCategory");
    }
    /**
     * @author 阿杰
     * @param [productCategory]
     * @return java.lang.String
     * @description 修改分类
     */
    @RequestMapping("/adminEditProductCategory")
    public ModelAndView editCategory(ProductCategory productCategory) {
        productService.editCategory(productCategory);
        return new ModelAndView("redirect:/AdminProductController/productCategoryList");
    }
    /**
     * @author 阿杰
     * @param [model, category_name]
     * @return java.lang.String
     * @description 搜索分类
     */
    @RequestMapping("/findProductCategory")
    public  ModelAndView findCategory(Model model,String category_name){
        List<ProductCategory> productCategories = productService.findByProductCategory(category_name);
        model.addAttribute("productCategories",productCategories);
        model.addAttribute("category_name",category_name);
        return new ModelAndView("admin/productCategorylists");
    }

}
