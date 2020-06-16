package com.wdy.yunplm.user.dao;

import com.wdy.yunplm.po.Role2Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRole2PermissionDao extends JpaRepository<Role2Permission, Integer> {
	List<Role2Permission> findByRoleId(Integer id);
}
