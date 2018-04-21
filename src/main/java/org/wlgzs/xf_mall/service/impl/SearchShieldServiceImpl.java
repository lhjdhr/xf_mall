package org.wlgzs.xf_mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlgzs.xf_mall.dao.SearchShieldRepository;
import org.wlgzs.xf_mall.entity.SearchShield;
import org.wlgzs.xf_mall.service.SearchShieldService;

import java.util.List;

/**
 * @author:胡亚星
 * @createTime 2018-04-20 16:43
 * @description:
 **/
@Service
public class SearchShieldServiceImpl implements SearchShieldService {

    @Autowired
    SearchShieldRepository searchShieldRepository;

    //遍历敏感词汇
    @Override
    public List<SearchShield> getSearchShieldList(){
        return searchShieldRepository.findAll();
    }

    //添加敏感词汇
    @Override
    public void save(SearchShield searchShield){
        searchShieldRepository.save(searchShield);
    }

    //删除敏感词汇
    @Override
    public void delete(Long id){
        searchShieldRepository.deleteById(id);
    }


    @Override
    public SearchShield findSearchShieldById(long id){
        return searchShieldRepository.findById(id);
    }

    @Override
    public List<SearchShield> findBySearchShieldSensitive(String search_shield_sensitive){
        return searchShieldRepository.findBySearchShieldSensitive(search_shield_sensitive);
    }

}
