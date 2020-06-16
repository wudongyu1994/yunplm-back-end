package com.wdy.yunplm.controller;

import com.wdy.yunplm.base.ConsumerBaseApiController;
import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.po.User;
import com.wdy.yunplm.feign.IUserService;
import com.wdy.yunplm.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class UserController extends ConsumerBaseApiController {
	@Resource
	private IUserService iUserService;

	@GetMapping("/hello")
	public Result<String> hello() {
		return iUserService.hello_Feign();
	}

	@GetMapping("/user")
	@PreAuthorize("hasAuthority('/user/get')")
	public Result<List<UserVO>> getUserVOByPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		return iUserService.getUserVOByPage_Feign(page, size);
	}

	@PostMapping("/user")
	public Result<UserVO> addUserVO(@RequestBody @Valid UserVO userVO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return Result.failure(ResultCode.BINDING_ERROR);
		return iUserService.addUserVO_Feign(userVO);
	}

	@PutMapping("/user/{id}")
	@PreAuthorize("hasAuthority('/user/modify')")
	public Result<UserVO> modifyUserVOById(@PathVariable Integer id, @RequestBody UserVO userVO) {
		return iUserService.modifyUserVOById_Feign(id, userVO);
	}

	@PutMapping("/user/{id}/password")
	public Result<User> changeUserPasswordById(@PathVariable Integer id, @RequestParam String oldPassword,
	                                           @RequestParam String newPassword) {
		return iUserService.changeUserPasswordById_Feign(id, oldPassword, newPassword);
	}

	@DeleteMapping("/user/{id}")
	@PreAuthorize("hasAuthority('/user/delete')")
	public Result<User> deleteUserById(@PathVariable Integer id) {
		return iUserService.deleteUserById_Feign(id);
	}
}
