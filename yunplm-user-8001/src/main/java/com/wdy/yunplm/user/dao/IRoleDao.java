package com.wdy.yunplm.user.dao;

import com.wdy.yunplm.po.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDao extends JpaRepository<Role, Integer> {
	@Modifying(clearAutomatically = true)
	@Query("SELECT DISTINCT r  " +
			"FROM Role2User r2u " +
			"INNER JOIN Role r ON r.id = r2u.roleId " +
			"WHERE r2u.userId = :userId")
	List<Role> findRoleByUserId(@Param("userId") Integer userId);
}
