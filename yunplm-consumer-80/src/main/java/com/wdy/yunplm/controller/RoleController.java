package com.wdy.yunplm.controller;

import com.wdy.yunplm.base.ConsumerBaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.po.Role;
import com.wdy.yunplm.feign.IRoleService;
import com.wdy.yunplm.vo.RoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class RoleController extends ConsumerBaseApiController {
	@Resource
	private IRoleService iRoleService;

	@GetMapping("/role")
	public Result<List<Role>> getAllRole() {
		return iRoleService.getAllRole_Feign();
	}

	@GetMapping("/roleByPage")
	@PreAuthorize("hasAuthority('/role/get')")
	public Result<List<RoleVO>> getRoleByPage(@RequestParam("page") Integer page,
	                                          @RequestParam("size") Integer size) {
		return iRoleService.getRoleByPage_Feign(page,size);
	}

	@PostMapping("/role")
	@PreAuthorize("hasAuthority('/role/add')")
	public Result<RoleVO> addRoleVO(@RequestBody @Valid RoleVO roleVO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return Result.failure(ResultCode.BINDING_ERROR);
		return iRoleService.addRoleVO_Feign(roleVO);
	}

	@PutMapping("/role/{id}")
	@PreAuthorize("hasAuthority('/role/modify')")
	public Result<RoleVO> modifyRoleVOById(@PathVariable Integer id, @RequestBody RoleVO roleVO) {
		return iRoleService.modifyRoleVOById_Feign(id,roleVO);
	}

	@DeleteMapping("/role/{id}")
	@PreAuthorize("hasAuthority('/role/delete')")
	public Result<Role> deleteRoleById(@PathVariable Integer id) {
		return iRoleService.deleteRoleById_Feign(id);
	}
}
