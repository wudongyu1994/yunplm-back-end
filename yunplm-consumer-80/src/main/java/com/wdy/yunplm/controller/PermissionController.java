package com.wdy.yunplm.controller;

import com.wdy.yunplm.base.ConsumerBaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.po.Permission;
import com.wdy.yunplm.po.PermissionTree;
import com.wdy.yunplm.feign.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class PermissionController extends ConsumerBaseApiController {
	@Resource
	private IPermissionService iPermissionService;

	@GetMapping("/treePermission")
	public Result<List<PermissionTree>> getAllPermissionTree() {
		return iPermissionService.getAllPermissionTree_Feign();
	}

	@PostMapping("/permission")
	@PreAuthorize("hasAuthority('/permission/add')")
	public Result<Permission> addPermission(@RequestBody @Valid Permission permission,
	                                        BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return Result.failure(ResultCode.BINDING_ERROR);
		return iPermissionService.addPermission_Feign(permission);
	}

	@PutMapping("/permission/{id}")
	@PreAuthorize("hasAuthority('/permission/modify')")
	public Result<Permission> modifyPermissionById(@PathVariable Integer id,
	                                               @RequestBody Permission permission) {
		return iPermissionService.modifyPermissionById_Feign(id, permission);
	}


	@DeleteMapping("/permission/{id}")
	@PreAuthorize("hasAuthority('/permission/delete')")
	public Result<Permission> deletePermissionById(@PathVariable Integer id) {
		return iPermissionService.deletePermissionById_Feign(id);
	}

}
