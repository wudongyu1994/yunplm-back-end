package com.wdy.yunplm.user.service;

import com.wdy.yunplm.po.Role;
import com.wdy.yunplm.vo.RoleVO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRoleService {
	List<Role> getAllRole();
	List<RoleVO> getRoleVOByPage(Pageable pageable);
	Long getRoleTotalNumber();
	List<Role> getRoleByUserId(Integer userId);
	RoleVO addRoleVO(RoleVO roleVO);

	RoleVO modifyRoleVOById(Integer id, RoleVO roleVO);

	Role deleteRoleById(Integer id);
}
