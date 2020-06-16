package com.wdy.yunplm.user.controller;

import com.wdy.yunplm.base.BaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.po.Role;
import com.wdy.yunplm.user.service.IRoleService;
import com.wdy.yunplm.vo.RoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
//@PreAuthorize("hasAuthority('/role')")
public class RoleController extends BaseApiController {
	private final IRoleService roleService;

	@Autowired
	public RoleController(IRoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping("/role")
//	@PreAuthorize("true")
	public Result<List<Role>> getAllRole() {
		List<Role> roleList = roleService.getAllRole();
		return Result.success(roleList, roleList.size());
	}

	@GetMapping("/roleByPage")
//	@PreAuthorize("true")
	public Result<List<RoleVO>> getRoleByPage(@RequestParam("page") Integer page,
	                                          @RequestParam("size") Integer size) {
		Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, "id");
		List<RoleVO> roleVOList = roleService.getRoleVOByPage(pageable);
		return Result.success(roleVOList, roleService.getRoleTotalNumber().intValue());
	}
	@PostMapping("/role")
//	@PreAuthorize("hasAuthority('/role/add')")
	public Result<RoleVO> addRoleVO(@RequestBody @Valid RoleVO roleVO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			showBinding(bindingResult);
			return Result.failure("bindingResult.hasErrors");
		}
		log.info(roleVO.toString());
		return Result.success(roleService.addRoleVO(roleVO));
	}

	@PutMapping("/role/{id}")
//	@PreAuthorize("hasAuthority('/role/modify')")
	public Result<RoleVO> modifyRoleVOById(@PathVariable Integer id, @RequestBody RoleVO roleVO) {
		RoleVO roleVORet = roleService.modifyRoleVOById(id, roleVO);
		if (roleVORet == null) return Result.failure("没有该用户");
		else return Result.success(roleVORet);
	}

	@DeleteMapping("/role/{id}")
//	@PreAuthorize("hasAuthority('/role/delete')")
	public Result<Role> deleteRoleById(@PathVariable Integer id) {
		Role role = roleService.deleteRoleById(id);
		if(role==null){
			return Result.failure(ResultCode.ROLE_NOT_EXIST);
		}else{
			return Result.success(role);
		}
	}
}
