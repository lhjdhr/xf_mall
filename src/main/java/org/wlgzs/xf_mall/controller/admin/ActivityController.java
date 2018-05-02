package org.wlgzs.xf_mall.controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.xf_mall.entity.Product;
import org.wlgzs.xf_mall.entity.ProductActivity;
import org.wlgzs.xf_mall.service.ProductActivityService;
import org.wlgzs.xf_mall.service.ProductService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/5/2 00:26
 * @Description: 后台活动商品
 */
@RequestMapping("AdminActivityController")
@RestController
public class ActivityController {
    @Resource
    ProductActivityService productActivityService;
    @Resource
    ProductService productService;
    /**
     * @author 阿杰
     * @param []
     * @return org.springframework.web.servlet.ModelAndView
     * @description 跳转至活动页面
     */
    @RequestMapping("/activityProducts")
    public ModelAndView activityProductList(Model model, @RequestParam(value = "page",defaultValue = "0") int page,
                                            @RequestParam(value = "limit",defaultValue = "10") int limit){
        String activity_name ="";
        Page activityPages =  productActivityService.activityProductList(activity_name,page,limit);
        model.addAttribute("ActivityTotalPages", activityPages.getTotalPages());//查询的页数
        model.addAttribute("ActivityTotalElements", activityPages.getTotalElements());//查询的总记录数
        model.addAttribute("ActivityNumber", activityPages.getNumber());//查询的当前第几页
        List<ProductActivity> activities = activityPages.getContent();
        String activityImg;
        for(int i = 0; i < activities.size(); i++) {
            if (activities.get(i).getProduct_picture().contains(",")){
                activityImg = activities.get(i).getProduct_picture();
                activityImg = activityImg.substring(0,activityImg.indexOf(","));
                System.out.println("后台活动");
                activities.get(i).setProduct_picture(activityImg);
            }
        }
        model.addAttribute("activities", activities);//查询的当前页的集合
        model.addAttribute("ActivityNumberOfElements", activityPages.getNumberOfElements());//查询的当前页的记录数

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
                System.out.println("后台商品");
                products.get(i).setProduct_picture(img);
            }
        }
        model.addAttribute("products", products);//查询的当前页的集合
        model.addAttribute("NumberOfElements", pages.getNumberOfElements());//查询的当前页的记录数
        return new ModelAndView("admin/adminActivityList");
    }
    /**
     * @author 阿杰
     * @param [model, productId]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 跳转至添加该商品 为活动商品的页面
     */
    @RequestMapping("/toAdminAddActivity")
    public ModelAndView toAdminAddActivity(Model model,long productId){
        model.addAttribute("productId",productId);
        return new ModelAndView("admin/adminAddActivity");
    }
    /**
     * @author 阿杰
     * @param [productId, request]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 添加该商品 为活动商品
     */
    @RequestMapping("/adminAddActivity")
    public ModelAndView adminAddActivity(long productId, HttpServletRequest request){
        productActivityService.adminAddActivity(productId,request);
        return new ModelAndView("redirect:/AdminActivityController/activityProducts");
    }
    /**
     * @author 阿杰
     * @param [model, activityId, productId]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 跳转至修改该活动商品
     */
    @RequestMapping("/toAdminEditActivity")
    public ModelAndView toAdminEditActivity(Model model, long activityId) {
        ProductActivity productActivity = productActivityService.findByActivity(activityId);
        model.addAttribute("activityId",activityId);
        model.addAttribute("productActivity", productActivity);
        return new ModelAndView("admin/adminEditActivity");
    }
    /**
     * @author 阿杰
     * @param [activityId, request]
     * @return java.lang.String
     * @description 修改活动商品
     */
    @RequestMapping("/adminEditActivity")
    public ModelAndView adminEditActivity(long activityId, HttpServletRequest request) {
        productActivityService.editActivity(activityId, request);
        return new ModelAndView("redirect:/AdminActivityController/activityProducts");
    }
    /**
     * @author 阿杰
     * @param [activityId]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 删除活动商品
     */
    @RequestMapping("/adminDeleteActivity")
    public ModelAndView adminDeleteActivity(long activityId) {
        productActivityService.deleteActivity(activityId);
        return new ModelAndView("redirect:/AdminActivityController/activityProducts");
    }
    /**
     * @author 阿杰
     * @param [model, activity_name, page, limit]
     * @return org.springframework.web.servlet.ModelAndView
     * @description 搜索活动商品
     */
    @RequestMapping("/adminFindActivityProduct")
    public ModelAndView adminFindActivityProduct(Model model, String activity_name, @RequestParam(value = "page",defaultValue = "0") int page,
                                                 @RequestParam(value = "limit",defaultValue = "10") int limit) {
        Page activityPages =  productActivityService.activityProductList(activity_name,page,limit);
        model.addAttribute("ActivityTotalPages", activityPages.getTotalPages());//查询的页数
        model.addAttribute("ActivityTotalElements", activityPages.getTotalElements());//查询的总记录数
        model.addAttribute("ActivityNumber", activityPages.getNumber());//查询的当前第几页
        List<ProductActivity> activities = activityPages.getContent();
        String activityImg;
        for(int i = 0; i < activities.size(); i++) {
            if (activities.get(i).getProduct_picture().contains(",")){
                activityImg = activities.get(i).getProduct_picture();
                activityImg = activityImg.substring(0,activityImg.indexOf(","));
                System.out.println("后台搜索活动");
                activities.get(i).setProduct_picture(activityImg);
            }
        }
        model.addAttribute("activities", activities);//查询的当前页的集合
        model.addAttribute("ActivityNumberOfElements", activityPages.getNumberOfElements());//查询的当前页的记录数
        model.addAttribute("activity_name",activity_name);
        return new ModelAndView("admin/adminOnlyActivityList");
    }
}
