package com.wdy.yunplm.feign;

import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.po.Role;
import com.wdy.yunplm.feign.impl.MyFallback;
import com.wdy.yunplm.vo.RoleVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient(value = "user-server-8001", contextId = "role", fallback = MyFallback.class)
public interface IRoleService {
	@GetMapping("/api/v1/role")
	Result<List<Role>> getAllRole_Feign();

	@GetMapping("/api/v1/roleByPage")
	Result<List<RoleVO>> getRoleByPage_Feign(@RequestParam("page") Integer page,
	                                         @RequestParam("size") Integer size);

	@PostMapping("/api/v1/role")
	Result<RoleVO> addRoleVO_Feign(@RequestBody RoleVO roleVO);

	@PutMapping("/api/v1/role/{id}")
	Result<RoleVO> modifyRoleVOById_Feign(@PathVariable Integer id, @RequestBody RoleVO roleVO);

	@DeleteMapping("/api/v1/role/{id}")
	Result<Role> deleteRoleById_Feign(@PathVariable Integer id);
}
