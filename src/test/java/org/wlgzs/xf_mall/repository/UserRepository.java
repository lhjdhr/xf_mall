package org.wlgzs.xf_mall.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.wlgzs.xf_mall.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);




}