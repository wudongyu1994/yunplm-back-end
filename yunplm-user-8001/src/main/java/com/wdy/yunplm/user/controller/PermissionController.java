package com.wdy.yunplm.user.controller;

import com.wdy.yunplm.base.BaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.po.Permission;
import com.wdy.yunplm.po.PermissionTree;
import com.wdy.yunplm.user.service.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
//@PreAuthorize("hasAuthority('/permission')")
public class PermissionController extends BaseApiController {
	private final IPermissionService permissionService;

	@Autowired
	public PermissionController(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}

	@GetMapping("/treePermission")
	public Result<List<PermissionTree>> getAllPermissionTree() {
		return Result.success(permissionService.getAllPermissionTree(),
				permissionService.getPermissionTotalNumber().intValue());
	}

	@GetMapping("/permission/user/{userId}")
	public Result<List<Permission>> getPermissionByUserId(@PathVariable Integer userId) {
		List<Permission> permissionList = permissionService.getPermissionByUserId(userId);
		return Result.success(permissionList, permissionList.size());
	}

	@PostMapping("/permission")
//	@PreAuthorize("hasAuthority('/permission/add')")
	public Result<Permission> addPermission(@RequestBody @Valid Permission permission,
	                                        BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			showBinding(bindingResult);
			return Result.failure("bindingResult.hasErrors");
		}
		log.info(permission.toString());
		return Result.success(permissionService.addPermission(permission));
	}

	@PutMapping("/permission/{id}")
//	@PreAuthorize("hasAuthority('/permission/modify')")
	public Result<Permission> modifyPermissionById(@PathVariable Integer id,
	                                               @RequestBody Permission permission) {
		Permission permissionRet = permissionService.modifyPermissionById(id, permission);
		if (permissionRet == null) return Result.failure(ResultCode.PERMISSION_NOT_EXIST);
		return Result.success(permissionRet);
	}


	@DeleteMapping("/permission/{id}")
//	@PreAuthorize("hasAuthority('/permission/delete')")
	public Result<Permission> deletePermissionById(@PathVariable Integer id) {
		Permission permission = permissionService.deletePermissionById(id);
		if (permission == null) return Result.failure(ResultCode.PERMISSION_NOT_EXIST);
		return Result.success(permission);
	}

}
