package com.wdy.yunplm.user.dao;

import com.wdy.yunplm.po.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionDao extends JpaRepository<Permission, Integer> {
	@Modifying(clearAutomatically = true)
	@Query("SELECT DISTINCT p  " +
			"FROM Role2User r2u " +
			"INNER JOIN Role2Permission r2p ON r2p.roleId = r2u.roleId " +
			"LEFT JOIN Permission p ON r2p.permissionId = p.id " +
			"WHERE r2u.userId = :userId")
	List<Permission> findPermissionByUserId(@Param("userId") Integer userId);
	@Modifying(clearAutomatically = true)
	@Query("SELECT DISTINCT p  " +
			"FROM Role2Permission r2p " +
			"LEFT JOIN Permission p  ON r2p.permissionId = p.id " +
			"WHERE r2p.roleId = :roleId")
	List<Permission> findPermissionByRoleId(Integer roleId);
}
