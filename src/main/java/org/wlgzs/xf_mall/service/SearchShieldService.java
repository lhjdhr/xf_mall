package org.wlgzs.xf_mall.service;

import org.wlgzs.xf_mall.entity.SearchShield;

import java.util.List;

/**
 * @author:胡亚星
 * @createTime 2018-04-20 16:30
 * @description:
 **/
public interface SearchShieldService {
    List<SearchShield> getSearchShieldList();

    SearchShield findSearchShieldById(long id);

    void save(SearchShield searchShield);

    void delete(Long id);

    List<SearchShield> findBySearchShieldSensitive(String search_shield_sensitive);
}
