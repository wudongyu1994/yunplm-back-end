package com.wdy.yunplm.user.dao;

import com.wdy.yunplm.po.Role2User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRole2UserDao extends JpaRepository<Role2User, Integer> {
	List<Role2User> findByUserId(Integer userId);
}
