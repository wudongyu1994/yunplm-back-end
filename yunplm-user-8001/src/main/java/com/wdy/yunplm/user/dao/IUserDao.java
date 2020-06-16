package com.wdy.yunplm.user.dao;

import com.wdy.yunplm.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
