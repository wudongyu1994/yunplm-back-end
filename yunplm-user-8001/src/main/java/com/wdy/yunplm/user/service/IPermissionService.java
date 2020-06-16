package com.wdy.yunplm.user.service;


import com.wdy.yunplm.po.Permission;
import com.wdy.yunplm.po.PermissionTree;

import java.util.List;

public interface IPermissionService {
	List<PermissionTree> getAllPermissionTree();
	Long getPermissionTotalNumber();
	Permission addPermission(Permission permission);

	Permission modifyPermissionById(Integer id, Permission permission);

	Permission deletePermissionById(Integer id);

	List<Permission> getPermissionByUserId(Integer userId);
}
