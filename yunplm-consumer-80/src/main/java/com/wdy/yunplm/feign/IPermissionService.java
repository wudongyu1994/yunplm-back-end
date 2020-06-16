package com.wdy.yunplm.feign;

import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.po.Permission;
import com.wdy.yunplm.po.PermissionTree;
import com.wdy.yunplm.feign.impl.MyFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient(value = "user-server-8001", contextId = "permission", fallback = MyFallback.class)
public interface IPermissionService {
	@GetMapping("/api/v1/permission/user/{userId}")
	Result<List<Permission>> getPermissionByUserId_Feign(@PathVariable Integer userId);

	@GetMapping("/api/v1/treePermission")
	Result<List<PermissionTree>> getAllPermissionTree_Feign();

	@PostMapping("/api/v1/permission")
//	@PreAuthorize("hasAuthority('/permission/add')")
	Result<Permission> addPermission_Feign(@RequestBody Permission permission);

	@PutMapping("/api/v1/permission/{id}")
//	@PreAuthorize("hasAuthority('/permission/modify')")
	Result<Permission> modifyPermissionById_Feign(@PathVariable Integer id,
	                                              @RequestBody Permission permission);

	@DeleteMapping("/api/v1/permission/{id}")
//	@PreAuthorize("hasAuthority('/permission/delete')")
	Result<Permission> deletePermissionById_Feign(@PathVariable Integer id);
}
