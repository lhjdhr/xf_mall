package org.wlgzs.xf_mall.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wlgzs.xf_mall.entity.SearchShield;
import org.wlgzs.xf_mall.service.SearchShieldService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:胡亚星
 * @createTime 2018-04-20 16:30
 * @description:
 **/
@Controller
@RequestMapping("AdminProductController")
public class SearchShieldController {
    @Resource
    SearchShieldService searchShieldService;

    @RequestMapping("/toChangeProductSensitive")
    public String toChangeProductSensitive(Model model, Long id){
        SearchShield searchShield = searchShieldService.findSearchShieldById(id);
        model.addAttribute("searchShield",searchShield);
        return "admin/changeProductSensitive";
    }

    /**     
     * @author 胡亚星
     * @date 2018/4/20 17:18  
     * @param   
     * @return   
     *@Description:后台遍历敏感词汇
     */  
    @RequestMapping("/toProductSensitive")
    public String list(Model model) {
        List<SearchShield> searchShields = searchShieldService.getSearchShieldList();
        model.addAttribute("searchShields", searchShields);
        return "admin/productSensitive";
    }

    /**     
     * @author 胡亚星
     * @date 2018/4/20 16:52  
     * @param   
     * @return   
     *@Description:后台添加敏感词汇
     */  
    @RequestMapping("/addProductSensitive")
    public String addSearchShield(SearchShield searchShield){
        searchShieldService.save(searchShield);
        System.out.println("添加成功");
        return "redirect:/AdminProductController/toProductSensitive";
    }

    /**
     * @author 胡亚星
     * @date 2018/4/20 17:01
     * @param
     * @return
     *@Description:后台删除敏感词汇
     */
    @RequestMapping("/deleteProductSensitive")
    public String deleteSearchShield(Long id){
        searchShieldService.delete(id);
        return "redirect:/AdminProductController/toProductSensitive";
    }

    /**
     * @author 胡亚星
     * @date 2018/4/20 19:50
     * @param
     * @return
     *@Description:后台搜索敏感词汇
     */
    @RequestMapping("/findProductSensitive")
    public String findSearchShield(Model model,String search_shield_sensitive){
        System.out.println("准备查找");
        System.out.println(search_shield_sensitive);
        List<SearchShield> searchShields = searchShieldService.findBySearchShieldSensitive(search_shield_sensitive);
        model.addAttribute("searchShields", searchShields);
        model.addAttribute("searchShield_Sensitive",search_shield_sensitive);
        return "admin/productSensitive";
    }

}


